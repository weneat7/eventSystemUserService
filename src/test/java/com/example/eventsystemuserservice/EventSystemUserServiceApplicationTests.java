package com.example.eventsystemuserservice;

import com.example.eventsystemuserservice.repositories.UserRepository;
import com.example.eventsystemuserservice.security.repositories.ClientRepository;
import com.example.eventsystemuserservice.security.services.JpaRegisteredClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

@SpringBootTest
class EventSystemUserServiceApplicationTests {


   private JpaRegisteredClientRepository registeredClientRepository;
    @Autowired
   public EventSystemUserServiceApplicationTests(JpaRegisteredClientRepository registeredClientRepository) {
       this.registeredClientRepository = registeredClientRepository;
   }

    @Test
    void contextLoads() {
    }

    @Test
    void registerClientInDataBase() {
                RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("oidc-client")
                .clientSecret("$2a$12$FRoc8K6PBVSSdmuWOQhTHuCU1yZTc1U3rqW/YJ9pYGxwJy2GwNB92")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("https://oauth.pstmn.io/v1/callback")
                .postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .scope("ADMIN")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();

        registeredClientRepository.save(oidcClient);
    }

}
