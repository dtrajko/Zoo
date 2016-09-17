package com.dtrajko.zoo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dtrajko.zoo.R;
import com.dtrajko.zoo.adapters.DrawerNavigationListAdapter;
import com.dtrajko.zoo.events.DrawerSectionItemClickedEvent;
import com.dtrajko.zoo.utils.EventBus;

/**
 * Created by Dejan Trajkovic on 9/13/2016.
 */
public class DrawerNavigationListView extends ListView implements AdapterView.OnItemClickListener {

    public DrawerNavigationListView(Context context) {
        this(context, null);
    }

    public DrawerNavigationListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerNavigationListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        DrawerNavigationListAdapter adapter = new DrawerNavigationListAdapter(getContext(), 0);
        adapter.add(getContext().getString(R.string.section_exhibits));
        adapter.add(getContext().getString(R.string.section_gallery));
        adapter.add(getContext().getString(R.string.section_map));

        setAdapter(adapter);
        setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Toast.makeText(getContext(), "Section Clicked: " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
        EventBus.getInstance().post(new DrawerSectionItemClickedEvent( (String) parent.getItemAtPosition(position)));
    }
}
