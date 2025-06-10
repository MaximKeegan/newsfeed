plugins {
    java
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    maven {
        url = uri("https://nexus.bank24.int/content/groups/repo")
    }
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("com.tochka.common.jsonrpc4j:jsonrpc4j:1.0.0")

    implementation("org.postgresql:postgresql:42.7.5")
    implementation("org.flywaydb:flyway-core:11.3.4")
    implementation("org.flywaydb:flyway-database-postgresql:11.3.4")
    implementation("org.jsoup:jsoup:1.17.2")

    implementation("ch.qos.logback:logback-classic")

    implementation("com.rometools:rome:1.16.0")

    implementation("org.freemarker:freemarker")

    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:testcontainers:1.20.2")
    testImplementation("org.testcontainers:postgresql:1.20.2")
    testImplementation("org.testcontainers:junit-jupiter:1.20.2")
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    archiveFileName.set("newsfeed.jar")
    destinationDirectory.set(file("$buildDir/libs"))
}

tasks.named<Jar>("jar") {
    enabled = false
}

tasks.test {
    useJUnitPlatform()
}