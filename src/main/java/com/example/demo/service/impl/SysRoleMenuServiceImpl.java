package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.SysRoleMenuDao;
import com.example.demo.entity.SysRoleMenuEntity;
import com.example.demo.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色与菜单对应关系
 *
 * @author Frez
 *
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity>
        implements SysRoleMenuService {

    @Override
    @Transactional
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        if (menuIdList.size() == 0) {
            return;
        }
        // 先删除角色与菜单关系
        baseMapper.deleteNoMapper(roleId);

        // 保存角色与菜单关系
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("menuIdList", menuIdList);
        baseMapper.save(map);
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return baseMapper.queryMenuIdList(roleId);
    }

}
