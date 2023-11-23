package com.csfrez.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csfrez.demo.dao.mapper.SysUserMapper;
import com.csfrez.demo.dao.entity.SysUserEntity;
import com.csfrez.demo.service.SysUserRoleService;
import com.csfrez.demo.service.SysUserService;
import com.csfrez.demo.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author Frez
 *
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Resource
    private SysUserMapper dao;

    @Override
    public List<String> queryAllPerms(Long userId) {
        return baseMapper.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    public SysUserEntity queryByUserName(String username) {
        return baseMapper.queryByUserName(username);
    }

    @Override
    @Transactional
    public void saveUser(SysUserEntity user) throws Exception {
        user.setCreateTime(DateUtils.getCurrentUnixTime());
        // sha256加密
        user.setPassword(user.getPassword());
        baseMapper.insert(user);
        // 保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] userId) {
        baseMapper.deleteBatch(userId);
    }

    @Override
    public int updatePassword(Long userId, String password, String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return baseMapper.updatePassword(map);
    }

    @Override
    @Transactional
    public void updateUser(SysUserEntity entity) {
        if (StringUtils.isBlank(entity.getPassword())) {
            entity.setPassword(null);
        } else {
            entity.setPassword(entity.getPassword());
        }
        dao.updateUser(entity);
        // 保存用户与角色关系
        sysUserRoleService.saveOrUpdate(entity.getUserId(), entity.getRoleIdList());
    }

    @Override
    public Page<SysUserEntity> queryListByPage(Integer offset, Integer limit, String email, String userName,
                                               String sort, Boolean order) {
        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
        if (StringUtils.isNoneBlank(sort) && null != order) {
            wrapper.orderBy(true, order, sort);
        }
        if (StringUtils.isNoneBlank(userName)) {
            wrapper.like("username", userName);
        }
        if (StringUtils.isNoneBlank(email)) {
            wrapper.like("email", email);
        }
        Page<SysUserEntity> page = new Page<>(offset, limit);
        return this.page(page, wrapper);
    }

    @Override
    public int updateAvatar(SysUserEntity entity) {
        int update = dao.updateAvatar(entity);
        return update;
    }

    @Override
    public int updateStatus(Long userId, int status) {
        return dao.updateStatus(userId, status);
    }

}
