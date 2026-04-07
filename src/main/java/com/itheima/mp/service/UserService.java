package com.itheima.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.query.UserQuery;
import com.itheima.mp.domain.vo.UserVO;

import java.util.List;

public interface UserService  extends IService<User> {
    void addUserMoney(String id, Integer money);

    List<User> listUser(UserQuery userQuery);
}
