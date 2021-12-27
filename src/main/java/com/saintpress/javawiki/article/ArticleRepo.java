package com.saintpress.javawiki.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends JpaRepository <ArticleEntity, Long> {

    ArticleEntity findByTitle(String title);

    ArticleEntity save(ArticleEntity article);
}
