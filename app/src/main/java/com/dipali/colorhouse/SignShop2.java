package com.dipali.colorhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.dipali.colorhouse.data.MyHandler;
import com.dipali.colorhouse.models.Stores;
import com.dipali.colorhouse.models.Users;
import com.google.android.material.textfield.TextInputLayout;

public class SignShop2 extends AppCompatActivity {

    //Variables
    Button b_sign;
    TextInputLayout house,area,street,landmark,state,city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_shop2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        //Hooks
        b_sign = findViewById(R.id.signup_next_btn);
        house = findViewById(R.id.signup_house);
        area = findViewById(R.id.signup_area);
        street = findViewById(R.id.signup_street);
        landmark = findViewById(R.id.signup_landmark);
        state = findViewById(R.id.signup_state);
        city = findViewById(R.id.signup_city);


        b_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyFields()) {
                    return;
                }
                Intent intent = getIntent();
                MyHandler db = new MyHandler(SignShop2.this);
                Stores users = new Stores();
                users.setOwner_name(intent.getStringExtra("name"));
                users.setEmail(intent.getStringExtra("email"));
                users.setPhone(intent.getStringExtra("phone"));
                users.setShop_no(house.getEditText().getText().toString());
                users.setArea(area.getEditText().getText().toString());
                users.setStreet(street.getEditText().getText().toString());
                users.setLandmark(landmark.getEditText().getText().toString());
                users.setState(state.getEditText().getText().toString());
                users.setCity(city.getEditText().getText().toString());
                users.setPassword(intent.getStringExtra("password"));
                db.signShops(users);

                Toast.makeText(SignShop2.this,"Sign up successfully done!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),LoginShop.class));
            }
        });
    }

    //Validations Function
    private boolean emptyFields() {
        String val = house.getEditText().getText().toString().trim();
        String val1 = area.getEditText().getText().toString().trim();
        String val2 = street.getEditText().getText().toString().trim();
        String val3 = state.getEditText().getText().toString().trim();
        String val4 = city.getEditText().getText().toString().trim();
        String val5 = landmark.getEditText().getText().toString().trim();

        if (val.isEmpty() || val1.isEmpty() || val2.isEmpty() ||val3.isEmpty() ||val4.isEmpty() ||val5.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Fields cannot be empty!",Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}