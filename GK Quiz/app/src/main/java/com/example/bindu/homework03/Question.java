package com.example.bindu.homework03;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bindu on 2/16/2018.
 */

public class Question implements Serializable {
    private String question;
    private String imageURL;
    private ArrayList<String> answers;
    private int answer;

    public Question(String question, String imageURL, ArrayList<String> answers, int answer) {
        this.question = question;
        this.answers = answers;
        this.imageURL = imageURL;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", answers=" + answers +
                ", answer=" + answer +
                '}';
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
