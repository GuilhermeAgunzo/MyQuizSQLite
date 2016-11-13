package com.example.guilh.myquizsqlite;

/**
 * Created by guilh on 12/11/2016.
 */

public class Question {
    private int ID;
    private String question;
    private String answer;
    private String optA;
    private String optB;
    private String optC;


    //Constructors

    public Question(){
        ID = 0;
        question = "";
        answer = "";
        optA = "";
        optB = "";
        optC = "";
    }

    public Question(String qUESTION, String oPTA, String oPTB, String oPTC,String aNSWER){
        question = qUESTION;
        answer = aNSWER;
        optA = oPTA;
        optB = oPTB;
        optC = oPTC;
    }

    @Override
    public String toString(){
        return question;
    }

    //Getters

    public int getID(){
        return ID;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getOptA() {
        return optA;
    }

    public String getOptB() {
        return optB;
    }

    public String getOptC() {
        return optC;
    }

    //Setters

    public void setID(int id){
        ID=id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }

    public void setOptC(String optC) {
        this.optC = optC;
    }

}
