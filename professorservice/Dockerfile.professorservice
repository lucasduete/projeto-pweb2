FROM openjdk:8-jdk-alpine

WORKDIR /home

COPY target/professorservice-0.0.1-SNAPSHOT.jar professorservice.jar
COPY run.sh run.sh

CMD ["sh", "run.sh"]

EXPOSE 8084
