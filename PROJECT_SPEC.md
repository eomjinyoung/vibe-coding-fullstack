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

## 5. 프로젝트 구조 (Project Structure)

### 패키지 구조 (Package Structure)
- `com.example.vibeapp.home`: 홈 화면 및 공통 API 관련 컨트롤러
- `com.example.vibeapp.post`: 게시글 CRUD, 페이징 로직 (Entity, Repository, Service, Controller)

### 뷰 템플릿 구조 (View Templates)
- `src/main/resources/templates/home/`: 메인 페이지 (`home.html`)
- `src/main/resources/templates/post/`: 게시글 관련 페이지 (`posts.html`, `post_detail.html`, `post_new_form.html`, `post_edit_form.html`)

## 6. 구현된 기능 (Implemented Features)

- **게시글 관리 (CRUD)**: 게시글 등록, 목록 조회, 상세 조회, 수정, 삭제 기능
- **페이징 처리 (Pagination)**:
  - 한 페이지당 5개의 게시글 출력
  - 이전/다음 및 페이지 번호 네비게이션 구현
- **UI/UX**:
  - **Tailwind CSS**: 프리미엄 테마 및 반응형 디자인 적용
  - **SVG 아이콘**: 페이지 이동 및 시각적 요소에 고품질 SVG 사용
  - **조회수 증가**: 상세 조회 시 조회수 자동 증가 로직 포함

## 7. 명명 규칙 (Naming Conventions)

- **Repository/Service**: Spring Data JPA 관례를 따름 (`findById`, `findAll`, `create`, `update`, `delete` 등)
- **View**: 기능별 하위 폴더 기반 경로 사용
