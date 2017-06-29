package com.example.michaeliverson.animalhouse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.michaeliverson.animalhouse.Utils.dbHelper;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private dbHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialations

        if(!checkDatabase())
        {
            dbHelper helper = new dbHelper(this);
            FirebaseDatabase db = FirebaseDatabase.getInstance();

        }

    }

    public void onEnter(View view) {

    }


    // Utilites
    public boolean checkDatabase()
    {
        return (this.helper.numberOfRecords() >0) ? true:false;
    }
}
