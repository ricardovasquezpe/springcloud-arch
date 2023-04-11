FROM eclipse-temurin:17
ADD target/payments-service-1.0.jar /usr/share/payments-service-1.0.jar
EXPOSE  8070
ENTRYPOINT ["java","-jar", "/usr/share/payments-service-1.0.jar"]
# docker build -t ms-payments-service .
# docker run -p 8070:8070 --name ms-payments-service -d ms-payments-service