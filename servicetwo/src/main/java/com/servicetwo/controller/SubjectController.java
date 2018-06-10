/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicetwo.controller;

import com.servicetwo.entity.request.SubjectRequest;
import com.servicetwo.entity.request.UserRequest;
import com.servicetwo.entitys.Subject;
import com.servicetwo.repository.SubjectRepository;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Yannick van Leeuwen
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("subjects")
public class SubjectController {

    private SubjectRepository subjectRepository;
    private SubjectRequest subjectRequest;
    private UserRequest userRequest;

    @Autowired
    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
        subjectRequest = new SubjectRequest();
        userRequest = new UserRequest();
    }

    // TEST URL: http://localhost:8092/subjects
    @RequestMapping(method = RequestMethod.GET)
    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

    // TEST URL: http://localhost:8092/subjects/getAll
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Subject> getAllFromOne() {
        return subjectRepository.findAll();
    }

    @RequestMapping(value = "/getAllByName", method = RequestMethod.GET)
    public List<Subject> getAllFromOneByName(@RequestParam("naam") String naam) {
        return userRequest.getAllSubjectsPerUserFromOne(naam);
    }
}
