package com.example.demo.dao;

import com.example.demo.entity.SysUserRoleEntity;
import com.example.demo.util.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 *
 * @author Frez
 *
 */
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {

    /**
     * 根据用户ID，获取角色ID列表
     *
     * @param userId 用户ID
     * @return 角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

    /**
     * 根据用户ID，获取角色名字列表
     *
     * @param userId 用户ID
     * @return 角色名字列表
     */
    List<String> queryRoleNames(Long userId);

    /**
     * 删除用户与角色关系
     *
     * @param userId 用户
     */
    void deleteNoMapper(Long userId);

    /**
     * 保存用户与角色关系
     *
     * @param map Map<String, Object>
     */
    void save(Map<String, Object> map);
}
