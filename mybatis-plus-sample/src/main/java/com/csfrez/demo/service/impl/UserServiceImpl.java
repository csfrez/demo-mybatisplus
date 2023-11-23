package com.csfrez.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csfrez.demo.dao.entity.UserEntity;
import com.csfrez.demo.dao.mapper.UserMapper;
import com.csfrez.demo.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户接口实现
 */
@Service
@DS("slave_1")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @DS("slave_1")
    @Override
    public List<UserEntity> getList() {
        return this.list();
    }

    @DS("master")
    @Override
    public boolean saveUser(UserEntity userEntity) {
        return this.save(userEntity);
    }
}