package ${package}.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.context.ThemeSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import ${package}.Application;

@Configuration
@ComponentScan(basePackageClasses = Application.class, includeFilters = @Filter(Controller.class), useDefaultFilters = false)
class WebMvcConfig extends WebMvcConfigurationSupport {

	private static final String VIEWS = "/WEB-INF/views/";
	private static final String VIEWS_SUFFIX = ".jsp";

	private static final String RESOURCES_LOCATION = "/resources/";
	private static final String RESOURCES_HANDLER = "/static/**";
	private static final int RESOURCES_CACHE = 3600;
	private static final boolean RESOURCES_CHAIN = true;
	
	private static final String THEME_PARAM = "theme";
	private static final String THEME_DEFAULT = "theme1";
	private static final String THEME_COOKIE = "theme";
	private static final String THEME_PREFIX = "themes/";
	private static final int THEME_MAXAGE = 2400;
	
	private static final String MESSAGE_SOURCE = "/WEB-INF/messages/message";
	private static final String MESSAGE_ENCODING = "UTF-8";
	private static final int MESSAGE_CACHE = 5;
	
	private static final String LOCALE_PARAM = "lang";
	private static final String LOCALE_DEFAULT = "en";
	private static final String LOCALE_COOKIE = "lang";
	private static final int LOCALE_MAXAGE = 4800;
	
	
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix(VIEWS);
		resolver.setSuffix(VIEWS_SUFFIX);
		resolver.setOrder(1);
		return resolver;
	}
	
	@Bean
	public ThemeSource themeSource() {	
		ResourceBundleThemeSource source = new ResourceBundleThemeSource();
		source.setBasenamePrefix(THEME_PREFIX);
		return source;
	}

	@Bean
	public ThemeResolver themeResolver() {
		CookieThemeResolver resolver = new CookieThemeResolver();
		resolver.setCookieMaxAge(THEME_MAXAGE);
		resolver.setCookieName(THEME_COOKIE);
		resolver.setDefaultThemeName(THEME_DEFAULT);
		return resolver;
	}
	
	@Bean
	public LocaleResolver localeResolver() {	
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale(LOCALE_DEFAULT));
		resolver.setCookieName(LOCALE_COOKIE);
		resolver.setCookieMaxAge(LOCALE_MAXAGE);
		return resolver;
	}

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(MESSAGE_SOURCE);
		messageSource.setDefaultEncoding(MESSAGE_ENCODING);
		messageSource.setCacheSeconds(MESSAGE_CACHE);
		return messageSource;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(RESOURCES_HANDLER)
				.addResourceLocations(RESOURCES_LOCATION)
				.setCachePeriod(RESOURCES_CACHE)
				.resourceChain(RESOURCES_CHAIN)
				.addResolver(new GzipResourceResolver())
				.addResolver(new PathResourceResolver());
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// Internationalization and Localization
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName(LOCALE_PARAM);
		registry.addInterceptor(interceptor);
		
		// Theme specific
		ThemeChangeInterceptor themeInterceptor = new ThemeChangeInterceptor();
		themeInterceptor.setParamName(THEME_PARAM);
		registry.addInterceptor(themeInterceptor);
	
	}
	
	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
		requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
		requestMappingHandlerMapping.setUseTrailingSlashMatch(false);
		return requestMappingHandlerMapping;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}

}