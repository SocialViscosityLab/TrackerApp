package com.example.dani.exampletracker;

import android.location.Location;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dani on 13/09/18.
 */

public class Logic extends Thread{
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

    //Session arraylist
    private ArrayList<Session>sessions;
    //Boolean to know if is currently creating data for a session
    private boolean isInSession;
    //Loop state
    private boolean isRunning;
    //Current user ID
    private String id_user;
    //Current session ID TODO: ask for the last id in the database to know the next id to use
    private int id_session;
    //Current session time in millis
    private Long session_time;


    private Logic(){
        trace =  new ArrayList<Location>();
        json = new String("");
        trace.add(new Location(" "));
        trace.get(0).setLatitude(40.104125);
        trace.get(0).setLongitude(-88.233423);
        goalLoc = trace.get(0);

        //Set the value to run thread
        isRunning =  true;
        //Initialize the sessions arraylist
        sessions = new ArrayList<Session>();

    }

    public static Logic getInstance(){
        if(ref == null) ref = new Logic();
        return ref;
    }

    //return the current time with the format YYYY/MM/dd - HH:mm:ss
    public String getStartTime(){
        DateFormat formatter = new SimpleDateFormat("YYYY/MM/dd - HH:mm:ss");
       return  String.valueOf(formatter.format(currentDateAndTime().getTime()));
    }

    //Return the current date and work as a time manager
    public Calendar currentDateAndTime(){
        Calendar currentDateAndTime = Calendar.getInstance();
        return  currentDateAndTime;
    }

    //Creates a new session in the arrayList sessions and start the counter
    public void startNewSession(){
        String string_session_id = String.format("%05d", id_session);
        sessions.add(new Session(string_session_id, id_user, getStartTime()));
        session_time = currentDateAndTime().getTimeInMillis();
        isInSession = true;
    }

    //Close the current session
    public void closeSession(){
        isInSession = false;
    }

    public void run(){
        while (isRunning){
            try {
                if(isInSession){
                    //add new data_points to the last session created every second
                    long current_session_time = currentDateAndTime().getTimeInMillis()-session_time;
                    sessions.get(sessions.size()-1).addNewDataPoint(current_session_time,myLoc.getLongitude(),myLoc.getLatitude(), 0, 0, 0);
                }

                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
