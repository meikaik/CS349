package com.meikaik.app.a4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.text.Editable;
import android.graphics.Color;
import android.graphics.PorterDuff;

public class MainActivity extends AppCompatActivity {

    EditText name;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.Name);
        nextButton = findViewById(R.id.NextButton);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 0) {
                    nextButton.setEnabled(false);
                    nextButton.getBackground().clearColorFilter();
                } else {
                    nextButton.setEnabled(true);
                    nextButton.getBackground().setColorFilter(Color.parseColor("#F0D68C"), PorterDuff.Mode.MULTIPLY);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        }

    public void goNextPage(View view){

        String username = name.getText().toString();

        // Set the selected value
        if(username != ""){
            username = " " + username;
            Intent intent = new Intent(this, TopicActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
    }
}
