package com.example.firstProject.controller.dto;

import com.example.firstProject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor // title, content 매개변수로 하는 생성자가 자동으로 만들어짐
@ToString // ToString을 자동적으로 만들어줌.
@Getter
@Setter
public class ArticleForm {
    private Long id;
    private String title;
    private String content;






    public Article toEntity() {
        return new Article(id, title, content); // 생성자에 맞게 값을 작성
    }
}
