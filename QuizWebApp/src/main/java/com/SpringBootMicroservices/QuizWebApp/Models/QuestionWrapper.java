package com.SpringBootMicroservices.QuizWebApp.Models;

import lombok.Data;

@Data
public class QuestionWrapper {
    private Integer id;
    private String questionTitle,option1,option2,option3,Option4;

    public QuestionWrapper(Integer id, String questionTitle, String option1, String option2,
                           String option3, String option4) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.Option4 = option4;
    }
}
