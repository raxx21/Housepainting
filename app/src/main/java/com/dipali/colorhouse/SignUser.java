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
import com.dipali.colorhouse.models.Users;
import com.google.android.material.textfield.TextInputLayout;

public class SignUser extends AppCompatActivity {

    Button b_sign, b_login, b_shop, b_admin;
    TextInputLayout name ,email,phone,state,city,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_user);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        //Hooks
        b_sign = findViewById(R.id.signup_next_btn);
        b_login = findViewById(R.id.signup_login_btn);
        b_shop = findViewById(R.id.signup_shops_btn);
        name = findViewById(R.id.signup_fullname);
        email = findViewById(R.id.signup_email);
        phone = findViewById(R.id.signup_phone);
        state = findViewById(R.id.signup_state);
        city = findViewById(R.id.signup_city);
        password = findViewById(R.id.signup_password);
        b_admin = findViewById(R.id.signup_admin);

        b_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminLogin.class));
            }
        });
        b_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyFields() | !Email() |  !validatePassword()) {
                    return;
                }
                MyHandler db = new MyHandler(SignUser.this);
                Users users = new Users();
                users.setName(name.getEditText().getText().toString());
                users.setEmail(email.getEditText().getText().toString());
                users.setPhone(phone.getEditText().getText().toString());
                users.setState(state.getEditText().getText().toString());
                users.setCity(city.getEditText().getText().toString());
                users.setPassword(password.getEditText().getText().toString());
                db.signUser(users);
                Log.d("signUser","Successfully Signed in");
                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                intent.putExtra("session_email", email.getEditText().getText().toString());
                startActivity(intent);
            }
        });
        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginUser.class));
            }
        });
        b_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignShop.class));
            }
        });

    }

    //Validations Function
    private boolean emptyFields() {
        String val = name.getEditText().getText().toString().trim();
        String val1 = email.getEditText().getText().toString().trim();
        String val2 = phone.getEditText().getText().toString().trim();
        String val3 = state.getEditText().getText().toString().trim();
        String val4 = city.getEditText().getText().toString().trim();
        String val5 = password.getEditText().getText().toString().trim();

        if (val.isEmpty() || val1.isEmpty() || val2.isEmpty() ||val3.isEmpty() ||val4.isEmpty() ||val5.isEmpty()) {
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