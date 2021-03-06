package com.example.michaeliverson.animalhouse;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michaeliverson.animalhouse.Utils.Animalss;
import com.example.michaeliverson.animalhouse.Utils.dbHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Animal extends AppCompatActivity {

    private ImageView ivAnimalImage;
    private TextView tvAnimalText;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        // Initialize the Textview
        this.tvAnimalText = (TextView)findViewById(R.id.tvanimalText);
        this.ivAnimalImage = (ImageView)findViewById(R.id.ivAnimalImage);

        Intent intent = getIntent();
        dbHelper db = new dbHelper(this);
        Animalss pet = db.getAnimal(intent.getStringExtra("ANIMAL"));

        byte[] imageData = pet.getPictureData();
        Bitmap bmp = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
        this.ivAnimalImage.setImageBitmap(Bitmap.createScaledBitmap(bmp, this.ivAnimalImage.getWidth(),
                this.ivAnimalImage.getHeight(), false));
        playMp3(pet.getSoundData());
    }


    private void playMp3(byte[] mp3SoundByteArray) {
        try {
            // create temp file that will hold byte array
            File tempMp3 = File.createTempFile("temp", "wav", getCacheDir());
            tempMp3.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tempMp3);
            fos.write(mp3SoundByteArray);
            fos.close();

            // resetting mediaplayer instance to evade problems
            mediaPlayer.reset();

            // In case you run into issues with threading consider new instance like:
            // MediaPlayer mediaPlayer = new MediaPlayer();

            // Tried passing path directly, but kept getting
            // "Prepare failed.: status=0x1"
            // so using file descriptor instead
            FileInputStream fis = new FileInputStream(tempMp3);
            mediaPlayer.setDataSource(fis.getFD());

            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException ex) {
            String s = ex.toString();
            ex.printStackTrace();
        }
    }
}
