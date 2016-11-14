package com.example.guilh.myquizsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guilh.db.DbHelper;

public class AddQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        Button btn = (Button) findViewById(R.id.addButton);

        //Listener for add question button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addQuestion();
                finish();
            }
        });
    }

    private void addQuestion(){

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
        Question quest = new Question(question,opta,optb,optc,answer);

        //Calling addQuest from DbHelper, this inserts a new row with the Question in the DB
        db.addQuest(quest);

    }
}
