package org.goafabric.example.searchservice.crossfunctional;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.stereotype.Component;

@Component
@RegisterReflectionForBinding(TenantIdBean.class)
public class TenantIdBean {
    public String getPrefix() {
        return "tenant-" + HttpInterceptor.getTenantId() + "-";
    }
}
