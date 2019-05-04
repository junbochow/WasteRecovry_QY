package com.example.zhoujunbo.wasterecovery10.mode;

public class Goods {
    private String goods,count,price;

    public Goods(String goods,String count,String price){
        this.goods=goods;
        this.count=count;
        this.price=price;
    }

    public String getGoods(){
        return goods;
    }

    public String getCount(){
        return count;
    }

    public String getPrice() {
        return price;
    }
}
