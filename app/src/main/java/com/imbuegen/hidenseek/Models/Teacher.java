package com.imbuegen.hidenseek.Models;

public class Teacher {

    private String name;
    private Boolean busy;
    private String latitude;
    private String longitude;
    private String altitude;
    private String department;

    public Teacher(String name, Boolean busy, String latitude, String longitude, String altitude, String department) {
        this.latitude = latitude;
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

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public String getDepartment() { return department; }
}
