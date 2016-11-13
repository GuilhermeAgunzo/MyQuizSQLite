package com.example.guilh.myquizsqlite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Floating Action Menu and Floating Action Buttons

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

        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,HistoryActivity.class);
                startActivity(i);
            }
        });

        //Click listener for start quiz button
        Button btn = (Button) findViewById(R.id.btnStart);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTest();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = this.getSharedPreferences("com.example.guilh.quiz", Context.MODE_PRIVATE);
        int qtde = pref.getInt("qtde", 0);
        TextView qtdeText = (TextView)findViewById(R.id.numTestsTextView);
        qtdeText.setText(qtde+"");
        float nota = pref.getFloat("nota", 0);
        TextView notaText = (TextView)findViewById(R.id.numLastScoreTextView);
        notaText.setText(nota+"%");
        float soma = pref.getFloat("soma", 0);
        TextView mediaText = (TextView)findViewById(R.id.numAvgScore);
        mediaText.setText((soma/qtde)+"%");

    }

    private void initTest(){
        Intent i = new Intent(this,TestActivity.class);
        startActivity(i);
    }
}
