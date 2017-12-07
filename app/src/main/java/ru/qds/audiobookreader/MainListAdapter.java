package ru.qds.audiobookreader;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ru.qds.audiobookreader.model.Book;

/**
 * Created by eduard on 02.12.17.
 */

public class MainListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Book> books;

    public MainListAdapter(Context context, ArrayList<Book> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vw;
        LayoutInflater linfltr = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vw = linfltr.inflate(R.layout.main_list_item, viewGroup, false);
        TextView name = (TextView) vw.findViewById(R.id.name);
        TextView percent = (TextView) vw.findViewById(R.id.percent);
        name.setText(books.get(i).getName());
        percent.setText(String.valueOf(books.get(i).getPercent())+"%   ");
        return vw;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
