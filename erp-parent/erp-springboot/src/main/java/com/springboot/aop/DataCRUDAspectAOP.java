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
package com.springboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.framework.spring.aspect.DataCRUDAspect;

/**
 * AOP用于拦截DAO层的Model对象并set对应的通用字段
 * @author dongbin
 */
@Aspect
@Component
public class DataCRUDAspectAOP extends DataCRUDAspect {
	
	//日志处理
	private Logger logger = LoggerFactory.getLogger(DataCRUDAspectAOP.class);
	
	@Pointcut("execution(* com.erp.permission.dao.*.*(..))||"
	        + "execution(* com.erp.dataset.dao.*.*(..))||"
	        + "execution(* com.erp.hr.dao.*.*(..))||"
	        + "execution(* com.erp.masterdata.*.dao.*.*(..))||"
	        + "execution(* com.erp.order.*.dao.*.*(..))||"
	        + "execution(* com.erp.finance.*.dao.*.*(..))")
	public void pointcut(){}
	
	@Around("pointcut()")
	public Object doAroundAdvice(ProceedingJoinPoint jp) throws Throwable {
		return super.doAroundAdvice(jp);
	}

}
