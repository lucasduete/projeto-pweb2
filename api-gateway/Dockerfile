FROM openjdk:8-jdk-alpine

WORKDIR /home

COPY target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar
COPY run.sh run.sh

CMD ["sh", "run.sh"]

EXPOSE 80
