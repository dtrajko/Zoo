package com.dtrajko.zoo.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dtrajko.zoo.R;
import com.dtrajko.zoo.events.DrawerSectionItemClickedEvent;
import com.dtrajko.zoo.fragments.ExhibitsListFragment;
import com.dtrajko.zoo.fragments.GalleryFragment;
import com.dtrajko.zoo.fragments.ZooMapFragment;
import com.dtrajko.zoo.models.Animal;
import com.dtrajko.zoo.utils.EventBus;
import com.squareup.otto.Subscribe;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        Animal animal = getIntent().getExtras().getParcelable(EXTRA_ANIMAL);

        TextView species = (TextView) findViewById(R.id.species);
        TextView description = (TextView) findViewById(R.id.description);
        ImageView image = (ImageView) findViewById(R.id.image);

        species.setText(animal.getSpecies());
        description.setText(animal.getDescription());

        Picasso.with(this).load(animal.getImage()).into(image);
    }
}
