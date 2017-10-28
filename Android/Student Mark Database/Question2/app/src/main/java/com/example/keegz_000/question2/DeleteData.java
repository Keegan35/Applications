package com.example.keegz_000.question2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteData extends AppCompatActivity
{
    DBAdapter dbAdapter;
    EditText IdEdt;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data);

        dbAdapter = new DBAdapter(this);

        IdEdt = (EditText)findViewById(R.id.edtId);
        btnDelete = (Button)findViewById(R.id.btnDelete);

    }

    public void deleteDataRow(View v)
    {
        dbAdapter.open();

        Integer deletedRows = dbAdapter.deleteData(IdEdt.getText().toString());
        if(deletedRows > 0)
            Toast.makeText(DeleteData.this, "Delete Successful!", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(DeleteData.this, "Delete Unsuccessful!", Toast.LENGTH_LONG).show();

        dbAdapter.close();
    }

    public void BackHome(View v)
    {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
