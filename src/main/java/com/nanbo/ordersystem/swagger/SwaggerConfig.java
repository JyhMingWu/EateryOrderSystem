package com.nanbo.ordersystem.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
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

@EnableSwagger2
@Configuration
@EnableSwaggerBootstrapUI
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("资源管理")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nanbo.ordersystem.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("吴志明", "https://github.com/sdcuike", "976703055@qq.com");
        return new ApiInfoBuilder()
                .title("食堂外卖--API接口文档")
                .description("简单优雅的restfun风格")
                .termsOfServiceUrl("https://www.baidu.com")
                .contact(contact)
                .version("1.0")
                .build();
    }

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build()
//                .operationOrdering(new Ordering<Operation>() {
//                    @Override
//                    public int compare(Operation left, Operation right) {
//                        return left.getMethod().name().compareTo(right.getMethod().name());
//                    }
//                });
//    }

//
//    @Bean
//    public Docket createMonitorRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .groupName("实时监测")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.lishiots.dc.monitor.ctl"))
//                .paths(PathSelectors.any())
//            .build();
//    }
//
//    @Bean
//    public Docket createActivitiRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo()).groupName("工作流引擎")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.lishiots.dc.activiti.ctl"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    @Bean
//    public Docket createBaseRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .groupName("kernel模块")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.lishiots.dc.kernel.ctl"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    @Bean
//    public Docket createComplaintRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .groupName("投诉管理")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.lishiots.dc.complaint.ctl"))
//                .paths(PathSelectors.any())
//                .build();
//    }

}
