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
