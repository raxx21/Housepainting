package com.dipali.colorhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.dipali.colorhouse.data.MyHandler;
import com.dipali.colorhouse.models.Stores;

import java.util.ArrayList;
import java.util.List;

public class Admin1 extends AppCompatActivity {

    //Variables
    ListView shopList;
    Button b_logout,b_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        //Hooks
        b_user = findViewById(R.id.admin_user);
        shopList = findViewById(R.id.shop_list);
        b_logout = findViewById(R.id.admin_logout);

        ArrayList<String> shopArray = new ArrayList<>();

        MyHandler db = new MyHandler(this);

        // Display all shop in log
        List<Stores> shop =db.getAllStores();
        for(Stores user: shop){
            Log.d("goon","ID: " + user.getId() + "\n" +
                    "Name: " + user.getOwner_name() + "\n" +
                    "Email: " + user.getEmail() + "\n" +
                    "Phone Number : " + user.getPhone() + "\n" +
                    "State : " + user.getState() + "\n" +
                    "City : " + user.getCity() + "\n" +
                    "Password : " + user.getPassword() + "\n" );
            shopArray.add(user.getOwner_name());
        }

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shopArray);
        shopList.setAdapter(arrayAdapter1);

        b_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUser.class));
                finish();
            }
        });

        b_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Admin.class));
            }
        });
    }
}