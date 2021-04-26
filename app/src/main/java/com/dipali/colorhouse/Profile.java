package com.dipali.colorhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dipali.colorhouse.data.MyHandler;
import com.dipali.colorhouse.models.Users;

import java.util.HashMap;
import java.util.List;

public class Profile extends AppCompatActivity {

    TextView name,phone,email,city,state;
    HashMap<String,String> details ;
    ImageView back_arrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        //Hooks
        name = findViewById(R.id.full_name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        back_arrow = findViewById(R.id.back_arrow);

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

        MyHandler db = new MyHandler(Profile.this);
        details = new HashMap<String, String>();
        Intent intent1 = getIntent();
        String userEmail = intent1.getStringExtra("session_email");
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

        name.setText(details.get("fullnameS"));
        phone.setText(details.get("emailS"));
        email.setText(details.get("phoneS"));
        city.setText(details.get("stateS"));
        state.setText(details.get("cityS"));

    }
}