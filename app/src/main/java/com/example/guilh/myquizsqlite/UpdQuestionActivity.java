package com.example.guilh.myquizsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guilh.db.DbHelper;


public class UpdQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upd_question);

        TextView t1 = (TextView)findViewById(R.id.QuesEditText);
        TextView t2 = (TextView)findViewById(R.id.answerEditText);
        TextView t3= (TextView)findViewById(R.id.optaEditText);
        TextView t4 = (TextView)findViewById(R.id.optbEditText);
        TextView t5 = (TextView)findViewById(R.id.optcEditText);
        final String question = this.getIntent().getStringExtra("question");
        //teste.setText(question);
        DbHelper db = new DbHelper(this);
        Question q =   db.getquestion(question);

        t1.setText(q.getQuestion().toString());
        t2.setText(q.getAnswer().toString());
        t3.setText(q.getOptA().toString());
        t4.setText(q.getOptB().toString());
        t5.setText(q.getOptC().toString());

        Button btn = (Button) findViewById(R.id.updButton);

        //Listener for add question button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updQuestion(question);
                finish();
            }
        });
    }
    private void updQuestion(String oquest){

        //Getting views by ID
        EditText quesEd = (EditText) findViewById(R.id.QuesEditText);
        EditText answerEd = (EditText) findViewById(R.id.answerEditText);
        EditText optaEd = (EditText) findViewById(R.id.optaEditText);
        EditText optbEd = (EditText) findViewById(R.id.optbEditText);
        EditText optcEd = (EditText) findViewById(R.id.optcEditText);

        String question,answer,opta,optb,optc;

        //New DbHelper Instance
        DbHelper db= new DbHelper(this);

        question = quesEd.getText().toString();
        answer = answerEd.getText().toString();
        opta = optaEd.getText().toString();
        optb = optbEd.getText().toString();
        optc = optcEd.getText().toString();

        //Question instance
        Question nquest = new Question(question,opta,optb,optc,answer);

        //Calling addQuest from DbHelper, this inserts a new row with the Question in the DB
        db.updateQuest(oquest,nquest);

        Intent i = new Intent(this,ListActivity.class);
        startActivity(i);


    }
}