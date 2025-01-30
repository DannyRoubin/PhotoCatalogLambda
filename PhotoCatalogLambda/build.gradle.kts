plugins {
    id("java")
    id("io.spring.dependency-management") version "1.1.6"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // AWS Lambda Dependencies
    implementation("com.amazonaws:aws-lambda-java-core:1.2.1")
    implementation("com.amazonaws:aws-lambda-java-events:3.11.0")
    implementation("com.amazonaws:aws-java-sdk-dynamodb:1.12.480")

    implementation("com.azure:azure-cosmos:4.51.0")
    implementation("org.slf4j:slf4j-simple:2.0.7")

}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

// Ensures the JAR is correctly structured for AWS Lambda
tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "org.example.Main"
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    // Include dependencies inside JAR
    from(configurations.runtimeClasspath.get().map { zipTree(it) })
}

tasks.test {
    useJUnitPlatform()
}
