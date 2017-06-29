package com.example.michaeliverson.animalhouse.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by michaeliverson on 6/27/17.
 */

public class dbHelper extends SQLiteOpenHelper {

    private static final String NAME = "IVYDB";
    private static final int VERSION = 1;

    private static final String TABLE_NAME = "ANIMALS";
    private static final String ANIMAL_TYPE = "animal";
    private static final String ANIMAL_DESCIPTION = "description";
    private static final String ANIMAL_CATEGORY = "category";
    private static final String ANIMAL_PICTURE = "picture";
    private static final String ANIMAL_SOUND = "sound";
    private static final String ANIMAL_PICTUREDATA = "picturedata";
    private static final String ANIMAL_SOUNDDATA = "sounddata";

    public dbHelper(Context context)
    {
        super(context, NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE" +TABLE_NAME+ "(" +
                ANIMAL_TYPE+ "TEXT PRIMARY KEY," +
                ANIMAL_DESCIPTION + "TEXT," +
                ANIMAL_CATEGORY  + "TEXT," +
                ANIMAL_PICTUREDATA +"BLOB," +
                ANIMAL_SOUNDDATA + "BLOB" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void saveAnimal (Animal Animal)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ANIMAL_TYPE, Animal.getAnimal());
            contentValues.put(ANIMAL_DESCIPTION, Animal.getDescription());
            contentValues.put(ANIMAL_CATEGORY, Animal.getCategory());
            contentValues.put(ANIMAL_SOUNDDATA,Animal.getSoundData());
            contentValues.put(ANIMAL_PICTUREDATA, Animal.getPicture());
            database.insert(TABLE_NAME, null, contentValues);

        }catch (Exception ex)
        {
            Log.d(TAG,ex.toString());
        }finally {
            database.close();
        }

    }
    public ArrayList<Animal> getAnimals()
    {
        String query ="SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        ArrayList<Animal> Animals = new ArrayList<>();

        try {
            do {
                Animal pets = new Animal();
                pets.setAnimal(cursor.getString(0));
                pets.setDescription(cursor.getString(1));
                pets.setCategory(cursor.getString(2));
                pets.setPictureData(cursor.getBlob(3));
                pets.setSoundData(cursor.getBlob(4));
                Animals.add(pets);
            } while (cursor.moveToNext());
        }catch(Exception ex)
        {
            Log.d(TAG, "getAnimals: " + ex.toString());

        }finally {
            cursor.close();
        }
        return Animals; // there is nothing or an exception3
    }

    public ArrayList<Animal> getAnimalCategory()
    {
        String query ="SELECT * FROM" + TABLE_NAME + "GROUP BY " + ANIMAL_CATEGORY;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        ArrayList<Animal> Animals = new ArrayList<>();

        try {
            do {
                Animal pets = new Animal();
                pets.setAnimal(cursor.getString(0));
                pets.setDescription(cursor.getString(1));
                pets.setCategory(cursor.getString(2));
                pets.setPictureData(cursor.getBlob(3));
                pets.setSoundData(cursor.getBlob(4));
                Animals.add(pets);
            } while (cursor.moveToNext());
        }catch(Exception ex)
        {
            Log.d(TAG, "getAnimals: " + ex.toString());

        }finally {
            cursor.close();
        }
        return Animals; // there is nothing or an exception3
    }

    public ArrayList<Animal> getAnimals(String catagory)
    {
        String query ="SELECT * FROM" + TABLE_NAME + "WHERE catagory =" + catagory;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        ArrayList<Animal> Animals = new ArrayList<>();

        try {
            do {
                Animal pets = new Animal();
                pets.setAnimal(cursor.getString(0));
                pets.setDescription(cursor.getString(1));
                pets.setCategory(cursor.getString(2));
                pets.setPictureData(cursor.getBlob(3));
                pets.setSoundData(cursor.getBlob(4));
                Animals.add(pets);
            } while (cursor.moveToNext());
        }catch(Exception ex)
        {
            Log.d(TAG, "getAnimals: " + ex.toString());

        }finally {
            cursor.close();
        }
        return Animals; // there is nothing or an exception3
    }

    public Animal getAnimal(String animal)
    {
        String query = "SELECT * FROM" + TABLE_NAME + "WHERE animal="+animal;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        try
        {
            Animal pet = new Animal();
            pet.setAnimal(cursor.getString(0));
            pet.setDescription(cursor.getString(1));
            pet.setCategory(cursor.getString(2));
            pet.setPictureData(cursor.getBlob(3));
            pet.setSoundData(cursor.getBlob(4));
            return pet;

        }catch (Exception ex)
        {
            Log.d(TAG,ex.toString());
        }finally {
            cursor.close();
        }
        return null;
    }

    public ArrayList<String> getCategory()
    {
        ArrayList<String> to_return = new ArrayList<>();
        String query = "SELECT DISTINCT category FROM" + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        try{
            do {
                to_return.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }catch(Exception ex)
        {
            Log.d(TAG, ex.toString());

        }finally {
            cursor.close();
        }
        return to_return;
    }

    public int numberOfRecords()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT COUNT(*) FROM" + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        int to_return = 0;

        try
        {
            to_return= cursor.getInt(0);
            cursor.close();
        }catch (Exception ex)
        {
            Log.d(TAG,ex.toString());
        }finally {
            cursor.close();
        }
        return to_return;
    }
}

