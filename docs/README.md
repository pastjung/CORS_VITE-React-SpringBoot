# Docker를 사용한 Vite(React) 프로젝트와 SpringBoot 프로젝트의 연결 및 CORS 설정 방법 학습

> Initial written at September 20, 2024 <br/>
> last updated at: September 20, 2024


## Current: ver. 1.0.0<br/>
>* ver 1.0.0.
>   * Init: 프로젝트 세팅 ( React + Spring Boot )
>   * CORS 설정

# 1. 프로그램 (프로젝트) 설명

- 본 프로젝트의 운영체제는 Linux OS를 기반으로 작성되었습니다.
- 본 프로젝트는 Spring Security와 JWT를 고려하지 않은 프로젝트 입니다. ( 추후에 Spring Security를 도입할 경우 Filter와 연결해서 고려할 예정입니다. )
- 본 프로젝트는 CORS 설정의 경우 Backend에서 설정하는 것이 바람직하다고 생각하여 Spring Boot 프로젝트에서 설정을 했습니다.

# 2. Prerequisite

- 본 프로젝트는 Docker를 사용하므로 `.env.template` 파일을 참고하여 `.env` 파일에 환경 변수값을 작성해주세요.
    - `HOST_PORT` : 외부에서 컨테이너의 애플리케이션에 접근하는데 사용하는 포트 ( 노출되도 괜찮은 포트 )
    - `SERVER_PORT` : 애플리케이션이 컨테이너 내에서 통신하는 포트 ( 노출되면 안되는 포트 )
    - Vite에서는 보안이 필요한 환경변수의 유출을 막기 위해서 `VITE_`으로 시작하지 않는 환경변수는 무시되기 때문에 `VITE_SPRINGBOOT_HOST_PORT`가 필요합니다.
    ```
    # 예시
    SPRINGBOOT_HOST_PORT=8081
    SPRINGBOOT_SERVER_PORT=8080

    REACT_HOST_PORT=5174
    REACT_SERVER_PORT=5173

    VITE_SPRINGBOOT_HOST_PORT=8081
    ```

# 3. 구동 방법

## 3.1. 프로젝트 실행

본 프로젝트는 Docker Compose를 사용하므로 이를 실행시켜주세요.

```shell
(sudo) docker compose up (--build)
```

## 3.2 프로젝트 종료

본 프로젝트는 Docker Compose를 사용하므로 이를 실행시켜주세요.

```shell
(sudo) docker compose down (-v)
```

# 4. 디렉토리 및 파일 설명
```
    /LAW-AIER
    ├── react-app/
    │   ├── public/
    │   │   └── vite.svg
    │   ├── src/
    │   │   ├── assets/
    │   │   │   └── vite.svg
    │   │   ├── App.css
    │   │   ├── App.jsx
    │   │   ├── index.css
    │   │   └── main.jsx
    │   │
    │   ├── .gitignore
    │   ├── dockerfile
    │   ├── entrypoint.sh
    │   ├── eslint.config.js
    │   ├── index.html
    │   ├── package-lock.json
    │   ├── package.json
    │   ├── README.md
    │   └── vite.config.js
    │
    ├── springboot-app/
    │   ├── gradle/
    │   │   └── wrapper/
    │   │       ├── gradle-wrapper.jar
    │   │       └── gradle-wrapper.properties
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/com/inha/springbootapp/
    │   │   │   │   └── springbootAppApplication.java
    │   │   │   └── resources/
    │   │   │       ├── application.properties
    │   │   │       └── application.properties.template
    │   │   └── test/
    │   │       └── java/com/inha/springbootapp/
    │   │           └── springbootAppApplicationTest.java
    │   │
    │   ├── .gitignore
    │   ├── build.gradle
    │   ├── dockerfile
    │   ├── entrypoint.sh
    │   ├── gradlew
    │   ├── gradlew.bat
    │   └── settings.gradle
    │
    ├── .env
    ├── .env.template
    ├── .gitattributes
    ├── .gitignore
    ├── docker-compose.yml
    └── README.md
```
