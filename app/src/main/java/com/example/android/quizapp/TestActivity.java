package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.android.quizapp.database.AnswerUtility;
import com.example.android.quizapp.database.CatalogUtility;
import com.example.android.quizapp.database.DatabaseHelper;
import com.example.android.quizapp.database.QuestionUtility;
import com.example.android.quizapp.database.SubjectUtility;
import com.example.android.quizapp.model.AnsCheck;
import com.example.android.quizapp.model.ChoiceTest;
import com.example.android.quizapp.model.TestAnswer;
import com.example.android.quizapp.model.TestQuestion;
import com.example.android.quizapp.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Button submit_btn;
    private ChoiceTest choiceTest;
    private List<AnsCheck> ans_user;
    private List<AnsCheck> ans_correct;
    private float question_point=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent=getIntent();
        choiceTest=(ChoiceTest) intent.getSerializableExtra("choicetest");
        final User logged_user=(User)intent.getSerializableExtra("userLogat");
        linearLayout=(LinearLayout) findViewById(R.id.test_zone);
        submit_btn=(Button)findViewById(R.id.submit_btn);

        ans_correct=new ArrayList<>();
        ans_user=new ArrayList<>();



        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float mark=getFinalMark();
                DatabaseHelper myDBHelper=new DatabaseHelper(TestActivity.this);
                myDBHelper.openDataBase();
                SubjectUtility subject=new SubjectUtility(TestActivity.this);
                CatalogUtility catalogutil=new CatalogUtility(TestActivity.this);
                subject.openDB();
                catalogutil.openDB();
                String id_subj=subject.getSubjectId(subject.SUBJECT_ID,choiceTest.getTest_subject());
                catalogutil.insertMark(String.valueOf(logged_user.getUserId()),id_subj,String.valueOf(choiceTest.getTest_id()),mark);
                Log.i("ID SUBIECT",String.valueOf(choiceTest.getSubject_id()));
                Intent new_activity=new Intent(TestActivity.this,TestFinishActivity.class);
                new_activity.putExtra("mark",mark);
                TestActivity.this.startActivity(new_activity);
                subject.closeDB();
                catalogutil.closeDB();
                myDBHelper.close();

            }
        });
        getTest();
        ans_correct=createList(choiceTest.getTest_question_lst(),false);
        ans_user=createList(choiceTest.getTest_question_lst(),true);
        createTest();

    }

    private List<AnsCheck> createList(List<TestQuestion> lst_question, boolean isEmpty){
        List<AnsCheck> ans=new ArrayList<>();
        if(!isEmpty){
            for (TestQuestion question:lst_question) {
                List<TestAnswer> lst_ans=question.getQuestion_answer_list();
                for (TestAnswer answer:lst_ans) {
                    AnsCheck ansch=new AnsCheck();
                    ansch.setId(answer.getAnswer_id());
                    ansch.setIsRight(answer.isAnswer_right());
                    ansch.setQuestion(question.getQuestion_id());
                    ans.add(ansch);
                }
            }
        }else{
            for (TestQuestion question:lst_question) {
                List<TestAnswer> lst_ans=question.getQuestion_answer_list();
                for (TestAnswer answer:lst_ans) {
                    AnsCheck ansch=new AnsCheck();
                    ansch.setId(answer.getAnswer_id());
                    ansch.setIsRight(0);
                    ansch.setQuestion(question.getQuestion_id());
                    ans.add(ansch);
                }
            }
        }
        return ans;
    }

    private float getFinalMark(){
        float mark=10;
        question_point=90/ans_correct.size();
        for(int i=0;i<ans_correct.size();i++){
            AnsCheck ansCorr=ans_correct.get(i);
            AnsCheck ansUser=ans_user.get(i);
            if (ansUser.getIsRight() == ansCorr.getIsRight()) {
                mark=mark+question_point;
            }

        }
        return mark;
    }


    private void getTest(){
        DatabaseHelper myDBHelper=new DatabaseHelper(this);
        myDBHelper.openDataBase();
        QuestionUtility questionUtil=new QuestionUtility(TestActivity.this);
        questionUtil.openDB();
        AnswerUtility answerUtil=new AnswerUtility(TestActivity.this);
        answerUtil.openDB();
        choiceTest.setTest_question_lst(questionUtil.getQuestion(String.valueOf(choiceTest.getTest_id())));
        for (TestQuestion question:choiceTest.getTest_question_lst()) {
            question.setQuestion_answer_list(answerUtil.getAnswerList(String.valueOf(question.getQuestion_id())));
        }
        questionUtil.closeDB();
        answerUtil.closeDB();
        myDBHelper.close();
    }

    private void createTest(){
        List<TestQuestion> lst_question=choiceTest.getTest_question_lst();
        List<TestAnswer> lst_answer;
        TextView title=new TextView(TestActivity.this);
        title.setText(choiceTest.getTest_subject()+" - "+choiceTest.getTest_name());
        title.setTextSize(25);
        title.setGravity(Gravity.CENTER);
        linearLayout.addView(title);
        for (TestQuestion question:lst_question) {
            lst_answer=question.getQuestion_answer_list();
            TextView question_zone=new TextView(TestActivity.this);
            question_zone.setTextSize(20);
            question_zone.setText(question.getQuestion_text());
            linearLayout.addView(question_zone);
            if(question.getQuestion_multch()==0){
                RadioGroup radiogrup=new RadioGroup(this);
                radiogrup.setOrientation(RadioGroup.VERTICAL);
                for (final TestAnswer answer:lst_answer) {
                    final RadioButton radio_btn=new RadioButton(this);
                    radio_btn.setText(answer.getAnswer_text());
                    radio_btn.setTextSize(15);
                    radio_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if(compoundButton.isChecked()){
                                Log.i("ANS ID",""+answer.getAnswer_id()+"  "+compoundButton.isChecked());
                                for (AnsCheck ans:ans_user) {
                                    if(ans.getId()==answer.getAnswer_id()){
                                        ans.setIsRight(1);
                                    }
                                }
                            }else{
                                Log.i("ANS ID",""+answer.getAnswer_id()+"  "+compoundButton.isChecked());
                                for (AnsCheck ans:ans_user) {
                                    if(ans.getId()==answer.getAnswer_id()){
                                        ans.setIsRight(0);
                                    }
                                }
                            }

                        }
                    });
                    radiogrup.addView(radio_btn);
                }
                linearLayout.addView(radiogrup);
            }else if (question.getQuestion_multch()==1){
                for(final TestAnswer answer1:lst_answer){
                    CheckBox check=new CheckBox(this);
                    check.setText(answer1.getAnswer_text());
                    check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if(compoundButton.isChecked()){
                                Log.i("ANS ID",""+answer1.getAnswer_id()+"  "+compoundButton.isChecked());
                                for (AnsCheck ans:ans_user) {
                                    if(ans.getId()==answer1.getAnswer_id()){
                                        ans.setIsRight(1);
                                    }
                                }
                            }else{
                                Log.i("ANS ID",""+answer1.getAnswer_id()+"  "+compoundButton.isChecked());
                                for (AnsCheck ans:ans_user) {
                                    if(ans.getId()==answer1.getAnswer_id()){
                                        ans.setIsRight(0);
                                    }
                                }
                            }
                        }
                    });
                    linearLayout.addView(check);
                }
            }
        }
    }
}
