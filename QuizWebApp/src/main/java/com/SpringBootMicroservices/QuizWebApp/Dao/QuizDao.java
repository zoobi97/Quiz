package com.SpringBootMicroservices.QuizWebApp.Dao;

import com.SpringBootMicroservices.QuizWebApp.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer> {

}
