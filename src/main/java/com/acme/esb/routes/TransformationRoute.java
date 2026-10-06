package com.acme.esb.routes;

import jakarta.enterprise.context.ApplicationScoped;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

import com.acme.esb.dto.MessagePayloadDTO;
import com.acme.esb.dto.OutputPayloadDTO;

@ApplicationScoped
public class TransformationRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        JacksonDataFormat inDataFormat = new JacksonDataFormat(MessagePayloadDTO[].class);
        inDataFormat.setAutoDiscoverObjectMapper(true);

        JacksonDataFormat outDataFormat = new JacksonDataFormat(OutputPayloadDTO[].class);
        outDataFormat.setAutoDiscoverObjectMapper(true);

        onException(NullPointerException.class, IllegalArgumentException.class).handled(true)
            .log(LoggingLevel.ERROR, "TRV-02 La estructura del mensaje a procesar presenta errores en la ruta ${routeId}")
            .bean("transformationComponent", "logException")
        .end();

        from("direct:transformationRoute").routeId("transformationRoute")
            .log("processing body ${body}")
            .setVariable("transactionId", simple("${id}"))
            .unmarshal(inDataFormat) 
            .to("bean-validator:validatePayload") 
            .bean("transformationComponent", "transform") 
            .log("sending to producer")
            .log("sending message ${body}")
            .marshal(outDataFormat)
        .end();
    }

}