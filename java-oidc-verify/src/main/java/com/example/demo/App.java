package com.example.demo;

import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.JsonWebSignature;

public class App {
	private static final String ID_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiQVVESUVOQ0UiXSwiZXhwIjoyNTMyMzM1NDAwLCJpYXQiOjE3NDM0MTcwMDAsImlzcyI6IklTU1VFUiIsInN1YiI6IlVTRVJfSUQifQ.BGCTrZjsI25RJXlaUPZqnCeyoBinQwUOzvtZt4Ttrf_FsA6ZhNZ0MabhZfq7lHLHZ-L7VqyzIMCcwXQjOL9WBckmhfuYSTh5j6qDyen27KH7wNei3EavZ13R_wQTfu1GqfSBXDgRed6v5F2QZtndaNhb63YIMymKWycEZOvYeHZAjmqg1ggO1s2IvQHKeWdaWM6RVi7bg5XhZsb2st2qmIfCMdKCAk6Zg5sRJVtAl3iQsDCTBFT--hMaTtxwISWIeQW8Io5TCcGoeKNFsAAn4t0T-ag-sdSmYaVQeE7EPo8moKAMn_g4p3ungbrVf26YvxHJ54BS1I7oljG03URhmQ";

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
