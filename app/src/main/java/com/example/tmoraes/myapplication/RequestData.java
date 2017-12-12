package com.example.tmoraes.myapplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tmoraes on 12/12/2017.
 */

public class RequestData {
    private String user;
    private int type;
    private Date date;

    public RequestData(){}
    public RequestData(String user, int type, String date) {
        this.user = user;
        this.type = type;
       /* SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");

        try {
            this.date = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return user + " - " + type + " - " + date;
    }
}
