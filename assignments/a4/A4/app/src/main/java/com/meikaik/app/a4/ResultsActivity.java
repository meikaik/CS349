package com.meikaik.app.a4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;


public class ResultsActivity extends AppCompatActivity {

    String username;
    String score;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Get the intent that started this activity
        Intent intent = getIntent();

        // extract the intent value to a string
        username = intent.getStringExtra("username");
        score = intent.getStringExtra("score");
        String totalQuestions = intent.getStringExtra("totalQuestions");
        score = "Your Score: " + score + "/" + totalQuestions;

        SetName();
        SetResult();
    }

    void SetName(){
        TextView label = findViewById( R.id.username);

        // set username
        label.setText(username);
    }

    void SetResult() {
        TextView scoreLabel = findViewById(R.id.score);

        scoreLabel.setText(score);

    }

    void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        username = "";
        intent.putExtra("username", username);
        startActivity(intent);
    }

    void topicSelection(View view) {
        Intent intent = new Intent(this, TopicActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
