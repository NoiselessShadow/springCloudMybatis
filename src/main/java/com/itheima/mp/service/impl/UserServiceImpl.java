package com.itheima.mp.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.query.UserQuery;
import com.itheima.mp.domain.vo.UserVO;
import com.itheima.mp.mapper.UserMapper;
import com.itheima.mp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
     UserMapper userMapper;



    @Override
    public void addUserMoney(String id, Integer money) {
         User user = this.getById(id);

         if(ObjectUtil.isEmpty(user) ||  user.getStatus().equals("2")){
            throw new RuntimeException("用户信息异常！");
         }

         userMapper.addUserMoney(id,money);
    }

    @Override
    public List<User> listUser(UserQuery userQuery) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        if (ObjectUtil.isNotEmpty(userQuery.getName()))
//            queryWrapper.eq(User::getUsername,userQuery.getName());
//
//        if(ObjectUtil.isNotEmpty(userQuery.getStatus()))
//            queryWrapper.eq(User::getStatus, userQuery.getStatus());
//
//        if (ObjectUtil.isNotEmpty(userQuery.getMinBalance()))
//            queryWrapper.gt(User::getBalance, userQuery.getMinBalance());
//
//        if (ObjectUtil.isNotEmpty(userQuery.getMaxBalance()))
//            queryWrapper.lt(User::getBalance, userQuery.getMaxBalance());
//
//        List<User> users = userMapper.selectList(queryWrapper);
        queryWrapper.eq(ObjectUtil.isNotEmpty(userQuery.getName()), User::getUsername,userQuery.getName())
                .eq(ObjectUtil.isNotEmpty(userQuery.getStatus()), User::getUsername,userQuery.getName())
                .gt(ObjectUtil.isNotEmpty(userQuery.getMinBalance()),User::getBalance, userQuery.getMinBalance())
                .lt(ObjectUtil.isNotEmpty(userQuery.getMinBalance()),User::getBalance, userQuery.getMinBalance());


        List<User> users = userMapper.selectList(queryWrapper);

        return users;
    }
}
