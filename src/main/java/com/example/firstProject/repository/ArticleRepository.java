package com.example.firstProject.repository;

import com.example.firstProject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

//JPA 리파지터리에 들어있는 기능들을 사용
//이렇게 사용하면 crudRepository가 제공하는 별도 정의없이 바로 사용이 가능하다.
public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    // Iterable에서 ArrayList로 변경
    ArrayList<Article> findAll();
}
