# Örnek Dockerfile içeriği
FROM openjdk:21-jdk
WORKDIR /app
COPY target/myapp.jar myapp.jar
ENTRYPOINT ["java", "-jar", "myapp.jar"]
