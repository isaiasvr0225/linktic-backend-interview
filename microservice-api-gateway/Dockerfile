FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} microservice-api-gateway-1.0.0.jar
ENTRYPOINT ["java","-jar","/microservice-api-gateway-1.0.0.jar"]