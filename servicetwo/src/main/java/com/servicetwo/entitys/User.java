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
@Table(name = "app_user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    
    @NotNull
    private String name;
    
    @NotNull
    private String mail;
    
    @NotNull
    private String password;
    
    @NotNull
    private int age;
    
    @ManyToMany
    private List<News> followingNews;
    
    public User()
    {
        followingNews = new ArrayList<News>();
    }
    
    public User(String name, String mail, String password, int age)
    {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.age = age;
        followingNews = new ArrayList<News>();
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<News> getFollowingNews() {
        return followingNews;
    }

    public void setFollowingNews(List<News> followingNews) {
        this.followingNews = followingNews;
    }

    public List<News> addNews(News n) {
        List<News> returnList = getFollowingNews();
        returnList.add(n);
        setFollowingNews(returnList);
        return returnList;
    }
    
    
}
