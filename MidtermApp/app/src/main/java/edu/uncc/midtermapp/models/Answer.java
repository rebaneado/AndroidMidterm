package edu.uncc.midtermapp.models;

import org.json.JSONObject;

import java.io.Serializable;

public class Answer implements Serializable {
    public String answer_id, answer_text;

    public Answer(String answer_id, String answer_text) {
        this.answer_id = answer_id;
        this.answer_text = answer_text;
    }

    public Answer() {
    }

    public Answer(JSONObject json) {

    }

    public String getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer_text() {
        return answer_text;
    }

    public void setAnswer_text(String answer_text) {
        this.answer_text = answer_text;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer_id='" + answer_id + '\'' +
                ", answer_text='" + answer_text + '\'' +
                '}';
    }
}
