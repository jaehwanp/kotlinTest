plugins {
    // 모든 하위 모듈에서 사용할 공통 플러그인 등록
    kotlin("jvm") version "1.9.25" apply false
    // spring boot 플러그인 (앱 실행 / 패키징)
    id("org.springframework.boot") version "3.5.3" apply false
    // 의존성 관리 (버전 정리 및 자동 관리)
    id("io.spring.dependency-management") version "1.1.7" apply false
    // kotlin + Spring 연동 플러그인 (Spring 관련 기능 자동 지원)
    kotlin("plugin.spring") version "1.9.25" apply false
    // kotlin + JPA 연동 플러그인 (엔티티의 기본 생성자 자동 생성)
    id("org.jetbrains.kotlin.plugin.jpa") version "1.9.25" apply false
}

allprojects {
    group = "com.qzce"
    version = "0.0.1"

    repositories {
        mavenCentral()
    }
}