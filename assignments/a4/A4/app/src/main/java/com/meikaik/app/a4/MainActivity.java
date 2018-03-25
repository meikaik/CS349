package com.meikaik.app.a4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.Name);

    }

    public void goNextPage(View view){

        String username = name.getText().toString();

        // Set the selected value
        if(username != ""){
            Intent intent = new Intent(this, TopicActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
    }
}
