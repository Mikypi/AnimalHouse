package com.example.michaeliverson.animalhouse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.michaeliverson.animalhouse.Utils.Animal;
import com.example.michaeliverson.animalhouse.Utils.dbHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
            DatabaseReference refernce = db.getReference().child("Animals");

            refernce.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot child: dataSnapshot.getChildren())
                    {
                        Animal animal = child.getValue(Animal.class);
                        StorageReference storageRef = storage.getReference();

                        StorageReference gsReference = Stor.getReferenceFromUrl(animal.getPicture());


                    }
                    myListAdapter adapter=new myListAdapter(this, itemname, imgid);
                    list=(ListView)findViewById(R.id.list);
                    list.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            })

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
