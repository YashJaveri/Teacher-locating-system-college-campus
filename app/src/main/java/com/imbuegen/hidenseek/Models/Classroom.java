package com.imbuegen.hidenseek.Models;

import com.google.firebase.database.PropertyName;

import java.io.Serializable;

public class Classroom implements Serializable {

    @PropertyName("Floor")
    private int floor;
    @PropertyName("Name")
    private String name;
    @PropertyName("Latitude")
    private Double latitude;
    @PropertyName("Longitude")
    private Double longitude;
    @PropertyName("Altitude")
    private Double altitude;

    public Classroom(int floor, double latitude, double longitude, double altitude, String name) {
        this.floor = floor;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.name = name;
    }

    public Classroom(){}

    public int getFloor() {
        return floor;
    }

    public String getName() {
        return name;
    }

    public double getlatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getAltitude() {
        return altitude;
    }
}
