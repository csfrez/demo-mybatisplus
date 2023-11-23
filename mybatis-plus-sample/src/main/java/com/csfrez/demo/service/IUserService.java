package com.csfrez.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csfrez.demo.dao.entity.UserEntity;

import java.util.List;

/**
 * 用户服务接口
 */
public interface IUserService extends IService<UserEntity> {

    public List<UserEntity> getList();

    public boolean saveUser(UserEntity userEntity);

}
