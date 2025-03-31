package main

import (
	"fmt"
	"time"

	"github.com/lestrrat-go/jwx/v2/jwa"
	"github.com/lestrrat-go/jwx/v2/jwe"
	"github.com/lestrrat-go/jwx/v2/jwk"
	"github.com/lestrrat-go/jwx/v2/jws"
	"github.com/lestrrat-go/jwx/v2/jwt"
)

var signKeyJWK = `
{"d":"MRw8OyPFI5enTUAYb5T_y090yiYt7iHhr8ZDr6OjUP1T1FH03DUcW2qYbgye-iZDkh4ykgxWgB4kTGSGpMst-iluN42MOH5VHh2vrGdHjAuClDu3SW6v0Qxb2iiDZn125VlLz5bAYVkYYsfEStUSqMwzBdNoad-j-98Kika0u6RfcGdRCR_9VvM0ARTAfH1G8QsxQCm0J83N2XKj_5VOtPW5EACi78ba9s9QQ2YFxrpyzS4l45IugIJAcou2zbfE7GbvCjWaRYadYQ_4oLxQ_bZ-ocz3REtcuGd-uCTlyDbUd_b0JJOPG72lTuFDjsQ-Ku7S3GHOplRwKM88p-wtBQ","dp":"hnYVI1aQHoQgX9xIKHbvuKiEI9xdDGJMTrS0W4TsdmyKYXOSFQ7Wv2EuEhaCtuF4TgFM8ciAIUF4SM7sl26_0ctBn6rne7OWmT98dHD3FbEgxkaSCc7wgRJ8De5H5LPoY6qhoQ4bhl4fAhjwnts5wcNtgaO1EwIbZKxVPHtJ_68","dq":"PQyKB0o7YNKiiKU4aUl-N_TXkuiai1eqWa8ad4vMRzkoh2dqcBTuRQ971K4M2M84SMLrVpUQ1f3h_x6HBDHdAy3NmT0aoDorgz04UO0YJyljntvKuMrVeUUh1aWsm6V0Bn9mYPdzSlAMUHMe2VGXhYvuVxVB1XN7ETXzrTqA5p8","e":"AQAB","kty":"RSA","n":"3mHo42X6FLWH_Ab2rDORRyVgcj83_QZq5p3f4G61hsbvWWjbuX6jn9QzlwWgEsV3EL8dUERbR1j1-vSuJSRTy2uji0EbHdmA-NCuOr3K42rCPwuu8CIf8KZa2vMrFppe9tLBs1B2cPt8HYGntiiaWBoavq94Y-dE_iAg_nKjBRFlY5udwk5XjsnpgFoxziiQk4w1jf9Teda4uGqfPNhWXn78Ueb5TrOepxq3Bo31ZovRydbBsuLWybm1V3e-kRBsI7KlMwkwrj5TlaJHrHlcWfjLDp_xwE91DklOAnYtmy4RPw8jzM6es4f3dNhXhVAhfpYpS0gg_4YQFqyb-XgpEQ","p":"8471WONKa8pcDXn5rgQ0RW1F7iF5Rd3GAHlhm84o5_i0KEJr_cVJK2-rV9UvPTKXZgw0dzgH0uXPaD0AQ6pv8_cNaihzsHbioqctvipEEkGiz5sTpsUu6v_uHbD6qjjLxb9L2sfjVVJG6Z6lTBinz3he9hTkXGaVf1nERioscPc","q":"6b4H3T8UOJ-KMlZ9oxeynCfGkM47yvg0ERJLFpoxT1Uj4zlafZcTIOJivcIx9y1RWEXQyrAznReszOdPUme44qdeOQW54HR4-_zh7Qbw0tdgZPkwVMsTOEm_wtpcHj3h8Bu2H5WT5TBr7d1w_5Kns9YfLukdwgM1U_4ZUrGePDc","qi":"4nWKzPeOas1gjThZkg4Tg7vKDBwZTfzK4saIi35No0tgmRP4-44-EiZ553KBGeDjEww9bklk5VEcCGOZYcjDCLX4cY_DO8tLJjfBmizX_OK_ZPwJ4PChlRCPlrxr5jSU-OA-7bFk1jimSULxPrXohhtA1yeq7so-hWhnyEgBPrk"}
`

var encKeyJWK = `
{"e":"AQAB","kty":"RSA","n":"t3T9YErVeSvGZ-9aiuWtubr3TRUeFTJ4J6cRwz69e5yMTXemnDDJR-aqxiMbbIycJZ8beh8VFSD99VckP-nTzpSOGk3umS8f9RqUZ8l8-_0iPqyZVFU0r1kNfWkE0dzdZ8AVdGgwfHAy7HbhspntrXO-UdPmocsg5eFPbu036aQKfqLd0lkkWvRR-al3PdFMZ8B_smNjPnI1AXu6bBFY22PqxxddsG8DsNxdkc5JjzL8cJquoy8coXPhqZEm3ja9vJVKsiT__xj8ix53BZ9EaZnYum7C4f9j15BAk0whf1Bc8415wf06GP3YAyF9x2BYw-TUEx4GAykT6MkO6RiK-w"}
`

func main() {
	signKey, err := jwk.ParseKey([]byte(signKeyJWK))
	if err != nil {
		panic(err)
	}

	encKey, err := jwk.ParseKey([]byte(encKeyJWK))
	if err != nil {
		panic(err)
	}

	token := jwt.New()
	token.Set(jwt.SubjectKey, "USER_ID")
	token.Set(jwt.AudienceKey, "AUDIENCE")
	token.Set(jwt.IssuerKey, "ISSUER")

	iat := time.Date(2025, 3, 31, 10, 30, 0, 0, time.UTC)
	exp := time.Date(2050, 3, 31, 10, 30, 0, 0, time.UTC)
	token.Set(jwt.IssuedAtKey, iat.Unix())
	token.Set(jwt.ExpirationKey, exp.Unix())

	serializer := jwt.NewSerializer().
		Sign(jwt.WithSignOption(jws.WithKey(jwa.RS256, signKey))).
		Encrypt(
			jwt.WithEncryptOption(jwe.WithKey(
				jwa.RSA_OAEP,
				encKey,
			)),
			jwt.WithEncryptOption(jwe.WithContentEncryption(jwa.A128CBC_HS256)),
		)

	compact, err := serializer.Serialize(token)
	if err != nil {
		return
	}

	fmt.Printf("%v\n", string(compact))
}
