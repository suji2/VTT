package org.mysite.ysmproject3.config;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

@Configuration
public class YouTubeConfig {

    private final OAuth2AuthorizedClientService clientService;

    public YouTubeConfig(OAuth2AuthorizedClientService clientService) {
        this.clientService = clientService;
    }

//    @Bean
//    public YouTube youTube() {
//        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
//                "google", "user");
//        GoogleCredentials credentials = GoogleCredentials.create(new AccessToken(client.getAccessToken().getTokenValue(), null));
//        return new YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), new HttpCredentialsAdapter(credentials))
//                .setApplicationName("spring-youtube-application")
//                .build();
//    }
}
