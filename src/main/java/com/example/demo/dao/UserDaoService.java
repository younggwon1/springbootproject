package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Component
public class UserDaoService implements IUserService {
    private static List<User> users = new ArrayList<>();

    private static int userCount = 3;

    static{

//        list.add(new User(1,"Kenneth", new Date(),"test1","701010-1111111"));
//        list.add(new User(2,"Alice", new Date(),"test2","801111-2222222"));
//        list.add(new User(3,"Elena", new Date(),"test3","901313-1111111"));

        users.add(new User(1,"Kenneth", new Date(),"test1","701010-1111111"));
        users.add(new User(2,"Alice", new Date(),"test2","801111-2222222"));
        users.add(new User(3,"Elena", new Date(),"test3","901313-1111111"));
    }

    @Override
    public List<User> getUserList() {
        return users;
    }

    @Override
    public User getUser(Integer id) {
        for (User user : users) {
            if(id.equals(user.getId())) {
                return user;
            }
        }
        return null;
    }

    //사용자 생성
    @Override
    public User createUser(User newUser) {
        if(newUser.getId() == null) {
            newUser.setId(users.get(users.size() -1).getId()+1); //id 증가하는 코드 -> db를 사용한다면 자동으로 증가할 것
        }

        users.add(newUser);

        return newUser;
    }

    //사용자 수정
    @Override
    public User modifyUser(User modifyUser) {
        Iterator<User> itUsers = users.iterator();

        while (itUsers.hasNext()) {
            User user = itUsers.next();

            if(user.getId() == modifyUser.getId()) {
                user.setName(modifyUser.getName());
                user.setJoinDate(modifyUser.getJoinDate());
                user.setPassword(modifyUser.getPassword());
                user.setSsn(modifyUser.getSsn());

                return user;
            }
        }
        return null;
    }

    //사용자 삭제
    @Override
    public User removeUser(Integer id) {
        Iterator<User> itUser = users.iterator();

        //순차적으로 데이터를 관리하지 않기 위해서 list 대신 키 값으로 진행하는 map()을 사용하자!
        //list -> 순차적인 데이터 지원O
        //map(key,value) -> 순차적인 데이터 지원X, 중복O
        //set -> 순차적인 데이터 지원X, 중복 허용X
        while (itUser.hasNext()) {
            User user = itUser.next();

            if(user.getId() == id) {
                itUser.remove();
                return user;
            }
        }
        return null;
    }

    //    public User create(User createuser)
//    {
//        users.add(createuser);
//        return createuser;
//    }
}
