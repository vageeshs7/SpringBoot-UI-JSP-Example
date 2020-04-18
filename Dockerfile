FROM openjdk:8u252-slim
COPY ./target/uitest-0.0.1-SNAPSHOT.war /opt/uitest.war
CMD ["java", "-jar", "/opt/uitest.war"]
EXPOSE 8080