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

public class LoginShop extends AppCompatActivity {

    Button b_login;
    TextInputLayout email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_shop);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        //Hooks
        b_login = findViewById(R.id.loginshop_btn);
        email = findViewById(R.id.loginshop_email);
        password = findViewById(R.id.loginshop_password);

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyFields()) {
                    return;
                }
                MyHandler db = new MyHandler(LoginShop.this);
                boolean check = db.loginShops(email.getEditText().getText().toString(),password.getEditText().getText().toString());
                if (check){
                    Intent intent = new Intent(getApplicationContext(),ShopDashboard.class);
                    intent.putExtra("session_shop_email", email.getEditText().getText().toString());
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