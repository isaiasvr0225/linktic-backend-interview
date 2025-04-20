FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} microservice-config-eureka-1.0.0.jar
ENTRYPOINT ["java","-jar","/microservice-config-eureka-1.0.0.jar"]