package com.saintpress.javawiki.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepo articleRepo;

    public Optional<ArticleEntity> findByTitle(String title){
        return Optional.ofNullable(articleRepo.findByTitle(title));
    }
}
