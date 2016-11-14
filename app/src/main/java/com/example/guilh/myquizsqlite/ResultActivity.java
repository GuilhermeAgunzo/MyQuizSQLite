package com.example.guilh.myquizsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button btn = (Button) findViewById(R.id.btnFinish);

        //Listener to finish button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initHome();
                finish();
            }
        });


        TextView tScore = (TextView) findViewById(R.id.numScoreTextView);
        TextView tResult = (TextView) findViewById(R.id.resultTextView);

        //Get the intent extras
        Bundle bundle = getIntent().getExtras();

        //Set extras values to variables
        int score = bundle.getInt("score");
        int qtQuest = bundle.getInt("questSize");
        double result = bundle.getDouble("result");

        //Modify text views with result and score
        tResult.setText(Double.toString(result) + "%");
        tScore.setText(Integer.toString(score) +" / "+ Integer.toString(qtQuest));
    }

    //Returns to Home
    private void initHome(){
        Intent i = new Intent(this,HomeActivity.class);
        startActivity(i);
    }
}
