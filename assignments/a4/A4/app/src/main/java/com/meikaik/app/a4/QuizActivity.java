package com.meikaik.app.a4;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.Button;

public class QuizActivity extends AppCompatActivity {

    QuestionLib questions = new QuestionLib();
    TextView question;
    TextView questionNumber;
    Chronometer chrono;


    String username;;
    int currentQNumber = 1;
    int currentScore = 0;
    int numQuestions;

    int[] timeTakenArray = new int[5];

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
        startChronograh();
        updateQuestion();
        setButtonColor();
    }

    public void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        username = "";
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void next(View view) {
        updateChronoTime();
        if (currentQNumber < numQuestions) {
            currentQNumber++;
            updateQuestion();
        } else {
            calculateScore();
            gotoResults();
        }
    }

    public void previous(View view) {
        if (currentQNumber > 1) {
            updateChronoTime();
            currentQNumber--;
            updateQuestion();
        }
    }

    private void SetName(){
        TextView label = findViewById(R.id.username);
        // set username
        label.setText(username);
    }

    private void updateQuestion() {
        setQuestionMetadata();

        removeRadioCheckboxGroup();

        setRadioCheckboxGroup();

        setFlagImage();

        enableDisablePreviousButtion();
    }

    private void updateChronoTime() {
        int elapsedTime = (int) ((SystemClock.elapsedRealtime() - chrono.getBase()))/1000;
        int subtractedTime = 0;
        for (int time : timeTakenArray) {
            subtractedTime += time;
        }
        timeTakenArray[currentQNumber-1] += elapsedTime - subtractedTime;
    }

    private void setButtonColor() {
        Button nextButton = findViewById(R.id.next);
        nextButton.getBackground().setColorFilter(Color.parseColor("#F0D68C"), PorterDuff.Mode.MULTIPLY);
        Button logoutButton = findViewById(R.id.logoutbutton);
        logoutButton.getBackground().setColorFilter(Color.parseColor("#F0D68C"), PorterDuff.Mode.MULTIPLY);
    }

    private void startChronograh() {
        chrono = findViewById(R.id.elapsedtime);
        chrono.start();
    }

    private void calculateScore() {
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
    }

    private void gotoResults() {
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("score", String.valueOf(currentScore));
        intent.putExtra("totalQuestions", String.valueOf(numQuestions));
        intent.putExtra("timeTaken", chrono.getText());
        intent.putExtra("timeTakenArray", timeTakenArray);
        startActivity(intent);
    }

    private void setQuestionMetadata() {
        TextView questionFraction = findViewById(R.id.questionfraction);
        questionFraction.setText(currentQNumber + "/" + numQuestions);

        question.setText(questions.getQuestions()[currentQNumber - 1]);
        String qNumber = String.valueOf(currentQNumber);
        qNumber = qNumber + ": ";
        questionNumber.setText(qNumber);

        if (currentQNumber == numQuestions) {
            Button nextButton = findViewById(R.id.next);
            nextButton.setText("Finish");
        }
    }

    private void removeRadioCheckboxGroup() {
        RadioGroup rgp = (RadioGroup) findViewById(R.id.radiogroup);
        rgp.removeAllViews();
        LinearLayout linearl = (LinearLayout) findViewById(R.id.checkboxgroup);
        linearl.removeAllViews();
    }

    private void setRadioCheckboxGroup() {
        if (!questions.getRadio()[currentQNumber - 1]) {
            redrawCheckboxGroup();
        } else {
            redrawRadioGroup();
        }
    }

    private void redrawCheckboxGroup() {
        LinearLayout linearl = (LinearLayout) findViewById(R.id.checkboxgroup);
        LinearLayout.LayoutParams checkBoxChoices;
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
            checkBoxChoices = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            linearl.addView(cb, checkBoxChoices);
        }
    }

    private void redrawRadioGroup() {
        RadioGroup rgp = (RadioGroup) findViewById(R.id.radiogroup);
        RadioGroup.LayoutParams radioGroupChoices;
        for (int i = 0; i <= 3; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(questions.getChoices()[currentQNumber - 1][i]);
            radioButton.setId(i);
            if (questions.getSelected(currentQNumber-1, i)) {
                radioButton.setChecked(true);
            }
            radioGroupChoices = new RadioGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            rgp.addView(radioButton, radioGroupChoices);
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

    private void setFlagImage() {
        ImageView image_view = findViewById(R.id.imageView);
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
    }

    private void enableDisablePreviousButtion() {
        Button prevButton = findViewById(R.id.previous);
        if (currentQNumber == 1) {
            prevButton.setEnabled(false);
            prevButton.getBackground().clearColorFilter();
        } else {
            prevButton.setEnabled(true);
            prevButton.getBackground().setColorFilter(Color.parseColor("#F0D68C"), PorterDuff.Mode.MULTIPLY);
        }
    }


}
