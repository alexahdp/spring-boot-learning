package com.alexahdp.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration(proxyBeanMethods = true)
//@ComponentScan(
//        basePackages = "com.alexahdp.spring",
//        useDefaultFilters = false,
//        includeFilters = {
//                @Filter(type = FilterType.ANNOTATION, value = Component.class),
////                @Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class)
////                @Filter(type = FilterType.REGEX, value = "com\\..+Repository"),
//        }
//)
public class ApplicationConfiguration {
}
