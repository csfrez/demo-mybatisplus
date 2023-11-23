package com.csfrez.demo;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csfrez.demo.dao.mapper.SysUserLoginLogMapper;
import com.csfrez.demo.dao.entity.SysUserLoginLogEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MybatisPlusSampleApplicationTests {

	@Resource
	private SysUserLoginLogMapper sysUserLoginLogDao;

	@Test
	void contextLoads() {
	}

	@Test
	public void testPage(){
		Page<SysUserLoginLogEntity> page = new Page<>(1, 5);
		sysUserLoginLogDao.selectPage(page, Wrappers.emptyWrapper());
	}

}
