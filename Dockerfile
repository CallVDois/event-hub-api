FROM eclipse-temurin:21.0.7_6-jdk-alpine AS builder

WORKDIR /usr/app

COPY . .

RUN ./gradlew clean bootJar --no-daemon

FROM eclipse-temurin:21.0.7_6-jre-alpine

ARG APP_USER=app
ARG APP_UID=1100
ARG APP_GID=1100

RUN addgroup -g ${APP_GID} -S ${APP_USER} \
    && adduser -u ${APP_UID} -S ${APP_USER} -G ${APP_USER}

WORKDIR /opt/app
COPY --from=builder /usr/app/build/libs/*.jar application.jar
RUN chmod +x application.jar

USER ${APP_UID}:${APP_GID}

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]