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

import java.util.ArrayList;

/**
 * Created by michaeliverson on 6/28/17.
 */

public class myListAdapter extends ArrayAdapter<String>
{
    private Activity context;
    private String[] catagoires;
    private ArrayList<byte[]> images;
    private ImageView image;
    private TextView text;

    public myListAdapter(Activity context,String [] catagoires,ArrayList<byte[]> images)
    {
        super(context,R.layout.categorylist,catagoires);
        this.catagoires = catagoires;
        this.images = images;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.categorylist, null,true);

        // initialize
        this.image = (ImageView) rowView.findViewById(R.id.icon);
        this.text = (TextView) rowView.findViewById(R.id.category);


        this.text.setText(catagoires[position]);
        byte[] imagaDate = this.images.get(position);
        Bitmap bmp = BitmapFactory.decodeByteArray(imagaDate, 0, imagaDate.length);
        this.image.setImageBitmap(Bitmap.createScaledBitmap(bmp, image.getWidth(),
                image.getHeight(), false));
        return rowView;
    }

}
