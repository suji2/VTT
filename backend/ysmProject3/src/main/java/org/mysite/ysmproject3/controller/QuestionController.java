package org.mysite.ysmproject3.controller;

import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.domain.Question;
import org.mysite.ysmproject3.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    // 질문 목록 조회
    //http://localhost:8080/question
    @GetMapping("/question")
    @ResponseBody
    public List<Question> getAllQuestion() {
        return questionService.getAllQuestion();
    }

    //단일 질문 조회
    @GetMapping("/question/{id}")
    @ResponseBody
    public Question getQuestionById(@PathVariable Long id) {
        return questionService.getQuestion(id);
    }

    // 질문 수정
    //http://localhost:8080/question/{id}
    @PutMapping("/question/{id}")
    @ResponseBody
    public Question updateQuestion(@PathVariable Long id,
                                   @RequestParam String title,
                                   @RequestParam String content,
                                   @RequestParam String secretYN) {
        return questionService.updateQuestion(id, title, content, secretYN);
    }

    //질문 삭제
    //http://localhost:8080/question/{id}
    @DeleteMapping("/question/{id}")
    @ResponseBody
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }

}
