FROM eclipse-temurin:17
ADD target/config-server-1.0.jar /usr/share/config-server-1.0.jar
EXPOSE  8888
ENTRYPOINT ["java","-jar", "/usr/share/config-server-1.0.jar"]
# docker run -d -p 8200:8200 --name vault -e 'VAULT_DEV_ROOT_TOKEN_ID=mysecrettoken' -e 'VAULT_DEV_LISTEN_ADDRESS=0.0.0.0:8200' vault
# docker build -t ms-config-server .
# docker run -p 8888:8888 --name ms-config-server -d ms-config-server