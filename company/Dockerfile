FROM alpine/java:17-jre

COPY target/*.jar companyApp.jar

ENTRYPOINT ["java", "-jar", "companyApp.jar"]