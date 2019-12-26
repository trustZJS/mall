package com.nf.mall.config;


import config.EnableMybatisSpring;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc   // 等价于xml的mvc:annotation-driven 这个开启之后hi，是关于校验
//扫描dao层
@MapperScan("com.nf.mall.dao")
//扫描service ，controller 等。。。   切面  过滤器
@ComponentScan(value = {"com.nf.mall.service","com.nf.mall.controller"})
@EnableMybatisSpring    //这个只开启了  等一些重复操作的注解

//WebMvcConfigurer 这里面有很多默认实现的接口，
//例如下面的格式化，拦截器，静态资源处理等
public class AppConfig implements WebMvcConfigurer {


    //这是日期的格式化
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    //这是拦截器，，，

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        InterceptorRegistration interceptorRegistry = registry.addInterceptor(new FirstInterceptors());
//        interceptorRegistry.addPathPatterns("/**");
    }

    //这是静态资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //这个是自己的静态资源的存放路径访问设置
        ResourceHandlerRegistration registration = registry.addResourceHandler("/static/**");
        registration.addResourceLocations("classpath:/static/");


        //这个是swagger 静态资源的访问路径设置
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");


    }
}
