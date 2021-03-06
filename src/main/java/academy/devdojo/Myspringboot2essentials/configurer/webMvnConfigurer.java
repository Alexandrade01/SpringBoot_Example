package academy.devdojo.Myspringboot2essentials.configurer;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class webMvnConfigurer implements WebMvcConfigurer {
		
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolversitens) {
		
		PageableHandlerMethodArgumentResolver pageHandler  = new PageableHandlerMethodArgumentResolver();
		pageHandler.setFallbackPageable(PageRequest.of(0,5));
		resolversitens.add(pageHandler);
	}
}
