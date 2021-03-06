package com.levin.framework.hibernate.model;


import com.levin.framework.hibernate.core.annotations.Column;
import com.levin.framework.hibernate.core.annotations.Entity;
import com.levin.framework.hibernate.core.annotations.GenerateValue;
import com.levin.framework.hibernate.core.annotations.Id;

import java.util.Date;

/**
 * 用户实体类
 *
 */
@Entity("t_user")
public class User {
    @Id
    @GenerateValue
    private Long    id;
    private String  name;
    private String  password;
    @Column("myAge")
    private Integer age;
    private Date    createOn;
    private Date    modifiedOn;

    public User() {
    }

    public User(Long id, String name, String password, Integer age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
