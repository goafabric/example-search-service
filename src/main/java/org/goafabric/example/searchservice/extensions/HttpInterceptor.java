package org.goafabric.example.searchservice.extensions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class HttpInterceptor implements WebMvcConfigurer {
    private static final ThreadLocal<String> tenantId = new ThreadLocal<>();
    private static final ThreadLocal<String> userName = new ThreadLocal<>();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
                tenantId.set(request.getHeader("X-TenantId"));
                userName.set(request.getHeader("X-Auth-Request-Preferred-Username"));
                return true;
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
                tenantId.remove();
                userName.remove();
            }
        });
    }

    public static void setTenantId(String tenantId) {
        HttpInterceptor.tenantId.set(tenantId);
    }

    public static String getTenantId() {
        return tenantId.get() != null ? tenantId.get() : "0"; //tdo
    }

}