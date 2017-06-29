package com.example.michaeliverson.animalhouse;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michaeliverson.animalhouse.Utils.Animal;

import java.util.ArrayList;

/**
 * Created by michaeliverson on 6/28/17.
 */

public class myListAdapter extends ArrayAdapter<Animal>
{

    private Activity context;
    private ArrayList<Animal> animals;
    private TextView category;
    private ImageView image;


    public myListAdapter(Activity context, ArrayList<Animal> animals)
    {
        super(context,R.layout.categorylist,animals);
        this.animals = animals;
        this.context = context;
    }

    public View getView(int position, View view, ViewGroup parent) {

        View rowView = view;
        if (view == null) {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.categorylist,parent,false);
        }

        // initialize
        this.image = (ImageView) rowView.findViewById(R.id.icon);
        this.category = (TextView) rowView.findViewById(R.id.category);
        Animal animal = this.animals.get(position);


        this.category.setText(animal.getCategory());
        Bitmap bmp = BitmapFactory.decodeByteArray(animal.getPictureData(bytes), 0, animal.getPictureData(bytes).length);
        this.image.setImageBitmap(Bitmap.createScaledBitmap(bmp, image.getWidth(),
                image.getHeight(), false));
        return rowView;
    }

}
