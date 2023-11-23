package com.csfrez.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csfrez.demo.dao.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * 角色与菜单对应关系
 *
 * @author Frez
 *
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {

    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     *
     * @param roleId 角色ID
     * @return 菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

}
