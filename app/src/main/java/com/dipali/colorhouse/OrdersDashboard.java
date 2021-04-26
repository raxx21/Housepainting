package com.dipali.colorhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.dipali.colorhouse.data.MyHandler;
import com.dipali.colorhouse.models.Orders;
import com.dipali.colorhouse.models.Stores;

import java.util.HashMap;
import java.util.List;

public class OrdersDashboard extends AppCompatActivity {

    HashMap<String,String> details ;
    private static int SPLASH_SCREEN = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        String user_email = intent.getStringExtra("session_email");
        Log.d("goon","Booking " + user_email);
        details = new HashMap<>();
        MyHandler db = new MyHandler(this);
        List<Stores> users = db.getphoneShop(phone);
        if(users != null) {
            for (Stores user : users) {
                details.put("fullnameS", user.getOwner_name());
                details.put("emailS", user.getEmail());
                details.put("phoneS", user.getPhone());
                details.put("passwordS", user.getPassword());
                details.put("stateS", user.getState());
                details.put("cityS", user.getCity());

            }
        }
        else {
        }
        Log.d("goon","email " + details.get("emailS"));

//        Sign
        Orders raj = new Orders();
        raj.setUser(user_email);
        raj.setShop(details.get("emailS"));
        db.signOrders(raj);

        //getOrders
        List<Orders> orders = db.getOrders();
        if(orders != null) {
            for (Orders user : orders) {
                Log.d("goon" , "Orders \n" +
                        "User : " + user.getUser() + "\n"
                + "Shop : " + user.getShop());
            }
        }
        else {
            Log.d("goon", "Not working");
        }

        // get Orders from shop
        List<Orders> orders_shop = db.getShopOrders(details.get("emailS"));
        if(orders_shop != null){
            for (Orders user : orders_shop) {
                Log.d("goon" , "Orders SHops \n" +
                        "User : " + user.getUser() + "\n"
                        + "Shop : " + user.getShop());
            }
        }
        else {
            Log.d("goon", "Not working");
        }

        //Splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(OrdersDashboard.this, Dashboard.class);
                intent.putExtra("session_email", user_email);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Your Booking has be done!",Toast.LENGTH_SHORT).show();
                finish();
            }
        },SPLASH_SCREEN);
    }
}