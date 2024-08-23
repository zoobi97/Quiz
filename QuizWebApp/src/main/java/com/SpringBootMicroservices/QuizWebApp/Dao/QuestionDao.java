package com.SpringBootMicroservices.QuizWebApp.Dao;

import com.SpringBootMicroservices.QuizWebApp.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String category);

    @Query(value = "Select * from question q where q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery =true )
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
