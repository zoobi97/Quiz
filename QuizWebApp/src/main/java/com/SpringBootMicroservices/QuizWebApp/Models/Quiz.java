package com.SpringBootMicroservices.QuizWebApp.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ManyToAny;

import java.util.List;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String title,category;
    private Integer numofQuestions;

    @ManyToMany
    private List<Question> questionList;
}
