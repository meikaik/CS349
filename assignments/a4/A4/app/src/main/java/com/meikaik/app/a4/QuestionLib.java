package com.meikaik.app.a4;

public class QuestionLib {

    private String Questions[] = {
            "Select the country that has this flag",
            "Select the countries that have these flags",
            "Select the country that has this flag",
            "Select the country that has this flag",
            "Select the countries that have these flags",
    };

    private String Choices[][] = {
            {"Canada", "Taiwan", "China", "Peru"},
            {"Brazil", "Ivory Coast", "Argentina", "Luxembourg"},
            {"Netherlands", "Taiwan", "China", "Slovakia"},
            {"Canada", "India", "Brazil", "South Korea"},
            {"Canada", "Taiwan", "South Africa", "United Kingdom"},
    };

    private boolean Answers[][] = {
            {true, false, false, false},
            {true, false, true, false},
            {false, false, true, false},
            {false, false, false, true},
            {false, false, true, true},
    };

    private boolean Selected[][] = {
            {false, false, false, false},
            {false, false, false, false},
            {false, false, false, false},
            {false, false, false, false},
            {false, false, false, false},
    };

    private boolean radio[] = {
            true, false, true, true, false
    };

    public boolean[] getRadio() {
        return radio;
    }

    public boolean getSelected(int i, int j) {
        return Selected[i][j];
    }

    public void setSelected(int i, int j, boolean val) {
        Selected[i][j] = val;
    }

    public String[] getQuestions() {
        return Questions;
    }

    public String[][] getChoices() {
        return Choices;
    }

    public boolean[][] getAnswers() {
        return Answers;
    }

}
