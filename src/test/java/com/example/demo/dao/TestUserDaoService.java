package com.example.demo.dao;

import com.example.demo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*; //Assertions.이라고 선언하지 않고 사용할 수 있다.

public class TestUserDaoService {

    UserDaoService service = new UserDaoService();

    @Test
    //전체 조회
    public void test_사용자목록조회() {
        List<User> list = service.getUserList();
//        Assertions.assertTrue(list.size() == 3, "초기 사용자는 3명이어야합니다.");
        Assertions.assertEquals(3, list.size(), "초기 사용자는 3명이어야합니다.");
//        Assertions.assertNotEquals(2, list.size(), "초기 사용자는 3명이어야합니다.");

    }

    @Test
    public void test_사용자정보확인() {
        User user = service.getUserList().get(0);
        Assertions.assertTrue(user.getId() == 1);
    }

    @Test
    public void test_사용자상세조회() {
        User user = service.getUser(Integer.valueOf(1));
        assertNotNull(user);
        assertEquals(1, user.getId(), "사용자 id가 잘못되었습니다.");
    }
}
