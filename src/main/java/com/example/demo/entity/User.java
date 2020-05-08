package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data // setter와 getter를 해결해준다. toString, equals 다 해결
@AllArgsConstructor // Constructor 생성자를 해결
@NoArgsConstructor //default 생성자
//@JsonIgnoreProperties(value = {"password","ssn"})
@JsonFilter("UserInfo")
public class User {
    private Integer id; //기본키로 사용하지만, 사용자가 직접 입력하지 않는다.
    private String name;
    private Date joinDate;

    //@JsonIgnore
    //@NotNull, @Size는 @Valid로 연결되어 적용됨
    @NotNull
    @Size(min = 6, max = 16)
    private String password;
    //@JsonIgnore
    private String ssn;
}
