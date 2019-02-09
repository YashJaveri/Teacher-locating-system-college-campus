package com.imbuegen.hidenseek.Models;

public class Teacher {

    private String name;
    private Boolean busy;
    private double lattitude;
    private double longitude;
    private double altitude;
    private String department;

    public Teacher(String name, Boolean busy, double lattitude, double longitude, double altitude, String department) {
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.name = name;
        this.busy = busy;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public Boolean getBusy() {
        return busy;
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

    public String getDepartment() { return department; }
}
