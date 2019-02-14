package com.hello.world.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
/**
 * @author jarck-lou
 * @date 2019/02/12 16:46
 **/
@Configuration
@EnableSwagger2
@ConditionalOnExpression("${swagger.enable: true}")
public class Swagger2Config {
  /**
   * 创建docket
   *
   * @return docket
   */
  @Bean
  public Docket api() {

    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.hello.world.web.rest"))
            .paths(PathSelectors.any())
            .build()
            .securitySchemes(securitySchemes())
            .securityContexts(securityContexts());
  }

  /**
   * API基本信息
   *
   * @return apiInfo
   */
  private ApiInfo apiInfo() {
    Contact contact = new Contact("jarck", "https://github.com/Jarck/graphql-spring-boot-demo", null);
    return new ApiInfoBuilder()
            .title("spring boot demo restful api")
            .description("spring boot demo RESTFul API 文档")
            .contact(contact)
            .version("1.0")
            .build();
  }

  /**
   * 设置认证模式
   *
   * @return securitySchemes
   */
  private List<ApiKey> securitySchemes() {
    return newArrayList(
            new ApiKey("auth-token", "auth-token", "header"));
  }

  /**
   * 设置认证上下文
   *
   * @return securityContexts
   */
  private List<SecurityContext> securityContexts() {
    return newArrayList(
            SecurityContext.builder()
                    .securityReferences(defaultAuth())
                    .forPaths(PathSelectors.regex("/api/.*"))
                    .build()
    );
  }

  /**
   * 设置默认的全局鉴权策略
   *
   * @return securityReferences
   */
  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return newArrayList(
            new SecurityReference("auth-token", authorizationScopes));
  }
}
