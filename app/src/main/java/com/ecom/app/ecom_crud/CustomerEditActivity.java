package com.ecom.app.ecom_crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.ecom.app.ecom_crud.models.Customer;
import com.ecom.app.ecom_crud.services.CustomerManager;

public class CustomerEditActivity extends AppCompatActivity {

    private String customerId = "";
    private CustomerManager customerManager;
    private Customer customer;
    private EditText customerNameEdit, customerNoEdit, customerIdnoEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit);

        customerNameEdit = (EditText) findViewById(R.id.customer_name_edit);
        customerNoEdit = (EditText) findViewById(R.id.customer_no_edit);
        customerIdnoEdit = (EditText) findViewById(R.id.customer_no_edit);

        customerManager = new CustomerManager(getApplicationContext());


        //get the customer id that we passd from the previous intent
        Intent ourNewIntent = getIntent();
        customerId = ourNewIntent.getStringExtra("customer_id").toString();

        //get the customer
        customer = customerManager.GetSingleCustomer(customerId);

        //populate the fields
        customerNameEdit.setText(customer.getCustomerName());
        customerNoEdit.setText(customer.getCustomerNo());
        customerIdnoEdit.setText(customer.getCustomerNo());

    }
}
