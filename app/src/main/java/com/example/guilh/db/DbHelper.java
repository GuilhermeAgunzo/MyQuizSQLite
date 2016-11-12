package com.example.guilh.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.guilh.myquizsqlite.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guilh on 12/11/2016.
 */

public class DbHelper extends SQLiteOpenHelper {
    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database name
    private static final String DATABASE_NAME = "Quiz";
    //Table name
    private static final String TABLE_QUEST = "quest";
    //Table Columns
    private static final String KEY_ID = "id";
    private static final String KEY_QUEST = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OPTA = "opta";
    private static final String KEY_OPTB = "optb";
    private static final String KEY_OPTC = "optc";
    private SQLiteDatabase dbase;

    //Constructor
    public DbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + "(" +
                        KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        KEY_QUEST + " TEXT," +
                        KEY_ANSWER + " TEXT," +
                        KEY_OPTA + " TEXT," +
                        KEY_OPTB + " TEXT," +
                        KEY_OPTC + " TEXT" +
                        ")";

        db.execSQL(sql);
        addQuestions();
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldV,int newV){
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        //Create tables again
        onCreate(db);
    }

    private void addQuestions(){
        Question q1=new Question("Which company is the largest manufacturer" +
                " of network equipment?","HP", "IBM", "CISCO", "C");
        this.addQuestion(q1);
        Question q2=new Question("Which of the following is NOT " +
                "an operating system?", "SuSe", "BIOS", "DOS", "B");
        this.addQuestion(q2);
        Question q3=new Question("Which of the following is the fastest" +
                " writable memory?","RAM", "FLASH","Register","C");
        this.addQuestion(q3);
        Question q4=new Question("Which of the following device" +
                " regulates internet traffic?",    "Router", "Bridge", "Hub","A");
        this.addQuestion(q4);
        Question q5=new Question("Which of the following is NOT an" +
                " interpreted language?","Ruby","Python","BASIC","C");
        this.addQuestion(q5);
    }

    //Add new question method
    private void addQuestion(Question quest){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUEST, quest.getQuestion());
        values.put(KEY_ANSWER, quest.getAnswer());
        values.put(KEY_OPTA, quest.getOptA());
        values.put(KEY_OPTB, quest.getOptB());
        values.put(KEY_OPTC, quest.getOptC());
        //Inserting row
        dbase.insert(TABLE_QUEST,null,values);
    }

    public List<Question> getAllQuestions(){
        List<Question> questionList = new ArrayList<Question>();
        //Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_QUEST;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery,null);

        //adding to list
        if(cursor.moveToFirst()){
            do{
                Question quest = new Question();
                quest.setQuestion(cursor.getString(1));
                quest.setAnswer(cursor.getString(2));
                quest.setOptA(cursor.getString(3));
                quest.setOptB(cursor.getString(4));
                quest.setOptC(cursor.getString(5));
                questionList.add(quest);
            }while(cursor.moveToNext());
        }
        //return Question List
        return questionList;
    }

    public int rowCount(){
        int row = 0;
        String selectQuery = "SELECT * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        row = cursor.getCount();
        return row;
    }

}
