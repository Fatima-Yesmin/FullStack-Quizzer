package com.dailydevelopment.quizonline.service;

import com.dailydevelopment.quizonline.model.Question;
import com.dailydevelopment.quizonline.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public List<String> getAllSubjects() {
        return questionRepository.findDistinctSubject();
    }

    @Override
    public Question updateQuestion(Long id, Question question) {
        Optional<Question> theQuestion = this.getQuestionById(id);
        if (theQuestion.isPresent()) {
            Question updateQuestion = theQuestion.get();
            updateQuestion.setQuestion(question.getQuestion());
            updateQuestion.setChoices(question.getChoices());
            updateQuestion.setCorrectAnswers(question.getCorrectAnswers());
            return questionRepository.save(updateQuestion);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found");
        }
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> getQuestionsForUser(Integer numOfQuestions, String subject) {
        Pageable pageable = PageRequest.of(0, numOfQuestions);
        return questionRepository.findBySubject(subject, pageable).getContent();
    }
}
