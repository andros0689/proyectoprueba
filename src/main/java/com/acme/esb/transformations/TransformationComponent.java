package com.acme.esb.transformations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import org.apache.camel.Exchange;
import org.jboss.logging.Logger;

import com.acme.esb.dto.MessagePayloadDTO;
import com.acme.esb.dto.OutputPayloadDTO;
import com.acme.esb.mapper.PayloadMapper;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.inject.Inject;

import java.util.List;

import org.apache.camel.Body;


@ApplicationScoped
@Named("transformationComponent")
@RegisterForReflection
public class TransformationComponent {

    private static final Logger logger = Logger.getLogger("app-prueba-extension");

    @Inject
    PayloadMapper payloadMapper;

    public List<OutputPayloadDTO> transform(@Body List<MessagePayloadDTO> request) {
        return payloadMapper.tOutputPayloadDTOs(request);
    }

    public void logException(Exchange exchange) {
        Exception exc = exchange.getProperty(Exchange.EXCEPTION_CAUGHT) != null ? (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT) : exchange.getException();
        logger.errorf("Exception class: '%s'", exc.getClass());
        logger.errorf("Exception message '%s'", exc.getMessage() == null ? "No message found." : exc.getMessage());
        logger.errorf("Exception Cause '%s'", exc.getCause() == null ? "No cause found." : exc.getCause().toString());
        logger.errorf(exc, "Error logException:'%s'", exc.getMessage());
    }


}