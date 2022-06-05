
package io.atlassian.micros.myservice;

import com.atlassian.asap.core.server.interceptor.Asap;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greetings")
@Asap
public class WebServiceController {
    private static final Logger log = LoggerFactory.getLogger(WebServiceController.class);

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Receive a greeting")
    public SampleResponse getResponse(@Parameter(description = "Name of the user") @PathVariable("name") String name) {
        log.debug("Received a request");
        return new SampleResponse("Hello " + name);
    }

    @Schema
    public static class SampleResponse {
        private final String greeting;

        SampleResponse(@JsonProperty("greeting") String greeting) {
            this.greeting = greeting;
        }

        @Schema(name = "The greeting produced by the service", required = true)
        public String getGreeting() {
            return greeting;
        }
    }
}
