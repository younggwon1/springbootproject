package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data // setter와 getter를 해결해준다. toString, equals 다 해결
@AllArgsConstructor // Constructor 생성자를 해결
@NoArgsConstructor //default 생성자
//@JsonIgnoreProperties(value = {"password","ssn"})
@JsonFilter("UserInfo")
public class User {
    private Integer id;
    private String name;
    private Date joinDate;

    //@JsonIgnore
    private String password;
    //@JsonIgnore
    private String ssn;
}
