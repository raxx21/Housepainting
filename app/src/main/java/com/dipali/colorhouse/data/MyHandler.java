package com.dipali.colorhouse.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dipali.colorhouse.Booking;
import com.dipali.colorhouse.models.Bookings;
import com.dipali.colorhouse.models.Orders;
import com.dipali.colorhouse.models.Stores;
import com.dipali.colorhouse.models.Users;
import com.dipali.colorhouse.params.Params;
import com.dipali.colorhouse.params.Params_Bookings;
import com.dipali.colorhouse.params.Params_Orders;
import com.dipali.colorhouse.params.Params_stores;
import com.dipali.colorhouse.params.Params_users;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends SQLiteOpenHelper {
    public MyHandler(Context context) { super(context, Params.DB_NAME, null, Params.DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create userTable
        String createUser ="CREATE TABLE " + Params_users.TABLE_NAME + "(" + Params_users.KEY_ID + " INTEGER PRIMARY KEY," + Params_users.KEY_NAME + " TEXT,"
                + Params_users.KEY_EMAIL + " TEXT," + Params_users.KEY_PHONE + " TEXT," + Params_users.KEY_PASSWORD + " TEXT," +
                Params_users.KEY_STATE + " TEXT," +Params_users.KEY_CITY + " TEXT" + ")";
        db.execSQL(createUser);

        // create ShopTable
        String createGaraaj ="CREATE TABLE " + Params_stores.TABLE_NAME + "(" + Params_stores.KEY_ID + " INTEGER PRIMARY KEY," + Params_stores.KEY_NAME + " TEXT,"
                + Params_stores.KEY_EMAIL + " TEXT," + Params_stores.KEY_PHONE + " TEXT," + Params_stores.KEY_SHOP + " TEXT,"  + Params_stores.KEY_AREA + " TEXT," + Params_stores.KEY_STREET +
                " TEXT," +Params_stores.KEY_LANDMARK + " TEXT," +Params_stores.KEY_STATE + " TEXT," +Params_stores.KEY_CITY +
                " TEXT," +  Params_stores.KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(createGaraaj);

        // create BookingTable
        String createBooking ="CREATE TABLE " + Params_Orders.TABLE_NAME + "(" + Params_Orders.KEY_ID + " INTEGER PRIMARY KEY," + Params_Orders.KEY_USER + " TEXT,"
                + Params_Orders.KEY_SHOP + " TEXT" +  ")";
        db.execSQL(createBooking);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // SignIN users
    public boolean signUser(Users users){
        SQLiteDatabase db = this.getWritableDatabase();

        // creating the inserting values
        ContentValues values = new ContentValues();
        values.put(Params_users.KEY_NAME, users.getName());
        values.put(Params_users.KEY_EMAIL, users.getEmail());
        values.put(Params_users.KEY_PHONE, users.getPhone());
        values.put(Params_users.KEY_PASSWORD, users.getPassword());
        values.put(Params_users.KEY_STATE, users.getState());
        values.put(Params_users.KEY_CITY, users.getCity());
        // inserting values in database
        db.insert(Params_users.TABLE_NAME, null, values);
        Log.d("goon", "Successfully user insert");
        db.close();
        return true;
    }

    //Check Email
    public boolean checkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        // check the email exists
        Cursor cursor = db.rawQuery("SELECT * FROM " + Params_users.TABLE_NAME + " WHERE " + Params_users.KEY_EMAIL + " =?" , new String[]{email});
        int cursorCount = cursor.getCount();
        if(cursorCount > 0){
            Log.d("goon", "Email or phone already exists");
            return false;
        }
        else {
            return true;
        }
    }

    //Check PhoneNo
    public boolean checkPhone(String phone){
        SQLiteDatabase db = this.getReadableDatabase();
        // check the email exists
        Cursor cursor = db.rawQuery("SELECT * FROM " + Params_users.TABLE_NAME + " WHERE " + Params_users.KEY_PHONE + " =?" , new String[]{phone});
        int cursorCount = cursor.getCount();
        if(cursorCount > 0){
            Log.d("goon", "Email or phone already exists");
            return false;
        }
        else {
            return true;
        }
    }

    // Get all users
    public List<Users> getAllUser(){
        List<Users> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

//      Generating the query to show all data from database
        String select = "SELECT * FROM " + Params_users.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        // Looping through the data
        if(cursor.moveToFirst()){
            do{
                Users users = new Users();
                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setName(cursor.getString(1));
                users.setEmail(cursor.getString(2));
                users.setPhone(cursor.getString(3));
                users.setPassword(cursor.getString(4));
                users.setState(cursor.getString(5));
                users.setCity(cursor.getString(6));
                userList.add(users);
            }while (cursor.moveToNext());
        }
        return userList;
    }

    //Get Users Details
    public List<Users> getUser(String email){
        List<Users> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

//      Generating the query to show all data from database
        Cursor cursor = db.rawQuery("select * from " + Params_users.TABLE_NAME +" where "+ Params_users.KEY_EMAIL + " =?", new String[]{email});
        // Looping through the data
        if(cursor.moveToFirst()){
            do{
                Users users = new Users();
                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setName(cursor.getString(1));
                users.setEmail(cursor.getString(2));
                users.setPhone(cursor.getString(3));
                users.setPassword(cursor.getString(4));
                users.setState(cursor.getString(5));
                users.setCity(cursor.getString(6));
                userList.add(users);
            }while (cursor.moveToNext());
        }
        return userList;
    }

    public List<Users> getphoneUser(String phone){
        List<Users> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

//      Generating the query to show all data from database
        Cursor cursor = db.rawQuery("select * from " + Params_users.TABLE_NAME +" where "+ Params_users.KEY_PHONE + " =?", new String[]{phone});
        // Looping through the data
        if(cursor.moveToFirst()){
            do{
                Users users = new Users();
                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setName(cursor.getString(1));
                users.setEmail(cursor.getString(2));
                users.setPhone(cursor.getString(3));
                users.setPassword(cursor.getString(4));
                users.setState(cursor.getString(5));
                users.setCity(cursor.getString(6));
                userList.add(users);
            }while (cursor.moveToNext());
        }
        return userList;
    }

    // SignIN shops
    public void signShops(Stores stores){
        SQLiteDatabase db = this.getWritableDatabase();

        // creating the inserting values
        ContentValues values = new ContentValues();
        values.put(Params_stores.KEY_NAME, stores.getOwner_name());
        values.put(Params_stores.KEY_EMAIL, stores.getEmail());
        values.put(Params_stores.KEY_PHONE, stores.getPhone());
        values.put(Params_stores.KEY_SHOP, stores.getShop_no());
        values.put(Params_stores.KEY_AREA, stores.getArea());
        values.put(Params_stores.KEY_STREET, stores.getStreet());
        values.put(Params_stores.KEY_LANDMARK, stores.getLandmark());
        values.put(Params_stores.KEY_STATE, stores.getState());
        values.put(Params_stores.KEY_CITY, stores.getCity());
        values.put(Params_stores.KEY_PASSWORD, stores.getPassword());

        // inserting values in database
        db.insert(Params_stores.TABLE_NAME, null, values);
        Log.d("goon", "Successfully garaaj insert");
        db.close();
    }

    // Get all garaaj
    public List<Stores> getAllStores(){
        List<Stores> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

//      Generating the query to show all data from database
        String select = "SELECT * FROM " + Params_stores.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        // Looping through the data
        if(cursor.moveToFirst()){
            do{
                Stores users = new Stores();
                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setOwner_name(cursor.getString(1));
                users.setEmail(cursor.getString(2));
                users.setPhone(cursor.getString(3));
                users.setShop_no(cursor.getString(4));
                users.setArea(cursor.getString(5));
                users.setStreet(cursor.getString(6));
                users.setLandmark(cursor.getString(7));
                users.setState(cursor.getString(8));
                users.setCity(cursor.getString(9));
                users.setPassword(cursor.getString(10));
                userList.add(users);
            }while (cursor.moveToNext());
        }
        return userList;
    }

    // Getting the near by Store
    public List<Stores> nearStore(String area){
        List<Stores> garaajList = new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();

        // Search query
        Cursor cursor = db.rawQuery("select * from " + Params_stores.TABLE_NAME +" where "+ Params_stores.KEY_CITY + " =?", new String[]{area});
        Log.d("goon", "count "+ cursor.getCount());
        if(cursor.moveToFirst()){
            do{
                Stores users = new Stores();
                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setOwner_name(cursor.getString(1));
                users.setEmail(cursor.getString(2));
                users.setPhone(cursor.getString(3));
                users.setShop_no(cursor.getString(4));
                users.setStreet(cursor.getString(5));
                users.setLandmark(cursor.getString(6));
                users.setState(cursor.getString(7));
                users.setCity(cursor.getString(8));
                users.setArea(cursor.getString(9));
                users.setPassword(cursor.getString(10));
                garaajList.add(users);
            }while (cursor.moveToNext());
        }else {
            Log.d("goon", "There are no Shops available nearby");
            return garaajList;
        }
        return garaajList;
    }

    public List<Stores> getphoneShop(String phone){
        List<Stores> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

//      Generating the query to show all data from database
        Cursor cursor = db.rawQuery("select * from " + Params_stores.TABLE_NAME +" where "+ Params_stores.KEY_PHONE + " =?", new String[]{phone});
        // Looping through the data
        if(cursor.moveToFirst()){
            do{
                Stores users = new Stores();
                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setOwner_name(cursor.getString(1));
                users.setEmail(cursor.getString(2));
                users.setPhone(cursor.getString(3));
                users.setShop_no(cursor.getString(4));
                users.setArea(cursor.getString(5));
                users.setStreet(cursor.getString(6));
                users.setLandmark(cursor.getString(7));
                users.setState(cursor.getString(8));
                users.setCity(cursor.getString(9));
                users.setPassword(cursor.getString(10));
                userList.add(users);
            }while (cursor.moveToNext());
        }
        return userList;
    }

    // User Login system
    public boolean loginUser(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        boolean check;
        Cursor cursor = db.rawQuery("SELECT * FROM " + Params_users.TABLE_NAME + " WHERE " + Params_users.KEY_EMAIL + " =?" + " AND " + Params_users.KEY_PASSWORD + " =?", new String[]{email,password});
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount > 0){
            Log.d("goon","The email and Password is correct for user login");
            check = true;
        }
        else{
            Log.d("goon","The email and Password is incorrect for user login");
            check = false;
        }
        return check;
    }

    // Garaaj Login system
    public boolean loginShops(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        boolean check;
        Cursor cursor = db.rawQuery("SELECT * FROM " + Params_stores.TABLE_NAME + " WHERE " + Params_stores.KEY_EMAIL + " =?" + " AND " + Params_stores.KEY_PASSWORD + " =?", new String[]{email,password});
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount > 0){
            Log.d("goon","The email and Password is correct for shop login");
            check = true;
        }
        else{
            Log.d("goon","The email and Password is incorrect for shop login");
            check = false;
        }
        return check;
    }

    // booking entry
