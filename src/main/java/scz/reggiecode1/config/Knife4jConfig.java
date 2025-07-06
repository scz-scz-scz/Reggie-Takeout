package scz.reggiecode1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                // 接口文档标题
                .info(new Info().title("瑞吉外卖API接口文档")
                        // 接口文档简介
                        .description("瑞吉外卖的所有API接口")
                        // 接口文档版本
                        .version("0.0.1-SNAPSHOT")
                        // 开发者联系方式
                        .contact(new Contact().name("诗长在")));
    }
}
