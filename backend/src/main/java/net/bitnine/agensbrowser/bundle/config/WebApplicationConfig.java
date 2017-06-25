package net.bitnine.agensbrowser.bundle.config;

//import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 

@Configuration
@EnableWebMvc
public class WebApplicationConfig extends WebMvcConfigurerAdapter {
	
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
			"classpath:/META-INF/resources/", "classpath:/resources/",
			"classpath:/static/", "classpath:/public/" };
	
//    @Bean
//    ServletRegistrationBean h2servletRegistration(){
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
//        registrationBean.addUrlMappings("/h2-console/*");
//        return registrationBean;
//    }

    /*
     * 참고 https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
     */
	@Override
	public void addCorsMappings(CorsRegistry registry) {

		// 일단 모두 해제 상태로 개발하다가 추후 클라이언트의 접근 URL 기준으로 조정 
		registry.addMapping("/**");

//		registry.addMapping("/api/**")
//		.allowedOrigins("http://domain2.com")
//		.allowedMethods("PUT", "DELETE")
//		.allowedHeaders("header1", "header2", "header3")
//		.exposedHeaders("header1", "header2")
//		.allowCredentials(false).maxAge(3600);		
	}

	// index.html을 찾기 위한 리소스 로케이션 등록 
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/webjars/**")) {
			registry.addResourceHandler("/webjars/**").addResourceLocations(
					"classpath:/META-INF/resources/webjars/");
		}
		if (!registry.hasMappingForPattern("/**")) {
			registry.addResourceHandler("/**").addResourceLocations(
					CLASSPATH_RESOURCE_LOCATIONS);
		}
/*		
		if (!registry.hasMappingForPattern("/test/**")) {
			registry.addResourceHandler("/test/**").addResourceLocations(
					"classpath:/static/test/");
		}
		if (!registry.hasMappingForPattern("/graph/**")) {
			registry.addResourceHandler("/graph/**").addResourceLocations(
					"classpath:/static/graph/");
		}
*/		
	}	
}
