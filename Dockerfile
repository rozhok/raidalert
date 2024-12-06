FROM eclipse-temurin:23-jre-alpine
ADD build/libs/raidalert-1.0.0.jar /
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/raidalert-1.0.0.jar"]
