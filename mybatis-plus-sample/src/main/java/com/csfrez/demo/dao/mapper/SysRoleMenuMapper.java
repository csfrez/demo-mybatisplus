package com.csfrez.demo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csfrez.demo.dao.entity.SysRoleMenuEntity;
import com.csfrez.demo.util.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * 角色与菜单对应关系
 *
 * @author Frez
 *
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {

    /**
     * 根据角色ID，获取菜单ID列表
     *
     * @param roleId 角色ID
     * @return 菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

    /**
     * 删除角色与菜单关系
     *
     * @param roleId 角色ID
     */
    void deleteNoMapper(Long roleId);

    /**
     * 保存角色与菜单关系
     *
     * @param map Map
     */
    void save(Map<String, Object> map);
}
