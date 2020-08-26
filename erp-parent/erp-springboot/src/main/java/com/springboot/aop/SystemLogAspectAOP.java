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
import org.springframework.stereotype.Component;

import com.framework.spring.aspect.SystemLogAspect;

/**
 * AOP拦截service层方法用于日志输出
 * @author dongbin
 *
 */
@Aspect
@Component
public class SystemLogAspectAOP extends SystemLogAspect {
	
	//日志处理
	private Logger logger = LoggerFactory.getLogger(SystemLogAspectAOP.class);
	
	@Pointcut("execution(* com.erp.permission.service.*.*(..))||"
	        + "execution(* com.erp.dataset.service.*.*(..))||"
	        + "execution(* com.erp.hr.service.*.*(..))||"
	        + "execution(* com.erp.masterdata.*.service.*.*(..))||"
	        + "execution(* com.erp.order.*.service.*.*(..))||"
	        + "execution(* com.erp.finance.*.service.*.*(..))||"
	        + "execution(* com.erp.inv.*.service.*.*(..))")
	public void pointcut(){}
	
	@Around("pointcut()")
	public Object doAroundAdvice(ProceedingJoinPoint jp) throws Throwable {
		//日志
		Logger logger = LoggerFactory.getLogger(jp.getTarget().getClass());
		
		//获取方法参数
		Object[] args = jp.getArgs();
		
		//返回值
		Object result = null;
		
		try{
			result = jp.proceed(args);
		}catch(Throwable e){
			logger.error("[AOP日志处理]", e);
			throw e;
		}
		
		return result;
	}

}
