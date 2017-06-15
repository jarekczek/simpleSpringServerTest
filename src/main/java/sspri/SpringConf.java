package sspri;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "sspri")
@EnableWebMvc
public class SpringConf extends WebMvcConfigurerAdapter {
  @Bean
  ViewResolver urlViewRes() {
    InternalResourceViewResolver vr = new InternalResourceViewResolver();
    vr.setPrefix("/WEB-INF/");
    vr.setSuffix(".jsp");
    return vr;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**/*.html").addResourceLocations("/");
    super.addResourceHandlers(registry);
  }
}
