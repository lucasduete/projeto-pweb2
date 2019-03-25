FROM openjdk:8-jdk-alpine

WORKDIR /home

COPY target/ambienteservice-0.0.1-SNAPSHOT.jar ambienteservice.jar
COPY run.sh run.sh

CMD ["sh", "run.sh"]

EXPOSE 8081
