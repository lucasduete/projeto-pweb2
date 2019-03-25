FROM openjdk:8-jdk-alpine

WORKDIR /home

COPY target/cursoservice-0.0.1-SNAPSHOT.jar cursoservice.jar
COPY run.sh run.sh

CMD ["sh", "run.sh"]

EXPOSE 8082
