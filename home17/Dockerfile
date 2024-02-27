FROM openjdk:17
RUN mkdir -p /app/
WORKDIR /app/
COPY /target/library.jar /app/library.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/library.jar"]
