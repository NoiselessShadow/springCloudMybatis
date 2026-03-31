package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.itheima.mp.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
        User user = new User();
//        user.setId(5L);
        user.setUsername("dadan");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Test
    void testSelectById() {
        User user = userMapper.selectById(5L);
        System.out.println("user = " + user);
    }


    @Test
    void testQueryByIds() {
        List<User> users = userMapper.selectBatchIds(List.of(1L, 2L, 3L, 4L));
        users.forEach(System.out::println);
    }

    @Test
    void testQuerWrapper() {
        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
        lambda.like(User::getUsername, "o");
        lambda.ge(User::getBalance, "1000");
        userMapper.selectList(lambda).forEach(System.out::println);
//        users.forEach(System.out::println);
    }


    @Test
    void testupdateWrapper1() {
        User user = new User();
        LambdaUpdateWrapper<User> lambda = new UpdateWrapper<User>().lambda();
        lambda.set(User::getBalance, 10000);
        lambda.eq(User::getUsername, "jack");
        userMapper.update(user,lambda);
//        users.forEach(System.out::println);
    }

    @Test
    void testupdateWrapper2() {
        User user = new User();
        LambdaUpdateWrapper<User> lambda = new UpdateWrapper<User>().lambda();
        lambda.setSql(true, "balance = balance + 10000");
        lambda.in(User::getId, 1L,2L,3L,4L,5L);
        userMapper.update(user,lambda);
//        users.forEach(System.out::println);
    }


    @Test
    void testcustomUpdateWrapper2() {
         int balance = 1000;
        LambdaUpdateWrapper<User> lambda = new UpdateWrapper<User>().lambda();
        lambda.in(User::getId, 1L,2L,3L,4L,5L);
        userMapper.customUpdate(lambda, balance);
//        users.forEach(System.out::println);
    }


    @Test
    void testUpdateById() {
        User user = new User();
        user.setId(5L);
        user.setBalance(20000);
        userMapper.updateById(user);
    }

    @Test
    void testDeleteUser() {
        userMapper.deleteById(5L);
    }
}