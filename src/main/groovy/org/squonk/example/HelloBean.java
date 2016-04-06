package org.squonk.example;

import org.apache.camel.CamelContext;
import org.apache.camel.Header;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by timbo on 22/02/2016.
 */
@Named("hello")
public class HelloBean {

    @Inject
    CamelContext context;

    public String hello(@Header("name") String name) {
        return "Hello " + name + ", I'm " + context + "!";
    }
}

