package org.squonk.example;

import org.apache.camel.builder.ThreadPoolProfileBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.ThreadPoolProfile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

/**
 * Created by timbo on 06/04/16.
 */
@ApplicationScoped
public class CustomCamelContext extends DefaultCamelContext {

    public static final String THREAD_POOL_PROFILE = "customThreadPool";

    @PostConstruct
    void customize() {
        System.out.println("========================== Using CustomCamelContext ==========================");
        ThreadPoolProfile profile = new ThreadPoolProfileBuilder(THREAD_POOL_PROFILE).poolSize(4).maxPoolSize(50).build();
        getExecutorServiceManager().registerThreadPoolProfile(profile);
    }

    @PreDestroy
    void cleanUp() {
        // ...
    }
}
