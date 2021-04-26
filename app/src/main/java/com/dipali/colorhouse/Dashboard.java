package com.dipali.colorhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.dipali.colorhouse.data.MyHandler;
import com.dipali.colorhouse.data.SessionManager;
import com.dipali.colorhouse.models.NearByHelper;
import com.dipali.colorhouse.models.Stores;
import com.dipali.colorhouse.models.Users;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    RecyclerView featured_recycle;
    RecyclerView.Adapter adapter;
    Button b_profile,b_logout;
    TextInputLayout search_text;
    HashMap<String, String> details;
    Button search_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        MyHandler db = new MyHandler(Dashboard.this);

        //Hooks
        featured_recycle = findViewById(R.id.recycle_view);
        b_profile = findViewById(R.id.profile_btn);
        search_text = findViewById(R.id.search_text);
        search_btn = findViewById(R.id.search_btn);
        b_logout = findViewById(R.id.logout_btn);

        // Display all users in log
        List<Users> users =db.getAllUser();
        for(Users user: users){
            Log.d("goon","ID: " + user.getId() + "\n" +
                    "Name: " + user.getName() + "\n" +
                    "Email: " + user.getEmail() + "\n" +
                    "Phone Number : " + user.getPhone() + "\n" +
                    "State : " + user.getState() + "\n" +
                    "City : " + user.getCity() + "\n" +
                    "Password : " + user.getPassword() + "\n" );
        }

        b_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = getIntent();
                String userEmail = intent1.getStringExtra("session_email");
                Intent intent= new Intent(getApplicationContext(),Profile.class);
                intent.putExtra("session_email" , userEmail);
                startActivity(intent);
            }
        });
        b_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUser.class));
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = getIntent();
                String userEmail = intent1.getStringExtra("session_email");
                Intent intent= new Intent(getApplicationContext(),Search.class);
                intent.putExtra("search_city" , search_text.getEditText().getText().toString());
                intent.putExtra("session_email" , userEmail);
                startActivity(intent);
            }
        });

        nearbyRecycle();
    }

    private void nearbyRecycle() {

        featured_recycle.setHasFixedSize(true);
        featured_recycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<NearByHelper> featuredLocations = new ArrayList<>();

        MyHandler db = new MyHandler(Dashboard.this);
        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("session_email");
        details = new HashMap<>();
//        //get User
        List<Users> users = db.getUser(userEmail);
        if(users != null) {
            for (Users user : users) {
                details.put("fullnameS", user.getName());
                details.put("emailS", user.getEmail());
                details.put("phoneS", user.getPhone());
                details.put("stateS", user.getState());
                details.put("cityS", user.getCity());
            }
        }

        // Display all garaaj in log
        List<Stores> garaaj = db.nearStore(details.get("cityS"));
        if(!garaaj.isEmpty()) {
            for(Stores user: garaaj){
                featuredLocations.add(new NearByHelper(user.getOwner_name(),user.getShop_no() +"," + user.getArea() +"," +user.getStreet() +"," +user.getLandmark() +"," +user.getState() +"," +user.getCity(),  user.getPhone(), user.getEmail(), userEmail));
            }
        }
        else {
            featuredLocations.add(new NearByHelper("No Shops", "No Shops Available",  "NON","NON", "NON"));
        }


//        featuredLocations.add(new NearByHelper("Shop 1", "Lorem ipsum, or lipsum as is dummy text used in laying out p",  "9011842557"));
//        featuredLocations.add(new NearByHelper("Shop 2", "Lorem ipsum, or lipsum as is dummy text used in laying out p",  "9011842557"));
//        featuredLocations.add(new NearByHelper("Shop 3", "Lorem ipsum, or lipsum as is dummy text used in laying out p",  "9011842557"));

        adapter = new NearByRecycleAdapter(Dashboard.this,featuredLocations);
        featured_recycle.setAdapter(adapter);
    }
}