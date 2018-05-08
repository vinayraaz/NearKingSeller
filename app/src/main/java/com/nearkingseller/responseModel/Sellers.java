package com.nearkingseller.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vinay on 5/1/2018.
 */

public class Sellers {
    @SerializedName("screenname")
    @Expose
    private String screenname;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("shortprofile")
    @Expose
    private Object shortprofile;
    @SerializedName("twitterid")
    @Expose
    private Object twitterid;
    @SerializedName("paypalid")
    @Expose
    private Object paypalid;
    @SerializedName("paypalfirstname")
    @Expose
    private Object paypalfirstname;
    @SerializedName("paypallastname")
    @Expose
    private Object paypallastname;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("facebookid")
    @Expose
    private Object facebookid;
    @SerializedName("companyname")
    @Expose
    private String companyname;
    @SerializedName("gst")
    @Expose
    private Object gst;
    @SerializedName("commission")
    @Expose
    private Object commission;

    public String getScreenname() {
        return screenname;
    }

    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getShortprofile() {
        return shortprofile;
    }

    public void setShortprofile(Object shortprofile) {
        this.shortprofile = shortprofile;
    }

    public Object getTwitterid() {
        return twitterid;
    }

    public void setTwitterid(Object twitterid) {
        this.twitterid = twitterid;
    }

    public Object getPaypalid() {
        return paypalid;
    }

    public void setPaypalid(Object paypalid) {
        this.paypalid = paypalid;
    }

    public Object getPaypalfirstname() {
        return paypalfirstname;
    }

    public void setPaypalfirstname(Object paypalfirstname) {
        this.paypalfirstname = paypalfirstname;
    }

    public Object getPaypallastname() {
        return paypallastname;
    }

    public void setPaypallastname(Object paypallastname) {
        this.paypallastname = paypallastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Object getFacebookid() {
        return facebookid;
    }

    public void setFacebookid(Object facebookid) {
        this.facebookid = facebookid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public Object getGst() {
        return gst;
    }

    public void setGst(Object gst) {
        this.gst = gst;
    }

    public Object getCommission() {
        return commission;
    }

    public void setCommission(Object commission) {
        this.commission = commission;
    }


}
