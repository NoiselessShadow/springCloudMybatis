package com.itheima.mp.controller;


import cn.hutool.core.bean.BeanUtil;
import com.itheima.mp.domain.dto.UserFormDTO;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.query.UserQuery;
import com.itheima.mp.domain.vo.UserVO;
import com.itheima.mp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("新增用户")
    @PostMapping
    public void saveUser(@RequestBody UserFormDTO userFormDTO){
        User user = BeanUtil.copyProperties(userFormDTO, User.class);
        userService.save(user);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("{id}")
    public void delUserByid(@ApiParam("用户ID") @PathVariable("id") Long id){
        userService.removeById(id);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("{id}")
    public UserVO getUserByid(@ApiParam("用户ID") @PathVariable("id") Long id){
        User user = userService.getById(id);
        return BeanUtil.copyProperties(user, UserVO.class);
    }

    @ApiOperation("批量获取用户信息")
    @GetMapping
    public List<UserVO> getUsersByids(@ApiParam("用户ID") @RequestParam("ids") List<Long> ids){
        List<User> users = userService.listByIds(ids);
        return BeanUtil.copyToList(users, UserVO.class);
    }

    @ApiOperation("给指定用户加钱")
    @PostMapping("/{id}/addMoney/{money}")
    public void addUserMoney(@ApiParam("用户ID")@PathVariable("id") String id,  @ApiParam("加的钱")@PathVariable("money") Integer money){
        userService.addUserMoney(id, money);
    }
    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public List<User> listUser(@RequestBody UserQuery userQuery){
        List<User> users = userService.listUser(userQuery);
        return  users;
    }

}
