package com.example.michaeliverson.animalhouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.michaeliverson.animalhouse.Utils.Animalss;
import com.example.michaeliverson.animalhouse.Utils.dbHelper;

import java.util.ArrayList;

public class Categories extends AppCompatActivity {

    private ListView list;
    private  ArrayList<Animalss> animal;
    private String catagory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        dbHelper helper = new dbHelper(this);
        ArrayList<Animalss> animals = helper.getAnimalCategory();
        myListAdapter adapter=new myListAdapter(this, animals);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Animalss pet = (Animalss)adapterView.getItemAtPosition(i);
                String category = pet.getCategory();
                Intent intent = new Intent(Categories.this,Animals.class);
                intent.putExtra("CATEGORY",category);
                startActivity(intent);
            }
        });
    }
}
