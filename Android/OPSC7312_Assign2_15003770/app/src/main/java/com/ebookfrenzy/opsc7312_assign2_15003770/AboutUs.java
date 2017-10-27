//15003770
//Keegan Scannell
package com.ebookfrenzy.opsc7312_assign2_15003770;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class AboutUs extends AppCompatActivity {

    //Declared Variables
    TextToSpeech textToSpeech;
    TextView about, textTo;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        about = (TextView) findViewById(R.id.tvUs);
        back = (Button) findViewById(R.id.btnback);
        textTo = (Button) findViewById(R.id.btnTextToSpeech);

        //Code to convert text to speech
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener(){
            public void onInit(int status){
                if(status != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        textTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = about.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        //Back to the Main page
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(AboutUs.this, MainNav.class);
                startActivity(activity);
            }
        });
    }


    public void onPause(){
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }
}
