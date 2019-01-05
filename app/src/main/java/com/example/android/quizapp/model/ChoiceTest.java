package com.example.android.quizapp.model;

import java.util.List;

public class ChoiceTest {

    private int test_id;
    private String test_name;
    private String test_subject;
    private int test_question_no;
    private List<TestQuestion> test_question_lst;
    private String test_author;
    private int subject_id;

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getTest_subject() {
        return test_subject;
    }

    public void setTest_subject(String test_subject) {
        this.test_subject = test_subject;
    }

    public int getTest_question_no() {
        return test_question_no;
    }

    public void setTest_question_no(int test_question_no) {
        this.test_question_no = test_question_no;
    }

    public List<TestQuestion> getTest_question_lst() {
        return test_question_lst;
    }

    public void setTest_question_lst(List<TestQuestion> test_question_lst) {
        this.test_question_lst = test_question_lst;
    }

    public String getTest_author() {
        return test_author;
    }

    public void setTest_author(String test_author) {
        this.test_author = test_author;
    }

}
