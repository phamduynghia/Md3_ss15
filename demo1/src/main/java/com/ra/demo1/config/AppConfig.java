package com.ra.demo1.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.ra.demo1"})
public class AppConfig implements WebMvcConfigurer,ApplicationContextAware {
     private ApplicationContext applicationContext;
     @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         this.applicationContext = applicationContext;
     }

     @Bean
    public SpringResourceTemplateResolver templateResolver() {
         SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
         templateResolver.setApplicationContext(applicationContext);
         templateResolver.setPrefix("/WEB-INF/views/");
         templateResolver.setSuffix(".html");
         templateResolver.setTemplateMode(TemplateMode.HTML);
         return templateResolver;
     }

     @Bean
    public SpringTemplateEngine templateEngine() {
         SpringTemplateEngine templateEngine = new SpringTemplateEngine();
         templateEngine.setTemplateResolver(templateResolver());
         return templateEngine;
     }

     @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
         ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
         viewResolver.setTemplateEngine(templateEngine());
         viewResolver.setCharacterEncoding("UTF-8");
         return viewResolver;
     }

     @Bean
    public DataSource dataSource() {
         DriverManagerDataSource dataSource = new DriverManagerDataSource();
         dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
         dataSource.setUrl("jdbc:mysql://localhost:3306/demo1?createDatabaseIfNotExist=true");
         dataSource.setUsername("root");
         dataSource.setPassword("10011996");
         return dataSource;
     }

     @Bean
    public LocalSessionFactoryBean sessionFactory() {
         LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
         sessionFactoryBean.setDataSource(dataSource());
         sessionFactoryBean.setPackagesToScan("com.ra.demo1.model.entity");
         Properties properties = new Properties();
         properties.setProperty("hibernate.show_sql", "true");
         properties.setProperty("hibernate.hbm2ddl.auto", "update");
         properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
         sessionFactoryBean.setHibernateProperties(properties);
         return sessionFactoryBean;
     }

     @Bean
    public CommonsMultipartResolver multipartResolver() {
         CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
         multipartResolver.setMaxUploadSizePerFile(10*1024*1024);
         return multipartResolver;
     }

     @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/uploads/**")
                 .addResourceLocations("D:\\java_Md3\\demo1\\src\\main\\webapp\\uploads");
     }
}
