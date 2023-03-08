package edu.uncc.midtermapp.models;

import java.util.ArrayList;

public class TriviaResponse {
    public ArrayList<Question> questions;

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
