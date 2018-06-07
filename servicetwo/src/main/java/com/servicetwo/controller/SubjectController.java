/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicetwo.controller;

import com.servicetwo.repository.SubjectRepository;
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
    
    private SubjectRepository subjectController;
    
    @Autowired
    public SubjectController(SubjectRepository subjectController){
        this.subjectController = subjectController;
    }
    
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public void getAllFromOne() {
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> subjectsMap = restTemplate.getForObject("http://localhost:8090/subjects", List.class);

        if (subjectsMap != null) {
            for (LinkedHashMap<String, Object> map : subjectsMap) {
                System.out.println("Subject : naam=" + map.get("naam"));
            }
        } else {
            System.out.println("No user exist----------");
        }
    }
    
}
