package com.csfrez.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csfrez.demo.dao.entity.UserEntity;
import com.csfrez.demo.enums.SexEnum;
import com.csfrez.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 新增
     */
    @RequestMapping("/save")
    public boolean save() {
        UserEntity userEntity = new UserEntity();
        userEntity.setNickname("Frez");
        userEntity.setSex(SexEnum.MAN);
        return userService.saveUser(userEntity);
    }

    /**
     * 修改
     *
     * @param nickname
     * @param id
     */
    @RequestMapping("/update")
    public boolean update(@RequestParam String nickname, @RequestParam Long id) {
        UserEntity userEntity = new UserEntity();
        userEntity.setNickname(nickname);
        userEntity.setId(id);
        return userService.updateById(userEntity);
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping("/delete")
    public boolean delete(@RequestParam Long id) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        return userService.removeById(userEntity);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public List<UserEntity> list() {
        return userService.getList();
    }

    /**
     * 分页列表
     *
     * @param current
     * @param size
     */
    @RequestMapping("/page")
    public Page page(@RequestParam(defaultValue = "1") int current , @RequestParam(defaultValue = "3") int size) {
        return userService.page(new Page<>(current, size), new QueryWrapper(new UserEntity()));
    }

}