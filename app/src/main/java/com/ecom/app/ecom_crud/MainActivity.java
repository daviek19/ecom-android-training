package com.ecom.app.ecom_crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ecom.app.ecom_crud.services.CustomerManager;

public class MainActivity extends AppCompatActivity {

    private EditText customerName;
    private EditText customerId;
    private EditText customerNo;
    private Button submitBtn;
    private String customerNameStr, customerNoStr, customerIdStr;
    private final String TAG = "CustomerCreate";
    private CustomerManager customerManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerManager = new CustomerManager(getApplicationContext());

        customerName = (EditText) findViewById(R.id.customer_no);
        customerId = (EditText) findViewById(R.id.customer_id);
        customerNo = (EditText) findViewById(R.id.customer_no);
        submitBtn = (Button) findViewById(R.id.submit_btn);

        //handle validation when button is clicked;
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //validate inputs
                customerNameStr = customerName.getText().toString();
                customerNoStr = customerNo.getText().toString();
                customerIdStr = customerId.getText().toString();

                if (validInput(customerNoStr, customerNameStr, customerIdStr)) {
                    //post to db;
                    if (customerManager.CreateCustomer(customerNameStr, customerNoStr, customerIdStr)) {
                        Toast.makeText(getApplicationContext(), "Customer added", Toast.LENGTH_LONG)
                                .show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Customer could not be added", Toast.LENGTH_LONG)
                                .show();
                    }
                }

            }
        });
    }

    private Boolean validInput(String customerNoInpt, String customerNameInpt, String custsomerIdInpt) {
        Boolean isValid = true;

        if (customerNoInpt.isEmpty()) {
            customerNo.setError("Enter Valid Customer name");
            isValid = false;
        }

        if (customerNameInpt.isEmpty()) {
            customerName.setError("Enter valid id");
            isValid = false;
        }

        if (custsomerIdInpt.isEmpty()) {
            customerId.setError("Enter valid customer no");
            isValid = false;
        }

        Log.d(TAG, "The details" + customerNo + " " + customerName + " " + customerId);

        return isValid;
    }

}
