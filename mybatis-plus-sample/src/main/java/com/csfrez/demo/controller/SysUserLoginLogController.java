package com.csfrez.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csfrez.demo.dao.entity.SysUserLoginLogEntity;
import com.csfrez.demo.service.SysUserLoginLogService;
import com.csfrez.demo.util.PageUtils;
import com.csfrez.demo.util.RestResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统用户登录日志
 *
 * @author Frez
 *
 */
@RestController
@RequestMapping("/admin/sys/log")
public class SysUserLoginLogController extends AbstractController {

    @Resource
    private SysUserLoginLogService sysUserLoginLogService;

    @RequestMapping("/list")
    @ResponseBody
    public RestResult list(Integer offset, Integer limit, String sort, String order,
                           @RequestParam(name = "search", required = false) String loginIp, HttpServletRequest request) {
        offset = (offset / limit) + 1;
        Boolean flag = null; // 排序逻辑
        if (StringUtils.isNoneBlank(order)) {
            if (order.equalsIgnoreCase("asc")) {
                flag = true;
            } else {
                flag = false;
            }
        }
        Page<SysUserLoginLogEntity> self = sysUserLoginLogService.getSelf(offset, limit, getAdminId(), loginIp, sort, flag);
        PageUtils pageUtil = new PageUtils(self.getRecords(), self.getTotal(), self.getSize(), self.getCurrent());
        return RestResult.ok().put("page", pageUtil);

    }
}
