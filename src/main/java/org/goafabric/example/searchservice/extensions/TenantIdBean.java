package org.goafabric.example.searchservice.extensions;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.stereotype.Component;

@Component
@RegisterReflectionForBinding(TenantIdBean.class)
public class TenantIdBean {
    public String getPrefix() {
        return "tenant-" + HttpInterceptor.getTenantId() + "-";
    }
}
