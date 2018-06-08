package org.squonk.example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import javax.activation.DataHandler;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;

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
                .process((exchange) -> {
                        Long l = exchange.getIn().getHeader("foobarbaz", Long.class);
                        System.out.println("L: " + l);
                })
                .transform().constant("Hello World!")
                .endRest()
                .get("hello/{name}")
                .route()
                .bean("hello")
                .log("${body}");

        rest("/multi/")
                .produces("text/plain")
                .post()
                .route()
                .unmarshal().mimeMultipart()
                .process((exchange) -> {
                    // the body is now the first form field
                    String contentType = exchange.getIn().getHeader(Exchange.CONTENT_TYPE, String.class);
                    System.out.println(String.format("Body Content-Type: %s", contentType));
                    System.out.println(String.format("Body:\n%s", exchange.getIn().getBody(String.class)));

                    // the first attachment is the second form field
                    Map<String,DataHandler> attachments = exchange.getIn().getAttachments();
                    System.out.println(String.format("Attachments: %s", attachments.size()));
                    exchange.getIn().getAttachments().forEach((id, dh) -> {
                        try {
                            Object content = dh.getContent();
                            System.out.println(String.format("%s %s %s\n%s", id, dh.getContentType(), dh.getName(), content.toString()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });
                })
                .transform(constant("OK\n"))
                .endRest();
    }
}
