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
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Yannick van Leeuwen
 */
public class SubjectRequest {

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
