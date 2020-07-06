package com.example.demo;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.SysUserLoginLogDao;
import com.example.demo.entity.SysUserLoginLogEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {

	@Resource
	private SysUserLoginLogDao sysUserLoginLogDao;

	@Test
	void contextLoads() {
	}

	@Test
	public void testPage(){
		Page<SysUserLoginLogEntity> page = new Page<>(1, 5);
		sysUserLoginLogDao.selectPage(page, Wrappers.emptyWrapper());
	}

}
