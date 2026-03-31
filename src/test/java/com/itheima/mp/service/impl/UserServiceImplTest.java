package com.itheima.mp.service.impl;

import com.itheima.mp.domain.po.User;
import com.itheima.mp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test

    public  void test(){

        List<User> list = userService.list();
        list.forEach(System.out::println);

    }


}