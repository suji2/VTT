package org.mysite.ysmproject3.service;

import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.domain.Question;
import org.mysite.ysmproject3.exception.DataNotFoundException;
import org.mysite.ysmproject3.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    //전체 게시글 조회
    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }

    //단일 게시글 조회
    public Question getQuestion(Long id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

//    //게시글 댓글 전체 보기
//    public List<QuestionText> getAllQusetionText(Long question_id){
//        return questionTextRepository.findAll();
//    }

    public void create(String password, String name, String subject, String text) {
        Question q1 = new Question();
        q1.setPassword(password);
        q1.setName(name);
        q1.setSubject(subject);
        q1.setText(text);
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);
    }

}
