FROM eclipse-temurin:17
ADD target/user-service-1.0.jar /usr/share/user-service-1.0.jar
EXPOSE  8080
ENTRYPOINT ["java","-jar", "/usr/share/user-service-1.0.jar"]
# docker build -t ms-user-service .
# docker run -p 8080:8080 --name ms-user-service -d ms-user-service