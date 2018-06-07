/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicetwo.entity.request;

import com.fasterxml.classmate.GenericType;
import com.servicetwo.entitys.Subject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Yannick van Leeuwen
 */
public class SubjectRequest {
    
    @Autowired
    public SubjectRequest(){
        
    }
    
    public List<Subject> getAllFromOne() {
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> subjectsMap = restTemplate.getForObject("http://localhost:8090/subjects", List.class);
        
        List<Subject> returnList = new ArrayList<>();
        
        if (subjectsMap != null) {
            for (LinkedHashMap<String, Object> map : subjectsMap) {
                System.out.println("Subject : naam=" + map.get("naam"));
                String omschrijving = map.get("omschrijving").toString();
                String naam = map.get("naam").toString();
                Subject s = new Subject(naam, omschrijving);
                returnList.add(s);
            }
        } else {
            System.out.println("No user exist----------");
        }
        return returnList;
    }

}
