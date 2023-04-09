FROM eclipse-temurin:17
ADD target/registry-service-1.0.jar /usr/share/registry-service-1.0.jar
EXPOSE  8761
ENTRYPOINT ["java","-jar", "/usr/share/registry-service-1.0.jar"]