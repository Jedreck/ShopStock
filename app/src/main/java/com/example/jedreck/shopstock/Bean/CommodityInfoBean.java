package com.example.jedreck.shopstock.Bean;

import com.google.gson.Gson;

import java.util.List;

public class CommodityInfoBean {
    /**
     * stock :
     * enter : []
     * out : []
     */

    private StockBean stock;
    private List<EnterBean> enter;
    private List<OutBean> out;

    public static CommodityInfoBean objectFromData(String str) {
        return new Gson().fromJson(str, CommodityInfoBean.class);
    }

    public static String object2Json(CommodityInfoBean commodityInfoBean) {
        return new Gson().toJson(commodityInfoBean);
    }

    public StockBean getStock() {
        return stock;
    }

    public void setStock(StockBean stock) {
        this.stock = stock;
    }

    public List<EnterBean> getEnter() {
        return enter;
    }

    public void setEnter(List<EnterBean> enter) {
        this.enter = enter;
    }

    public List<OutBean> getOut() {
        return out;
    }

    public void setOut(List<OutBean> out) {
        this.out = out;
    }

//    public static void main(String[] args) {
//        CommodityInfoBean commodityInfoBean = new CommodityInfoBean();
//        StockBean stockBean=new StockBean();
//        stockBean.setId("123123");
//        stockBean.setName("namename");
//        stockBean.setPrice("789789");
//        stockBean.setStock("456456");
//        commodityInfoBean.stock=stockBean;
//        List<EnterBean> enterBeans = new ArrayList<>();
//        EnterBean enterBean = new EnterBean();
//        enterBean.setId("123123");
//        enterBean.setNum("456456");
//        enterBean.setPrice_in("789789");
//        enterBean.setTime("datetime");
//        enterBeans.add(enterBean);
//        enterBean = new EnterBean();
//        enterBean.setId("123123");
//        enterBean.setNum("456666");
//        enterBean.setPrice_in("789666");
//        enterBean.setTime("datetime666");
//        enterBeans.add(enterBean);
//        commodityInfoBean.enter=enterBeans;
//        List<OutBean> outBeans = new ArrayList<>();
//        OutBean outBean = new OutBean();
//        outBean.setId("123123");
//        outBean.setNum("456444");
//        outBean.setTime("timeout");
//        outBean.setPrice_out("1234123");
//        outBeans.add(outBean);
//        commodityInfoBean.out=outBeans;
//        System.out.println(CommodityInfoBean.object2Json(commodityInfoBean));
//    }
}
