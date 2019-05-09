package com.example.zhoujunbo.wasterecovery10.mode;

import java.util.List;

public class Order_Summary {
    String ID,state,time,collectorName,collectorNum,custmerName,custmerNum,whatsmore,Total;
    private List<Order_Body> order_bodies;

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getCollectorName() {
        return collectorName;
    }
    public void setCollectorName(String collectorName) {
        this.collectorName = collectorName;
    }

    public String getCollectorNum() {
        return collectorNum;
    }
    public void setCollectorNum(String collectorNum) {
        this.collectorNum = collectorNum;
    }

    public String getCustmerName() {
        return custmerName;
    }
    public void setCustmerName(String custmerName) {
        this.custmerName = custmerName;
    }

    public String getCustmerNum() {
        return custmerNum;
    }
    public void setCustmerNum(String custmerNum) {
        this.custmerNum = custmerNum;
    }

    public String getWhatsmore() {
        return whatsmore;
    }
    public void setWhatsmore(String whatsmore) {
        this.whatsmore = whatsmore;
    }

    public String getTotal() {
        return Total;
    }
    public void setTotal(String total) {
        Total = total;
    }

    public List<Order_Body> getOrder_bodies() {
        return order_bodies;
    }

    public void setOrder_bodies(List<Order_Body> order_bodies) {
        this.order_bodies = order_bodies;
    }
}
