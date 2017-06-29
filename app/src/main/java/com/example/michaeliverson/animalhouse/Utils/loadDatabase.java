package com.example.michaeliverson.animalhouse.Utils;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.content.ContentValues.TAG;


/**
 * Created by michaeliverson on 6/29/17.
 */

public class loadDatabase extends Thread {

    private FirebaseDatabase db;
    private DatabaseReference dbReference;
    private String ThreadName;
    private dbHelper _dbHelper;


    public loadDatabase(String name) {
        super(name);
        this.ThreadName = name;
    }

    @Override
    public void run() {
        super.run();
        try {
            loadAnimals();
        } catch (Exception ex) {
            Log.d(TAG, "run: " + ex.toString());
        }
    }

    private void loadAnimals() {
        final dbHelper db = this._dbHelper;
        this.db = FirebaseDatabase.getInstance();
        this.dbReference = this.db.getReference();
        this.dbReference.child("Animals");
        final ValueEventListener dbListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                    final Animal pet = snapShot.getValue(Animal.class);
                    String urlPicture = pet.getPicture();
                    final String soundUrl = pet.getSound();
                    final FirebaseStorage storage1 = FirebaseStorage.getInstance();
                    StorageReference picture = storage1.getReferenceFromUrl(urlPicture);
                    final long size = 2048 * 2048;
                    picture.getBytes(size).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            pet.setPictureData(bytes);
                            FirebaseStorage storage2 = FirebaseStorage.getInstance();
                            StorageReference sound = storage2.getReferenceFromUrl(soundUrl);
                            sound.getBytes(size).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {
                                    pet.setSoundData(bytes);
                                    db.saveAnimal(pet);
                                }
                            });
                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }


    public dbHelper get_dbHelper() {
        return _dbHelper;
    }

    public void set_dbHelper(dbHelper _dbHelper) {
        this._dbHelper = _dbHelper;
    }

}
