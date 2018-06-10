/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicetwo.controller;

import com.servicetwo.entitys.News;
import com.servicetwo.repository.NewsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Yannick van Leeuwen
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("news")
public class NewsController {
    
    private NewsRepository newsRepository;
    
    @Autowired
    public NewsController(NewsRepository newsRepository)
    {
        this.newsRepository = newsRepository;
    }
    
    // TEST URL: http://localhost:8092/news
    @RequestMapping(method = RequestMethod.GET)
    public List<News> findAllNews() {
        return newsRepository.findAll();
    }
    
    //TEST URL: http://localhost:8092/news/findNewsBySubject?naam=Twee
    @RequestMapping(value = "/findNewsBySubject", method = RequestMethod.GET)
    public List<News> findNewsBySubject(@RequestParam("naam") String naam) {
        return newsRepository.findNewsBySubject(naam);
    }
    
    //TEST URL: http://localhost:8092/news/findNewsByTitle?id=1titel=Twee in het nieuws
    @RequestMapping(value = "/findNewsByTitle", method = RequestMethod.GET)
    public News findNewsByTitle(@RequestParam("id") long id) {
        return newsRepository.findNewsByTitle(id);
    }
}
