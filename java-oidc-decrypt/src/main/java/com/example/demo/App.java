package com.example.demo;

import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwe.JsonWebEncryption;

public class App {
	private static final String ID_TOKEN = "eyJhbGciOiJSU0EtT0FFUCIsImN0eSI6IkpXVCIsImVuYyI6IkExMjhDQkMtSFMyNTYifQ.Z_mW9h3R8P0UdMItX5NLcHP7w_3ISuWz05lGjJzX7sbGCf8LHZoAGEC1K1Ewq_1stk8xalu1-rC4j5SwF-Cdf_kvwwyjB1oreeQ4km-jRnB7n7PU-MZQOm1rqSNW1KMPE0riT-yH9wPoZem05d0yH3aFiVZ21rJM_EiLb56Z1oKZpJrNsWg-HoJunDPgt3EjeCf1C9tjQGxT9N5u4mEtnaVu8lYP-6wOpysFuEfy1KHqaa3_q3o1Ke6aSJmLkbAvIW6tgJ5hjz7XWgkTYxZBaO9uP7Qiulxp8qzsxtftRBmZ4fzqQoKrxaMEwcNVfx7blHf-K4Mwg_IpEueD86vXew.1FGC1bRZnzyZmpzhkqEBog.6Vvkx9_aD7AHLJn2bc34OP7buOUNdlYLAirtOwDMinX3lE8oeUCVwQuFMGQpShiFO70oLpZs_3ejyTxZYiaYttzKiZCmjrCibPOYhs7yMHww0aF88NJJxjiZEsqaue1NJlThIeBZwTzrbAE-Bm0cg8BsBmTaJlrL3AbRLtMAkvvMkqPgyoCCThOgHCaYJgpkAf16VxRv8ncClaL0OAA9VHaWuW0RAWDgK3343i-rzE4AlQU_zVpWu43CPwi4-hPdlMn7IEqYrl_fSpwjE18Xp-MWSOp-CDQpCzGGp4VbQpaScXRNpqBWmur5TS5AnCBdQkJr39o_dRrv7Oel-wRwpoeEZzh52uSdWKstnQU38w4AHAEK_g2XxifgwCvUOCV7NeB_rebmYQSNstIY4IcMI6o5OU2cKd5ClE6XPtsMHVXEMNdjzpAg_fyOJB-qbRpRWCevaoAUQJxTp12pxkH26StUVzhJSkv06kKiyAOv_NgnhpR5H3SpiTOkdnOItxmJB6bpgFPGYE3e3TwAwz1ZyVGKm2zu62fQryJTQwMTKMhs2ufhVQwG7-e0MBR3gNjwu3StjY1J8CT-F4wvCfadNPPllJHtZBXr4npy07-vYg-wCvmD0mrV0DmJBl2FPk3EC4bY0u8NPpzyI76gHytW0g.8qe393lB2zJg8nZF852WFg";

	private static final String ENC_KEY = """
{"d":"LDCH36vm47sBnYNwAcj1IanBa9XMWzq-6n45rxfruFyrBvXtMe4mbaVIOVW3g1wTOJSsn6vg_ifmRDRrTOHb3QS3feqLsGZDglyWprFH48LDfulAPaoQeLQwpaaoPIzA3_7KrDF0jGTSP1cbYlPlhkQ88aTjBtHQ6M5uvb9yGlq6q5_-AaHJlQD1o5KXMdtfLzEUacPNLxoGd80GHXP7Lo3lqxHFVYjYYw-WPsJieIL5BsDzsZZCGX7_GJYrBgfRaf8GqzkvylIgF_ucjf_jFkFLgjwPUvf0WmK88ocr7RDpM5W3mKC_EeNoFACugkmpYS89h4GZRxhIxxYpngEAMQ","dp":"GD2p8GOy1aX5dxrylFtpJ17c9BzyRCMB0lU_xLY7iFNB8lP4FYy5Kq_EyXOVMKXUWJeKf9W3SoqzoynMR1QLCeVRf8qNBbejtF4j4zlKWz1JEJBa3Si2Fv3VbvqUBL4wfr6Za7cPSdGivtZPxAC7UajMYEjCHgCJ0_kSoAWugkc","dq":"SFyQ3MCUFcbEsfsBanZSIU5glaDbwlpeSYbKgTDo1pGGD0xM7xy1vJ3tgwhe1bZ_YI9cROZeJ_hVx8P80znlfgGuqYqfnXykzw2wMpgGIbzGKUGqmF8j0TOe0HHw3YTIFC9Y8bTDJweOImDVHd0Lfai5wII-_1DWzSbPBVxeGDk","e":"AQAB","kty":"RSA","n":"t3T9YErVeSvGZ-9aiuWtubr3TRUeFTJ4J6cRwz69e5yMTXemnDDJR-aqxiMbbIycJZ8beh8VFSD99VckP-nTzpSOGk3umS8f9RqUZ8l8-_0iPqyZVFU0r1kNfWkE0dzdZ8AVdGgwfHAy7HbhspntrXO-UdPmocsg5eFPbu036aQKfqLd0lkkWvRR-al3PdFMZ8B_smNjPnI1AXu6bBFY22PqxxddsG8DsNxdkc5JjzL8cJquoy8coXPhqZEm3ja9vJVKsiT__xj8ix53BZ9EaZnYum7C4f9j15BAk0whf1Bc8415wf06GP3YAyF9x2BYw-TUEx4GAykT6MkO6RiK-w","p":"20dY3mJGFLwXN7zIQKmEx7ZfuIy3WtHjAJFe31C_msI1jYM2XPV10gb6SJcvQCskukfWtP30fXAflZ9gC1GHt-4HUXLCtCjlrQdSxEpmazFfkzgga4s6QaYxtZXjx2aw6yfmE09KjU0Dp_u14P5iz0cPMmRdYZUSNydrWaeull8","q":"1i3v97E0crm1yETaz7En4jkLWouLCEeZAb8pgaWEp0GbytfiKfcJtD5dkZWDzgW_SiRzwlZJMJCia6MYwZMwcUulBfUlNPqXr76Bcs-EudqVxWcyx5NeO2u7gk88di4Ul1A6d-RO2UDUHGI5AeXi2XSmsDNrwBW23D8hC5_A-OU","qi":"kL_g6ACh8R72qmV8bDU6U4zZPnmf4ZNpVILEWePy2hhos5IsF4APmMzb2vSdbPpFKDm3Q3Mc2Tq24ab9KHwIcfuxOR-nYflXjQqBLHJL5zGQMSx1OratQ5dGImkyViWw2hvzRp9HJCWEgSvA0QrpibixR5usmk2wwe1emcOfji8"}
""";

	public static void main(String[] args) throws Exception {
		var jwk = (RsaJsonWebKey) JsonWebKey.Factory.newJwk(ENC_KEY);
		var decryption = new JsonWebEncryption();
		decryption.setCompactSerialization(ID_TOKEN);
		decryption.setKey(jwk.getRsaPrivateKey());
		var plaintext = decryption.getPlaintextString();
		System.out.printf("%s\n", plaintext);
	}
}
