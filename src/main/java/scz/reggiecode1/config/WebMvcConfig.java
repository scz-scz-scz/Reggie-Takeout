/*
package scz.reggiecode1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import scz.reggiecode1.common.JacksonObjectMapper;
import scz.reggiecode1.interceptor.LoginCheckInterceptor;

import java.util.List;

//静态资源映射，当前端页面资源不在static包中时，可以这么映射
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    //设置页面资源
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/static/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/static/front/");
    }

    //添加拦截器
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/backend/**","/front/**","/employee/login","/employee/logout");   //静态页面可以展示，请求数据需要拦截
    }

    //扩展mvc的消息转换器
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建新的消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter=new MappingJackson2HttpMessageConverter();
        //设置消息转换器中的对象转换器
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将自定义的消息转换器加入到mvc的消息转换器集合中
        converters.add(0,messageConverter); //设置执行顺序为第一个转换器
    }
}
*/
