package org.mysite.ysmproject3.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.domain.Answer;
import org.mysite.ysmproject3.domain.Question;
import org.mysite.ysmproject3.form.QuestionForm;
import org.mysite.ysmproject3.service.AnswerService;
import org.mysite.ysmproject3.service.QuestionService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @GetMapping("/qna/list")
    @ResponseBody
    public List<Question> questions() {
        return questionService.getAllQuestion();
    }

    @GetMapping("/qna/detail/{id}")
    @ResponseBody
    public Answer Answers(@PathVariable("id") Long id) {
        Optional<Answer> optionalAnswer = answerService.getAnswer(id);
        return optionalAnswer.orElse(null);
    }

    //TEST
    @GetMapping(value = "/question/detail/{id}")
    @ResponseBody
    public Question detail(@PathVariable("id") Long id) {
        Question question = this.questionService.getQuestion(id);
        return question;
    }

//    @PostMapping("/qna/question")
//    public String createQuestion() {
//
//    }

//    @GetMapping("/qna/question")
//    public AnswerForm createQuestion(AnswerForm answerForm) {
//        return answerForm;
//    }
    @PostMapping("/qna/question")
    public String createQuestion(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "redirect:/qna/question";
        }
        this.questionService.create(questionForm.getPassword(), questionForm.getName(),questionForm.getSubject(), questionForm.getText());
        return "redirect:/qna/list";
    }


}
