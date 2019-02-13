package com.imbuegen.hidenseek.Models;

import com.google.firebase.database.PropertyName;
import java.io.Serializable;

public class Teacher implements Serializable {
    @PropertyName("Name")
    private String name;
    @PropertyName("Busy")
    private long busy;
    @PropertyName("Latitude")
    private Double latitude;
    @PropertyName("Longitude")
    private Double longitude;
    @PropertyName("Altitude")
    private Double altitude;
    @PropertyName("Department")
    private String department;
    @PropertyName("Number")
    private String number;
    @PropertyName("email")
    private String email;

    public Teacher(String name, String email, Double latitude, Double longitude, Double altitude, String department, String number, long busy) {
        this.name = name;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.department = department;
        this.number = number;
        this.busy = busy;
    }

    public Teacher(){}

    public String getName() { return name; }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getNumber() { return number; }

    public long getBusy() { return busy; }

    public Double getAltitude() {
        return altitude;
    }

    public String getDepartment() { return department; }

    public String getEmail() { return email; }
}
