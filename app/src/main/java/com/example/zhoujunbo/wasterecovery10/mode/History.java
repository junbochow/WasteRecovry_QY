package com.example.zhoujunbo.wasterecovery10.mode;

public class History {
    private String ID,state,time,collectorName,collectorNum,custmerName,custmerNum;
    public History(String ID,String state,String time,String collectorName,String collectorNum,String custmerName,String custmerNum){
        this.ID=ID;
        this.state=state;
        this.time=time;
        this.collectorName=collectorName;
        this.collectorNum=collectorNum;
        this.custmerName=custmerName;
        this.custmerNum=custmerNum;
    }

    public String getID() {
        return ID;
    }

    public String getState() {
        return state;
    }

    public String getTime() {
        return time;
    }

    public String getCollectorName() {
        return collectorName;
    }

    public String getCollectorNum() {
        return collectorNum;
    }

    public String getCustmerName() {
        return custmerName;
    }

    public String getCustmerNum() {
        return custmerNum;
    }
}
