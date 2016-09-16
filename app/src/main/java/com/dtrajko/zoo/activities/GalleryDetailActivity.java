package com.dtrajko.zoo.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtrajko.zoo.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Dejan Trajkovic on 9/15/2016.
 */
public class GalleryDetailActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE = "extra_image";
    public static final String EXTRA_CAPTION = "extra_caption";

    private TextView mCaptionTextView;
    private ImageView mImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Log.e("Zoo", "GalleryDetailActivity onCreate");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gallery_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        mCaptionTextView = (TextView) findViewById(R.id.caption);
        mImageView = (ImageView) findViewById(R.id.image);

        if (getIntent() != null && getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey(EXTRA_IMAGE)) {
                Picasso.with(this).load(getIntent().getExtras().getString(EXTRA_IMAGE)).into(mImageView);
            }
            if (getIntent().getExtras().containsKey(EXTRA_CAPTION)) {
                mCaptionTextView.setText(getIntent().getExtras().getString(EXTRA_CAPTION));
            }
        }
    }
}
