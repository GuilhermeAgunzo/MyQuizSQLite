package com.example.guilh.myquizsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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

        //Populating ListView
        ArrayAdapter<Question> aa = new ArrayAdapter<Question>(this, android.R.layout.simple_list_item_1, questions);
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
        final AlertDialog alertDialogBuilder = new Builder(ListActivity.this).create();
        ListView listOptions = new ListView(ListActivity.this);

        //Creating an Array of options
        ArrayList<String> options = new ArrayList<>();
        options.add(getResources().getString(R.string.updateQuestion));
        options.add(getResources().getString(R.string.deleteQuestion));

        //Populating the List View with the option Modify and Delete
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options);
        listOptions.setAdapter(aa);

        //Listener for the options on the List View
        listOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                  initUpd(question);
                }else if(i == 1){
                    deleteQuestion(question);
                    alertDialogBuilder.dismiss();
                }
            }
        });

        //Showing Alert Dialog with options
        alertDialogBuilder.setView(listOptions);
        alertDialogBuilder.show();

    }

    private void deleteQuestion(String q){
        DbHelper db = new DbHelper(this);
        db.deleteQuest(q);
        this.recreate();
    }
    private void initUpd(String question){
       Intent i = new Intent(ListActivity.this, UpdQuestionActivity.class);

        i.putExtra("question", question);
        startActivity(i);
    }
}
