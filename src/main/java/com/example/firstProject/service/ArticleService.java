package com.example.firstProject.service;

import com.example.firstProject.controller.dto.ArticleForm;
import com.example.firstProject.entity.Article;
import com.example.firstProject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // 서비스 객체 선언
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 수정용 엔티티 만들기
        Article article = dto.toEntity(); // dto를 엔티티로 변환
        log.info("id: {}, article: {}", id, article.toString());

        // 타깃 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리하기
        if(target == null || id!=article.getId()) {
            log.info("잘못된 요청! id: {}, article: {}", id,article.toString());
            return null;
        }

        // 업데이트 및 정상 응답하기
        target.patch(article);
        Article updated = articleRepository.save(article);
        return updated;
    }

    public Article delete(Long id) {
        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if(target == null) {
            return null; // 더이상 ResponseEntity로 본문에 보낼 필요 없음.
        }

        // 대상 삭제
        articleRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //1. dto 묶음을 엔티티 묶음으로 반환
        List<Article> articleList = dtos.stream() // dto를 스트림화
                .map(dto -> dto.toEntity()) // map으로 dto 하나하나 올때마다 dto.toEntity 수행
                .collect(Collectors.toList()); // 매핑한 것을 리스트로 묶음

        //2. 엔티티 묶음을 DB에 저장하기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        //3. 강제 예외 발생시키기
        articleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("결제 실패!")); // 찾는 데이터가 없으면 예외발생

        //4. 결과 값 반환하기
        return articleList;
    }
}
