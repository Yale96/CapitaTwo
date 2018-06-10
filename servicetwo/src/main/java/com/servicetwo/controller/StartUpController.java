/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicetwo.controller;

import com.servicetwo.entity.request.SubjectRequest;
import com.servicetwo.entity.request.UserRequest;
import com.servicetwo.entitys.News;
import com.servicetwo.entitys.Subject;
import com.servicetwo.entitys.User;
import com.servicetwo.repository.NewsRepository;
import com.servicetwo.repository.SubjectRepository;
import com.servicetwo.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Yannick van Leeuwen
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("startup")
public class StartUpController {

    private SubjectRepository subjectRepository;
    private SubjectRequest subjectRequest;
    private NewsRepository newsRepository;
    private UserRequest userRequest;
    private UserRepository userRepository;

    @Autowired
    public StartUpController(SubjectRepository subjectRepository, NewsRepository newsRepository, UserRepository userRepository) {
        subjectRequest = new SubjectRequest();
        userRequest = new UserRequest();
        this.subjectRepository = subjectRepository;
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }
    
    // TEST URL: http://localhost:8092/startup
    @RequestMapping(method = RequestMethod.GET)
    public void initialize(ApplicationArguments aa) {

        for (Subject s : subjectRequest.getAllFromOne()) {
            Subject toPersist = new Subject(s.getNaam(), s.getOmschrijving(), s.getAgeLimit());
            subjectRepository.save(toPersist);
            String ss = "Debug";
        }

        for (User u : userRequest.getAllFromOne()) {
            User toPersist = new User(u.getName(), u.getMail(), u.getPassword(), u.getAge());
            for (Subject s : userRequest.getAllSubjectsPerUserFromOne(u.getName())) {
                List<Subject> toAdd = new ArrayList<>();
                Subject addHere = subjectRepository.findSingleSubjectByNaam(s.getNaam());
                toAdd.add(addHere);
                toPersist.setFollowingSubjects(toAdd);
//                if(subjectRepository.findSingleSubjectByNaam(s.getNaam()) == null)
//                {
//                    subjectRepository.save(s);
//                }
//                else
//                    subjectRepository.save(s);
            }
            userRepository.save(toPersist);
        }

        List<Subject> subjects = subjectRepository.findAll();

        for (Subject s : subjects) {
            News n = new News();
            n.setTitel(s.getNaam() + " in het nieuws");
            n.setContent(s.getNaam() + " heel mooi contentje dit.");
            n.setAgeLimit(0);
            n.setSubject(s);
            newsRepository.save(n);
        }
    }
}
