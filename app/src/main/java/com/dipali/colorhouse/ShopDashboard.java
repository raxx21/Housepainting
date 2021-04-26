package com.dipali.colorhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.dipali.colorhouse.data.MyHandler;
import com.dipali.colorhouse.models.NearByHelper;
import com.dipali.colorhouse.models.Orders;
import com.dipali.colorhouse.models.OrdersHelper;
import com.dipali.colorhouse.models.Stores;
import com.dipali.colorhouse.models.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopDashboard extends AppCompatActivity {

    RecyclerView featured_recycle;
    RecyclerView.Adapter adapter;
    HashMap<String,String> details;
    Button b_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        MyHandler db = new MyHandler(ShopDashboard.this);

        //getOrders
//        List<Stores> orders = db.getAllStores();
//        if(orders != null) {
//            for (Stores user : orders) {
//                Log.d("goon" , "Orders \n" +
//                        "User : " + user.getArea() + "\n"
//                        + "Shop : " + user.getEmail());
//            }
//        }
//        else {
//            Log.d("goon", "Not working");
//        }


        //Hooks
        featured_recycle = findViewById(R.id.recycle_view);
        b_profile = findViewById(R.id.profile_btn);

        b_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignShop.class));
                finish();
            }
        });

        nearbyRecycle();
    }

    private void nearbyRecycle() {

        featured_recycle.setHasFixedSize(true);
        featured_recycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<OrdersHelper> featuredLocations = new ArrayList<>();
        MyHandler db = new MyHandler(ShopDashboard.this);

        Intent intent = getIntent();
        String shopEmail = intent.getStringExtra("session_shop_email");
        Log.d("goon", "0 " + shopEmail);

        details = new HashMap<>();
        details.put("user_email" , "dipalishop");
        List<Orders> orders_shop = db.getShopOrders(shopEmail);
        if(orders_shop != null){
            for (Orders user : orders_shop) {
                Log.d("goon" , "Orders SHops \n" +
                        "User : " + user.getUser() + "\n"
                        + "Shop : " + user.getShop());
                details.put("user_email" , user.getUser());
            }
        }
        else {
            Log.d("goon", "Not working");
        }

        // Display all garaaj in log
        List<Users> garaaj = db.getUser(details.get("user_email"));
        if(!garaaj.isEmpty()) {
            for(Users user: garaaj){
                featuredLocations.add(new OrdersHelper(user.getName(),user.getEmail(),user.getPhone()));
            }
        }
        else {
            featuredLocations.add(new OrdersHelper("No booking", "No booking Available",  "NON"));
        }

        adapter = new OrdersRecycleAdapter(ShopDashboard.this,featuredLocations);
        featured_recycle.setAdapter(adapter);
    }

}