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
package com.springboot.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @description ajax json post提交@RequestBody参数类型转换
 * @date 2020年7月8日
 * @author dongbin
 * 
 */
@Configuration
public class DateJacksonConverterConf extends JsonDeserializer<Date> {
    
    private static String[] pattern = new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss.S", "yyyy.MM.dd", "yyyy.MM.dd HH:mm", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss.S",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss.S" };

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        Date targetDate = null;
        String originDate = p.getText();
        if (StringUtils.isNotEmpty(originDate)) {
            try {
                long longDate = Long.valueOf(originDate.trim());
                targetDate = new Date(longDate);
            } catch (NumberFormatException e) {
                try {
                    targetDate = DateUtils.parseDate(originDate, DateJacksonConverterConf.pattern);
                } catch (ParseException pe) {
                    throw new IOException(String.format(
                            "'%s' can not convert to type 'java.util.Date',just support timestamp(type of long) and following date format(%s)",
                            originDate, StringUtils.join(pattern, ",")));
                }
            }
        }

        return targetDate;
    }

    @Override
    public Class<?> handledType() {
        return Date.class;
    }
    
    
    @Bean
    public DateJacksonConverterConf dateJacksonConverter() {
        return new DateJacksonConverterConf();
    }

    @Bean
    public Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean(@Autowired DateJacksonConverterConf dateJacksonConverter) {
        Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean = new Jackson2ObjectMapperFactoryBean();
        jackson2ObjectMapperFactoryBean.setDeserializers(dateJacksonConverter);
        return jackson2ObjectMapperFactoryBean;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(@Autowired ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        return mappingJackson2HttpMessageConverter;
    }
}
