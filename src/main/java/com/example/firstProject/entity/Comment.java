package com.example.firstProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor // 모든 매개변수 생성자 생성
@NoArgsConstructor // 매개변수가 아에 없는 기본 생성자 자동생성

public class Comment {
    @Id // 대표키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 자동으로 1씩 증가
    private Long id;

    @ManyToOne // Comment 엔티티와 Article 엔티티를 다대일 관계로 설정
    @JoinColumn(name="article_id") // 외래키 설정, Article 엔티티의 기본키 매핑
    private Article article; // 해당 댓글의 부모 게시글

    @Column // 해당 필드의 테이블 속성으로 바뀜
    private String nickname; // 작성자
    @Column
    private String body; // 댓글 본문
}
