package com.meikaik.app.a4;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        setName();

        String[] arraySpinner = new String[] {
                "1", "2", "3", "4", "5"
        };
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        setButtonColor();

    }



    public void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        username = "";
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void load(View view) {
        Spinner spinner = findViewById(R.id.spinner);
        String spinnerVal = spinner.getSelectedItem().toString();

        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("spinnerVal", spinnerVal);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    private void setName(){
        TextView label = findViewById(R.id.username);

        // set username
        label.setText(username);
    }

    private void setButtonColor(){
        Button logoutButton = findViewById(R.id.logoutbutton);
        logoutButton.getBackground().setColorFilter(Color.parseColor("#F0D68C"), PorterDuff.Mode.MULTIPLY);
        Button loadButton = findViewById(R.id.loadButton);
        loadButton.getBackground().setColorFilter(Color.parseColor("#F0D68C"), PorterDuff.Mode.MULTIPLY);
    }

}
