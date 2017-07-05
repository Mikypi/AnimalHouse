package com.example.michaeliverson.animalhouse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michaeliverson.animalhouse.Utils.Animalss;

import java.util.ArrayList;

/**
 * Created by michaeliverson on 6/29/17.
 */

public class AnimalsAdaptor extends RecyclerView.Adapter<AnimalsAdaptor.MyViewHolder> {

    private ArrayList<Animalss> Animals;
    private Context context;

    public AnimalsAdaptor(ArrayList<Animalss> animals)
    {
        this.Animals = animals;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvAnimal;
        private TextView tvCatagorie;
        private ImageView ivAnimal;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvAnimal = (TextView)itemView.findViewById(R.id.animal);
            this.tvCatagorie = (TextView)itemView.findViewById(R.id.category);
            this.ivAnimal = (ImageView)itemView.findViewById(R.id.Animalphoto);
        }


        @Override
        public String toString() {
            return super.toString();
        }
        
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_animals, parent, false);
        view.setOnClickListener(com.example.michaeliverson.animalhouse.Animals.AnimalsOnClick);
        MyViewHolder mvh = new MyViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ImageView image = holder.ivAnimal;
        TextView animal = holder.tvAnimal;
        TextView categorie = holder.tvCatagorie;

        categorie.setText(this.Animals.get(position).getAnimal());
        animal.setText(this.Animals.get(position).getCategory());
        byte[] imageData = this.Animals.get(position).getPictureData();
        Bitmap bmp = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
        image.setImageBitmap(Bitmap.createScaledBitmap(bmp, image.getWidth(),
                image.getHeight(), false));
    }

    @Override
    public int getItemCount() {
        return this.Animals.size();
    }
}


