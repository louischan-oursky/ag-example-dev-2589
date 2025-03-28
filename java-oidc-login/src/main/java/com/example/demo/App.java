package com.example.demo;

import java.util.Scanner;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.AccessTokenRequestParams;

public class App {
	private static final String CLIENT_ID = "baacd0d565eb7f8a";
	private static final String ENDPOINT = "https://louischan.authgear.cloud";
	private static final String REDIRECT_URI = "https://localhost/";

	public static class AuthgearAPI extends DefaultApi20 {
		@Override
		public String getAccessTokenEndpoint() {
			return ENDPOINT + "/oauth2/token";
		}

		@Override
		public String getAuthorizationBaseUrl() {
			return ENDPOINT + "/oauth2/authorize";
		}
	}

	public static void main(String[] args) throws Exception {
		var service = new ServiceBuilder(CLIENT_ID)
			.callback(REDIRECT_URI)
			.defaultScope("openid offline_access")
			.build(new AuthgearAPI());

		var builder = service.createAuthorizationUrlBuilder().initPKCE();
		var authorizationURL = builder.build();
		System.err.printf("Copy the following URL to your browser and proceed the login\n\n");
		System.err.printf("%s\n\n", authorizationURL);
		System.err.printf("After you have finished, paste the authorization code here\n\n");

		var scanner = new Scanner(System.in, "UTF-8");
		var code = scanner.nextLine();

		System.err.printf("\nYou entered: %s\n", code);

		var params = AccessTokenRequestParams.create(code).pkceCodeVerifier(builder.getPkce().getCodeVerifier());
		params.addExtraParameter("client_id", CLIENT_ID);
		params.addExtraParameter("redirect_uri", REDIRECT_URI);
		var accessToken = service.getAccessToken(params);
		System.out.printf("%s\n", accessToken.getRawResponse());
	}
}
