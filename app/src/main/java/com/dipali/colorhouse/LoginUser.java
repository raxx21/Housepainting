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
import com.dipali.colorhouse.data.SessionManager;
import com.dipali.colorhouse.models.Users;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.List;

public class LoginUser extends AppCompatActivity {

    Button b_login;
    TextInputLayout email,password;
    HashMap<String,String> details ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        //Hooks
        b_login = findViewById(R.id.login_btn);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);

        //Creating Session
//        SessionManager sessionManager = new SessionManager(LoginUser.this);

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyFields()) {
                    return;
                }
                MyHandler db = new MyHandler(LoginUser.this);
                boolean check = db.loginUser(email.getEditText().getText().toString(),password.getEditText().getText().toString());
                if (check){
                    String emailS = email.getEditText().getText().toString();
//                    List<Users> users = db.getUser(emailS);
//                    if(users != null) {
//                        for (Users user : users) {
//                            details.put("fullnameS", user.getName());
//                            details.put("emailS", user.getEmail());
//                            details.put("phoneS", user.getPhone());
//                            details.put("passwordS", user.getPassword());
//                            details.put("stateS", user.getState());
//                            details.put("cityS", user.getState());
//                        }
//                    }
//                    else {
//                    }

//                    sessionManager.createLoginSession(details.get("fullnameS"),details.get("emailS"),details.get("phoneS"),
//                            details.get("passwordS"),details.get("stateS"),details.get("cityS"));
                    Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                    intent.putExtra("session_email",emailS);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Logged In Successfully!", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(), "Your Email or Password is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //Validations Function
    private boolean emptyFields() {
        String val = email.getEditText().getText().toString().trim();
        String val1 = password.getEditText().getText().toString().trim();

        if (val.isEmpty() || val1.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Fields cannot be empty!",Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}