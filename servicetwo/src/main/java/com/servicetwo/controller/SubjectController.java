/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicetwo.controller;

import com.servicetwo.entity.request.SubjectRequest;
import com.servicetwo.entitys.Subject;
import com.servicetwo.repository.SubjectRepository;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("subjects")
public class SubjectController {
    
    private SubjectRepository subjectRepository;
    private SubjectRequest subjectRequest; 
    
    @Autowired
    public SubjectController(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
        subjectRequest = new SubjectRequest();
    }
    
    // TEST URL: http://localhost:8092/subjects
    @RequestMapping(method = RequestMethod.GET)
    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }
    
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Subject> getAllFromOne() {
        return subjectRequest.getAllFromOne();
    }
    
}
