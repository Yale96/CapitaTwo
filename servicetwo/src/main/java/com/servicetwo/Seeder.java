/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicetwo;

import com.servicetwo.entity.request.SubjectRequest;
import com.servicetwo.entitys.Subject;
import com.servicetwo.repository.SubjectRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author yanni
 */
@Component
public class Seeder implements ApplicationRunner{
    
    private SubjectRepository subjectRepository;
    private SubjectRequest subjectRequest;
    
    @Autowired
    public Seeder(SubjectRepository subjectRepository)
    {
        subjectRequest = new SubjectRequest();
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void run(ApplicationArguments aa) {
        
        for(Subject s: subjectRequest.getAllFromOne())
        {
            Subject toPersist = new Subject(s.getNaam(), s.getOmschrijving());
            subjectRepository.save(toPersist);
            String ss = "Debug";
        }
    }
    
}
