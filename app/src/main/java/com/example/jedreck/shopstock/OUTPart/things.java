package com.example.jedreck.shopstock.OUTPart;

/**
 * Created by i on 2018/4/19.
 */

public class things {
    private String tname;
    private String tno;
    private int outnumber;
    private int imageid;
    public things(String tname,int imageid,String tno,int outnumber){
        this.tname=tname;
        this.imageid=imageid;
        this.tno=tno;
        this.outnumber=outnumber;
    }
    public String getTname(){
        return tname;
    }
    public String getTno(){
        return tno;
    }
    public int getOutnumber(){
        return outnumber;
    }
    public int getimageid(){
        return imageid;
    }
}
