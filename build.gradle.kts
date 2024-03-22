plugins {
	java
	id("org.springframework.boot") version "3.2.2"
}

group = "com.github.group37"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(platform("org.springframework.boot:spring-boot-dependencies:3.2.2"))

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("mysql:mysql-connector-java:8.0.33")
	implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
	implementation("org.springframework.security:spring-security-config:6.1.5")
	implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation ("org.thymeleaf.extras:thymeleaf-extras-springsecurity6:3.1.1.RELEASE")
	implementation ("org.springframework.security:spring-security-test")


	testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	testImplementation("org.springframework.boot:spring-test")
	testImplementation("io.projectreactor:reactor-test")
	testRuntimeOnly("com.h2database:h2")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
