package com.dipali.colorhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.dipali.colorhouse.data.MyHandler;
import com.dipali.colorhouse.models.Stores;
import com.dipali.colorhouse.models.Users;

import java.util.ArrayList;
import java.util.List;

public class Admin extends AppCompatActivity {

    //Variables
    ListView userList, shopList;
    Button b_logout, b_painter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        //Hooks
        userList = findViewById(R.id.user_list);
        b_logout = findViewById(R.id.admin_logout);
        b_painter = findViewById(R.id.admin_painters);

        ArrayList<String> userArray = new ArrayList<>();

        MyHandler db = new MyHandler(this);

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
            userArray.add(user.getName());
        }



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userArray);
        userList.setAdapter(arrayAdapter);

        b_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUser.class));
                finish();
            }
        });

        b_painter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Admin1.class));
            }
        });
    }
}