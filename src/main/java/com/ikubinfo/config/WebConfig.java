package com.ikubinfo.config;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.ikubinfo.utils.AppProperties;

@Configuration
@EnableWebMvc
@ComponentScan(AppProperties.PACKAGE_BASE)
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");

	}

	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder jbuilder = new Jackson2ObjectMapperBuilder();
		jbuilder.failOnUnknownProperties(false);
		jbuilder.defaultViewInclusion(true);
		jbuilder.serializationInclusion(JsonInclude.Include.ALWAYS);
		jbuilder.indentOutput(true);

		Hibernate5Module hibernate5Module = new Hibernate5Module();
		hibernate5Module.disable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
		hibernate5Module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);

		jbuilder.modules(hibernate5Module);

		return jbuilder;
	}

	@Bean
	public ObjectMapper objectMapper() {
		return jacksonBuilder().build();
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new ResourceHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter(objectMapper()));
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource msgSource = new ResourceBundleMessageSource();
		msgSource.setBasenames("i18n/messages", "i18n/email");
		msgSource.setDefaultEncoding("UTF-8");
		msgSource.setFallbackToSystemLocale(false);
		msgSource.setUseCodeAsDefaultMessage(true);
		msgSource.setAlwaysUseMessageFormat(true);
		return msgSource;
	}

	@Bean(name = "multipartResolver")
	public StandardServletMultipartResolver resolver() {
		return new StandardServletMultipartResolver();
	}

}
