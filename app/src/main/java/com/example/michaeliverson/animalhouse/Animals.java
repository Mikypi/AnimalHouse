package com.example.michaeliverson.animalhouse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.michaeliverson.animalhouse.Utils.dbHelper;

import java.util.ArrayList;

public class Animals extends AppCompatActivity {

    private ArrayList<Animal> Animals;
    private dbHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);

    }
}
