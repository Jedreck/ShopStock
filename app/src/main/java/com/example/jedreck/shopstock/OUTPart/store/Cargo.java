package com.example.jedreck.shopstock.OUTPart.store;

/**
 * Created by zjx on 2018/4/20.
 */

public class Cargo
{
    private String info;
    private int imageid;
    public Cargo(String info,int imageid)
    {
        this.info=info;
        this.imageid=imageid;
    }
    public String getInfo()
    {
        return info;
    }
    public int getImageid()
    {
        return imageid;
    }
}
