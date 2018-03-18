package com.example.ioana.volleyscoreapp;

import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int home_points;
    int guest_points;
    int setHome;
    int setGuest;
    int ace_PointsH;
    int kill_PointsH;
    int block_PointsH;
    int ace_PointsG;
    int kill_PointsG;
    int block_PointsG;
    long time;
    boolean startt;
    boolean resett;
    boolean plusOnePointHomeE;
    boolean plusOnePointGuestT;
    TextView homeScore;
    TextView guestScore;
    TextView setPointsHome;
    TextView setPointsGuest;
    TextView endGame;
    TextView aceScoreHome;
    TextView aceScoreGuest;
    TextView killScoreHome;
    TextView killScoreGuest;
    TextView blockScoreHome;
    TextView blockScoreGuest;
    Chronometer simpleChronometer;
    Button start;
    Button reset;
    Button plusOnePointHome;
    Button plusOnePointGuest;
    Button aceH;
    Button killH;
    Button blockH;
    Button aceG;
    Button killG;
    Button blockG;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initiate views
        plusOnePointHome = (Button) findViewById(R.id.plusOnePointHome);
        plusOnePointGuest = (Button) findViewById(R.id.plusOnePointGuest);
        aceH = (Button) findViewById(R.id.aceH);
        killH = (Button) findViewById(R.id.killH);
        blockH = (Button) findViewById(R.id.blockH);
        aceG = (Button) findViewById(R.id.aceG);
        killG = (Button) findViewById(R.id.killG);
        blockG = (Button) findViewById(R.id.blockG);
        simpleChronometer = (Chronometer) findViewById(R.id.clockTimer);
        aceScoreHome = (TextView) findViewById(R.id.aceScoreHome);
        killScoreHome = (TextView) findViewById(R.id.killScoreHome);
        blockScoreHome = (TextView) findViewById(R.id.blockScoreHome);
        aceScoreGuest = (TextView) findViewById(R.id.aceScoreGuest);
        killScoreGuest = (TextView) findViewById(R.id.killScoreGuest);
        blockScoreGuest = (TextView) findViewById(R.id.blockScoreGuest);
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
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.music1);
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

        //Perform click  event on restart button to set the base time on chronometer

        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                //  time =simpleChronometer.getBase()-SystemClock.elapsedRealtime();
                simpleChronometer.stop();
                simpleChronometer.setBase(SystemClock.elapsedRealtime());
                displayHomePoints(0);
                displayGuestPoints(0);
                home_points = 0;
                guest_points = 0;
                setHome = 0;
                setGuest = 0;
                ace_PointsH = 0;
                kill_PointsH = 0;
                block_PointsH = 0;
                ace_PointsG = 0;
                kill_PointsG = 0;
                block_PointsG = 0;
                displayAcePointsGuest(0);
                displayAcePointsHome(0);
                displayKillPointsGuest(0);
                displayKillPointsHome(0);
                displayBlockPointsGuest(0);
                displayBlockPointsHome(0);
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


    // //This method is called when the user presses the Back button.

    @Override
    public void onBackPressed() {
        mediaPlayer.release();
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save myVar's value in saveInstanceState bundle
        outState.putString(Constants.varHome, endGame.getText().toString());
        outState.putString(Constants.varGuest, endGame.getText().toString());
        time = simpleChronometer.getBase() - SystemClock.elapsedRealtime();
        simpleChronometer.stop();
        outState.putLong(Constants.timer, time);
        outState.putInt(Constants.variableHomePoints, home_points);
        outState.putInt(Constants.variableGuestPoints, guest_points);
        outState.putInt(Constants.variableSetHome, setHome);
        outState.putInt(Constants.variableSetGuest, setGuest);
        outState.putInt(Constants.variableHomePointsAce, ace_PointsH);
        outState.putInt(Constants.variableGuestPointsAce, ace_PointsG);
        outState.putInt(Constants.variableHomePointsKill, kill_PointsH);
        outState.putInt(Constants.variableGuestPointsKill, kill_PointsG);
        outState.putInt(Constants.variableHomePointsBlock, block_PointsH);
        outState.putInt(Constants.variableGuestPointsBlock, block_PointsG);
        outState.putInt(Constants.position, mediaPlayer.getCurrentPosition());
        mediaPlayer.pause();
        outState.putBoolean(Constants.start, startt);
        outState.putBoolean(Constants.reset, resett);
        outState.putBoolean(Constants.plusHomePoints, plusOnePointHomeE);
        outState.putBoolean(Constants.plusGuestPoints, plusOnePointGuestT);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        endGame.setText(savedInstanceState.getString(Constants.varHome));
        endGame.setText(savedInstanceState.getString(Constants.varGuest));
        home_points = savedInstanceState.getInt(Constants.variableHomePoints);
        guest_points = savedInstanceState.getInt(Constants.variableGuestPoints);
        setHome = savedInstanceState.getInt(Constants.variableSetHome);
        setGuest = savedInstanceState.getInt(Constants.variableSetGuest);
        homeScore.setText(String.valueOf(home_points));
        guestScore.setText(String.valueOf(guest_points));
        setPointsHome.setText(String.valueOf(setHome));
        setPointsGuest.setText(String.valueOf(setGuest));
        startt = savedInstanceState.getBoolean(Constants.start);
        resett = savedInstanceState.getBoolean(Constants.reset);
        plusOnePointHomeE = savedInstanceState.getBoolean(Constants.plusHomePoints, plusOnePointHomeE);
        plusOnePointGuestT = savedInstanceState.getBoolean(Constants.plusGuestPoints, plusOnePointGuestT);
        ace_PointsH = savedInstanceState.getInt(Constants.variableHomePointsAce);
        ace_PointsG = savedInstanceState.getInt(Constants.variableGuestPointsAce);
        kill_PointsH = savedInstanceState.getInt(Constants.variableHomePointsKill);
        kill_PointsG = savedInstanceState.getInt(Constants.variableGuestPointsKill);
        block_PointsH = savedInstanceState.getInt(Constants.variableHomePointsBlock);
        block_PointsG = savedInstanceState.getInt(Constants.variableGuestPointsBlock);
        aceScoreHome.setText(String.valueOf(ace_PointsH));
        aceScoreGuest.setText(String.valueOf(ace_PointsG));
        killScoreHome.setText(String.valueOf(kill_PointsH));
        killScoreGuest.setText(String.valueOf(kill_PointsG));
        blockScoreHome.setText(String.valueOf(block_PointsH));
        blockScoreGuest.setText(String.valueOf(block_PointsG));
        int pos = savedInstanceState.getInt(Constants.position);
        mediaPlayer.seekTo(pos);
        reset.setEnabled(resett);
        start.setEnabled(startt);
        plusOnePointHome.setEnabled(plusOnePointHomeE);
        plusOnePointGuest.setEnabled(plusOnePointGuestT);
        simpleChronometer.setBase(SystemClock.elapsedRealtime() + savedInstanceState.getLong(Constants.timer, 0));
        if (startt) {
            simpleChronometer.setBase(SystemClock.elapsedRealtime());
            simpleChronometer.stop();
        } else {
            simpleChronometer.start();
        }
    }

    //This method is called when the +1 Point Home button is clicked.

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

    //This method is called when the +1 Point Guest button is clicked.

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

    //Display home points.

    public void displayHomePoints(int home_points) {
        homeScore.setText(String.valueOf(home_points));
    }

    //Display guest points.

    public void displayGuestPoints(int guest_points) {
        guestScore.setText(String.valueOf(guest_points));
    }

    //Display home sets.

    public void displaySetHome(int setHome) {
        setPointsHome.setText(String.valueOf(setHome));
        if (setHome == 3) {
            displayEndGameHome(getString(R.string.homeWin));
            plusOnePointHome.setEnabled(false);
            plusOnePointGuest.setEnabled(false);
            plusOnePointHomeE = false;
            plusOnePointGuestT = false;
            simpleChronometer.stop();
        }
    }

    //Display guest sets.

    public void displaySetGuest(int setGuest) {
        setPointsGuest.setText(String.valueOf(setGuest));
        if (setGuest == 3) {
            displayEndGameGuest(getString(R.string.guestWin));
            plusOnePointHome.setEnabled(false);
            plusOnePointGuest.setEnabled(false);
            plusOnePointHomeE = false;
            plusOnePointGuestT = false;
            simpleChronometer.stop();
        }

    }

    //Display home endGame.

    public void displayEndGameHome(String a) {
        endGame.setText(a);
    }

    //Display guest endGame.

    public void displayEndGameGuest(String b) {
        endGame.setText(b);
    }
    //Add ace point for home;

    public void acePointHome(View view) {
        if (ace_PointsH == 100) {
            //show  message as a toast
            Toast.makeText(this, "You cannot have more than 100 ace", Toast.LENGTH_LONG).show();
            return;
        }
        ace_PointsH++;
        displayAcePointsHome(ace_PointsH);
    }

    //Add kill point for home;

    public void killPointHome(View view) {
        if (kill_PointsH == 100) {
            //show  message as a toast
            Toast.makeText(this, "You cannot have more than 100 kill", Toast.LENGTH_LONG).show();
            return;
        }
        kill_PointsH++;
        displayKillPointsHome(kill_PointsH);
    }

    //Add block point for home;

    public void blockPointHome(View view) {
        if (block_PointsH == 100) {
            //show  message as a toast
            Toast.makeText(this, "You cannot have more than 100 block", Toast.LENGTH_LONG).show();
            return;
        }
        block_PointsH++;
        displayBlockPointsHome(block_PointsH);
    }

    //Display ace points for home team.

    public void displayAcePointsHome(int ace_PointsH) {
        aceScoreHome.setText(String.valueOf(ace_PointsH));
    }

    //Display kill points for home team.

    public void displayKillPointsHome(int kill_PointsH) {
        killScoreHome.setText(String.valueOf(kill_PointsH));
    }

    //Display block points for home team.

    public void displayBlockPointsHome(int block_PointsH) {
        blockScoreHome.setText(String.valueOf(block_PointsH));
    }

    //Add ace point for guest;

    public void acePointGuest(View view) {
        if (ace_PointsG == 100) {
            //show  message as a toast
            Toast.makeText(this, "You cannot have more than 100 ace", Toast.LENGTH_LONG).show();
            return;
        }
        ace_PointsG++;
        displayAcePointsGuest(ace_PointsG);
    }

    //Add kill point for guest;

    public void killPointGuest(View view) {
        if (kill_PointsG == 100) {
            //show  message as a toast
            Toast.makeText(this, "You cannot have more than 100 kill", Toast.LENGTH_LONG).show();
            return;
        }
        kill_PointsG++;
        displayKillPointsGuest(kill_PointsG);
    }

    //Add block point for guest;

    public void blockPointGuest(View view) {
        if (block_PointsG == 100) {
            //show  message as a toast
            Toast.makeText(this, "You cannot have more than 100 block", Toast.LENGTH_LONG).show();
            return;
        }
        block_PointsG++;
        displayBlockPointsGuest(block_PointsG);
    }

    //Display ace points for guest team.

    public void displayAcePointsGuest(int ace_PointsG) {
        aceScoreGuest.setText(String.valueOf(ace_PointsG));
    }

    //Display kill points for guest team.

    public void displayKillPointsGuest(int kill_PointsG) {
        killScoreGuest.setText(String.valueOf(kill_PointsG));
    }

    //Display block points for home team.

    public void displayBlockPointsGuest(int block_PointsG) {
        blockScoreGuest.setText(String.valueOf(block_PointsG));
    }

    //Constants class for the strings.

    public class Constants {

        public static final String varHome = "myVarEndHomeGame";
        public static final String varGuest = "myVarEndGuestGame";
        public static final String variableHomePoints = "varHomePoints";
        public static final String variableGuestPoints = "varGuestPoints";
        public static final String timer = "timer";
        public static final String variableSetHome = "varSetHome";
        public static final String variableSetGuest = "varSetGuest";
        public static final String position = "position";
        public static final String start = "start";
        public static final String reset = "reset";
        public static final String plusHomePoints = "plusOnePointHomeE";
        public static final String plusGuestPoints = "plusOnePointGuestT";
        public static final String variableHomePointsAce = "variableHomePointsAce";
        public static final String variableGuestPointsAce = "variableGuestPointsAce";
        public static final String variableHomePointsKill = "variableHomePointsKill";
        public static final String variableGuestPointsKill = "variableGuestPointsKill";
        public static final String variableHomePointsBlock = "variableHomePointsBlock";
        public static final String variableGuestPointsBlock = "variableGuestPointsBlock";
    }
}
