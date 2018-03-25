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

    private String Answers[][] = {
            {"Canada"},
            {"Brazil", "Argentina"},
            {"China"},
            {"South Korea"},
            {"South Africa", "United Kingdom"},
    };

    public String[] getQuestions() {
        return Questions;
    }

    public String[][] getChoices() {
        return Choices;
    }

    public String[][] getAnswers() {
        return Answers;
    }

}
