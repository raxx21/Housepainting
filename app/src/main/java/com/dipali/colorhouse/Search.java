package com.dipali.colorhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.dipali.colorhouse.data.MyHandler;
import com.dipali.colorhouse.models.NearByHelper;
import com.dipali.colorhouse.models.Stores;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {

    RecyclerView featured_recycle;
    RecyclerView.Adapter adapter;
    ImageView back_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        //Hooks
        back_arrow = findViewById(R.id.back_arrow);
        featured_recycle = findViewById(R.id.recycle_view);

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = getIntent();
                String userEmail = intent1.getStringExtra("session_email");
                Intent intent= new Intent(getApplicationContext(),Dashboard.class);
                intent.putExtra("session_email" , userEmail);
                startActivity(intent);
            }
        });

        nearbyRecycle();
    }

    private void nearbyRecycle() {

        featured_recycle.setHasFixedSize(true);
        featured_recycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Intent intent = getIntent();
        String city = intent.getStringExtra("search_city");
        ArrayList<NearByHelper> featuredLocations = new ArrayList<>();

        MyHandler db = new MyHandler(this);
        Intent intent1 = getIntent();

        String userEmail = intent1.getStringExtra("session_email");

        // Display all garaaj in log
        List<Stores> garaaj = db.nearStore(city);
        if(!garaaj.isEmpty()) {
            for(Stores user: garaaj){
                featuredLocations.add(new NearByHelper(user.getOwner_name(), user.getShop_no() +"," + user.getArea() +"," +user.getStreet() +"," +user.getLandmark() +"," +user.getState() +"," +user.getCity(),  user.getPhone(), user.getEmail(),userEmail));
            }
        }
        else {
            featuredLocations.add(new NearByHelper("No Shops", "No Shops Available",  "NON","NON","NON"));
        }

        adapter = new NearByRecycleAdapter(getApplicationContext(),featuredLocations);
        featured_recycle.setAdapter(adapter);
    }
}