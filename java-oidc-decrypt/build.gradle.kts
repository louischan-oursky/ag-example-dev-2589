plugins {
	java
	application
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

application {
	mainClass = "com.example.demo.App"
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.bitbucket.b_c:jose4j:0.9.6")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.run.configure {
    standardInput = System.`in`
}
