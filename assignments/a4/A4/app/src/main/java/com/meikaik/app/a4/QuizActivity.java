package com.meikaik.app.a4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    QuestionLib questions = new QuestionLib();

    String username;;
    int currentQNumber = 1;
    int currentScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Get the intent that started this activity
        Intent intent = getIntent();

        // extract the intent value to a string
        username = intent.getStringExtra("username");

        SetName();
    }


    void SetName(){
        TextView label = findViewById(R.id.username);

        // set username
        label.setText(username);
    }

    void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        username = "";
        intent.putExtra("username", username);
        startActivity(intent);
    }

    void next(View view) {
        currentQNumber++;
    }

    void previous(View view) {
        currentQNumber--;
    }
}
