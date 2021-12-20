package com.saintpress.javawiki.article;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleController {

    @RequestMapping(value="/w/home")
    public ModelAndView readArticle(){
        ModelAndView mav = new ModelAndView("/article/read");
        return mav;
    }
}
