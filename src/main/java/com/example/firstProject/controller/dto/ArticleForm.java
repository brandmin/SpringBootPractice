package com.example.firstProject.controller.dto;

import com.example.firstProject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // title, content 매개변수로 하는 생성자가 자동으로 만들어짐
@ToString // ToString을 자동적으로 만들어줌.
public class ArticleForm {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    // 전송받은 제목과 내용을 필드에 저장하는 생성자 추가




    public Article toEntity() {
        return new Article(null, title, content); // 생성자에 맞게 값을 작성
    }
}
