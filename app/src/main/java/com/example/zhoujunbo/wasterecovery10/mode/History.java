package com.example.zhoujunbo.wasterecovery10.mode;

public class History {
    private String ID,state,time,collectorName,collectorNum,custmerName,custmerNum,whatsmore;
    private String goods1,count1,price1,goods2,count2,price2,goods3,count3,price3,goods4,count4,price4;
    public History(String ID,String state,String time,String collectorName,String collectorNum,String custmerName,String custmerNum,String whatsmore,
                   String goods1,String count1,String price1,String goods2,String count2,String price2,String goods3,String count3,String price3,String goods4,String count4,String price4){
        this.ID=ID;
        this.state=state;
        this.time=time;
        this.collectorName=collectorName;
        this.collectorNum=collectorNum;
        this.custmerName=custmerName;
        this.custmerNum=custmerNum;
        this.whatsmore=whatsmore;
        this.goods1=goods1;
        this.count1=count1;
        this.price1=price1;

        this.goods2=goods2;
        this.count2=count2;
        this.price2=price2;

        this.goods3=goods3;
        this.count3=count3;
        this.price3=price3;

        this.goods4=goods4;
        this.count4=count4;
        this.price4=price4;
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

    public String getWhatsmore() {
        return whatsmore;
    }

    public String getGoods1() {
        return goods1;
    }

    public String getCount1() {
        return count1;
    }

    public String getPrice1() {
        return price1;
    }

    public String getGoods2() {
        return goods2;
    }

    public String getCount2() {
        return count2;
    }

    public String getPrice2() {
        return price2;
    }

    public String getGoods3() {
        return goods3;
    }

    public String getCount3() {
        return count3;
    }

    public String getPrice3() {
        return price3;
    }

    public String getGoods4() {
        return goods4;
    }

    public String getCount4() {
        return count4;
    }

    public String getPrice4() {
        return price4;
    }
}