//    public void booking(Bookings bookings){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // creating the inserting values
//        ContentValues values = new ContentValues();
//        values.put(Params_Bookings.KEY_USER, bookings.getUser());
//        values.put(Params_Bookings.KEY_SHOP, bookings.getShop());
//        // inserting values in database
//        db.insert(Params_Bookings.TABLE_NAME, null, values);
//        Log.d("goon", "Successfully user insert booking!");
//        db.close();
//    }
//
////     Get all bookings
//    public List<Bookings> getAllBookings(){
//        List<Bookings> userList = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Log.d("goon", "Started1 ");
//
////      Generating the query to show all data from database
//        Cursor cursor = null;
//        String select = "SELECT * FROM " + Params_Bookings.TABLE_NAME;
//        Log.d("goon", "Started1 " + select);
//        cursor = db.rawQuery(select, null);
////
////        // Looping through the data
//        if(cursor.moveToFirst()){
//            do{
//                Bookings users = new Bookings();
//                users.setId(Integer.parseInt(cursor.getString(0)));
//                users.setUser(cursor.getString(1));
//                users.setShop(cursor.getString(2));
//                userList.add(users);
//            }while (cursor.moveToNext());
//        }
//        cursor.close();
//        return userList;
//    }
//
//    // Get all bookings in shopDash
//    public List<Bookings> getBook(String shop_email){
//        List<Bookings> garaajList = new ArrayList<>();
//        SQLiteDatabase db= this.getReadableDatabase();
//        Log.d("goon", "Started ");
//
//        // Search query
//        Cursor cursor = db.rawQuery("SELECT * FROM " + Params_Bookings.TABLE_NAME +" where "+ Params_Bookings.KEY_SHOP + " =?", new String[]{shop_email});
//        Log.d("goon", "count "+ cursor);
//        if(cursor.moveToFirst()){
//            do{
//                Bookings users = new Bookings();
//                users.setId(Integer.parseInt(cursor.getString(0)));
//                users.setUser(cursor.getString(1));
//                users.setShop(cursor.getString(2));
//                garaajList.add(users);
//            }while (cursor.moveToNext());
//        }else {
//            Log.d("goon", "There are no Bookings");
//            return garaajList;
//        }
//        return garaajList;
//    }

    //Orders
    public void signOrders(Orders orders){
        SQLiteDatabase db = this.getWritableDatabase();

        // creating the inserting values
        ContentValues values = new ContentValues();
        values.put(Params_Orders.KEY_USER, orders.getUser());
        values.put(Params_Orders.KEY_SHOP, orders.getShop());
        // inserting values in database
        db.insert(Params_Orders.TABLE_NAME, null, values);
        Log.d("goon", "Successfully user insert");
        db.close();

    }

    //Get Orders
    public List<Orders> getOrders(){
        List<Orders> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

//      Generating the query to show all data from database
        String select = "SELECT * FROM " + Params_Orders.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        // Looping through the data
        if(cursor.moveToFirst()){
            do{
                Orders users = new Orders();
                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setUser(cursor.getString(1));
                users.setShop(cursor.getString(2));
                userList.add(users);
            }while (cursor.moveToNext());
        }
        return userList;
    }

    public List<Orders> getShopOrders(String shop_email){
        List<Orders> garaajList = new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        Log.d("goon", "Started ");
        // Search query
        Cursor cursor = db.rawQuery("SELECT * FROM " + Params_Orders.TABLE_NAME +" where "+ Params_Orders.KEY_SHOP + " =?", new String[]{shop_email});
        Log.d("goon", "count "+ cursor);
        if(cursor.moveToFirst()){
            do{
                Orders users = new Orders();
                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setUser(cursor.getString(1));
                users.setShop(cursor.getString(2));
                garaajList.add(users);
            }while (cursor.moveToNext());
        }else {
            Log.d("goon", "There are no Bookings");
            return garaajList;
        }
        return garaajList;
    }



}
