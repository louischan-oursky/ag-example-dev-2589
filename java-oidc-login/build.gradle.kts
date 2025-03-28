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
	implementation("com.github.scribejava:scribejava-core:8.3.3")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.run.configure {
    standardInput = System.`in`
}
