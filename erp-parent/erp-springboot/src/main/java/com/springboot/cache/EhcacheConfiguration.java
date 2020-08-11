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
