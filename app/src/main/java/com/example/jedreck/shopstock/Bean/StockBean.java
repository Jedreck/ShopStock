package com.example.jedreck.shopstock.Bean;

import java.util.List;

public class StockBean {

    /**
     * id :
     * name :
     * price :
     * stock :
     */

    private String id;
    private String name;
    private String price;
    private String stock;

    public static StockBean objectFromData(String str) {
        return new com.google.gson.Gson().fromJson(str, StockBean.class);
    }

    public static String object2Json(StockBean stock) {
        return new com.google.gson.Gson().toJson(stock);
    }

    public static String objects2Json(List<StockBean> list) {
        return new com.google.gson.Gson().toJson(list);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

//    public static void main(String[] args) {
//        Stock stock = SearchIDLite.searchID("6907992500133");
//        System.out.println(Stock.object2Json(stock));
//        List<Stock> stocks = new ArrayList<>();
//        Stock stock = new Stock();
//        stock.id="123";
//        stock.name="456";
//        stock.price="789";
//        stock.stock="654";
//        stocks.add(stock);
//        stock = new Stock();
//        stock.id="12";
//        stock.name="46";
//        stock.price="78";
//        stock.stock="65";
//        stocks.add(stock);
//        System.out.println(Stock.objects2Json(stocks));
//    }
}
