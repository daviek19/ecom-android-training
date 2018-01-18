package com.ecom.app.ecom_crud.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ecom.app.ecom_crud.models.Customer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.concurrent.ExecutionException;

public class CustomerManager {
    private DatabaseManager dbManager;
    private String TAG = "CustomerManager";


    public CustomerManager(Context context) {
        dbManager = new DatabaseManager(context);
    }

    public boolean CreateCustomer(String customerName, String customerNo, String customerId) {
        Boolean customrCreated = false;

        SQLiteDatabase db = dbManager.getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("customer_name", customerName);
        cv.put("cusomer_idno", customerId);
        cv.put("cusomer_no", customerNo);
        cv.put("date_created", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date()));

        try {
            db.insert("tbl_customer", null, cv);
            customrCreated = true;
        } catch (Exception ex) {
            Log.d(TAG, "Error creating customer " + ex.getMessage());
        }

        //collect the details and insert int db.
        return customrCreated;
    }

    public List<Customer> GetAllCustomers() {

        List<Customer> customers = new ArrayList<Customer>();
        Cursor row = null;

        try {
            SQLiteDatabase db = dbManager.getWritableDatabase();
            String query = "SELECT * FROM tbl_customer;";
            row = db.rawQuery(query, null);

            while (row.moveToNext()) {
                String customerName = row.getString(row.getColumnIndex("customer_name"));
                String customerNo = row.getString(row.getColumnIndex("cusomer_no"));
                String customerid = row.getString(row.getColumnIndex("cusomer_idno"));
                //Add a new customer for every iteration
                customers.add(new Customer(customerName, customerNo, customerid));
            }

        } catch (Exception ex) {
            Log.d(TAG, "Error getting all customer " + ex.getMessage());
        }

        return customers;
    }
}
