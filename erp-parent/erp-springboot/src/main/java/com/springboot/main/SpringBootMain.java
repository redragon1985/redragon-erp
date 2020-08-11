/*
 * Copyright 2020-2021 redragon.dongbin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
import com.framework.dao.DaoSupport;

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
	 * @param hemf
	 * @return
	 */
	@Bean
	@Scope(scopeName="prototype")
	public DaoSupport daoSupport(EntityManagerFactory entityManagerFactory) {
		DaoSupport dao = new DaoSupport();
		dao.setSessionFactory(entityManagerFactory.unwrap(SessionFactory.class));
	    return dao;
	}
	
	/**
	 * @description 声明HibernateTransactionManager bean
	 * @date 2018年5月31日 下午3:55:01
	 * @author dongbin
	 * @param hemf
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
