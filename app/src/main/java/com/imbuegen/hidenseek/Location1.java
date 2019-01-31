package com.imbuegen.hidenseek;

public class Location1 {
    String teacherId;
    String latitude;
    String longitude;

    public Location1(){

    }
    public Location1(String teacherId,String latitude,String longitude){
        this.teacherId=teacherId;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
