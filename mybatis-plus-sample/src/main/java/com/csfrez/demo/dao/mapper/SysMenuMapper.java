package com.csfrez.demo.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csfrez.demo.dao.entity.SysMenuEntity;
import com.csfrez.demo.util.BaseDao;

import java.util.List;

/**
 * 菜单管理
 *
 * @author Frez
 *
 */
public interface SysMenuMapper extends BaseDao<SysMenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     * @return List<SysMenuEntity>
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     *
     * @return List<SysMenuEntity>
     */
    List<SysMenuEntity> queryNotButtonList();
}
