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

import com.google.android.material.textfield.TextInputLayout;

public class AdminLogin extends AppCompatActivity {

    //Variables
    TextInputLayout email, password;
    Button b_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        //Hooks
        email = findViewById(R.id.admin_email);
        password = findViewById(R.id.admin_password);
        b_admin = findViewById(R.id.admin_btn);

        b_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getEditText().getText().toString().trim().equals("admin") && password.getEditText().getText().toString().trim().equals("admin")){
                    startActivity(new Intent(getApplicationContext(),Admin.class));
                }else {
                    Log.d("goon", "email :" + email.getEditText().getText().toString());
                    Log.d("goon", "email :" + password.getEditText().getText().toString());
                    Toast.makeText(getApplicationContext(),"Your Email or Password is wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}