package com.acme.esb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Generated;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@RegisterForReflection
@Generated
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessagePayloadDTO {

    //keep fields in english and the dataformat in spanish if requirement defines it.
    @JsonProperty("idTransaccion")
    @NotEmpty(message = "field is required")
    private String transactionId;

    @JsonProperty("mensaje")
    @NotEmpty(message = "field is required")
    private String message;

    @JsonProperty("fechaTransaccion")
    private LocalDateTime dateTransaction;


}
