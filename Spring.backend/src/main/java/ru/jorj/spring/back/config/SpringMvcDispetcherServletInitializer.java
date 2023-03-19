package ru.jorj.spring.back.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcDispetcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
       return new Class[] {SpringConfig.class};
        //return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
