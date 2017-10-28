package com.example.keegz_000.question2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity
{
    DBAdapter dbAdapter;
    EditText IdEdt;
    EditText studentNumEdt;
    EditText nameEdt;
    EditText surnameEdt;
    EditText courseEdt;
    EditText moduleEdt;
    EditText marksEdt;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        dbAdapter = new DBAdapter(this);

        IdEdt = (EditText)findViewById(R.id.edtId);
        studentNumEdt = (EditText) findViewById(R.id.edtStudentNumber);
        nameEdt = (EditText) findViewById(R.id.edtName);
        surnameEdt = (EditText) findViewById(R.id.edtSurname);
        courseEdt = (EditText) findViewById(R.id.edtCourse);
        moduleEdt = (EditText) findViewById(R.id.edtModule);
        marksEdt = (EditText) findViewById(R.id.edtMark);
        btnUpdate = (Button)findViewById(R.id.btnDelete);

    }

    public void updateRecord(View v)
    {
        dbAdapter.open();

        boolean isUpdate = dbAdapter.updateData(IdEdt.getText().toString(), studentNumEdt.getText().toString(),
                nameEdt.getText().toString(), surnameEdt.getText().toString(), courseEdt.getText().toString(),
                moduleEdt.getText().toString(), marksEdt.getText().toString());
        if(isUpdate == true)
            Toast.makeText(UpdateData.this, "Update Successful!", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(UpdateData.this, "Update Unsuccessful!", Toast.LENGTH_LONG).show();

        dbAdapter.close();
    }

    public void Back(View v) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
