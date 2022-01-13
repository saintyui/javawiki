package com.saintpress.javawiki.article;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Optional;

@Slf4j
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
    public ModelAndView createArticle(@PathVariable("title") String title, String content){
        ModelAndView mav = new ModelAndView("/article/create");
        return mav;
    }

    @RequestMapping(value="/insert/{title}", method=RequestMethod.POST)
    public String insertArticle(@PathVariable("title") String title, ArticleEntity article){
        Optional <ArticleEntity> opt =  articleService.findByTitle(title);
        if (opt.isPresent()){
            return "redirect:/edit/{title}";
        } else {
            article.setTitle(title);
            articleService.createArticle(article);
            return "redirect:/w/{title}";
        }
    }

    @RequestMapping(value="/edit/{title}")
    public ModelAndView editArticle(@PathVariable("title") String title){
        ModelAndView mav = new ModelAndView("/article/edit");

        Optional <ArticleEntity> opt =  articleService.findByTitle(title);
        if (opt.isPresent()){
            ArticleEntity article = opt.orElse(null);
            mav.addObject("article", article);
        } else {
            mav.setViewName("redirect:/article/redirect");
        }

        return mav;
    }

    @RequestMapping(value="/update/{title}", method=RequestMethod.POST)
    public String updateArticle(@PathVariable("title") String title, @RequestParam HashMap map){
        Optional <ArticleEntity> opt =  articleService.findByTitle(title);
        if (opt.isPresent()) {
                String content = (String) map.get("content");
                ArticleEntity ex_article = opt.orElse(null);
                ex_article.setContent(content);
                articleService.updateArticle(ex_article);
                return "redirect:/w/{title}";
            } else {
                return "redirect:/create/{title}";
            }

    }
}
