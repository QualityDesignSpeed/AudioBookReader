package ru.qds.audiobookreader;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by eduard on 08.12.17.
 */

public class FSListAdapter extends BaseAdapter {
    Context context;
    ArrayList<FSElement> elements;
    LayoutInflater lInflater;

    public FSListAdapter(Context context, ArrayList<FSElement> elements) {
        this.context = context;
        this.elements = elements;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Object getItem(int i) {
        return elements.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vw = lInflater.inflate(R.layout.fs_list_item, viewGroup, false);
        ImageView iview = (ImageView) vw.findViewById(R.id.icon);
        TextView name = (TextView) vw.findViewById(R.id.name);
        if(elements.get(i).isDir())
        {
            iview.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_folder_black_24dp));
        }
       /* else if(elements.get(i).isAudio())
        {
            iview.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_music_note_black_24dp));
        }*/

        name.setText(elements.get(i).getName());
        return vw;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
