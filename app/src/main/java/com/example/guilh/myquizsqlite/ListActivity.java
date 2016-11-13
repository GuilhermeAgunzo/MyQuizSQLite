package com.example.guilh.myquizsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.guilh.db.DbHelper;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DbHelper db = new DbHelper(this);
        ArrayList<Question> questions = (ArrayList<Question>) db.getAllQuestions();

        ArrayAdapter<Question> aa = new ArrayAdapter<Question>(this,android.R.layout.simple_list_item_1,questions);

        ListView list = (ListView) findViewById(R.id.questionListView);

        list.setAdapter(aa);

    }
}
