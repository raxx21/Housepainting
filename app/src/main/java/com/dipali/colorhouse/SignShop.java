package com.dipali.colorhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.dipali.colorhouse.data.MyHandler;
import com.google.android.material.textfield.TextInputLayout;

public class SignShop extends AppCompatActivity {

    //Variables
    Button b_next, b_login;
    TextInputLayout name,email,phone,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_shop);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        //Hooks
        b_next = findViewById(R.id.signup_next_btn);
        b_login = findViewById(R.id.signup_already);
        name = findViewById(R.id.signup_fullname);
        email = findViewById(R.id.signup_email);
        phone = findViewById(R.id.signup_phone);
        password = findViewById(R.id.signup_password);

        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyFields() | !Email() |  !validatePassword()) {
                    return;
                }
                Intent intent = new Intent(getApplicationContext(),SignShop2.class);
                intent.putExtra("name", name.getEditText().getText().toString());
                intent.putExtra("email", email.getEditText().getText().toString());
                intent.putExtra("phone", phone.getEditText().getText().toString());
                intent.putExtra("password", password.getEditText().getText().toString());
                startActivity(intent);
            }
        });
        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginShop.class));
            }
        });

    }

    //Validations Function
    private boolean emptyFields() {
        String val = name.getEditText().getText().toString().trim();
        String val1 = email.getEditText().getText().toString().trim();
        String val2 = phone.getEditText().getText().toString().trim();
        String val3 = password.getEditText().getText().toString().trim();

        if (val.isEmpty() || val1.isEmpty() || val2.isEmpty() ||val3.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Fields cannot be empty!",Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean Email() {
        MyHandler db =new MyHandler(this);
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(!val.matches(checkEmail)){
            Toast.makeText(getApplicationContext(),"Invalid Email!",Toast.LENGTH_SHORT).show();
            return false;
        }else if(!db.checkemail(val)){
            Toast.makeText(getApplicationContext(),"Email Already Exists!",Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validatePassword() {
        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        if(!val.matches(checkPassword)){
            Toast.makeText(getApplicationContext(),"Password should contain 8 characters, at least 1 lower case letter, at least 1 upper case letter, at least 1 special character!",Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }
}