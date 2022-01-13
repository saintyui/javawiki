package com.saintpress.javawiki.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepo articleRepo;


    public Optional<ArticleEntity> findByTitle(String title){
        return Optional.ofNullable(articleRepo.findByTitle(title));
    }

    public void createArticle(ArticleEntity article){
        Date now = new Date();
        article.setCreated_date(now);
        articleRepo.save(article);
    }

    public void updateArticle(ArticleEntity article){
        Date now = new Date();
        article.setUpdated_date(now);
        articleRepo.save(article);
    }

    @Transactional
    public void deleteArticle(Long idx){
        articleRepo.deleteByIdx(idx);
    }
}
