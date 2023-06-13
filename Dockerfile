FROM eclipse-temurin:17-jre-alpine
ADD build/libs/raidalert-1.0.0.jar /
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/raidalert-1.0.0.jar"]
