package org.squonk.example;

import org.apache.camel.builder.ThreadPoolProfileBuilder;
import org.apache.camel.spi.ThreadPoolProfile;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * Created by timbo on 06/04/16.
 */
public class Factory {

    public static final String THREAD_POOL_PROFILE = "customThreadPool";

    @Produces
    @Named(THREAD_POOL_PROFILE)
    ThreadPoolProfile profile() {
        System.out.println("========================== ThreadPoolProfile ==========================");
        ThreadPoolProfile profile = new ThreadPoolProfileBuilder(THREAD_POOL_PROFILE).poolSize(4).maxPoolSize(50).build();
        return profile;
    }
}
