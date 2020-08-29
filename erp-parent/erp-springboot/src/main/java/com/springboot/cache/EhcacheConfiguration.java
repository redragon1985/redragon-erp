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
package com.springboot.cache;

import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Ehcache主配置类
 * @author dongbin
 *
 */
@Configuration
public class EhcacheConfiguration {
	
	@Bean(name="methodCache")
	public EhCacheFactoryBean ehCacheFactoryBean(){
		EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
		ehCacheFactoryBean.setCacheManager(this.ehCacheManagerFactoryBean().getObject());
		ehCacheFactoryBean.setCacheName("springbootCache");
		return ehCacheFactoryBean;
	}
	
	@Bean(name="cacheManager")
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		ClassPathResource classPathResource = new ClassPathResource("conf/ehcache/ehcache.xml");
		ehCacheManagerFactoryBean.setConfigLocation(classPathResource);
		ehCacheManagerFactoryBean.setCacheManagerName("springbootCacheManager");
		return ehCacheManagerFactoryBean;
	}

}
