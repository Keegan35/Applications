package com.example.keegz_000.question1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class StudentMarks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_marks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void AddActivity(View v)
    {
        Intent intent = new Intent(this, AddMark.class);
        startActivity(intent);
    }

    public void ViewActivity(View v)
    {
        Intent intent = new Intent(this, ViewMarks.class);
        startActivity(intent);
    }
}
