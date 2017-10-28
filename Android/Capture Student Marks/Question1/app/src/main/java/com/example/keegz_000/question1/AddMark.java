package com.example.keegz_000.question1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AddMark extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mark);
    }

    public void writeToFile(int studentNo, String name, String surname, String courseCode, String moduleCode, String markPer)
    {
        try{
            FileOutputStream fOut = openFileOutput("StudentRec.txt", MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);

            osw.write(studentNo + ",\n" + name + ",\n" + surname + ",\n" + courseCode + ",\n" + moduleCode + ",\n" + markPer + "\n"+"\n");
            osw.close();

            Toast.makeText(this, "File saved successfully!", Toast.LENGTH_LONG).show();
        } catch (IOException ioe) {
            Toast.makeText(this, "Save Unsuccessful!", Toast.LENGTH_SHORT).show();
            ioe.printStackTrace();
        }
    }

    public void SaveActivity(View v)
    {
        EditText studentNoEdt = (EditText) findViewById(R.id.edtStudentNumber);
        String studentNoEdtValue = studentNoEdt.getText().toString();
        int studentNum = Integer.parseInt(studentNoEdtValue);
        EditText nameEdt = (EditText) findViewById(R.id.edtName);
        EditText surnameEdt = (EditText) findViewById(R.id.edtSurname);
        EditText courseCodeEdt = (EditText) findViewById(R.id.edtCourse);
        EditText moduleCodeEdt = (EditText) findViewById(R.id.edtModule);
        EditText markPerEdt = (EditText) findViewById(R.id.edtMark);

        assert studentNoEdt != null;
        assert nameEdt != null;
        assert surnameEdt != null;
        assert courseCodeEdt != null;
        assert moduleCodeEdt != null;
        assert markPerEdt != null;
        writeToFile(studentNum, nameEdt.getText().toString(), surnameEdt.getText().toString(), courseCodeEdt.getText().toString(), moduleCodeEdt.getText().toString(), markPerEdt.getText().toString());

        Intent intent = new Intent(this, StudentMarks.class);
        startActivity(intent);

    }

    public void BackActivity(View v)
    {
        Intent intent = new Intent(this, StudentMarks.class);
        startActivity(intent);
    }
}
