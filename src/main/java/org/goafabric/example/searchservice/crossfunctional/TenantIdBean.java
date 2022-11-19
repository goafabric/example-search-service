package org.goafabric.example.searchservice.crossfunctional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RegisterReflectionForBinding(TenantIdBean.class)
public class TenantIdBean {
    public String getPrefix() {
        final String prefix = "tenant-" + HttpInterceptor.getTenantId() + "-";
        log.debug("got prefix " + prefix);
        return prefix;
    }
}
