package com.example.guilh.myquizsqlite;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.guilh.db.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        DbHelper db = new DbHelper(this);
        ArrayList<Question> questions = (ArrayList<Question>) db.getAllQuestions();

        //Populating ListView
        ArrayAdapter<Question> aa = new ArrayAdapter<Question>(this,android.R.layout.simple_list_item_1,questions);
        final ListView list = (ListView) findViewById(R.id.questionListView);
        list.setAdapter(aa);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                optionsMenu(list.getItemAtPosition(i).toString());

                return true;
            }
        });

    }

    private void optionsMenu(final String question){
        final AlertDialog alertDialogBuilder = new AlertDialog.Builder(ListActivity.this).create();
        ListView listOptions = new ListView(ListActivity.this);

        ArrayList<String> options = new ArrayList<>();
        options.add(getResources().getString(R.string.updateQuestion));
        options.add(getResources().getString(R.string.deleteQuestion));

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options);

        listOptions.setAdapter(aa);

        listOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){

                }else if(i == 1){
                    deleteQuestion(question);
                    alertDialogBuilder.dismiss();
                }
            }
        });

        alertDialogBuilder.setView(listOptions);
        alertDialogBuilder.show();

    }

    private void deleteQuestion(String q){
        DbHelper db = new DbHelper(this);
        db.deleteQuest(q);
        this.recreate();
    }
}
