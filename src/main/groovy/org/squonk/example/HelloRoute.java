package org.squonk.example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import javax.inject.Inject;

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
                .threads().executorServiceRef(CustomCamelContext.THREAD_POOL_PROFILE)
                .process(new Processor() {

                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Long l = exchange.getIn().getHeader("foobarbaz", Long.class);
                        System.out.println("L: " + l);
                    }
                })
                .transform().constant("Hello World!")
                .endRest()
                .get("hello/{name}")
                .route()
                .bean("hello")
                .log("${body}");
    }
}
