package org.goafabric.example.searchservice.crossfunctional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TenantIdBean {
    public String getPrefix() {
        log.info("getting prefix ");
        final String prefix = TenantRequestContext.getPrefix();
        log.info("got prefix" + prefix);
        return prefix;
    }
}
