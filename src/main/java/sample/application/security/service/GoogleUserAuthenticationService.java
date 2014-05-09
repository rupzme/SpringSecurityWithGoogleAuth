package sample.application.security.service;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;

@Service
public class GoogleUserAuthenticationService {

	@Value("${google.auth.clientId}")
	private String clientId;
	@Value("${google.auth.clientSecret}")
	private String clientSecret;
	@Value("${google.auth.callBackURL}")
	private String callBackURL;
	@Value("${google.auth.scopes}")
	private String scopes;
	
	@Value("${google.api.userInfoURL}")
	private String userInfoURL;

	public Iterable<String> getApplicationScopes(){
		return Arrays.asList(scopes.split(";"));
	}
	
	public String getAuthenticationURL() {

		final GoogleAuthorizationCodeRequestUrl url = getGoogleAuthorizationCodeFlow()
				.newAuthorizationUrl();

		return url.setRedirectUri(callBackURL)
				.setState(generateSecureStateToken()).build();
	}

	private GoogleAuthorizationCodeFlow getGoogleAuthorizationCodeFlow() {
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				new NetHttpTransport(), new JacksonFactory(), clientId,
				clientSecret, getApplicationScopes()).build();

		return flow;
	}

	// TODO Decide whether this is sufficient
	private String generateSecureStateToken() {

		SecureRandom secureRandom = new SecureRandom();

		return "google;" + secureRandom.nextInt();

	}

	// TODO Use this if possible
	private Credential loadGoogleCredentialIfAlreadyKnown(
			GoogleAuthorizationCodeFlow flow, String userId) {
		Credential loadedCredential = null;
		try {
			loadedCredential = flow.loadCredential(userId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loadedCredential;
	}

	public Credential getCredentialForAuthCode(String authCode) {
		GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow = getGoogleAuthorizationCodeFlow();
		GoogleTokenResponse response = null;
		try {
			response = googleAuthorizationCodeFlow.newTokenRequest(authCode)
					.setRedirectUri(callBackURL).execute();
		} catch (IOException e) {
			// TODO Log exception
			e.printStackTrace();
		}
		Credential credential = null;
		;
		try {
			credential = googleAuthorizationCodeFlow.createAndStoreCredential(
					response, null);
		} catch (IOException e) {
			// TODO log exception
			e.printStackTrace();
		}
		return credential;
	}

	public String getGoogleUser(Credential credential) {

		final HttpRequestFactory requestFactory = (new NetHttpTransport())
				.createRequestFactory(credential);
		// Make an authenticated request
		final GenericUrl url = new GenericUrl(userInfoURL);
		HttpRequest request = null;
		try {
			request = requestFactory.buildGetRequest(url);
		} catch (IOException e) {
			// TODO log exception
			e.printStackTrace();
		}
		request.getHeaders().setContentType("application/json");
		String jsonGoogleUser = null;
		try {
			jsonGoogleUser = request.execute().parseAsString();
		} catch (IOException e) {
			// TODO log exception
			e.printStackTrace();
		}

		return jsonGoogleUser;
	}

}
