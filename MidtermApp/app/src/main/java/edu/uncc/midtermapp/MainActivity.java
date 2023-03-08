package edu.uncc.midtermapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

import edu.uncc.midtermapp.models.Question;

public class MainActivity extends AppCompatActivity implements edu.uncc.midtermapp.WelcomeFragment.WelcomeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().beginTransaction().add(R.id.containerView, new WelcomeFragment()).commit();
    }


    @Override
    public void sendQuestionsToTrivia(ArrayList<Question> questions) {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerView, TriviaFragment.newInstance(questions)).commit();



    }
}