package com.example.jedreck.shopstock.Start;

/**
 * Created by i on 2018/4/23.
 */

public class StartBean {
    private String name;
    private String stock;
    private int imageid;
    public StartBean(String name,String stock,int imageid){
        this.name=name;
        this.stock=stock;
        this.imageid=imageid;
    }
    public String Getname(){
        return  name;
    }
    public String Getstock(){
        return stock;
    }
    public int Getimageid(){
        return imageid;
    }

}
