package com.dtrajko.zoo.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtrajko.zoo.R;
import com.dtrajko.zoo.models.GalleryImage;
import com.squareup.picasso.Picasso;

/**
 * Created by Dejan Trajkovic on 9/15/2016.
 */
public class GalleryImageAdapter extends ArrayAdapter<GalleryImage> {

    public GalleryImageAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_gallery_thumbnail, parent, false);

            holder.image = (ImageView) convertView.findViewById(R.id.image);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(getContext()).load(getItem(position).getThumbnail()).into(holder.image);

        Log.e("Zoo", "GalleryImageAdapter ViewHolder loaded for position " + position);

        return convertView;
    }

    class ViewHolder {
        ImageView image;
    }
}
