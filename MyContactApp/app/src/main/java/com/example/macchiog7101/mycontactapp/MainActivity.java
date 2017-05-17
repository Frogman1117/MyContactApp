package com.example.macchiog7101.mycontactapp;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editName;
    EditText editAge;
    EditText editAddress;
    Button btnAddDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DataBaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_NAME);
        editAge = (EditText) findViewById(R.id.editText_AGE);
        editAddress = (EditText) findViewById(R.id.editText_ADDRESS);
    }

    public void addData(View v){
        boolean isInserted = myDb.insertData(editName.getText().toString(),editAge.getText().toString(),editAddress.getText().toString());

        if (isInserted == true){
            Log.d("MyContact", "Success inserting data");
        }
        else {
            Log.d("MyContact", "Failure inserting data");
        }
    }
    public void viewData(View v){
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0){
            showMessage("Error", "No data is found in the data base");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        for(res.moveToFirst();!res.isAfterLast();res.moveToNext()){
            for (int i =0;i<res.getColumnCount();i++){
                buffer.append(res.getString(i) + "   ");
            }
            buffer.append("\n");
        }
        showMessage("Data",buffer.toString());
        return;
    }
    private void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title).setMessage(message)
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }
}
