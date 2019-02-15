package com.imbuegen.hidenseek.Models;

import com.google.firebase.database.PropertyName;

import java.io.Serializable;

public class Teacher implements Serializable {
    private String name;
    @PropertyName("busy")
    private long busy;
    private Double latitude;
    private Double longitude;
    private Double altitude;
    private String department;
    private String number;
    private String email;

    private Classroom cls;

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

    public Classroom getCls() { return cls; }

    public void setCls(Classroom cls) { this.cls = cls; }

    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public void setBusy(long busy) { this.busy = busy; }

    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public void setAltitude(Double altitude) { this.altitude = altitude; }
}
