FROM alpine/java:17-jre

COPY target/*.jar userApp.jar

ENTRYPOINT ["java", "-jar", "userApp.jar"]