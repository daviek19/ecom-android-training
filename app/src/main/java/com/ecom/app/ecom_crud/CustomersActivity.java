package com.ecom.app.ecom_crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ecom.app.ecom_crud.adapters.CustomersAdapter;
import com.ecom.app.ecom_crud.models.Customer;
import com.ecom.app.ecom_crud.services.CustomerManager;

import java.util.List;

public class CustomersActivity extends AppCompatActivity {

    private RecyclerView cutomerRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);

        List<Customer> customers = new CustomerManager(getApplicationContext()).GetAllCustomers();

        cutomerRecyclerView = findViewById(R.id.cutomer_recycler_view);
        cutomerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        cutomerRecyclerView.addItemDecoration(
                new DividerItemDecoration(cutomerRecyclerView.getContext(),
                        new LinearLayoutManager(this).getOrientation())
        );

        //SET THE ADAPETER
        CustomersAdapter adapater = new CustomersAdapter(
                customers,
                R.layout.list_of_customers,
                getApplicationContext());

        cutomerRecyclerView.setAdapter(adapater);

    }
}
