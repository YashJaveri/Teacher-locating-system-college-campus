package com.imbuegen.hidenseek;

public class Location1 {
    String teacherId;
    double latitude;
    double longitude;

    public Location1(){

    }
    public Location1(String teacherId,double latitude,double longitude){
        this.teacherId=teacherId;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
