package com.ecom.app.ecom_crud.adapters;


import android.content.ContentValues;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecom.app.ecom_crud.R;
import com.ecom.app.ecom_crud.models.Customer;

import static com.ecom.app.ecom_crud.adapters.CustomersAdapter.CustomersViewHolder;

import java.util.List;


public class CustomersAdapter extends RecyclerView.Adapter<CustomersViewHolder> {

    List<Customer> customers;
    private int rowLayout;
    private Context context;

    public static class CustomersViewHolder extends RecyclerView.ViewHolder {
        LinearLayout customerLayout;
        TextView customerName;

        public CustomersViewHolder(View itemView) {
            super(itemView);
            customerLayout = itemView.findViewById(R.id.layout_cutsomers);
            customerName = itemView.findViewById(R.id.layout_customer_name);
        }
    }

    public CustomersAdapter (List<Customer> customers, int rowLayout, Context context) {
        this.context = context;
        this.customers = customers;
        this.rowLayout = rowLayout;
    }


    @Override
    public CustomersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CustomersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomersViewHolder holder, int position) {
        //This is where we bind the data to the view objects that we inflated
        holder.customerName.setText(customers.get(position).getCustomerName());
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }
}
