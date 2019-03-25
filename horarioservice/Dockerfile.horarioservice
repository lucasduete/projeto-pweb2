FROM openjdk:8-jdk-alpine

WORKDIR /home

COPY target/horarioservice-0.0.1-SNAPSHOT.jar horarioservice.jar
COPY run.sh run.sh

CMD ["sh", "run.sh"]

EXPOSE 8085
