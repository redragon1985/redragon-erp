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
package com.springboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import redragon.io.Path;

public class GlobalInterceptor implements HandlerInterceptor {
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger("ControllerLogInteceptor");

    //controller执行开始时间
    long beginTimeMills;
    //controller执行结束时间
    long endTimeMills;
    
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
	    try {
	        //controller日志拦截器
	        HandlerMethod handlerMethod = (HandlerMethod)handler;
	        
	        //打印controller执行开始时间
	        logger.error("controller 开始时间"+beginTimeMills+"ms");
	        //打印controller执行结束时间
	        logger.error("controller 结束时间"+endTimeMills+"ms");
	        
	        //打印controller方法执行时间
	        logger.error("controller 执行耗时"+String.valueOf(((double)endTimeMills-(double)beginTimeMills)/1000D)+"s");
	        
	        //logger.error("===Controller Exception===", e);
	        logger.error("=================SpringMVC Controller end=================");
	    }catch(Exception e1) {
	    }
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //404异常处理  
	    if(response.getStatus()==404){
            if(request.getRequestURI().contains("/api/")) {
                response.sendRedirect(Path.getServerURL(request)+"apiError404");
            }else {
                response.sendRedirect(Path.getServerURL(request)+"error404");
            }
        }
	    
	    try {
		    //controller日志拦截器
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            
            //根据ModelAndView获取调转路径并打印
            if(modelAndView!=null) {
                logger.error("Result:"+modelAndView.getViewName());
            }else {
                logger.error("Result:当前返回结果为@ResponseBody");
            }
            
            //controller执行结束时间（视图渲染前）
            endTimeMills = System.currentTimeMillis();
		}catch(Exception e1) {
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    try {
	        HandlerMethod handlerMethod = (HandlerMethod)handler;
	        
	        //controller日志拦截器
	        logger.error("=================SpringMVC Controller begin=================");
	        
	        //找到当前请求的URI并打印
	        logger.error("Request URI:"+request.getRequestURI());
	        
	        //找到运行的Controller对象，并打印其类名
	        logger.error("Controller:"+handlerMethod.getBeanType().getName());
	        
	        //找到运行的Controller对象的方法，并打印其方法名
	        logger.error("Method:"+handlerMethod.getMethod().getName()+"()");
	        
	        //找到这次请求的request中的parameter参数，并打印  
	        MethodParameter[] params = handlerMethod.getMethodParameters();
	        for (MethodParameter param: params){
	            logger.error("Parameters:"+param.getParameter().toString());
	        }
	        
	        //controller执行开始时间
	        beginTimeMills = System.currentTimeMillis();
	    }catch(Exception e1) {
	    }
        
		return true;
	}

}
