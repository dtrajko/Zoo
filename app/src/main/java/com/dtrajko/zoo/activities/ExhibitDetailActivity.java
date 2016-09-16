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
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private String mCurrentFragmentTitle;

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

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_opened, R.string.drawer_closed) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.drawer_opened);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.drawer_closed);
            }
        };

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

        displayInitialFragment();

        Animal animal = getIntent().getExtras().getParcelable(EXTRA_ANIMAL);

        TextView species = (TextView) findViewById(R.id.species);
        TextView description = (TextView) findViewById(R.id.description);
        ImageView image = (ImageView) findViewById(R.id.image);

        species.setText(animal.getSpecies());
        description.setText(animal.getDescription());

        Picasso.with(this).load(animal.getImage()).into(image);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getInstance().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getInstance().unregister(this);
    }

    private void displayInitialFragment() {
        getSupportFragmentManager().beginTransaction().commit();
        mCurrentFragmentTitle = "Exhibit Details";
    }

    @Subscribe
    public void onDrawerSectionItemClickEvent(DrawerSectionItemClickedEvent event) {

        mDrawerLayout.closeDrawers();

        if (event == null || TextUtils.isEmpty(event.section) || event.section.equalsIgnoreCase(mCurrentFragmentTitle)) {
            return;
        }

        Toast.makeText(this, "MainActivity: Section Clicked: " + event.section, Toast.LENGTH_SHORT).show();

        if (event.section.equalsIgnoreCase("map")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, (Fragment) ZooMapFragment.getInstance()).commit();
        } else if (event.section.equalsIgnoreCase("gallery")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, GalleryFragment.getInstance()).commit();
        } else if (event.section.equalsIgnoreCase("exhibits")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, ExhibitsListFragment.getInstance()).commit();
        } else {
            return;
        }

        mCurrentFragmentTitle = event.section;
    }
}
