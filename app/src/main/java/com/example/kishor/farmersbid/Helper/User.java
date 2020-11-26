package com.example.kishor.farmersbid.Helper;

/**
 * Created by kishor on 04-02-2018.
 */

public class User {

    private static int Id;
    private static String BidId;
    private static String Name;
    private static String Dob;
    private static String Lat;
    private static String lon;
    private static String Address;
    private static String Contact;
    private static String Email;
    private static String Aadhar;
    private static String Pan;
    private static String Account;
    private static String IFSC;
    private static String Branch;
    private static String BankName;
    private static String Password;



    public User(int id, String name, String dob,String address,String lat,String lon,String contact, String email, String aadhar, String pan, String account, String IFSC, String branch, String bankName, String password) {
        this.Id = id;
        this.Name = name;
        this.Dob = dob;
        this.Lat=lat;
        this.lon=lon;
        this.Address = address;
        this.Contact = contact;
        this.Email = email;
        this.Aadhar = aadhar;
        this.Pan = pan;
        this.Account = account;
        this.IFSC = IFSC;
        this.Branch = branch;
        this.BankName = bankName;
        this.Password = password;
    }

    public User() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAadhar() {
        return Aadhar;
    }

    public void setAadhar(String aadhar) {
        Aadhar = aadhar;
    }

    public String getPan() {
        return Pan;
    }

    public void setPan(String pan) {
        Pan = pan;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getIFSC() {
        return IFSC;
    }

    public void setIFSC(String IFSC) {
        this.IFSC = IFSC;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
