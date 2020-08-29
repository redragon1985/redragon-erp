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
package com.springboot.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.framework.controller.ControllerSupport;

/**
 * @description Web全局异常处理
 * @date 2020年6月3日
 * @author dongbin
 * 
 */
@Component
public class WebExceptionResolver implements HandlerExceptionResolver {
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(WebExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //错误信息输出
        
        logger.error("===Controller Exception===", ex);
        
        try {
            if(request.getRequestURI().contains("/web/")) {
                if(ex!=null) {
                    if(ex.getCause().getClass()==org.hibernate.exception.ConstraintViolationException.class) {
                        /*
                        HandlerMethod handlerMethod = (HandlerMethod)handler;
                        ControllerSupport controllerSupport = (ControllerSupport)handlerMethod.getBean();
                        if(StringUtils.isNotBlank(controllerSupport.getExceptionRedirectURL())) {
                            ModelAndView mv = new ModelAndView(controllerSupport.getExceptionRedirectURL());
                            mv.addObject("hint", "hint");
                            mv.addObject("alertMessage", "不能插入重复的值");
                            return mv;
                        }
                        */
                        ModelAndView mv = new ModelAndView("forward:/error500");
                        mv.addObject("exception", ex);
                        return mv;
                    }else {
                        ModelAndView mv = new ModelAndView("forward:/error500");
                        mv.addObject("exception", ex);
                        return mv;
                    }
                    
                }
            }
            
        }catch(Exception e) {
        }
        return null;
    }

}
