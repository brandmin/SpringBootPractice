package com.example.firstProject.controller;


import com.example.firstProject.controller.dto.ArticleForm;
import com.example.firstProject.entity.Article;
import com.example.firstProject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Slf4j // 로깅 기능을 위한 어노테이션 추가
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
        //System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겨져 있는지 확인
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. 리파지토리로 엔티티를 DB에 저장
        // saved는 엔티티타입, article은 엔티티의 클래스 타입
        Article saved = articleRepository.save(article); // article이라는 엔티티를 저장해 saved 객체에 저장
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    // @PathVariable: id값을 가져올 수 있는 어노테이션
    public String show(@PathVariable Long id, Model model) {
        // 1. id를 조회해서 데이터 가져오기
        // orElse() 메서드는 해당 id값이 없으면 null로 반환
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록하기
        model.addAttribute("article",articleEntity);

        // 3. 뷰 페이지 반환
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model) {
        //1. 모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();

        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList",articleEntityList);

        //3. 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터 등록
        model.addAttribute("article", articleEntity);

        // 뷰 페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("articles/update")
    public String update(ArticleForm form) { // 매개변수로 DTO 받아오기
        log.info(form.toString());

        //DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 엔티티를 DB에 저장하기
        // 1) DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2) 기존 데이터 값을 갱신하기
        if(target != null) {
            articleRepository.save(articleEntity); //엔티티를 DB에 저장
        }
        return "redirect:/articles/" + articleEntity.getId();
    }
}
