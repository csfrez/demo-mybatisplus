package com.csfrez.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csfrez.demo.dao.mapper.SysUserLoginLogMapper;
import com.csfrez.demo.dao.entity.SysUserLoginLogEntity;
import com.csfrez.demo.service.SysUserLoginLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 用户登录日志
 *
 * @author Frez
 *
 */
@Service
public class SysUserLoginLogServiceImpl extends ServiceImpl<SysUserLoginLogMapper, SysUserLoginLogEntity>
        implements SysUserLoginLogService {

    @Override
    public Page<SysUserLoginLogEntity> getSelf(Integer offset, Integer limit, Long adminId, String loginIp, String sort,
                                               Boolean order) {
        QueryWrapper<SysUserLoginLogEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", adminId);
        if (StringUtils.isNoneBlank(sort) && null != order) {
            wrapper.orderBy(true, order, sort);
        }
        if (StringUtils.isNoneBlank(loginIp)) {
            wrapper.like("login_ip", loginIp);
        }
        Page<SysUserLoginLogEntity> page = new Page<>(offset, limit);
        return this.page(page, wrapper);
    }

}
