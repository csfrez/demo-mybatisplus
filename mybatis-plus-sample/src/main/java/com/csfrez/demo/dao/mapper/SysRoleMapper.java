package com.csfrez.demo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csfrez.demo.dao.entity.SysRoleEntity;
import com.csfrez.demo.util.BaseDao;

/**
 * 角色管理
 *
 * @author Frez
 *
 */
public interface SysRoleMapper extends BaseDao<SysRoleEntity> {

    /**
     * 更新角色
     *
     * @param role SysRoleEntity
     */
    void updateNoMapper(SysRoleEntity role);

}
