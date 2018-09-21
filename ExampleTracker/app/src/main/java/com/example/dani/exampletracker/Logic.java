package com.example.dani.exampletracker;

import android.location.Location;

import java.util.ArrayList;

/**
 * Created by Dani on 13/09/18.
 */

public class Logic {
    // My current location
    private Location myLoc;
    // The location where I have to go
    private Location goalLoc;
    //The position in the array of goal location
    private int stage;
    private String json;
    //Array of goal location
    private ArrayList<Location> trace;
    //Reference of my logic
    private static Logic ref=null;


    private Logic(){
        trace =  new ArrayList<Location>();
        json = new String("");
        trace.add(new Location(" "));
        trace.get(0).setLatitude(40.104125);
        trace.get(0).setLongitude(-88.233423);
        goalLoc = trace.get(0);
    }

    public static Logic getInstance(){
        if(ref == null) ref = new Logic();
        return ref;
    }



    public int getStage() {
        return stage;
    }

    public void setStage(int position) { this.stage = position; }

    public Location getMyLoc(){
        return myLoc;
    }
    public void setMyLoc(Location myLoc){this.myLoc = myLoc;}

    public Location getGoalLoc(){
        return goalLoc;
    }

    public void getJason(String json){ this.json = json; }


    public float getDistance(){
        float distanceInMeters =  goalLoc.distanceTo(myLoc);
        return distanceInMeters;
    }

    //TODO: load the locations from a json file
}
