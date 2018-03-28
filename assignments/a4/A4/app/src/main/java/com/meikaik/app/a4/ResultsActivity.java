package com.meikaik.app.a4;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;


public class ResultsActivity extends AppCompatActivity {

    String username;
    String score;
    String timetaken;

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

        timetaken = intent.getStringExtra("timeTaken");

        setName();
        setResults();
        setTimeTaken();
        setButtonColor();
    }

    void setName(){
        TextView label = findViewById( R.id.username);

        // set username
        label.setText(username);
    }

    void setResults() {
        TextView scoreLabel = findViewById(R.id.score);

        scoreLabel.setText(score);
    }
    void setTimeTaken() {
        TextView timetakenfield = findViewById(R.id.timetaken);
        String timeTakenFormatted = "Time Taken: " + timetaken;
        timetakenfield.setText(timeTakenFormatted);
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

    private void setButtonColor() {
        Button topic = findViewById(R.id.topicselection);
        topic.getBackground().setColorFilter(Color.parseColor("#F0D68C"), PorterDuff.Mode.MULTIPLY);
        Button logoutButton = findViewById(R.id.logoutbutton);
        logoutButton.getBackground().setColorFilter(Color.parseColor("#F0D68C"), PorterDuff.Mode.MULTIPLY);
    }

}
