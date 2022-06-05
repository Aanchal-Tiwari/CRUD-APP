package io.atlassian.micros.myservice;

import com.atlassian.asap.api.JwtBuilder;
import com.atlassian.asap.api.client.http.AuthorizationHeaderGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "micros.rest.asap.enabled=true", "micros.rest.asap.authorized-issuers[0]=test-client"
})
public class WebServiceAcceptanceTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private AuthorizationHeaderGenerator authorizationHeaderGenerator;
    @Value("${micros.rest.asap.audience}")
    private String audience;

    @Test
    public void restEndpointShouldGetUnauthorizedWhenMissingAuth() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/greetings/charlie", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void restEndpointShouldGetOkWhenAuthPresent() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorizationHeaderGenerator.generateAuthorizationHeader(
                JwtBuilder.newJwt()
                .audience(audience)
                .issuer("test-client")
                .keyId("test-client/local.pem")
                .build()));
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<WebServiceController.SampleResponse> response = restTemplate.exchange("/api/greetings/charlie",
                HttpMethod.GET, entity, WebServiceController.SampleResponse.class);
        assertThat(response.getBody()).hasFieldOrPropertyWithValue("greeting", "Hello charlie");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
