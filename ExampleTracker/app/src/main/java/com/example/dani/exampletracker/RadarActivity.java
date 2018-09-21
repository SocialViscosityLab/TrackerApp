package com.example.dani.exampletracker;

import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class RadarActivity extends AppCompatActivity {

    //Reference of the logic
    private Logic logic;
    //Reference to the compass illustration
    private ImageView compass;
    //Reference to the distance textView
    private TextView distance;
    // Reference to a Radar object
    private Radar my_radar;
    //Current orientation in a float value
    private float currentAzimuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);

        //Link the current reference of the logic
        logic =  Logic.getInstance();
        // init ImageView of compass
        initCompass();
        // init textView for the distance
        initDistance();
        //init Radar
         setupRadar();
    }

    private void initCompass(){
        this.compass = findViewById(R.id.compass);
    }

    private void initDistance(){
        this.distance = findViewById(R.id.distance);
        this.distance.setText(logic.getDistance()+" M");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("debugging", "start compass");
        my_radar.start();
    }


        @Override
        protected void onPause() {
            super.onPause();
            my_radar.stop();
        }

        @Override
        protected void onResume() {
            super.onResume();
            my_radar.start();
        }

        @Override
        protected void onStop() {
            super.onStop();
            Log.d("debugging", "stop compass");
            my_radar.stop();
        }

    private void setupRadar() {
        my_radar = new Radar(this);
        Radar.RadarListener cl = new Radar.RadarListener() {

            @Override
            public void onNewAzimuth(float azimuth) {
                adjustArrow(azimuth);
            }
        };
        my_radar.setListener(cl);
    }


    private void adjustArrow(float azimuth) {
        Log.d("debugging", "will set rotation from " + currentAzimuth + " to "
                + azimuth);

        Animation an = new RotateAnimation(-currentAzimuth, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        currentAzimuth = azimuth;

        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);

        compass.startAnimation(an);
    }
    //TODO: Update the distance when it changes
}
