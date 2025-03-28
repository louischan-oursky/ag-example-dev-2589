package com.example.demo;

import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.JsonWebSignature;

public class App {
	private static final String ID_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJVU0VSX0lEIn0.PaJKmgu-rBT1YI2OWj3UH75bd60dMhJTDQFQJ9d1GYh_NkZ-2PZ9dsUdcOaZnux_R7cG3m6Xib5my-BnZgXohwrVPLkQbsFQ-kefeP79wZSvbmcQH8EwXFGxfMe9EB6CbLc1VTeYZ9zn7CsIq70sn4WQXhgaPGU06jVhCY0Bc9Unc-EMdHkMELoySfPkgkryVnsDI7AYHhoGQZr5704Uq6CHdHoK6zzVLK0KGNvWb9Ow_I3Q7giwGwzLTturti7E12pr1wq2-uOje1YXhtHHON_zYOin0NvgXWcYR3JcuHkKuwPfLtGgs7PXlyceqiFewpAkwpnla3FxLz-0zZU54w";

	private static final String SIGN_KEY = """
{"e":"AQAB","kty":"RSA","n":"3mHo42X6FLWH_Ab2rDORRyVgcj83_QZq5p3f4G61hsbvWWjbuX6jn9QzlwWgEsV3EL8dUERbR1j1-vSuJSRTy2uji0EbHdmA-NCuOr3K42rCPwuu8CIf8KZa2vMrFppe9tLBs1B2cPt8HYGntiiaWBoavq94Y-dE_iAg_nKjBRFlY5udwk5XjsnpgFoxziiQk4w1jf9Teda4uGqfPNhWXn78Ueb5TrOepxq3Bo31ZovRydbBsuLWybm1V3e-kRBsI7KlMwkwrj5TlaJHrHlcWfjLDp_xwE91DklOAnYtmy4RPw8jzM6es4f3dNhXhVAhfpYpS0gg_4YQFqyb-XgpEQ"}
""";

	public static void main(String[] args) throws Exception {
		var jwk = (RsaJsonWebKey) JsonWebKey.Factory.newJwk(SIGN_KEY);
		var jws = new JsonWebSignature();
		jws.setCompactSerialization(ID_TOKEN);
		jws.setKey(jwk.getRsaPublicKey());
		var valid = jws.verifySignature();
		System.out.printf("%s\n", valid);
	}
}
