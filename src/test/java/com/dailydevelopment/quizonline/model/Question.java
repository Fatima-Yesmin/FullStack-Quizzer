package com.dailydevelopment.quizonline.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String question;

    @NotBlank
    private String subject;

    @NotBlank
    private String questionType;

    @NotBlank
    @ElementCollection
    private List<String> choices;

    @NotBlank
    @ElementCollection
    private List<String> correctAnswers;
}
