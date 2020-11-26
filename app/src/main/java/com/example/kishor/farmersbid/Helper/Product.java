package com.example.kishor.farmersbid.Helper;

/**
 * Created by kishor on 05-02-2018.
 */

public class Product {
    private  String Bid;
    private String BidDate;
    private String Fid;
    private String Fname;
    private String pname;
    private String pcategory;
    private String pquntity;
    private String prate;
    private String pgrade;
    private String pbidprice;
    private String Consumer_Name;
    private String new_bid_price;
    private int postion;
    private String address;
    private String transPortPrice;
    private String cid;

    public Product(String bid, String bidDate, String fid, String fname, String pname, String pcategory, String pquntity, String prate, String pgrade, String pbidprice) {
        this.Bid = bid;
        this.BidDate = bidDate;
        this.Fid = fid;
        this.Fname = fname;
        this.pname = pname;
        this.pcategory = pcategory;
        this.pquntity = pquntity;
        this.prate = prate;
        this.pgrade = pgrade;
        this.pbidprice = pbidprice;

    }

    public Product(String bid, String pname, String pcategory, String pquntity, String pbidprice, String consumer_Name, String new_bid_price) {
        this.Bid = bid;
        this.pname = pname;
        this.pcategory = pcategory;
        this.pquntity = pquntity;
        this.pbidprice = pbidprice;
        this.Consumer_Name = consumer_Name;
        this.new_bid_price = new_bid_price;
    }

    public Product() {

    }



    public String getBid() {
        return Bid;
    }

    public void setBid(String bid) {
        Bid = bid;
    }

    public String getBidDate() {
        return BidDate;
    }

    public void setBidDate(String bidDate) {
        BidDate = bidDate;
    }

    public String getFid() {
        return Fid;
    }

    public void setFid(String fid) {
        Fid = fid;
    }

    public String getFname() {
        return Fname;
    }

    public String setFname(String fname) {
        Fname = fname;
        return fname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPcategory() {
        return pcategory;
    }

    public void setPcategory(String pcategory) {
        this.pcategory = pcategory;
    }

    public String getPquntity() {
        return pquntity;
    }

    public void setPquntity(String pquntity) {
        this.pquntity = pquntity;
    }

    public String getPrate() {
        return prate;
    }

    public void setPrate(String prate) {
        this.prate = prate;
    }

    public String getPgrade() {
        return pgrade;
    }

    public void setPgrade(String pgrade) {
        this.pgrade = pgrade;
    }

    public String getPbidprice() {
        return pbidprice;
    }

    public void setPbidprice(String pbidprice) {
        this.pbidprice = pbidprice;
    }

    public String getConsumer_Name() {
        return Consumer_Name;
    }

    public void setConsumer_Name(String consumer_Name) {
        Consumer_Name = consumer_Name;
    }

    public String getNew_bid_price() {
        return new_bid_price;
    }

    public void setNew_bid_price(String new_bid_price) {
        this.new_bid_price = new_bid_price;
    }


    public void setPostion(int postion) {
        this.postion = postion;
    }

    public int getPostion() {
        return postion;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTransPortPrice() {
        return transPortPrice;
    }

    public String setTransPortPrice(String transPortPrice) {
        this.transPortPrice = transPortPrice;
        return transPortPrice;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
