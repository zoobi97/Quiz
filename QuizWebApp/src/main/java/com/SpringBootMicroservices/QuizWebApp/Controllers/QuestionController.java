package com.SpringBootMicroservices.QuizWebApp.Controllers;

import com.SpringBootMicroservices.QuizWebApp.Models.Question;
import com.SpringBootMicroservices.QuizWebApp.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @GetMapping(value = "/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping(value = "/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable("category") String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping(value = "/addQuestion",consumes = "application/json")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PutMapping(value = "/updateQuestion/{question}")
    public ResponseEntity<Question> updateQuestion(@PathVariable("question") int question, @RequestBody Question questionBody){
        Optional<Question> question1 =  questionService.findById(question);
        Question updateQuestion = null;
        if(question1 != null){
            updateQuestion=  questionService.updateQuestion(questionBody);
            return new ResponseEntity<>(updateQuestion,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(updateQuestion, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/deleteQuestionById/{question}")
    public ResponseEntity deleteQuestionById(@PathVariable("question") int question){
        questionService.deleteQuestionById(question);
        return new ResponseEntity("Question deleted",HttpStatus.OK);
    }
}
