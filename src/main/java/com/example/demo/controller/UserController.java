package com.example.demo.controller;

import com.example.demo.dao.UserDaoService;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.Resource;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Slf4j
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public MappingJacksonValue getUserList() {
        List<User> list = service.getUserList();

        List<EntityModel<User>> result = new ArrayList<>();

        // foreach를 사용했을 경우
//        list.forEach(user -> {
//            EntityModel<User> model = new EntityModel<>(user);
//            WebMvcLinkBuilder linkTO = linkTo(methodOn(this.getClass()).getUser(user.getId()));
//            model.add(linkTO.withRel("user-datail"));
//
//            result.add(model);
//        });

        for(User user : list) {
            EntityModel<User> model = new EntityModel<>(user);
            WebMvcLinkBuilder linkTO = linkTo(methodOn(this.getClass()).getUser(user.getId()));
            model.add(linkTO.withRel("user-datail"));

            result.add(model);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider provider = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(result);
        mapping.setFilters(provider);

        return mapping;
    }

    // 사용자 개별목록 요청
    @GetMapping("/users/{id}")
    public MappingJacksonValue getUser(@PathVariable Integer id) {
        User user = service.getUser(id);
        //예외처리
        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate");
        FilterProvider provider = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(provider);

        return mapping;
    }

    // 관리자 입장에서 개별 목록 요청
    @GetMapping("/admin/users/{id}")
    public MappingJacksonValue getUserByAdmin(@PathVariable Integer id) {
        User user = service.getUser(id);

        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider provider = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(provider);

        return mapping;
    }

    // Spring-boot 2.2 버전
    @GetMapping("/hateoas/users/{id}")
    public MappingJacksonValue retrieveUser(@PathVariable Integer id) {
        User user = service.getUser(id);

        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        EntityModel<User> model = new EntityModel<>(user);
        WebMvcLinkBuilder linkTO = linkTo(methodOn(this.getClass()).getUserList());

        model.add(linkTO.withRel("all-users"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider provider = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(model);
        mapping.setFilters(provider);

        return mapping;

    }

    @PostMapping("/users")
    public MappingJacksonValue createUser(@RequestBody User createuser) {

        User user = service.create(createuser);
        List<User> list = service.getUserList();

//        user.setId(createuser.getId());
//        user.setName(createuser.getName());
        user.setJoinDate(createuser.getJoinDate());
//        user.setPassword(createuser.getPassword());
//        user.setSsn(createuser.getSsn());

        EntityModel<User> model = new EntityModel<>(user);
        WebMvcLinkBuilder linkTO = linkTo(methodOn(this.getClass()).getUser(user.getId()));
        model.add(linkTO.withRel("user-datail"));
        list.add(user);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider provider = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(provider);

        return mapping;
    }
}
