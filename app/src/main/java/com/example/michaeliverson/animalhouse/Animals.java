package com.example.michaeliverson.animalhouse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.michaeliverson.animalhouse.Utils.dbHelper;

public class Animals extends AppCompatActivity {

    static View.OnClickListener AnimalsOnClick;
    private dbHelper db;
    private AnimalsAdaptor adaptor;
    private RecyclerView recycle;
    private RecyclerView.LayoutManager layoutManager;

    public Animals()
    {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);


        // Set Onlick listener
        AnimalsOnClick = new AnimalOnclickListener(this);
        // Initiate Views
        this.recycle = (RecyclerView)findViewById(R.id.rvAnimal);
        this.recycle.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        this.recycle.setLayoutManager(layoutManager);
        this.recycle.setItemAnimator(new DefaultItemAnimator());

        String categorie = getIntent().getExtras().getString("CATEGORY");

        this.adaptor = new AnimalsAdaptor(db.getAnimals(categorie));  // Can't fix error
        this.recycle.setAdapter(this.adaptor);
    }

    public class AnimalOnclickListener implements View.OnClickListener {

        private Context context;

        public AnimalOnclickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            getItem(view);
        }

        private void getItem(View v)
        {
            int itemS =recycle.getChildAdapterPosition(v);
            RecyclerView.ViewHolder viewHolder =recycle.findViewHolderForAdapterPosition(itemS);
            TextView Name = (TextView) viewHolder.itemView.findViewById(R.id.animal);
            String SelectedName=(String) Name.getText();

           Intent intent = new Intent(Animals.this,Animal.class);
            intent.putExtra("ANIMAL",SelectedName);
            startActivity(intent);
        }

    }
}
