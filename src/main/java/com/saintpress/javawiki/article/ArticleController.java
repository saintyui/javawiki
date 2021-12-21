package com.saintpress.javawiki.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping(value="/home")
    public ModelAndView homeArticle(){
        ModelAndView mav = new ModelAndView("/article/home");
        return mav;
    }

    @RequestMapping(value="/w/{title}")
    public ModelAndView readArticle(@PathVariable("title") String title){
        ModelAndView mav = new ModelAndView("/article/read");

        Optional <ArticleEntity> opt =  articleService.findByTitle(title);
        ArticleEntity article = opt.orElse(null);
        System.out.println(opt);
        mav.addObject("article", article);
        return mav;
    }
}
