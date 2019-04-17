package tbcloud.agent.admin.common;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author: Dmm
 * @date: 2019/4/3 10:35
 */
@Configuration
public class FilterConfig {

    @Bean
    /**
     * 得到自定义的过滤器
     */
    public Filter tokenFilter(){

        return new TokenFilter();
    }

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(tokenFilter());
        registration.addUrlPatterns("/api/*");
        //todo 待查看
        //registration.addInitParameter("paramName", "paramValue");
        registration.setName("tokenFilter");
        return registration;
    }






}
