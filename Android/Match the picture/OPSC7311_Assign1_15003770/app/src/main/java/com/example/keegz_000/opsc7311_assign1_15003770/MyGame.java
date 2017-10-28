package com.example.keegz_000.opsc7311_assign1_15003770;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyGame extends AppCompatActivity
{

    private Button button4x4;
    private Button button5x6;
    private Button button6x6;
    private Button button8x5;
    private TextView textvHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_game);

        //Code to change from screen to screen
        button4x4 = (Button)findViewById(R.id.button_4x4_game);

        button4x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MyGame.this, Game4x4Easy.class);
                startActivity(intent);
            }
        });

        button5x6 = (Button)findViewById(R.id.button_5x6_game);

        button5x6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MyGame.this, Game5x6Medium.class);
                startActivity(intent);
            }
        });

        button6x6 = (Button)findViewById(R.id.button_6x6_game);

        button6x6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MyGame.this, Game6x6Hard.class);
                startActivity(intent);
            }
        });

        button8x5 = (Button)findViewById(R.id.button_8x5_game);

        button8x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MyGame.this, Game8x5VeryHard.class);
                startActivity(intent);
            }
        });

        textvHelp = (TextView)findViewById(R.id.tvHelp);

        textvHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MyGame.this, Help.class);
                startActivity(intent);
            }
        });


    }
}
