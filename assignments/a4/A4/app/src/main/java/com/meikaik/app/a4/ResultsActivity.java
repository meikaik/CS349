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
    String totalQuestions;
    int[] timeTakenArray;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Get the intent that started this activity
        Intent intent = getIntent();

        // extract the intent value to a string
        username = intent.getStringExtra("username");
        score = intent.getStringExtra("score");
        timeTakenArray = intent.getIntArrayExtra("timeTakenArray");
        totalQuestions = intent.getStringExtra("totalQuestions");
        score = "Your Score: " + score + "/" + totalQuestions;

        timetaken = intent.getStringExtra("timeTaken");

        setName();
        setResults();
        setTimeTaken();
        setButtonColor();
    }

    private void setName(){
        TextView label = findViewById( R.id.username);

        // set username
        label.setText(username);
    }

    private void setResults() {
        TextView scoreLabel = findViewById(R.id.score);

        scoreLabel.setText(score);
    }
    private void setTimeTaken() {
        TextView timetakenfield = findViewById(R.id.timetaken);
        String timeTakenFormatted = "Time Taken: " + timetaken;
        timetakenfield.setText(timeTakenFormatted);

        TextView perQuestionField = findViewById(R.id.perQuestion);
        String individualTimes = "";
        for (int i = 1; i <= Integer.parseInt(totalQuestions); i++) {
            individualTimes += "Q" + i + ": " + timeTakenArray[i - 1] + "sec ";
        }
        perQuestionField.setText(individualTimes);
    }

    public void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        username = "";
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void topicSelection(View view) {
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
