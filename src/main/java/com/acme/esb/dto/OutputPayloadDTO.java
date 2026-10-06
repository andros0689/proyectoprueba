package com.acme.esb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Generated;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@RegisterForReflection
@Generated
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutputPayloadDTO {

    @JsonProperty("transactionId")
    private String id;

    private LocalDate dateTransaction;

}
