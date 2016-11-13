package com.example.guilh.myquizsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tScore = (TextView) findViewById(R.id.numScoreTextView);

        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("score");

        tScore.setText(Integer.toString(score));
    }
}
