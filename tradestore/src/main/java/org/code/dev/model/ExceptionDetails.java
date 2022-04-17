package org.code.dev.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "cause",
        "errorMessage"
})
@Data
@Builder
public class ExceptionDetails {

    @JsonProperty("type")
    private String type;

    @JsonProperty("cause")
    private String cause;

    @JsonProperty("errorMessage")
    private String errorMessage;

}
