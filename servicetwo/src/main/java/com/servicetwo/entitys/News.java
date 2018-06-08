/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicetwo.entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Yannick van Leeuwen
 */
@Entity
@Table(name = "app_news")
public class News implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    
    @NotNull
    private String titel;
    
    @NotNull
    private String content;
    
    @NotNull
    private int ageLimit;
    
    @NotNull
    @OneToOne
    private Subject subject;
    
    @ManyToMany
    private List<User> followers;
    
    public News()
    {
        followers = new ArrayList<>();
    }
    
    public News(String titel, String content, int ageLimit, Subject subject)
    {
        this.content = content;
        this.ageLimit = ageLimit;
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> addFollower(User u) {
        List<User> returnList = getFollowers();
        returnList.add(u);
        setFollowers(returnList);
        return returnList;
    }
        
}
