package com.example.android.quizapp.model;

import java.util.List;

public class TestQuestion {

    private int question_id;
    private String question_text;
    private int question_no_ans;
    private List<TestAnswer> question_answer_list;
    private int question_multch;
    private float question_points;

    public TestQuestion() {
    }

    public TestQuestion(int question_id, String question_text, int question_no_ans,
                        List<TestAnswer> question_answer_list, float question_points) {
        this.question_id = question_id;
        this.question_text = question_text;
        this.question_no_ans = question_no_ans;
        this.question_answer_list = question_answer_list;
        this.question_points = question_points;
    }

    public int getQuestion_multch() {
        return question_multch;
    }

    public void setQuestion_multch(int question_multch) {
        this.question_multch = question_multch;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public int getQuestion_no_ans() {
        return question_no_ans;
    }

    public void setQuestion_no_ans(int question_no_ans) {
        this.question_no_ans = question_no_ans;
    }

    public List<TestAnswer> getQuestion_answer_list() {
        return question_answer_list;
    }

    public void setQuestion_answer_list(List<TestAnswer> question_answer_list) {
        this.question_answer_list = question_answer_list;
    }

    public float getQuestion_points() {
        return question_points;
    }

    public void setQuestion_points(float question_points) {
        this.question_points = question_points;
    }

}
