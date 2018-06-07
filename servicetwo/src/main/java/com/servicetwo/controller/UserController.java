/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicetwo.controller;

import com.servicetwo.repository.NewsRepository;
import com.servicetwo.repository.UserRepository;
import java.util.ArrayList;
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
@RequestMapping("users")
public class UserController {

    private UserRepository userRepository;
    private NewsRepository newsRepository;

    @Autowired
    public UserController(UserRepository userRepository, NewsRepository newsRepository) {
        this.userRepository = userRepository;
        this.newsRepository = newsRepository;
    }
}
