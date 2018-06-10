/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicetwo.controller;

import com.servicetwo.entity.request.UserRequest;
import com.servicetwo.entitys.Subject;
import com.servicetwo.entitys.User;
import com.servicetwo.repository.NewsRepository;
import com.servicetwo.repository.SubjectRepository;
import com.servicetwo.repository.UserRepository;
import java.util.ArrayList;
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
@RequestMapping("users")
public class UserController {

    private UserRepository userRepository;
    private NewsRepository newsRepository;
    private SubjectRepository subjectRepository;
    private UserRequest userRequest;

    @Autowired
    public UserController(UserRepository userRepository, NewsRepository newsRepository, SubjectRepository subjectRepository) {
        this.userRepository = userRepository;
        this.newsRepository = newsRepository;
        this.subjectRepository = subjectRepository;
        userRequest = new UserRequest();
    }

    // TEST URL: http://localhost:8092/users
    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // TEST URL: http://localhost:8092/users/getAllByName?naam=Yannick
    @RequestMapping(value = "/getAllByName", method = RequestMethod.GET)
    public List<String> getAllFromOneByName(@RequestParam("naam") String naam) {
        List<String> returnList = new ArrayList<>();
        for(Subject s: userRequest.getAllSubjectsPerUserFromOne(naam))
        {
            returnList.add(s.getNaam());
        }
        return returnList;
    }

//     TEST URL: http://localhost:8092/users/refresh
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public User refreshFollowingSubjects(@RequestParam("naam") String naam) {
        User u = userRepository.findOne(userRepository.findByName(naam).getId());
        List<Subject> getAllPerUser = userRequest.getAllSubjectsPerUserFromOne(naam);
        List<Subject> savedObjects = new ArrayList<>();
        for (Subject s : getAllPerUser) {
            for (Subject subject : subjectRepository.findSubjectByNaam(s.getNaam())) {
                Subject toSave = subjectRepository.findOne(subject.getId());
                subjectRepository.save(toSave);
                savedObjects.add(toSave);
            }
        }
        u.setFollowingSubjects(savedObjects);
        userRepository.save(u);
        return u;
    }
}
