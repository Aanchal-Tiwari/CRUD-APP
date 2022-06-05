
package io.atlassian.micros.myservice;

import com.atlassian.asap.api.client.http.AuthorizationHeaderGenerator;
import com.atlassian.asap.core.client.http.AuthorizationHeaderGeneratorImpl;
import com.atlassian.asap.core.keys.KeyProvider;
import com.atlassian.asap.core.keys.PemReader;
import com.atlassian.asap.core.keys.privatekey.ClasspathPrivateKeyProvider;
import com.atlassian.asap.core.keys.publickey.ClasspathPublicKeyProvider;
import com.atlassian.asap.nimbus.serializer.NimbusJwtSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.PrivateKey;
import java.security.PublicKey;

@Configuration
public class AsapTestConfiguration {

    @Bean
    public KeyProvider<PublicKey> publicKeyProvider() {
        return new ClasspathPublicKeyProvider("/asap/public-keys/", new PemReader());
    }

    @Bean
    public KeyProvider<PrivateKey> privateKeyProvider() {
        return new ClasspathPrivateKeyProvider("/asap/private-keys/", new PemReader());
    }

    @Bean
    public AuthorizationHeaderGenerator authorizationHeaderGenerator(KeyProvider<PrivateKey> privateKeyProvider) {
        return new AuthorizationHeaderGeneratorImpl(new NimbusJwtSerializer(), privateKeyProvider);
    }

}
