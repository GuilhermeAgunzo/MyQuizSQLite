package com.example.guilh.myquizsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    }
}
