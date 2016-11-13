package com.example.guilh.myquizsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FloatingActionMenu materialDesignFAM;
        FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;

        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.floating_addQuestion);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.floating_questionList);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.floating_history);


        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, AddQuestionActivity.class);
                startActivity(i);
            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ListActivity.class);
                startActivity(i);
            }
        });

        Button btn = (Button) findViewById(R.id.btnStart);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTest();
            }
        });
    }

    private void initTest(){
        Intent i = new Intent(this,TestActivity.class);
        startActivity(i);
    }
}
