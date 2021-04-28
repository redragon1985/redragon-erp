/*
 * Copyright 2020-2021 redragon.dongbin
 *
 * This file is part of redragon-erp/赤龙ERP.

 * redragon-erp/赤龙ERP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.

 * redragon-erp/赤龙ERP is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with redragon-erp/赤龙ERP.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.springboot.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import javax.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import com.framework.dao.BasicDao;

/**
 * Springboot主入口
 * @author dongbin
 */
@SpringBootApplication(exclude=ErrorMvcAutoConfiguration.class)
//设置扫描spring mvc及spring相关注解的目录
@ComponentScan(basePackages="com.*")
//设置扫描实体的相关注解的目录
@EntityScan(basePackages="com.*")
public class SpringBootMain extends SpringBootServletInitializer{
	
	/**
	 * 配置Spring
	 */
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootMain.class);
    }
	
	
	/**
	 * @description 声明DaoSupport bean
	 * @date 2018年4月12日 下午5:14:41
	 * @author dongbin
	 * @return
	 */
	@Bean
	@Scope(scopeName="prototype")
	public BasicDao daoSupport(EntityManagerFactory entityManagerFactory) {
		BasicDao dao = new BasicDao();
		dao.setSessionFactory(entityManagerFactory.unwrap(SessionFactory.class));
	    return dao;
	}
	
	/**
	 * @description 声明HibernateTransactionManager bean
	 * @date 2018年5月31日 下午3:55:01
	 * @author dongbin
	 * @return
	 */
	@Bean
	public HibernateTransactionManager hibernateTransactionManager(EntityManagerFactory entityManagerFactory){
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(entityManagerFactory.unwrap(SessionFactory.class));
		return hibernateTransactionManager;
	}

	/**
	 * @description 主程序入口
	 * @date 2018年6月4日 下午7:51:10
	 * @author dongbin
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMain.class, args);

	}

}
