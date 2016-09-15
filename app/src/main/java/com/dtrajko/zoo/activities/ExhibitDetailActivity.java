package com.dtrajko.zoo.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtrajko.zoo.R;
import com.dtrajko.zoo.models.Animal;
import com.squareup.picasso.Picasso;

/**
 * Created by Dejan Trajkovic on 9/14/2016.
 */
public class ExhibitDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ANIMAL = "extra_animal";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Log.e("Zoo", "ExhibitDetailActivity::onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibit_detail);

        Animal animal = getIntent().getExtras().getParcelable(EXTRA_ANIMAL);

        TextView species = (TextView) findViewById(R.id.species);
        TextView description = (TextView) findViewById(R.id.description);
        ImageView image = (ImageView) findViewById(R.id.image);

        species.setText(animal.getSpecies());
        description.setText(animal.getDescription());

        Picasso.with(this).load(animal.getImage()).into(image);
    }
}
