package com.example.guilh.myquizsqlite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guilh.db.DbHelper;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        //Listener: Next button
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup)findViewById(R.id.alternativesRadioGroup);
                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
                if(currentQ.getAnswer().equals(answer.getText())){
                    score++; // if answer equals to the selected radio, then add 1 point
                }
                if(qid < quesList.size()){      // if not reached max size then gets a new question
                    currentQ=quesList.get(qid);
                    grp.clearCheck();           // clear the radio group selection
                    setQuestionView();
                }else{
                    // Converting to percent
                    double result = 100.0 * (double)score / (double)quesList.size();

                    // Getting actual date
                    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    String filename = date + ": [" + result + "%]"; // Defining file name
                    FileOutputStream output;
                    // Writing results in the file
                    try {
                        output = openFileOutput(filename, Context.MODE_PRIVATE);
                        output.write(("Acertos: " + score + " / " + quesList.size()).getBytes());
                        output.write(("\nNota: " + result + "%\n\n").getBytes());
                        output.close();
                    }catch (Exception ex){
                        Toast.makeText(getApplicationContext(),
                                "Erro ao gravar o arquivo: "+ex.getLocalizedMessage(),
                                Toast.LENGTH_LONG).show();
                    }

                    //Calling saveInPreferences, it saves SharedPreferences
                    saveInPreferences(result);

                    Intent intent = new Intent(TestActivity.this,ResultActivity.class);
                    intent.putExtra("score", score);
                    intent.putExtra("questSize",quesList.size());
                    intent.putExtra("result",result);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    //Method for getting a new Question
    private void setQuestionView(){
        txtQuestion.setText(currentQ.getQuestion());
        rda.setText(currentQ.getOptA());
        rdb.setText(currentQ.getOptB());
        rdc.setText(currentQ.getOptC());
        qid++;
    }

    //Method for saving in Shared Preferences
    private void saveInPreferences(double result){
        SharedPreferences pref = this.getSharedPreferences("com.example.guilh.quiz", Context.MODE_PRIVATE);
        int qtde = pref.getInt("qtde", 0)+1;
        float soma = (float) (pref.getFloat("soma", 0)+result);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("qtde", qtde);
        editor.putFloat("soma", soma);
        editor.putFloat("nota", (float) result);
        editor.commit();
    }
}
