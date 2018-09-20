package njust.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import njust.inteceptors.AccessProcessInterceptor;
import njust.util.DateStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter
{
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        configurer.enable();
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/file/**")
                .addResourceLocations("file:\\");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(originalAccessHandler());
    }



    @Bean
    public ViewResolver internalResourceViewResolver()
    {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

//    @Bean
//    public MultipartResolver multipartResolver() {
//        return new StandardServletMultipartResolver();
//    }

//    @Bean
//    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
//        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
//        List<HandlerMethodArgumentResolver> argumentResolvers = new LinkedList<HandlerMethodArgumentResolver>();
//        argumentResolvers.add(pageableHandlerMethodArgumentResolver());
//        adapter.setCustomArgumentResolvers(argumentResolvers);
//        return adapter;
//    }

    @Bean
    public HandlerInterceptor originalAccessHandler() {
        return new AccessProcessInterceptor();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(pageableHandlerMethodArgumentResolver());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        converters.add(jsonConverter());
    }

//    @Bean
//    public ObjectMapper objectMapper()
//    {
//        Jackson2ObjectMapperFactoryBean mapperFactoryBean = new Jackson2ObjectMapperFactoryBean();
//        mapperFactoryBean.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        mapperFactoryBean.setDateFormat(new SimpleDateFormat(DateStyle.YYYY_MM_DD_HH_MM.getValue()));
//        mapperFactoryBean.afterPropertiesSet();
//        return mapperFactoryBean.getObject();
//    }

    @Bean
    public Jackson2ObjectMapperFactoryBean objectMapperFactoryBean(){
        Jackson2ObjectMapperFactoryBean objectMapperFactoryBean = new Jackson2ObjectMapperFactoryBean();
        objectMapperFactoryBean.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapperFactoryBean.setDateFormat(new SimpleDateFormat(DateStyle.YYYY_MM_DD_HH_MM.getValue()));
        objectMapperFactoryBean.afterPropertiesSet();
        return objectMapperFactoryBean;
    }


    @Bean
    public MappingJackson2HttpMessageConverter jsonConverter()
    {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaType = new ArrayList<MediaType>();
        supportedMediaType.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaType.add(MediaType.TEXT_PLAIN);
        supportedMediaType.add(MediaType.TEXT_HTML);
        converter.setSupportedMediaTypes(supportedMediaType);
        converter.setObjectMapper(objectMapperFactoryBean().getObject());
        return converter;
    }

//    @Beanw
//    public AuthorizationAttributeSourceAdvisor
//    authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager)
//    {
//        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
//        aasa.setSecurityManager(securityManager);
//        return aasa;
//    }


    @Bean
    public PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver() {
        return new PageableHandlerMethodArgumentResolver();
    }

}
