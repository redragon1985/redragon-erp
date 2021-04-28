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

import com.framework.controller.JsonTextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ApiErrorInterceptor implements HandlerInterceptor {
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(ApiErrorInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	    //http状态码判断
        if(response.getStatus()==503){
            //服务暂不可用
            response.getWriter().write(JsonTextUtil.getErrorJson(101));
            response.getWriter().close();
        }else if(response.getStatus()==408){
            //请求超时
            response.getWriter().write(JsonTextUtil.getErrorJson(102));
            response.getWriter().close();
        }else if(response.getStatus()==501){
            //未知的方法
            response.getWriter().write(JsonTextUtil.getErrorJson(10001));
            response.getWriter().close();
        }
        else{
            /* bbc api请求的验证方式根据具体要求编写
            try{
                //获取请求的uri
                String uri = request.getRequestURI();
                //过滤除了getToken以外的所有请求，
                if(!uri.contains("app/getToken")){
                    String token = request.getParameter("token");
                    String echo = request.getParameter("echo");
                    
                    if(token==null||token.equals("")||echo==null||echo.equals("")){
                        //如果请求参数无效
                        response.getWriter().write(JsonResultUtil.getErrorJson(10002));
                        response.getWriter().close();
                    }else{
                        String redisEcho = RedisUtil.get(token);
                        //判断token回复值等于redis值
                        if(redisEcho!=null&&!redisEcho.equals("")&&redisEcho.equals(echo)){
                            RedisUtil.set(token, "", Integer.parseInt(String.valueOf(RedisUtil.getKeyExpireSeconds(token))));
                        }else{
                            //token回复值无效则报错
                            response.getWriter().write(JsonResultUtil.getErrorJson(1001));
                            response.getWriter().close();
                        }
                    }
                }
            }catch(Exception e){
                //获取请求url
                String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+request.getServletPath();
                //记录日志
                logger.error("===###"+url, e);
                
                //错误信息
                String errMsg = e.getMessage();
                
                response.getWriter().write(JsonResultUtil.getErrorJson(-1, errMsg));
                response.getWriter().close();
            }
            */
        }
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //设置编码格式
	    response.setHeader("Content-Type", "text/html;charset=UTF-8");
        
        //https请求判断
        if(!request.getScheme().equalsIgnoreCase("https")){
            response.getWriter().write(JsonTextUtil.getErrorJson(1003));
            response.getWriter().close();
        }else {
            return true;
        }
        
	    
        return false;
	}

}
