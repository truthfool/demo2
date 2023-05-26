package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;

@SpringBootApplication
public class DemoApplication {
	@Bean
	public FilterRegistrationBean<ForwardedHeaderFilter> forwardedHeaderFilter() {
		FilterRegistrationBean<ForwardedHeaderFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new ForwardedHeaderFilter());
		registrationBean.setOrder(0); // Set the order to ensure this filter is executed first
		return registrationBean;
	}
	@Configuration
	public class FilterConfig {
		@Bean
		public FilterRegistrationBean<ApiOriginFilter> loggingFilter() {
			FilterRegistrationBean<ApiOriginFilter> registrationBean = new FilterRegistrationBean<>();
			registrationBean.setFilter(new ApiOriginFilter());
			registrationBean.addUrlPatterns("/*");
			registrationBean.setOrder(1);
			return registrationBean;
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
