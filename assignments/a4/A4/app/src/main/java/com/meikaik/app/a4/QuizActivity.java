package com.meikaik.app.a4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.Button;


import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity {

    QuestionLib questions = new QuestionLib();
    TextView question;
    TextView questionNumber;

    String username;;
    int currentQNumber = 1;
    int currentScore = 0;
    int numQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Get the intent that started this activity
        Intent intent = getIntent();

        // extract the intent value to a string
        username = intent.getStringExtra("username");
        numQuestions = Integer.parseInt(intent.getStringExtra("spinnerVal"));

        SetName();
        question = findViewById(R.id.question);
        questionNumber = findViewById(R.id.questionNumber);
        updateQuestion();
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
        if (currentQNumber < numQuestions) {
            currentQNumber++;
            updateQuestion();
        } else {
            boolean[][] answers = questions.getAnswers();
            for (int i = 0; i < answers.length; i++) {
                boolean correct = true;
                for (int j = 0; j < answers[0].length; j++) {
                    if (answers[i][j] != questions.getSelected(i, j)) {
                        correct = false;
                    }
                }
                if (correct) {
                    currentScore += 1;
                }
            }
            Intent intent = new Intent(this, ResultsActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("score", String.valueOf(currentScore));
            intent.putExtra("totalQuestions", String.valueOf(numQuestions));
            startActivity(intent);
        }
    }

    void previous(View view) {
        if (currentQNumber > 1) {
            currentQNumber--;
            updateQuestion();
        }
    }

    void updateQuestion() {
        question.setText(questions.getQuestions()[currentQNumber - 1]);
        String qNumber = String.valueOf(currentQNumber);
        questionNumber.setText(qNumber);
        ImageView image_view = findViewById(R.id.imageView);
        if (questions.getAnswers()[currentQNumber - 1].length > 1) {
            RadioGroup rgp = (RadioGroup) findViewById(R.id.radiogroup);
            rgp.removeAllViews();
            // Set checkboxes
            LinearLayout linearl = (LinearLayout) findViewById(R.id.checkboxgroup);
            LinearLayout.LayoutParams checkParams;
            linearl.removeAllViews();
            for (int i = 0; i <= 3; i++) {
                CheckBox cb = new CheckBox(this);
                cb.setText(questions.getChoices()[currentQNumber - 1][i]);
                cb.setId(i);
                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundbutton, boolean bool) {
                        questions.setSelected(currentQNumber - 1, compoundbutton.getId(), bool);
                    }
                });
                if (questions.getSelected(currentQNumber-1, i)) {
                    cb.setChecked(true);
                }
                checkParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                linearl.addView(cb, checkParams);
            }
        } else {
            // Set radio buttons
            RadioGroup rgp = (RadioGroup) findViewById(R.id.radiogroup);
            rgp.removeAllViews();
            RadioGroup.LayoutParams rprms;
            LinearLayout linearl = (LinearLayout) findViewById(R.id.checkboxgroup);
            linearl.removeAllViews();
            for (int i = 0; i <= 3; i++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(questions.getChoices()[currentQNumber - 1][i]);
                radioButton.setId(i);
                if (questions.getSelected(currentQNumber-1, i)) {
                    radioButton.setChecked(true);
                }
                rprms = new RadioGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                rgp.addView(radioButton, rprms);
            }
            rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    for (int j = 0; j <= 3; j++) {
                        questions.setSelected(currentQNumber - 1, j, false);
                    }
                    questions.setSelected(currentQNumber-1, checkedId, true);
                }
            });
        }
        switch(currentQNumber){
            case 1:
                image_view.setImageResource(R.drawable.image1);
                break;
            case 2:
                image_view.setImageResource(R.drawable.image2);
                break;
            case 3:
                image_view.setImageResource(R.drawable.image3);
                break;
            case 4:
                image_view.setImageResource(R.drawable.image4);
                break;
            case 5:
                image_view.setImageResource(R.drawable.image5);
                break;
        }
        TextView questionFraction = findViewById(R.id.questionfraction);
        questionFraction.setText(currentQNumber + "/" + numQuestions);

        if (currentQNumber == numQuestions) {
            Button nextButton = findViewById(R.id.next);
            nextButton.setText("Finish");
        }
    }
}
