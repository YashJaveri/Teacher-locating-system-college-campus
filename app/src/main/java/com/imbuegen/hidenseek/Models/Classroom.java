package com.imbuegen.hidenseek.Models;

public class Classroom {

    private int floor;
    private String name;
    private double lattitude;
    private double longitude;
    private double altitude;

    public Classroom(int floor, double lattitude, double longitude, double altitude, String name) {
        this.floor = floor;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public String getName() {
        return name;
    }

    public double getLattitude() {
        return lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getAltitude() {
        return altitude;
    }
}
