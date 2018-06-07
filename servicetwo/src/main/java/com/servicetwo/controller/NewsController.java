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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Yannick van Leeuwen
 */
@RestController
@RequestMapping("news")
public class NewsController {
    
    private NewsRepository newsRepository;
    
    @Autowired
    public NewsController(NewsRepository newsRepository)
    {
        this.newsRepository = newsRepository;
    }
    
    // TEST URL: http://localhost:8090/news
    @RequestMapping(method = RequestMethod.GET)
    public List<News> findAllNews() {
        return newsRepository.findAll();
    }
    
    //TEST URL: http://localhost:8090/news/findNewsBySubject?naam=Twee
    @RequestMapping(value = "/findNewsBySubject", method = RequestMethod.POST)
    public News findNewsBySubject(@RequestParam("naam") String naam) {
        return newsRepository.findNewsBySubject(naam);
    }
}
