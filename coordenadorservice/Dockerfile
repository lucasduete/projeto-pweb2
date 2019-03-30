FROM openjdk:8-jdk-alpine

WORKDIR /home

COPY target/coordenadorservice-0.0.1-SNAPSHOT.jar coordenadorservice.jar
COPY run.sh run.sh

CMD ["sh", "run.sh"]

EXPOSE 80
