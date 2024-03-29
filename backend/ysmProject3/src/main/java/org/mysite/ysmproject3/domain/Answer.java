package org.mysite.ysmproject3.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "answer")
@Getter
@Setter
public class Answer {

    @Id
    @Column(name = "answer")
    private Integer answer;

    @Column(name = "Field")
    private String field;

    // getters and setters
}