package com.example.zhoujunbo.wasterecovery10;

public class Mine {
    private String name;
    private int imageId;

    public Mine(String name,int imageID){
        this.name=name;
        this.imageId=imageID;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }
}
