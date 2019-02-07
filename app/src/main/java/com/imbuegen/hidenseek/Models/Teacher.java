package com.imbuegen.hidenseek.Models;

public class Teacher {

    private String name;
    private Boolean busy;
    private String lattitude;
    private String longitude;
    private String altitude;
    private String email;// not sure (optional)
    public Teacher(){

    }
    public Teacher(String name, String lattitude, String longitude) {
        this.lattitude = lattitude;
        this.longitude = longitude;
        //this.altitude = altitude;
        this.name = name;
        //this.busy = busy;
        //this.email = email;
    }

    public String getName() {
        return name;
    }

    public Boolean getBusy() {
        return busy;
    }

    public String getLattitude () {
        return lattitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public String getEmail() {
        return email;
    }
}
