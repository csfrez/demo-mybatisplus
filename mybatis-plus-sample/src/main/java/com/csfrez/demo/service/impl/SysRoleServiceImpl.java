package com.csfrez.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csfrez.demo.dao.mapper.SysRoleMapper;
import com.csfrez.demo.dao.entity.SysRoleEntity;
import com.csfrez.demo.service.SysRoleMenuService;
import com.csfrez.demo.service.SysRoleService;
import com.csfrez.demo.service.SysUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author Frez
 *
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Override
    public List<SysRoleEntity> queryList(Map<String, Object> map) {
        return baseMapper.queryList(map);
    }

    @Override
    @Transactional
    public void saveRole(SysRoleEntity role) {
        baseMapper.insert(role);
        // 保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional
    public void update(SysRoleEntity role) {
        baseMapper.updateNoMapper(role);

        // 更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] roleIds) {
        baseMapper.deleteBatch(roleIds);
    }

    @Override
    public Page<SysRoleEntity> queryListByPage(Integer offset, Integer limit, String roleName, String sort,
                                               Boolean order) {
        QueryWrapper<SysRoleEntity> wrapper = new QueryWrapper<>();
        if (StringUtils.isNoneBlank(sort) && null != order) {
            wrapper.orderBy(true, order, sort);
        }
        if (StringUtils.isNoneBlank(roleName)) {
            wrapper.like("role_name", roleName);
        }
        Page<SysRoleEntity> page = new Page<>(offset, limit);
        return this.page(page, wrapper);
    }

}
