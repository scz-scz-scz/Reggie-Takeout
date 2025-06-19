package scz.reggiecode1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import scz.reggiecode1.common.JacksonObjectMapper;
import scz.reggiecode1.interceptor.LoginCheckInterceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
注意：
1.WebMvcConfigurer只能对自动配置类WebMvcAutoConfiguration进行功能完善和修改，无法对自定义的mvc配置类进行功能完善和修改
2.如果有配置类继承了WebMvcConfigurationSupport，那么该类就是自定义mvc配置类，此时自动配置类WebMvcAutoConfiguration就会失效
3.当有自定义mvc配置类时，由于自动配置类WebMvcAutoConfiguration失效，因此无法再使用WebMvcConfigurer接口
*/
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> ExcludePath=new ArrayList<>();
        Collections.addAll(ExcludePath,"/backend/**","/front/**","/employee/login","/employee/logout","/user/sendMsg","/user/login");
        registry.addInterceptor(new LoginCheckInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(ExcludePath);   //静态页面可以展示，请求数据需要拦截
    }

    //扩展mvc的消息转换器
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建新的消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter=new MappingJackson2HttpMessageConverter();
        //设置消息转换器中的对象转换器
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将自定义的消息转换器加入到mvc的消息转换器集合中
        converters.add(0,messageConverter); //设置执行顺序为第一个转换器
    }
}
