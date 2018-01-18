package com.ecom.app.ecom_crud.models;

public class Customer {

    private String customerName;
    private String customerNo;
    private String getCustomerId;

    public Customer(String customerName, String customerNo, String getCustomerId) {
        this.customerName = customerName;
        this.customerNo = customerNo;
        this.getCustomerId = getCustomerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getGetCustomerId() {
        return getCustomerId;
    }

    public void setGetCustomerId(String getCustomerId) {
        this.getCustomerId = getCustomerId;
    }
}
