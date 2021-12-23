package com.saintpress.javawiki.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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

    @RequestMapping(value="/article/redirect")
    public ModelAndView redirectArticle(@RequestParam("title") String title){
        ModelAndView mav = new ModelAndView("/article/redirect");
        mav.addObject("title", title);
        return mav;
    }

    @RequestMapping(value="/w/{title}")
    public ModelAndView readArticle(@PathVariable("title") String title, RedirectAttributes redirect){
        ModelAndView mav = new ModelAndView("/article/read");
        redirect.addAttribute("title",title);

        Optional <ArticleEntity> opt =  articleService.findByTitle(title);
        if (opt.isPresent()){
            ArticleEntity article = opt.orElse(null);
            mav.addObject("article", article);
        } else {
            mav.setViewName("redirect:/article/redirect");
        }
        return mav;
    }

    @RequestMapping(value="/create/{title}")
    public ModelAndView createArticle(@PathVariable("title") String title){
        ModelAndView mav = new ModelAndView("/article/create");
        return mav;
    }

    @RequestMapping(value="/edit/{title}")
    public ModelAndView editArticle(@PathVariable("title") String title){
        ModelAndView mav = new ModelAndView("/article/edit");
        return mav;
    }
}
