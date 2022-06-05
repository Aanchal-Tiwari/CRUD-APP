package io.atlassian.micros.myservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class WebServiceTestsIT {

    private JsonNode serviceEnv = new ObjectMapper().readTree(new FileReader("./envs.json"));
    private String protocol = serviceEnv.path("MICROS_SERVICE_PROTOCOL").asText();
    private String domainName = serviceEnv.path("MICROS_SERVICE_DOMAIN_NAME").asText();
    private String port = serviceEnv.path("MICROS_SERVICE_PORT").asText();
    private String url = protocol + "://" + domainName + ":" + port;

    public WebServiceTestsIT() throws IOException {}

    @Test
    public void serviceStartsInSandboxSuccessfully() throws IOException {
        HttpUriRequest request = new HttpGet(url + "/heartbeat");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

}
