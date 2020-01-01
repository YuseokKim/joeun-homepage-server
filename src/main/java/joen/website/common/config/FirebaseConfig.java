package joen.website.common.config;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfig {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";

	@Bean
	public GoogleCredentials googleCredentials() throws IOException {
		Resource resource = resourceLoader.getResource("classpath:firebase-adminsdk.json");
		
		String [] scopes = {MESSAGING_SCOPE};
		return GoogleCredentials.fromStream(resource.getInputStream())
				.createScoped(Arrays.asList(scopes));
	}
	
	@Bean
	public FirebaseOptions firebaseOptions(@Autowired GoogleCredentials credentials) {
		FirebaseOptions firebaseOptions = new FirebaseOptions.Builder().setCredentials(credentials).build();
		FirebaseApp.initializeApp(firebaseOptions);
		return firebaseOptions;
	}
}
