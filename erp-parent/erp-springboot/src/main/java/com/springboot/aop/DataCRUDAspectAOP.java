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
	        + "execution(* com.erp.finance..*.dao.*.*(..))||"
	        + "execution(* com.erp.common..*.dao.*.*(..))||"
	        + "execution(* com.erp.ledger..*.dao.*.*(..))||"
	        + "execution(* com.erp.report..*.dao.*.*(..))||"
	        + "execution(* com.erp.inv.*.dao.*.*(..))")
	public void pointcut(){}
	
	@Around("pointcut()")
	public Object doAroundAdvice(ProceedingJoinPoint jp) throws Throwable {
		return super.doAroundAdvice(jp);
	}

}
