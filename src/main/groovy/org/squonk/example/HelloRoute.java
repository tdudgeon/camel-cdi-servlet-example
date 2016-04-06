package org.squonk.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

import javax.inject.Inject;

@ContextName("hello")
public class HelloRoute extends RouteBuilder {

    @Inject
    private MyBean myBean;

    @Override
    public void configure() {

        System.out.println("HelloRoute bean: " + myBean);

        restConfiguration().component("servlet");

        rest("/say/")
                .produces("text/plain")
                .get("hello")
                .route()
                .transform().constant("Hello World!")
                .endRest()
                .get("hello/{name}")
                .route()
                .bean("hello")
                .log("${body}");
    }
}