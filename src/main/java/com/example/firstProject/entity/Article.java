package com.example.firstProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 모든 생성자를 대체하는 어노테이션
@NoArgsConstructor // 기본 생성자 코드를 추가 안해도 되는 어노테이션
@ToString
@Entity
@Getter // 생성자를 추가하지 않아도 자동적으로 추가되는 어노테이션
public class Article {
    @Id // 엔티티 대표값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동생성기능 추가, DB가 id 자동생성
    private Long id;

    @Column // title 필드 선언, 테이블의 title열과 연결됨
    private String title;
    @Column
    private String content;


    public void patch(Article article) {
        if(article.title != null) {
            this.title = article.title;
        }

        if(article.content != null) {
            this.content = article.content;
        }
    }
}
