package com.example.jedreck.shopstock.Bean;

import com.google.gson.Gson;

import java.util.List;

public class OutBean {
    /**
     * id :
     * time :
     * num :
     * price_out :
     */

    private String id;
    private String time;
    private String num;
    private String price_out;

    public static OutBean objectFromData(String str) {
        return new Gson().fromJson(str, OutBean.class);
    }

    public static String objects2Json(List<OutBean> outBeans) {
        return new Gson().toJson(outBeans);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPrice_out() {
        return price_out;
    }

    public void setPrice_out(String price_out) {
        this.price_out = price_out;
    }
}
