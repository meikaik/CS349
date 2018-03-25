package com.meikaik.app.a4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.view.View;



public class TopicActivity extends AppCompatActivity {

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        // Get the intent that started this activity
        Intent intent = getIntent();

        // extract the intent value to a string
        username = intent.getStringExtra("username");
        SetName();

        String[] arraySpinner = new String[] {
                "1", "2", "3", "4", "5"
        };
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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

    void load(View view) {
        Spinner spinner = findViewById(R.id.spinner);
        String spinnerVal = spinner.getSelectedItem().toString();

        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("spinnerVal", spinnerVal);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
