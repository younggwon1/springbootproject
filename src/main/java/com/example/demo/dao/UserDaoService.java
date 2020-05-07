package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> list = new ArrayList<>();

    private static int userCount = 3;

    static{

        list.add(new User(1,"Kenneth", new Date(),"test1","701010-1111111"));
        list.add(new User(2,"Alice", new Date(),"test2","801111-2222222"));
        list.add(new User(3,"Elena", new Date(),"test3","901313-1111111"));

//        users.add(new User(1,"Kenneth", new Date(),"test1","701010-1111111"));
//        users.add(new User(2,"Alice", new Date(),"test2","801111-2222222"));
//        users.add(new User(3,"Elena", new Date(),"test3","901313-1111111"));
    }

    public static List<User> getUserList() {
        return list;
    }

    public User getUser(Integer id) {
        for (User user : list) {
            if(id.equals(user.getId())) {
                return user;
            }
        }
        return null;
    }
}
