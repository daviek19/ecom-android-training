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
                String id = row.getString(row.getColumnIndex("id"));
                //Add a new customer for every iteration
                customers.add(new Customer(customerName, customerNo, customerid, id));
            }

        } catch (Exception ex) {
            Log.d(TAG, "Error getting all customer " + ex.getMessage());
        }

        return customers;
    }

    public Customer GetSingleCustomer(String identification) {
        Customer customer = new Customer();

        if (identification.isEmpty())
            return customer;
        //fetch the custome rfrom db

        Cursor row = null;

        try {
            SQLiteDatabase db = dbManager.getWritableDatabase();

            String query = String.format("SELECT * FROM tbl_customer WHERE id = '%s';", identification);
            row = db.rawQuery(query, null);

            while (row.moveToNext()) {

                String customerName = row.getString(row.getColumnIndex("customer_name"));
                String customerNo = row.getString(row.getColumnIndex("cusomer_no"));
                String customerid = row.getString(row.getColumnIndex("cusomer_idno"));
                String id = row.getString(row.getColumnIndex("id"));
                //Add a new customer for every iteration
                customer = new Customer(customerName, customerNo, customerid, id);

            }

        } catch (Exception ex) {
            Log.d(TAG, "Error getting single customer " + ex.getMessage());
        }
        return customer;

    }

    public boolean UpdateCustomer(Customer customer) {
        boolean updated = false;
        try {

            SQLiteDatabase db = dbManager.getReadableDatabase();

            ContentValues cv = new ContentValues();
            cv.put("cusomer_no", customer.getCustomerNo());
            cv.put("customer_name", customer.getCustomerName());
            cv.put("cusomer_idno", customer.getGetCustomerId());

            String whereClause = String.format("id=%s", customer.getId());

            if (db.update("tbl_customer", cv, whereClause, null) >= 1)
                updated = true;

        } catch (Exception ex) {
            Log.d(TAG, "Error Updating db");
        }

        return updated;
    }

    public boolean DeleteCustomer(Customer customer) {
        boolean deleted = false;

        try {
            SQLiteDatabase db = dbManager.getReadableDatabase();
            String whereClause = String.format("id=%s", customer.getId());

            if (db.delete("tbl_customer", whereClause, null) >= 1)
                deleted = true;

        } catch (Exception ex) {
            Log.d(TAG, "Error deleting db");
        }

        return deleted;
    }
}
