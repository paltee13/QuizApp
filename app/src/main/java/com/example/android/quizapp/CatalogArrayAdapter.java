package com.example.android.quizapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quizapp.model.Catalog;

import java.util.List;

public class CatalogArrayAdapter extends ArrayAdapter<Catalog> {


    public CatalogArrayAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public CatalogArrayAdapter(@NonNull Context context, int resource,@NonNull List<Catalog> list) {
        super(context, resource, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_item_layout, null);
        }

        Catalog catalog = getItem(position);

        TextView temperatureTextView = (TextView)convertView.findViewById(R.id.temperatureTextView);
        temperatureTextView.setText("Test no. " + String.valueOf(catalog.getTestId()));

        TextView descriptionTextView = (TextView)convertView.findViewById(R.id.descriptionTextView);
        descriptionTextView.setText("Subject: " + catalog.getSubject());

        TextView dayTextView = (TextView)convertView.findViewById(R.id.dayTextView);
        dayTextView.setText("Grade: " + String.valueOf(catalog.getScore()));

        return convertView;
    }
}
