package com.redhat.routes;
import org.apache.camel.builder.RouteBuilder;

public class MessageConsumer extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("kafka:{{kafka.topic}}?brokers={{kafka.brokers}}")
            .routeId("MessagesConsumerDemo")
            .log("Mensaje recibido en ${routeId} - ${date:now:yyyy-MM-dd HH:mm:ss}: ${body}");
    }
}
