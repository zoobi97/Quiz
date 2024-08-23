package com.SpringBootMicroservices.QuizWebApp.Services;

import com.SpringBootMicroservices.QuizWebApp.Dao.QuestionDao;
import com.SpringBootMicroservices.QuizWebApp.Dao.QuizDao;
import com.SpringBootMicroservices.QuizWebApp.Models.Question;
import com.SpringBootMicroservices.QuizWebApp.Models.QuestionWrapper;
import com.SpringBootMicroservices.QuizWebApp.Models.Quiz;
import com.SpringBootMicroservices.QuizWebApp.Models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try {
        List<Question> questionList = questionDao.findRandomQuestionsByCategory(category,numQ);

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setNumofQuestions(numQ);
            quiz.setCategory(category);
            quiz.setQuestionList(questionList);
            quizDao.save(quiz);

            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while creating the quiz", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(int quizId) {
        Optional<Quiz> quiz = quizDao.findById(quizId);
        List<Question> list = quiz.get().getQuestionList();
        List<QuestionWrapper> questionWrapperList = new ArrayList<>();

        for(Question q:list){
            QuestionWrapper questionsFiltered = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1()
            ,q.getOption2(),q.getOption3(),q.getOption4());
            questionWrapperList.add(questionsFiltered);
        }

        return new ResponseEntity<>(questionWrapperList,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questionList = quiz.getQuestionList();
        int right=0,i=0;
        for(Response resp:responses){
            if(resp.getResponse().equals(questionList.get(i).getRightAnswer())){
              right++;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
