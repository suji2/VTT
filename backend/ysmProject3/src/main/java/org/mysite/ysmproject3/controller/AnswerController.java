package org.mysite.ysmproject3.controller;

import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.domain.Question;
import org.mysite.ysmproject3.service.AnswerService;
import org.mysite.ysmproject3.service.QuestionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/qna/answer/{id}")
    public String createAnswer(@PathVariable("id") Long id, @RequestParam(value = "text") String text) {
        Question question = this.questionService.getQuestion(id);
        this.answerService.create(question, text);
        return "redirect:/qna/detail" + id;
    }
}
