package com.SpringBootMicroservices.QuizWebApp.Services;

import com.SpringBootMicroservices.QuizWebApp.Dao.QuestionDao;
import com.SpringBootMicroservices.QuizWebApp.Models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao repository;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(repository.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        repository.save(question);
        return new ResponseEntity<>("Question added sucessfully",HttpStatus.CREATED);
    }

    public String deleteQuestionById(int question) {
        repository.deleteById(question);
        return "Question deleted";
    }

    public Optional<Question> findById(int question) {
        return repository.findById(question);
    }

    public Question updateQuestion(Question questionBody) {
        Question updated =  repository.save(questionBody);
        return updated;
    }
}
