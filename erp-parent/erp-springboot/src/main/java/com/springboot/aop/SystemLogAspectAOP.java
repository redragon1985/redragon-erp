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
