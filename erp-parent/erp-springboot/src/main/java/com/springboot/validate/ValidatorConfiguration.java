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
