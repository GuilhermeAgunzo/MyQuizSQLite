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
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "triviaQuiz";
    //Table name
    private static final String TABLE_QUEST = "quest";
    //Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; //correct option
    private static final String KEY_OPTA= "opta"; //option a
    private static final String KEY_OPTB= "optb"; //option b
    private static final String KEY_OPTC= "optc"; //option c
    private SQLiteDatabase dbase;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";
        db.execSQL(sql);
        Question q1=new Question("Which company is the largest manufacturer" +
                " of network equipment?","HP", "IBM", "CISCO", "CISCO");
        this.addQuestion(q1);
        Question q2=new Question("Which of the following is NOT " +
                "an operating system?", "SuSe", "BIOS", "DOS", "BIOS");
        this.addQuestion(q2);
        Question q3=new Question("Which of the following is the fastest" +
                " writable memory?","RAM", "FLASH","Register","Register");
        this.addQuestion(q3);
        Question q4=new Question("Which of the following device" +
                " regulates internet traffic?",    "Router", "Bridge", "Hub","Router");
        this.addQuestion(q4);
        Question q5=new Question("Which of the following is NOT an" +
                " interpreted language?","Ruby","Python","BASIC","BASIC");
        this.addQuestion(q5);
        //db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        //Create tables again
        onCreate(db);
    }

    // Adding new question when creating the database
    public void addQuestion(Question quest) {

        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQuestion());
        values.put(KEY_ANSWER, quest.getAnswer());
        values.put(KEY_OPTA, quest.getOptA());
        values.put(KEY_OPTB, quest.getOptB());
        values.put(KEY_OPTC, quest.getOptC());

        // Inserting Row
        dbase.insert(TABLE_QUEST,null,values);

    }

    //Adding new question the user entered
    public void addQuest(Question question){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, question.getQuestion());
        values.put(KEY_ANSWER, question.getAnswer());
        values.put(KEY_OPTA, question.getOptA());
        values.put(KEY_OPTB, question.getOptB());
        values.put(KEY_OPTC, question.getOptC());
        // Inserting Row
        db.insert(TABLE_QUEST,null,values);
    }

    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        //Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        //Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQuestion(cursor.getString(1));
                quest.setAnswer(cursor.getString(2));
                quest.setOptA(cursor.getString(3));
                quest.setOptB(cursor.getString(4));
                quest.setOptC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        //Return question list
        return quesList;
    }

    public Question getquestion(String question){
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST + " WHERE "+KEY_QUES+" = '" + question + "'";
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);

        cursor.moveToFirst();

        Question quest = new Question();
        quest.setID(cursor.getInt(0));
        quest.setQuestion(cursor.getString(1));
        quest.setAnswer(cursor.getString(2));
        quest.setOptA(cursor.getString(3));
        quest.setOptB(cursor.getString(4));
        quest.setOptC(cursor.getString(5));

        return quest;
    }

    public List<Question> getAllDataQuestions(String ques) {
        List<Question> quesList = new ArrayList<Question>();
        //Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST + "WHERE "+ KEY_QUES + " like '?'";
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQuestion(cursor.getString(1));
                quest.setAnswer(cursor.getString(2));
                quest.setOptA(cursor.getString(3));
                quest.setOptB(cursor.getString(4));
                quest.setOptC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        //Return question list
        return quesList;
    }

    // Update method
    // oQuest = old Question, will be used in where statement
    // nQuest = new Question, this will be the object carrying the new question information
    public void updateQuest(String oQuest,Question nQuest){
        SQLiteDatabase db = this.getWritableDatabase();
        String updateWhere = KEY_QUES + " = '" + oQuest + "'"; // Where statement

        // New values
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, nQuest.getQuestion());
        values.put(KEY_ANSWER, nQuest.getAnswer());
        values.put(KEY_OPTA, nQuest.getOptA());
        values.put(KEY_OPTB, nQuest.getOptB());
        values.put(KEY_OPTC, nQuest.getOptC());

        db.update(TABLE_QUEST,values,updateWhere,null);
    }

    // Delete method
    // Delete the row based on question
    public void deleteQuest(String question){
        String deleteWhere = KEY_QUES + " = '" + question+ "'";
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUEST,deleteWhere,null);
    }

    /*
    public int rowcount(){
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
    */
}