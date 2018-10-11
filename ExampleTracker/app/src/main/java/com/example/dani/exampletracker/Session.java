package com.example.dani.exampletracker;

import java.util.ArrayList;

public class Session {
    private String id_session;
    private String id_user;
    private String start_time;
    private ArrayList <DataPoint> data_points;

    public Session(String id_session, String id_user, String start_time){
        this.id_session = id_session;
        this.id_user = id_user;
        this.start_time = start_time;
        data_points =  new ArrayList<DataPoint>();
    }

    public String getId_session() {
        return id_session;
    }

    public String getId_user() {
        return id_user;
    }

    public String getStart_time() {
        return start_time;
    }

    public ArrayList<DataPoint> getData_points() {
        return data_points;
    }
    public void addNewDataPoint(long time, double longitud, double latitud, int suggestion, int speed, int acceleration){
        data_points.add(new DataPoint(time, longitud, latitud, suggestion, speed, acceleration));
    }
}
