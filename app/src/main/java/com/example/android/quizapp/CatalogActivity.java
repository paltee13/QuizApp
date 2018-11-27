package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.android.quizapp.model.Catalog;

import java.util.List;

public class CatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        Intent intent = getIntent();

        List<Catalog> list = Catalog.generateRandomData();

        ListView listView = (ListView)findViewById(R.id.CatalogListView);
        CatalogArrayAdapter catalogArrayAdapter =
                new CatalogArrayAdapter(getApplicationContext(),
                        R.layout.listview_item_layout, list);
        listView.setAdapter(catalogArrayAdapter);
    }
}
