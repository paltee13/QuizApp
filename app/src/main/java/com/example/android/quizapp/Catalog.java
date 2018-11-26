package com.example.android.quizapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Catalog {

    private int userId;
    private int testId;
    private String subject;
    private int score;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static List<Catalog> generateRandomData() {
        ArrayList<Catalog> list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 1; i <= 7; i++) {
            Catalog catalog = new Catalog();
            catalog.testId = rand.nextInt(40);
            if (i % 2 == 0) {
                catalog.subject = "Mate";
            } else {
                catalog.subject = "SDD";
            }
            catalog.score = 5;
            list.add(catalog);
        }
    return list;
    }
}
