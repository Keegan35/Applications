package com.example.keegz_000.question2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewData extends AppCompatActivity
{
    DBAdapter dbAdapter;
    Button btnViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        dbAdapter = new DBAdapter(this);

        btnViewAll = (Button)findViewById(R.id.btnViewData);
    }

    public void viewRecords(View v)
    {
        dbAdapter.open();

        Cursor res = dbAdapter.getAllData();
        if(res.getCount() == 0)
        {
            showMessage("Error", "Nothing found!");
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext())
        {
            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("Student Number :" + res.getString(1) + "\n");
            buffer.append("Name :" + res.getString(2) + "\n");
            buffer.append("Surname :" + res.getString(3) + "\n");
            buffer.append("Course Code :" + res.getString(4) + "\n");
            buffer.append("Module Code :" + res.getString(5) + "\n");
            buffer.append("Mark :" + res.getString(6) + "\n");
        }

        //Show all data
        showMessage("Data", buffer.toString());

        dbAdapter.close();
    }

    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void BackMenu(View v) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
