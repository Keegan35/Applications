package com.example.keegz_000.question2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentMarks extends AppCompatActivity
{
    DBAdapter dbAdapter;
    EditText studentNumEdt;
    EditText nameEdt;
    EditText surnameEdt;
    EditText courseEdt;
    EditText moduleEdt;
    EditText marksEdt;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_marks);

        dbAdapter = new DBAdapter(this);

        studentNumEdt = (EditText) findViewById(R.id.edtStudentNumber);
        nameEdt = (EditText) findViewById(R.id.edtName);
        surnameEdt = (EditText) findViewById(R.id.edtSurname);
        courseEdt = (EditText) findViewById(R.id.edtCourse);
        moduleEdt = (EditText) findViewById(R.id.edtModule);
        marksEdt = (EditText) findViewById(R.id.edtMark);
        btnAdd = (Button) findViewById(R.id.btnDelete);


    }

    public void AddData(View v)
    {
        dbAdapter.open();
        if (dbAdapter.insertData(studentNumEdt.getText().toString(), nameEdt.getText().toString(), surnameEdt.getText().toString(), courseEdt.getText().toString(), moduleEdt.getText().toString(), marksEdt.getText().toString()))
        {
            Toast.makeText(this, "Add Successful.", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Add Unsuccessful.", Toast.LENGTH_LONG).show();
        }

        dbAdapter.close();
    }

    public void BackPage(View v)
    {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
