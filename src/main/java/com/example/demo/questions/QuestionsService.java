package com.example.demo.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class QuestionsService {

    private final QuestionsRepository questionsRepository;

    @Autowired
    public QuestionsService(QuestionsRepository questionsRepository) {this.questionsRepository = questionsRepository;}

    public List<Questions> getQuestions(){
        return questionsRepository.findAll();
    }
}
