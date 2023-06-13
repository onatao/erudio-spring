package com.devnatao.springrestfulapicourse.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.devnatao.springrestfulapicourse.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");
	
	@Value("${cors.originPatterns:default}")
	private String corsOriginPatterns = "";
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	
		
	/* VIA QUERY PARAM 
	 * url: http://localhost:8080/api/person/v1?mediaType=json or xml
	  
		configurer.favorParameter(true) // will accepts parameters
			.parameterName("mediaType") // parameters name 
			.ignoreAcceptHeader(true) // ignore header
			.useRegisteredExtensionsOnly(false)
			.defaultContentType(MediaType.APPLICATION_JSON) // default type json
			.mediaType("json", MediaType.APPLICATION_JSON) // first parameter extension, then mediatype
			.mediaType("xml", MediaType.APPLICATION_XML); // first parameter extension, then mediatype
	*/
		
		// VIA HEADER PARAM url: http://localhost:8080/api/person/v1
		configurer.favorParameter(false) // will not accepts parameters
			.ignoreAcceptHeader(false) // accept header
			.useRegisteredExtensionsOnly(false)
			.defaultContentType(MediaType.APPLICATION_JSON) // default type json
			.mediaType("json", MediaType.APPLICATION_JSON) // first parameter extension, then mediatype
			.mediaType("xml", MediaType.APPLICATION_XML)
			.mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML); // first parameter extension, then mediatype		
	}
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		var allowedOrigins = corsOriginPatterns.split(",");
		
		registry.addMapping("/**")
					//.allowedMethods("GET", "POST", "PUT")
					.allowedMethods("*")
					.allowedOrigins(allowedOrigins)
				.allowCredentials(true);
	}	
 
}
