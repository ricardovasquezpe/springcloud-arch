FROM eclipse-temurin:17
ADD target/ticket-service-1.0.jar /usr/share/ticket-service-1.0.jar
EXPOSE  8090
ENTRYPOINT ["java","-jar", "/usr/share/ticket-service-1.0.jar"]
# docker build -t ms-ticket-service .
# docker run -p 8090:8090 --name ms-ticket-service -d ms-ticket-service