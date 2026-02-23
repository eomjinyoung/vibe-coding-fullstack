# Project Specification: vibeapp

이 문서는 최소 기능 스프링부트 애플리케이션인 `vibeapp` 프로젝트의 기술 사양과 설정을 정의합니다.

## 1. 프로젝트 설정 (Project Settings)

- **JDK**: JDK 25 이상
- **언어 (Language)**: Java
- **Spring Boot**: 4.0.1 이상
- **빌드 도구 (Build Tool)**: Gradle 9.3.0 이상
  - **DSL**: Groovy DSL (`build.gradle`)
- **의존성 (Dependencies)**:
  - `spring-boot-starter-web`
  - `spring-boot-starter-thymeleaf`


## 2. 플러그인 (Plugins)

- **Spring Boot Plugin**: `org.springframework.boot`
- **Dependency Management**: `io.spring.dependency-management` (Spring Boot 버전에 맞춰 추가)

## 3. 프로젝트 메타데이터 (Project Metadata)

- **Group**: `com.example`
- **Artifact**: `vibeapp`
- **Name**: `vibeapp`
- **Description**: 최소 기능 스프링부트 애플리케이션을 생성하는 프로젝트다.
- **Main Class Name**: `com.example.vibeapp.VibeApp`
- **Package Name**: `com.example.vibeapp`

## 4. 설정 (Configuration)

- **설정 파일**: YAML 형식 사용 (`application.yml`)
