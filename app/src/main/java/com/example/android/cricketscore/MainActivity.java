/*
App Develped by : pk(spkforwork@gmail.com)
Credits:

All code snippets used in the app:

FeedbackUtils.java : http://blog.blundellapps.co.uk/tut-email-feedback-from-users-device-information/


*/

package com.example.android.cricketscore;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    int score = 0;
    int wickets = 0;
    int overs = 0;
    int balls = 0;
    String ScoreCard = "";
    Vibrator v;
    int vibrate_duration=42;

    public void resetAllVariables()
    {
        score=0;
        wickets=0;
        overs=0;
        balls=0;
        ScoreCard="";
    }


    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        LinearLayout reqscore_ll = (LinearLayout) findViewById(R.id.reqscore_ll);
        reqscore_ll.setVisibility(View.INVISIBLE);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
//                Intent intent = new Intent(this, SettingsActivity.class);
//                startActivity(intent);
                Toast.makeText(this, "This feature is under development :D",Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_help:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Toast.makeText(this, "This feature is under development :D",Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_about:

                Toast.makeText(this, "This feature is under development :D",Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_feedback:
                FeedbackUtils.askForFeedback(MainActivity.this);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(this,"Ran out of actions :D",Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);

        }
    }

    public void displayTeam(String team) {
        TextView scoreView = (TextView) findViewById(R.id.teamname_text_view);
        scoreView.setText(team);
    }

    public void displayScore(int score) {
        TextView scoreView = (TextView) findViewById(R.id.score_text_view);
        scoreView.setText(String.valueOf(score));
        calcRunRate();
    }

    public void displayRunRate(double runrate) {
        TextView scoreView = (TextView) findViewById(R.id.runrate_text_view);
        scoreView.setText(String.valueOf(df.format(runrate)));
    }

    public void displayWickets(int wickets) {
        TextView scoreView = (TextView) findViewById(R.id.wickets_text_view);
        scoreView.setText(String.valueOf(wickets));
    }

    public void displayOvers(int overs, int balls) {
        TextView scoreView = (TextView) findViewById(R.id.overs_text_view);
        scoreView.setText(String.valueOf(overs) + "." + String.valueOf(balls));
        calcRunRate();
    }

    public void displaySavedTeamAScore(int score, int wickets) {
        TextView scoreView = (TextView) findViewById(R.id.saved_teama_score);
        scoreView.setText(String.valueOf(score) + "/" + String.valueOf(wickets));
    }

    public void displaySavedTeamAOvers(int overs, int balls) {
        TextView scoreView = (TextView) findViewById(R.id.saved_teama_overs);
        scoreView.setText(String.valueOf(overs) + "." + String.valueOf(balls));
    }

    public void calcRunRate() {
        double runrate = 0;
        if (balls > 0 || overs > 0) {
            runrate = (score * 6.0) / (overs * 6.0 + balls);
        }
        displayRunRate(runrate);
    }

    public void addBall(View view) {
        balls = balls + 1;
        if (balls > 5) {
            overs = overs + 1;
            balls = 0;
        }
        if(balls==1){
            ScoreCard+="\n";
        }
        displayOvers(overs, balls);
    }

    public void subtractBall(View view) {
        if (balls != 0 || overs != 0) {
            ScoreCard += String.valueOf(overs) + "." + String.valueOf(balls) + "  " + "Correction : -1 Ball\n";
        }
        if (balls > 0) {
            balls = balls - 1;
        } else if (balls == 0) {
            if (overs > 0) {
                overs = overs - 1;
                balls = 5;
            }
        }
        displayOvers(overs, balls);
    }

    public void addDotBall(View view) {
        v.vibrate(vibrate_duration);
        addBall(view);
        ScoreCard += String.valueOf(overs) + "." + String.valueOf(balls) + "  " + "Dot Ball\n";
    }

    public void addOne(View view) {
        //starwars theme vibrate
//        v.vibrate(new long[]{0, 500, 110, 500, 110, 450, 110, 200, 110, 170, 40, 450, 110, 200, 110, 170, 40, 500}, -1);
        v.vibrate(vibrate_duration);
        score = score + 1;
        addBall(view);
        displayScore(score);
        ScoreCard += String.valueOf(overs) + "." + String.valueOf(balls) + "  " + "1 run\n";
    }

    public void addTwo(View view) {
        v.vibrate(vibrate_duration);
        score = score + 2;
        addBall(view);
        displayScore(score);
        ScoreCard += String.valueOf(overs) + "." + String.valueOf(balls) + "  " + "2 runs\n";
    }

    public void addThree(View view) {
        v.vibrate(vibrate_duration);
        score = score + 3;
        addBall(view);
        displayScore(score);
        ScoreCard += String.valueOf(overs) + "." + String.valueOf(balls) + "  " + "3 runs\n";
    }

    public void addFour(View view) {
        v.vibrate(vibrate_duration);
        score = score + 4;
        addBall(view);
        displayScore(score);
        ScoreCard += String.valueOf(overs) + "." + String.valueOf(balls) + "  " + "4 runs\n";
    }

    public void addFive(View view) {
        v.vibrate(vibrate_duration);
        score = score + 5;
        addBall(view);
        displayScore(score);
        ScoreCard += String.valueOf(overs) + "." + String.valueOf(balls) + "  " + "5 runs\n";
    }

    public void addSix(View view) {
        v.vibrate(vibrate_duration);
        score = score + 6;
        addBall(view);
        displayScore(score);
        ScoreCard += String.valueOf(overs) + "." + String.valueOf(balls) + "  " + "6 runs\n";
    }

    public void addExtra(View view) {
        v.vibrate(vibrate_duration);
        score = score + 1;
        displayScore(score);
        ScoreCard += String.valueOf(overs) + "." + String.valueOf(balls) + "  " + "1 extra\n";
    }

    public void subtractOne(View view) {
        v.vibrate(vibrate_duration);
        if (score > 0) {
            score = score - 1;
        }
        displayScore(score);
        ScoreCard += String.valueOf(overs) + "." + String.valueOf(balls) + "  " + "Correction : -1 run\n";
    }

    public void addWicket(View view) {
        v.vibrate(vibrate_duration);
        if (wickets == 9) {
            wickets = wickets + 1;
            addBall(view);
            saveandreset(view);
        } else if (wickets < 9) {
            wickets = wickets + 1;
            addBall(view);
            displayWickets(wickets);
            ScoreCard += String.valueOf(overs) + "." + String.valueOf(balls) + "  " + "Wicket\n";
        }


    }

    public void removeWicket(View view) {
        v.vibrate(vibrate_duration);
        if (wickets > 0) {
            wickets = wickets - 1;
            subtractBall(view);
            displayWickets(wickets);
            ScoreCard += String.valueOf(overs) + "." + String.valueOf(balls) + "  " + "Correction : -1 Wicket\n";
        }
    }

    public void viewScoreCard(View v) {
        Intent i = new Intent(this, ScoreCardActivity.class);
        i.putExtra("ScoreCard", ScoreCard);
        startActivity(i);
    }

    public void saveandreset(View view) {
        v.vibrate(vibrate_duration);
        LinearLayout reqscore_ll = (LinearLayout) findViewById(R.id.reqscore_ll);
        reqscore_ll.setVisibility(View.VISIBLE);
        displaySavedTeamAScore(score, wickets);
        displaySavedTeamAOvers(overs, balls);
        resetAllVariables();
        displayWickets(0);
        displayScore(0);
        displayOvers(0, 0);
        displayTeam("TEAM B");
        displayRunRate(0);
    }

    public void reset(View view) {
        v.vibrate(vibrate_duration);
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        resetAllVariables();
                        displayWickets(0);
                        displayScore(0);
                        displayOvers(0,0);
                        displaySavedTeamAScore(0, 0);
                        displaySavedTeamAOvers(0, 0);
                        displayTeam("TEAM A");
                        LinearLayout reqscore_ll = (LinearLayout) findViewById(R.id.reqscore_ll);
                        reqscore_ll.setVisibility(View.INVISIBLE);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to reset?").setMessage("This will clear all the existing data.").setPositiveButton("RESET", dialogClickListener)
                .setNegativeButton("CANCEL", dialogClickListener).show();


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Toast.makeText(MainActivity.this, "Please send your feedback", Toast.LENGTH_LONG).show();

            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            finish();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                        case DialogInterface.BUTTON_NEUTRAL:

                            FeedbackUtils.askForFeedback(MainActivity.this);
                            break;
                    }
                }
            };



            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are you sure you want to exit?").setMessage("This will clear all the existing score data. If you want your data, just press Home button to Exit.").setPositiveButton("EXIT", dialogClickListener)
                    .setNegativeButton("CANCEL", dialogClickListener).setNeutralButton("SEND FEEDBACK", dialogClickListener).show();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
