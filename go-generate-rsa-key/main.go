package main

import (
	"crypto/rand"
	"crypto/rsa"
	"crypto/x509"
	"encoding/json"
	"encoding/pem"
	"fmt"

	"github.com/lestrrat-go/jwx/v2/jwk"
)

func main() {
	privateKey, err := rsa.GenerateKey(rand.Reader, 2048)
	if err != nil {
		panic(err)
	}
	privateKeyBytes, err := x509.MarshalPKCS8PrivateKey(privateKey)
	if err != nil {
		panic(err)
	}

	privateKeyPem := pem.EncodeToMemory(&pem.Block{
		Type:  "PRIVATE KEY",
		Bytes: privateKeyBytes,
	})
	fmt.Printf("%v", string(privateKeyPem))
	privateKeyJWK, err := jwk.ParseKey(privateKeyPem, jwk.WithPEM(true))
	if err != nil {
		panic(err)
	}
	privateKeyJWKBytes, err := json.Marshal(privateKeyJWK)
	if err != nil {
		panic(err)
	}
	fmt.Printf("%v\n", string(privateKeyJWKBytes))

	publicKeyBytes, err := x509.MarshalPKIXPublicKey(&privateKey.PublicKey)
	if err != nil {
		panic(err)
	}

	publicKeyPem := pem.EncodeToMemory(&pem.Block{
		Type:  "PUBLIC KEY",
		Bytes: publicKeyBytes,
	})
	fmt.Printf("%v", string(publicKeyPem))
	publicKeyJWK, err := jwk.ParseKey(publicKeyPem, jwk.WithPEM(true))
	if err != nil {
		panic(err)
	}
	publicKeyJWKBytes, err := json.Marshal(publicKeyJWK)
	if err != nil {
		panic(err)
	}
	fmt.Printf("%v\n", string(publicKeyJWKBytes))
}
