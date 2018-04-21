package com.example.jedreck.shopstock.Internet;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestManager {
    private static String URL = "http://pvrfix.natappfree.cc/storage/";

    //获取当日销量前十
    public static Request getSaleWell() {
        return new Request.Builder()
                .url(URL + "SearchSaleWell_Servlet")
                .build();
    }

    //获取商品简单信息
    public static Request getIDLite(RequestBody requestBody) {
        return new Request.Builder()
                .url(URL + "SearchIDLite_Servlet")
                .post(requestBody)
                .build();
    }

    //获取商品完整信息-01
    public static Request getIDFull(RequestBody requestBody) {
        return new Request.Builder()
                .url(URL + "SearchIDFull_Servlet")
                .post(requestBody)
                .build();
    }

    //获取商品完整信息-02
    public static Request getIDFull(String id) {
        RequestBody requestBody = new FormBody.Builder()
                .add("id",id)
                .build();
        Request request= new Request.Builder()
                .url(URL + "SearchIDFull_Servlet")
                .post(requestBody)
                .build();
        return request;
    }

    //搜索商品的name或id
    public static Request getFuzzyIDOrName(RequestBody requestBody) {
        return new Request.Builder()
                .url(URL + "SearchFuzzyIDOrName_Servlet")
                .post(requestBody)
                .build();
    }

    //获取紧缺前十的库存商品简单信息
    public static Request getUnderStock(RequestBody requestBody) {
        return new Request.Builder()
                .url(URL + "SearchUnderStock_Servlet")
                .post(requestBody)
                .build();
    }

    //获取入库
    public static Request getEnterStock(RequestBody requestBody) {
        return new Request.Builder()
                .url(URL + "EnterStock_Servlet")
                .post(requestBody)
                .build();
    }
}
