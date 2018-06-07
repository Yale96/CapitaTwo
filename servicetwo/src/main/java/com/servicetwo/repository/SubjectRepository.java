/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicetwo.repository;

import com.servicetwo.entitys.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Yannick van Leeuwen
 */
public interface SubjectRepository extends JpaRepository<Subject, Long>{
    
}
