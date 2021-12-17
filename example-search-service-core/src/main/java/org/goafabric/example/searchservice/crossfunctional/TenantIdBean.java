package org.goafabric.example.searchservice.crossfunctional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TenantIdBean {
    public String getPrefix() {
        final String prefix = "tenant-" + TenantIdInterceptor.getTenantId() + "-";
        log.debug("got prefix " + prefix);
        return prefix;
    }
}
