package com.nf.mall.config;

        import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

        import javax.servlet.MultipartConfigElement;
        import javax.servlet.ServletRegistration;

public class SystemConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        super.customizeRegistration(registration);
        registration.setMultipartConfig(new MultipartConfigElement(""));
    }

    //只有一个容器  用root 这个来
    //如果有父子容器，root 是放父的，   servlet放子容器
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
