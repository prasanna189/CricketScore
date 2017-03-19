package com.example.android.cricketscore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    int score=0;
    int wickets=0;
    int overs=0;
    int balls=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        scoreView.setText(String.valueOf(runrate));
    }

    public void displayWickets(int wickets) {
        TextView scoreView = (TextView) findViewById(R.id.wickets_text_view);
        scoreView.setText(String.valueOf(wickets));
    }

    public void displayOvers(int overs, int balls) {
        TextView scoreView = (TextView) findViewById(R.id.overs_text_view);
        scoreView.setText(String.valueOf(overs)+"."+String.valueOf(balls));
        calcRunRate();
    }

    public void displaySavedTeamAScore(int score, int wickets) {
        TextView scoreView = (TextView) findViewById(R.id.saved_teama_score);
        scoreView.setText(String.valueOf(score)+"/"+String.valueOf(wickets));
    }

    public void displaySavedTeamAOvers(int overs, int balls) {
        TextView scoreView = (TextView) findViewById(R.id.saved_teama_overs);
        scoreView.setText(String.valueOf(overs)+"."+String.valueOf(balls));
    }

    public void calcRunRate(){
        double runrate;
        runrate = ( score * 6.0 ) / (overs * 6.0 + balls) ;
        displayRunRate(runrate);
    }

    public void addBall(View view){
        balls=balls+1;
        if(balls>5)
        {
            overs=overs+1;
            balls=0;
        }
        displayOvers(overs, balls);
    }

    public void subtractBall(View view){
        if(balls>0) {
            balls=balls-1;
        }
        else if (balls == 0){
            if(overs > 0){
                overs=overs-1;
                balls=5;
            }
        }
        displayOvers(overs, balls);
    }


    public void addOne(View view){
        score=score+1;
        addBall(view);
        displayScore(score);
    }

    public void addTwo(View view){
        score=score+2;
        addBall(view);
        displayScore(score);
    }

    public void addThree(View view){
        score=score+3;
        addBall(view);
        displayScore(score);
    }

    public void addFour(View view){
        score=score+4;
        addBall(view);
        displayScore(score);
    }

    public void addFive(View view){
        score=score+5;
        addBall(view);
        displayScore(score);
    }

    public void addSix(View view){
        score=score+6;
        addBall(view);
        displayScore(score);
    }

    public void addExtra(View view){
        score=score+1;
        displayScore(score);
    }

    public void subtractOne(View view){
        if(score>0){
            score=score-1;
        }
        subtractBall(view);
        displayScore(score);
    }

    public void addWicket(View view){
        wickets=wickets+1;
        addBall(view);
        displayWickets(wickets);
    }

    public void removeWicket(View view){
        if(wickets > 0){
            wickets=wickets-1;
        }
        subtractBall(view);
        displayWickets(wickets);
    }

    public void saveandreset(View view){

        displaySavedTeamAScore(score, wickets);
        displaySavedTeamAOvers(overs, balls);
        score=0;
        wickets=0;
        overs=0;
        balls=0;
        displayWickets(0);
        displayScore(0);
        displayOvers(0,0);
        displayTeam("TEAM B");
    }

    public void reset(View view){
        score=0;
        wickets=0;
        overs=0;
        balls=0;
        displayWickets(0);
        displayScore(0);
        displayOvers(0,0);
        displaySavedTeamAScore(0, 0);
        displaySavedTeamAOvers(0, 0);
        displayTeam("TEAM A");
    }

}
