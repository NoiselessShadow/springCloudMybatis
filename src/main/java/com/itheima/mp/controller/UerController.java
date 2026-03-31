package com.itheima.mp.controller;


import cn.hutool.core.bean.BeanUtil;
import com.itheima.mp.domain.dto.UserFormDTO;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.vo.UserVO;
import com.itheima.mp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Api("用户管理接口")
public class UerController {

    private  final UserService userService;

    @PostMapping
    public void saveUser(@RequestBody UserFormDTO userFormDTO){
        User user = BeanUtil.copyProperties(userFormDTO, User.class);
        userService.save(user);
    }

    @DeleteMapping("{id}")
    public void delUserByid(@ApiParam("用户ID") @PathVariable("id") Long id){
        userService.removeById(id);
    }

    @GetMapping("{id}")
    public UserVO getUserByid(@ApiParam("用户ID") @PathVariable("id") Long id){
        User user = userService.getById(id);
        return BeanUtil.copyProperties(user, UserVO.class);
    }

    @GetMapping
    public List<UserVO> getUsersByids(@ApiParam("用户ID") @RequestParam("ids") List<Long> ids){
        List<User> users = userService.listByIds(ids);
        return BeanUtil.copyToList(users, UserVO.class);
    }

}
