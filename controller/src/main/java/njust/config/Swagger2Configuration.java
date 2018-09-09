package njust.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author XiangDe Liu qq313700046@icloud.com .
 * @version 1.5
 * created in  20:32 2017/12/14.
 * @since little_mat
 */

@EnableSwagger2
@Configuration
public class Swagger2Configuration {
    private static final String SWAGGER_SCAN_BASE_PACKAGE = "njust.controller";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

//    @Bean
//    public Docket swaggerSpringMvcPlugin() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
//                .paths(PathSelectors.any())
//                .build();
//    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("little_mat说明文档")
                .description("本文档说明了每个接口返回、接收的字段信息。" +
                        "并且制定相应的规范作为前后端交流的渠道。")
                .version("2.0")
                .contact(new Contact("little_mat开发团队","/","luvletterU@njust.edu.cn"))
                .build();
    }
}
