//15003770
//Keegan Scannell
package com.ebookfrenzy.opsc7312_assign2_15003770;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SignIn extends AppCompatActivity {

    //Declare Variables
    TextView TVClick;
    EditText mChildName, mChildAge, mParentName, mParentNo, mTime;
    ImageView IVChild, IVParent;
    private Button btnChild, btnParent;
    private Uri file;


    //Firebase reference
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        TVClick = (TextView) findViewById(R.id.tvClick);
        mChildName = (EditText) findViewById(R.id.edtCName);
        mChildAge = (EditText) findViewById(R.id.edtAge);
        mParentName = (EditText) findViewById(R.id.edtParent);
        mParentNo = (EditText) findViewById(R.id.edtNo);
        mTime = (EditText) findViewById(R.id.edtTime);
        IVChild = (ImageView) findViewById(R.id.ivChild);
        IVParent = (ImageView) findViewById(R.id.ivParent);
        btnChild = (Button) findViewById(R.id.btnChildPic);
        btnParent = (Button) findViewById(R.id.btnParentPic);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Details");

        TVClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, TC.class);
                startActivity(intent);
                AddData();
            }
        });


        btnChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(intent, 100);
                }

            }
        });

        btnParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent1.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(intent1, 101);
                }

            }
        });


        //Permissions to be granted
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            btnChild.setEnabled(false);
            btnParent.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                btnChild.setEnabled(true);
                btnParent.setEnabled(true);
            }
        }
    }

    //Saves image to your gallery otherwise it will save to the firebase database
    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "P & C");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("P & C", "Failed to create directory");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator + "img_" + timeStamp + ".jpg");
    }

    //To save the image to the ImageView and display it
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                IVChild.setImageBitmap(bitmap);
            }
        }
        if (requestCode == 101){
            if (resultCode == RESULT_OK) {
                Bitmap bitmap1 = (Bitmap) data.getExtras().get("data");
                IVParent.setImageBitmap(bitmap1);
            }
        }
        Toast.makeText(getApplicationContext(), "Your Image has been saved in P & C Gallery", Toast.LENGTH_SHORT).show();
    }


    //Add data to the database
    public void AddData(){
        String ChildName = mChildName.getText().toString().trim();
        String ChildAge = mChildAge.getText().toString().trim();
        String ParentName = mParentName.getText().toString().trim();
        String ParentNo = mParentNo.getText().toString().trim();
        String Time = mTime.getText().toString().trim();

        Details details = new Details(ChildName, ChildAge, ParentName, ParentNo, Time);

        databaseReference.setValue(details);
    }
}
