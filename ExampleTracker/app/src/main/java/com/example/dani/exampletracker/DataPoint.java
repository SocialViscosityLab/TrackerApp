package com.example.dani.exampletracker;

public class DataPoint {
    private long time;
    private double longitud;
    private double latitud;
    //The suggestion can be -1, to stop, 0 to keep the velocity or 1 to accelerate
    private int suggestion;
    private int speed;
    private int acceleration;

    public DataPoint(long time, double longitud, double latitud, int suggestion, int speed, int acceleration){
        this.time = time;
        this.longitud = longitud;
        this.latitud = latitud;
        this.suggestion = suggestion;
        this.speed = speed;
        this.acceleration = acceleration;
    }

    public long getTime() {
        return time;
    }

    public double getLongitud() {
        return longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public int getSuggestion() {
        return suggestion;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAcceleration() {
        return acceleration;
    }
}
