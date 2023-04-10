FROM eclipse-temurin:17
ADD target/api-gateway-1.0.jar /usr/share/api-gateway-1.0.jar
EXPOSE  9191
ENTRYPOINT ["java","-jar", "/usr/share/api-gateway-1.0.jar"]
# docker build -t ms-api-gateway .
# docker run -p 9191:9191 --name ms-api-gateway -d ms-api-gateway