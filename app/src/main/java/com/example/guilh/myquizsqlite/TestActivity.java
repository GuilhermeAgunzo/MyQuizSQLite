package com.example.guilh.myquizsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.guilh.db.DbHelper;

import java.util.List;

public class TestActivity extends AppCompatActivity {
    List<Question> quesList;
    int score=0;
    int qid=0;
    Question currentQ;
    TextView txtQuestion;
    RadioButton rda, rdb, rdc;
    Button butNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        DbHelper db=new DbHelper(this);
        quesList=db.getAllQuestions();
        currentQ=quesList.get(qid);
        txtQuestion=(TextView)findViewById(R.id.questionTextView);
        rda=(RadioButton)findViewById(R.id.optA);
        rdb=(RadioButton)findViewById(R.id.optB);
        rdc=(RadioButton)findViewById(R.id.optC);
        butNext=(Button)findViewById(R.id.btnNext);
        setQuestionView();

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup)findViewById(R.id.alternativesRadioGroup);
                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
                if(currentQ.getAnswer().equals(answer.getText()))
                {
                    score++;
                }
                if(qid < quesList.size()){
                    currentQ=quesList.get(qid);
                    grp.clearCheck();
                    setQuestionView();
                }else{
                    Intent intent = new Intent(TestActivity.this,ResultActivity.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private void setQuestionView(){
        txtQuestion.setText(currentQ.getQuestion());
        rda.setText(currentQ.getOptA());
        rdb.setText(currentQ.getOptB());
        rdc.setText(currentQ.getOptC());
        qid++;
    }
}
