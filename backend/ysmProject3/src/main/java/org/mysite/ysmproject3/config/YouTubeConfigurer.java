package org.mysite.ysmproject3.config;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class YouTubeConfigurer {
    private static final String APPLICATION_NAME = "API Sample App";
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public static YouTube getService() throws IOException, GeneralSecurityException {
        Credential credential = authorize();
        return new YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private static Credential authorize() throws IOException, GeneralSecurityException {
        // Load client secrets.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, "your-client-id",
                "your-client-secret", List.of("https://www.googleapis.com/auth/youtube.readonly"))
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
}
