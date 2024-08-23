package com.SpringBootMicroservices.QuizWebApp.Controllers;

import com.SpringBootMicroservices.QuizWebApp.Models.QuestionWrapper;
import com.SpringBootMicroservices.QuizWebApp.Models.Response;
import com.SpringBootMicroservices.QuizWebApp.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ
     ,@RequestParam String title){
       return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping(value = "getQuizQuestionsById/{quiz}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable("quiz") Integer quizId){
        return quizService.getQuizQuestionsById(quizId);
    }

    @PostMapping(value = "/submitQuiz/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable("id") Integer id,
                                              @RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);
    }
}
