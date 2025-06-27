plugins {
    // kotlin JVM 플로그인 (JVM 기반 kotlin 컴파일러 사용)
    kotlin("jvm") version "1.9.25"
    // kotlin + Spring 연동 플러그인 (Spring 관련 기능 자동 지원)
    kotlin("plugin.spring") version "1.9.25"
    // kotlin + JPA 연동 플러그인 (엔티티의 기본 생성자 자동 생성)
    id("org.jetbrains.kotlin.plugin.jpa") version "1.9.25"
    // spring boot 플러그인 (앱 실행 / 패키징)
    id("org.springframework.boot") version "3.5.3"
    // 의존성 관리 (버전 정리 및 자동 관리)
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.qzce"
version = "0.0.2-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

configurations {
    compileOnly { // 컴파일 시 필요한 의존성 설정
        extendsFrom(configurations.annotationProcessor.get()) // 컴파일 타임 어노테이션 프로세서 사용 (Lombok 등)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-data-redis") // redis


    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// 컴파일러 옵션 설정
kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
