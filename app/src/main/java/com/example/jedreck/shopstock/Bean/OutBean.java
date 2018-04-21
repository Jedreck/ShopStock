package com.example.jedreck.shopstock.Bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
    private String name;


    public static OutBean json2Objective(String str) {
        return new Gson().fromJson(str, OutBean.class);
    }

    public static List<OutBean> json2Objectives(String data) {
        List<OutBean> list = new Gson().fromJson(data, new TypeToken<List<OutBean>>() {
        }.getType());
        return list;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


    @Override
    public String toString() {
        return this.getId() + "-" + this.getNum();
    }

    public static void main(String[] args) {
        String s = "[{\"id\":\"6907992500133\",\"time\":\"2018-04-20 14:48:24.0\",\"num\":\"10.0\",\"price_out\":\"25.0\"},{\"id\":\"6907992500133\",\"time\":\"2018-04-20 14:48:24.0\",\"num\":\"10.0\",\"price_out\":\"25.0\"}]";
        List<OutBean> list = OutBean.json2Objectives(s);
        System.out.println(list.get(0).toString() + list.get(1).toString());
    }
}
