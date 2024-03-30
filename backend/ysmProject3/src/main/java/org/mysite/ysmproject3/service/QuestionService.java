package org.mysite.ysmproject3.service;

import org.mysite.ysmproject3.domain.Answer;
import org.mysite.ysmproject3.domain.Question;
import org.mysite.ysmproject3.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    //전체 게시글 조회
    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }

//    //게시글 댓글 전체 보기
//    public List<QuestionText> getAllQusetionText(Long question_id){
//        return questionTextRepository.findAll();
//    }

}
