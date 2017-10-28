package com.example.keegz_000.question1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ViewMarks extends AppCompatActivity
{
    ListView ResultsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_marks);
        ResultsListView = (ListView) findViewById(R.id.lvReport);
        readFromFile();


    }

    private String readFromFile()
    {
        String ret = "";
        ArrayList<String> lines = new ArrayList<String>();

        try{
            InputStream inputStream = openFileInput("StudentRec.txt");

            if(inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null){
                    stringBuilder.append(receiveString);
                    lines.add(receiveString);
                }
                inputStream.close();

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.da_item ,lines);
                ResultsListView.setAdapter(adapter);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;

    }
}
