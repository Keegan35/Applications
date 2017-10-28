//15003770
//Keegan Scannell
package com.ebookfrenzy.opsc7312_assign2_15003770;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class Timer extends AppCompatActivity {

    TextView TVPayment, TVAmount;
    Chronometer chronometer;
    Button start, stop, back;
    int amount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        start = (Button) findViewById(R.id.btnStart);
        stop = (Button) findViewById(R.id.btnStop);
        back = (Button) findViewById(R.id.btnBack);
        chronometer = (Chronometer) findViewById(R.id.chrTimer);
        TVPayment = (TextView) findViewById(R.id.tvPayment);
        TVAmount = (TextView) findViewById(R.id.tvAmount);

        //Starts the timer
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.start();

            }
        });

        //Stops the timer
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                showElapsedTime();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(Timer.this, MainNav.class);
                startActivity(activity);
            }
        });
    }

    //Displays the final time in minutes
    private void showElapsedTime(){
        //int amount = 0;
        long elapsedMillis = chronometer.getBase() - SystemClock.elapsedRealtime();
        int elapsedSeconds = (int) (elapsedMillis / 1000);
        int elapsedMinutes = elapsedSeconds / 60;
        //int display = elapsedSeconds - (elapsedMinutes * 60);
        TVPayment.setText("Your Child has been here for: " + Math.abs(elapsedMinutes) + " minutes.");


        if (elapsedMinutes < 60){
            amount = 50;
            TVAmount.setText("An amount of R" + amount + " is owed.");
        }
        else if(elapsedMinutes > 60 || elapsedMinutes < 120){
            amount = 100;
            TVAmount.setText("An amount of R" + amount + " is owed.");
        }
        else{
            amount = 150;
            TVAmount.setText("An amount of R" + amount + " is owed.");
        }
    }
}
