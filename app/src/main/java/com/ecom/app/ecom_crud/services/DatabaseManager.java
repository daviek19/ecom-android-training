package com.ecom.app.ecom_crud.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ecom.db";
    public static final int DATABASE_VERSION = 1;
    private final String TAG = DATABASE_NAME;


    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    private String constructCustomerTable() {
        String customerQuery = new StringBuilder()
                .append("CREATE TABlE IF NOT EXISTS tbl_customer (")
                .append("id INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append("customer_name TEXT, ")
                .append("cusomer_idno TEXT, ")
                .append("cusomer_no TEXT, ")
                .append("date_created DATETIME")
                .append(")")
                .toString();
        return customerQuery;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(constructCustomerTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_customer");
        onCreate(db);
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}
