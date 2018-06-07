/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicetwo.repository;

import com.servicetwo.entitys.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Yannick van Leeuwen
 */
public interface NewsRepository extends JpaRepository<News, Long> {
     @Query("SELECT n FROM News n WHERE LOWER(n.subject.naam) = LOWER(:naam)")
    public News findNewsBySubject(@Param("naam") String naam);
}
