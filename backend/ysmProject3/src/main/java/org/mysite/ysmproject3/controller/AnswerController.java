package org.mysite.ysmproject3.controller;

import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.domain.Answer;
import org.mysite.ysmproject3.domain.Member;
import org.mysite.ysmproject3.domain.UserInfoEntity;
import org.mysite.ysmproject3.service.AnswerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;

    // 답변 등록
    //http://localhost:8080/answer/create?title=MyTitle&content=MyContent&member=1&questionNum=1
    @PostMapping("/create")
    public Answer createAnswer(@RequestParam String title,
                               @RequestParam String content,
                               @RequestParam UserInfoEntity userInfoEntity,
                               @RequestParam Long questionNum) {
        return answerService.createAnswer(title, content, userInfoEntity, questionNum);
    }

    //http://localhost:8080/answer/question/{questionNum}
    @GetMapping("/question/{questionNum}")
    public List<Answer> getAnswersByQuestionNum(@PathVariable Long questionNum) {
        return answerService.getAnswersByQuestionNum(questionNum);
    }
}
