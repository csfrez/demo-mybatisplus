package com.csfrez.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csfrez.demo.dao.mapper.SysMenuMapper;
import com.csfrez.demo.dao.entity.SysMenuEntity;
import com.csfrez.demo.service.SysMenuService;
import com.csfrez.demo.service.SysRoleMenuService;
import com.csfrez.demo.service.SysUserService;
import com.csfrez.demo.util.Constant;
import com.csfrez.demo.util.EhCacheNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 *
 * @author Frez
 *
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements SysMenuService {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysRoleMenuService sysRoleMenuService;
    @Resource
    private Constant constant;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenuEntity> menuList = baseMapper.queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }
        List<SysMenuEntity> userMenuList = new ArrayList<>();
        for (SysMenuEntity menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {
        String cacheName = EhCacheNames.menuCacheName + userId;
        // 系统管理员，拥有最高权限
        if (userId.equals(constant.adminId)) {
            List<SysMenuEntity> allMenuList = getAllMenuList(null);
            return allMenuList;
        }
        // 用户菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
        List<SysMenuEntity> allMenuList = getAllMenuList(menuIdList);
        return allMenuList;
    }

    @Override
    public List<SysMenuEntity> queryList(Map<String, Object> map) {
        return baseMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return baseMapper.queryTotal(map);
    }

    @Override
    @Transactional
    public Integer deleteBatch(Long[] menuIds) {
        return baseMapper.deleteBatch(menuIds);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList) {
        // 查询根菜单列表
        List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
        // 递归获取子菜单
        getMenuTreeList(menuList, menuIdList);
        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList) {
        List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();

        for (SysMenuEntity entity : menuList) {
            if (entity.getType() == Constant.MenuType.CATALOG.getValue()) {// 目录
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }
}
