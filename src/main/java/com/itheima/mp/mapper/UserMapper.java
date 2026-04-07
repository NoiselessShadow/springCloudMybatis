package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.itheima.mp.domain.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper  extends BaseMapper<User> {

    void customUpdate(@Param(Constants.WRAPPER) LambdaUpdateWrapper<User> lambda,  @Param("balance") int balance);

    @Update("update tb_user  set balance = balance + #{money} where id = #{id} ")
    void addUserMoney(@Param("id") String id, @Param("money") Integer money);
}
