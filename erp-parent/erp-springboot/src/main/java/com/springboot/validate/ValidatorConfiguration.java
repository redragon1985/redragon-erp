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
package com.springboot.validate;

import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @description 数据校验配置
 * @date 2020年6月9日
 * @author dongbin
 * 
 */
@Configuration
public class ValidatorConfiguration {
    
    /**
     * 
     * @description 根据资源文件返回校验提示信息
     * @date 2020年6月9日
     * @author dongbin
     * @param @return
     * @param @throws Exception
     * @return ResourceBundleMessageSource
     *
     */
    public ResourceBundleMessageSource getResourceBundleMessageSource() throws Exception {  
        ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();  
        rbms.setDefaultEncoding("UTF-8");  
        rbms.setBasenames("conf/validate/validateMessage");  
        return rbms;  
    }
    
    /**
     * 
     * @description 返回数据校验器
     * @date 2020年6月9日
     * @author dongbin
     * @param @return
     * @param @throws Exception
     * @return Validator
     *
     */
    @Bean  
    public Validator getValidator() throws Exception {  
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setProviderClass(HibernateValidator.class);
        validator.setValidationMessageSource(getResourceBundleMessageSource());
        return validator;  
    }

}
