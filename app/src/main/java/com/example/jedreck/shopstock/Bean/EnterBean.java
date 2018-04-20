package com.example.jedreck.shopstock.Bean;

import com.google.gson.Gson;

import java.util.List;

public class EnterBean {
    /**
     * id :
     * time :
     * num :
     * price_in :
     */

    private String id;
    private String time;
    private String num;
    private String price_in;

    public static EnterBean objectFromData(String str) {
        return new Gson().fromJson(str, EnterBean.class);
    }

    public static String objects2Json(List<EnterBean> enterBeanList) {
        return new Gson().toJson(enterBeanList);
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

    public String getPrice_in() {
        return price_in;
    }

    public void setPrice_in(String price_in) {
        this.price_in = price_in;
    }
}
