.PHONY: go-oidc-id-token
go-oidc-id-token:
	@cd go-oidc-id-token; go run .

.PHONY: java-oidc-login
java-oidc-login:
	@$(MAKE) -s -C ./java-oidc-login run

.PHONY: java-oidc-decrypt
java-oidc-decrypt:
	@$(MAKE) -s -C ./java-oidc-decrypt run

.PHONY: java-oidc-verify
java-oidc-verify:
	@$(MAKE) -s -C ./java-oidc-verify run

.PHONY: java-oidc-validate
java-oidc-validate:
	@$(MAKE) -s -C ./java-oidc-validate run
