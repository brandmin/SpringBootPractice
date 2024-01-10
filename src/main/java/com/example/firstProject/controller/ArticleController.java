package com.example.firstProject.controller;

import com.example.firstProject.controller.dto.ArticleForm;
import com.example.firstProject.entity.Article;
import com.example.firstProject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) { // 폼 데이터를 DTO로 받기
        System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겨져 있는지 확인
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        System.out.println(article.toString());

        // 2. 리파지토리로 엔티티를 DB에 저장
        // saved는 엔티티타입, article은 엔티티의 클래스 타입
        Article saved = articleRepository.save(article); // article이라는 엔티티를 저장해 saved 객체에 저장
        System.out.println(saved.toString());
        return "";
    }
}
