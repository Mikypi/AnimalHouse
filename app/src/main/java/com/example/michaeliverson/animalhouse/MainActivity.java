package com.example.michaeliverson.animalhouse;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.michaeliverson.animalhouse.Utils.dbHelper;
import com.example.michaeliverson.animalhouse.Utils.loadDatabase;

public class MainActivity extends AppCompatActivity {

    private dbHelper helper;
    private loadDatabase load;
    private ProgressBar spinner;
    private Boolean moveForward;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.spinner = (ProgressBar)findViewById(R.id.progressBar1);

        // initialations
        dbHelper helper = new dbHelper(this);
        helper.getWritableDatabase();
        this.moveForward = false;

        if(!checkDatabase())
        {
            this.load = new loadDatabase(this.helper);
            this.load.start();
            this.spinner.setVisibility(View.VISIBLE);
            Handler handle = new Handler(){

                @SuppressWarnings("unchecked")
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    String message = (String)msg.obj;

                    if (message.equals("1"))
                        moveForward = true;
                    else
                        moveForward = false;
                }
            };
        }else
            this.moveForward = true;

        if (this.moveForward)
        {
            this.spinner.setVisibility(View.GONE);
        }else
        {
            this.spinner.setVisibility(View.GONE);
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("There is Currently No Data To Display");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            finish();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

    }

    // On Click

    public void onSendMessage(View view) {

        if (this.moveForward)
        {
            Intent intent = new Intent(MainActivity.this, TextMessage.class);
            startActivity(intent);
        }
    }

    public void onEnterZoo(View view) {

        if(this.moveForward)
        {
            Intent intent = new Intent(MainActivity.this,Categories.class);
            startActivity(intent);
        }

    }

    public void onPDF(View view){

        if (this.moveForward) {
            Intent intent = new Intent(MainActivity.this, pdflist.class);
            startActivity(intent);
        }
    }

    public void onStopWatch(View view) {

        if (this.moveForward)
        {
            Intent intent = new Intent(MainActivity.this,StopWatch.class);
            startActivity(intent);
        }
    }

    // Utilites
    public boolean checkDatabase()
    {
        return (this.helper.numberOfRecords() >0);
    }

}
