package com.redhat.routes;
import org.apache.camel.builder.RouteBuilder;

public class MessageProducer extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:java?period=5000")
            .routeId("MessagesProducerDemo")
            .setBody()
                .simple("{{body.message}} ${routeId} - ${date:now:yyyy-MM-dd HH:mm:ss}")
            .log("${body}")
            .to("kafka:{{kafka.topic}}?brokers={{kafka.brokers}}");
    }
}
