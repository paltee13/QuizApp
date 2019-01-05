package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.android.quizapp.database.CatalogUtility;
import com.example.android.quizapp.model.Catalog;
import com.example.android.quizapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class SearchStudActivity extends AppCompatActivity {

    private ListView listView_stud;
    private EditText search_stud;
    private String[] id;
    private List<Catalog> lst_catalog = new ArrayList<>();
    private List<User> lst_stud=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_stud);

        setTitle("Student list");

        Intent intent=getIntent();
        String[] stud=intent.getStringArrayExtra("stud");
        id=intent.getStringArrayExtra("id");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stud );
        listView_stud=(ListView)findViewById(R.id.student_lstv);
        listView_stud.setAdapter(arrayAdapter);
        listView_stud.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CatalogUtility catalogUtility=new CatalogUtility(SearchStudActivity.this);
                catalogUtility.openDB();
                lst_catalog=catalogUtility.getCatalog(String.valueOf(id[i]));
                catalogUtility.closeDB();
                String[] catalog=convertList();
                Intent new_activity = new Intent(SearchStudActivity.this, CatalogActivity.class);
                new_activity.putExtra("catalog",catalog);
                SearchStudActivity.this.startActivity(new_activity);
            }
        });
    }
    private String[] convertList(){
        String[] catalog;
        List<String> lst_string=new ArrayList<>();
        for (Catalog cat:lst_catalog) {
            String s;
            s=cat.getSubject() + getString(R.string.pct_obt) + cat.getScore();
            lst_string.add(s);
        }
        catalog=new String[lst_string.size()];
        catalog=lst_string.toArray(catalog);
        return catalog;


    }
}
