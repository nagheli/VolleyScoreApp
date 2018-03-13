package com.example.ioana.volleyscoreapp;

import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int home_points = 0;
    int guest_points = 0;
    int setHome = 0;
    int setGuest = 0;

    long time;
    boolean startt,resett,plusOnePointHomeE,plusOnePointGuestT;
    TextView homeScore,guestScore, setPointsHome, setPointsGuest, endGame;
    Chronometer simpleChronometer;
    Button start, reset,plusOnePointHome,plusOnePointGuest;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initiate views
        plusOnePointHome = (Button) findViewById(R.id.plusOnePointHome);
        plusOnePointGuest = (Button) findViewById(R.id.plusOnePointGuest);
        simpleChronometer = (Chronometer) findViewById(R.id.clockTimer);
        homeScore = (TextView) findViewById(R.id.homeScore);
        guestScore = (TextView) findViewById(R.id.guestScore);
        setPointsHome = (TextView) findViewById(R.id.setPointsHome);
        setPointsGuest = (TextView) findViewById(R.id.setPointsGuest);
        endGame = (TextView) findViewById(R.id.endGame);
        start = (Button) findViewById(R.id.buttonStart);
        reset = (Button) findViewById(R.id.buttonReset);
        plusOnePointHome.setEnabled(false);
        plusOnePointGuest.setEnabled(false);
        plusOnePointGuestT = false;
        plusOnePointHomeE = false;
        startt = true;
        resett = true;

        //media player
        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.music1);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        // perform click  event on start button to start a chronometer

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpleChronometer.setBase(SystemClock.elapsedRealtime());
                simpleChronometer.start();
                plusOnePointHome.setEnabled(true);
                plusOnePointGuest.setEnabled(true);
                start.setEnabled(false);
                plusOnePointGuestT = true;
                plusOnePointHomeE = true;
                resett = true;
                startt = false;

            }
        });

        /**
         *  Perform click  event on restart button to set the base time on chronometer
         */

        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                //  time =simpleChronometer.getBase()-SystemClock.elapsedRealtime();
                simpleChronometer.stop();
                simpleChronometer.setBase(SystemClock.elapsedRealtime());
                displayHomePoints(0);
                displayGuestPoints(0);
                setHome = 0;
                setGuest = 0;
                displaySetGuest(0);
                displaySetHome(0);
                displayEndGameGuest(" ");
                displayEndGameHome(" ");
                plusOnePointHome.setEnabled(false);
                plusOnePointGuest.setEnabled(false);
                start.setEnabled(true);
                plusOnePointGuestT = false;
                plusOnePointHomeE = false;
                startt = true;
                resett = true;

            }
        });
    }

    @Override
    public void onBackPressed() {
        mediaPlayer.release();
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save myVar's value in saveInstanceState bundle
        outState.putString("myVarEndHomeGame",endGame.getText().toString());
        outState.putString("myVarEndGuestGame",endGame.getText().toString());
        time = simpleChronometer.getBase()-SystemClock.elapsedRealtime();
        simpleChronometer.stop();
        outState.putLong("timer",time);
        outState.putInt("varHomePoints", home_points);
        outState.putInt("varGuestPoints", guest_points);
        outState.putInt("varSetHome", setHome);
        outState.putInt("varSetGuest", setGuest);

        outState.putInt("position", mediaPlayer.getCurrentPosition());
        mediaPlayer.pause();


        outState.putBoolean("start",startt);
        outState.putBoolean("reset",resett);
        outState.putBoolean("plusOnePointHomeE",plusOnePointHomeE);
        outState.putBoolean("plusOnePointGuestT",plusOnePointGuestT);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        endGame.setText(savedInstanceState.getString("myVarEndHomeGame"));
        endGame.setText(savedInstanceState.getString("myVarEndGuestGame"));
        home_points = savedInstanceState.getInt("varHomePoints");
        guest_points = savedInstanceState.getInt("varGuestPoints");
        setHome = savedInstanceState.getInt("varSetHome");
        setGuest = savedInstanceState.getInt("varSetGuest");

        homeScore.setText(String.valueOf(home_points));
        guestScore.setText(String.valueOf(guest_points));
        setPointsHome.setText(String.valueOf(setHome));
        setPointsGuest.setText(String.valueOf(setGuest));

        startt = savedInstanceState.getBoolean("start");
        resett = savedInstanceState.getBoolean("reset");
        plusOnePointHomeE = savedInstanceState.getBoolean("plusOnePointHomeE", plusOnePointHomeE);
        plusOnePointGuestT = savedInstanceState.getBoolean("plusOnePointGuestT", plusOnePointGuestT);

        int pos = savedInstanceState.getInt("position");
        mediaPlayer.seekTo(pos);

        reset.setEnabled(resett);
        start.setEnabled(startt);
        plusOnePointHome.setEnabled(plusOnePointHomeE);
        plusOnePointGuest.setEnabled(plusOnePointGuestT);

        simpleChronometer.setBase(SystemClock.elapsedRealtime() + savedInstanceState.getLong("timer", 0));

        if(startt){simpleChronometer.setBase(SystemClock.elapsedRealtime()); simpleChronometer.stop();}
        else{simpleChronometer.start();}

    }

    /**
     * This method is called when the +1 Point Home button is clicked.
     */

    public void addHomePoints(View view) {
        home_points++;
        displayHomePoints(home_points);
        if (home_points >= 25 && home_points - guest_points > 1) {
            displayHomePoints(home_points);
            ++setHome;
            displaySetHome(setHome);
            home_points = 0;
            guest_points = 0;
            displayHomePoints(home_points);
            displayGuestPoints(guest_points);
        }
    }

    /**
     * This method is called when the +1 Point Guest button is clicked.
     */

    public void addGuestPoints(View view) {
        guest_points++;
        displayGuestPoints(guest_points);

        if (guest_points >= 25 && guest_points - home_points > 1) {
            displayGuestPoints(guest_points);
            ++setGuest;
            displaySetGuest(setGuest);
            guest_points = 0;
            home_points = 0;
            displayGuestPoints(guest_points);
            displayHomePoints(home_points);
        }
    }

    /**
     * Display home points.
     */

    public void displayHomePoints(int home_points) {
        homeScore.setText(String.valueOf(home_points));
    }


    /**
     * Display guest points.
     */

    public void displayGuestPoints(int guest_points) {
        guestScore.setText(String.valueOf(guest_points));
    }


    /**
     * Display home sets.
     */

    public void displaySetHome(int setHome) {
        setPointsHome.setText(String.valueOf(setHome));
        if (setHome == 3){
            displayEndGameHome(getString(R.string.homeWin));
            plusOnePointHome.setEnabled(false);
            plusOnePointGuest.setEnabled(false);
            plusOnePointHomeE = false;
            plusOnePointGuestT = false;
            simpleChronometer.stop();



        }
    }



    /**
     * Display guest sets.
     */

    public void displaySetGuest(int setGuest) {
        setPointsGuest.setText(String.valueOf(setGuest));
        if (setGuest == 3){
            displayEndGameGuest(getString(R.string.guestWin));
            plusOnePointHome.setEnabled(false);
            plusOnePointGuest.setEnabled(false);
            plusOnePointHomeE = false;
            plusOnePointGuestT = false;
            simpleChronometer.stop();

        }

    }

    /**
     * Display home endGame.
     */

    public void displayEndGameHome(String a){
        endGame.setText(a);
    }


    /**
     * Display guest endGame.
     */
    public void displayEndGameGuest(String b){
        endGame.setText(b);
    }



}
