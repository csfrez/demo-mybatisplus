package com.example.demo.dao;

import com.example.demo.entity.SysRoleEntity;
import com.example.demo.util.BaseDao;

/**
 * 角色管理
 *
 * @author Frez
 *
 */
public interface SysRoleDao extends BaseDao<SysRoleEntity> {

    /**
     * 更新角色
     *
     * @param role SysRoleEntity
     */
    void updateNoMapper(SysRoleEntity role);

}
