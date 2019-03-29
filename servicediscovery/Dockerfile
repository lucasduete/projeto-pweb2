FROM openjdk:8-jdk-alpine

WORKDIR /home

COPY target/servicediscovery-0.0.1-SNAPSHOT.jar servicediscovery.jar

ENTRYPOINT ["/usr/bin/java"]

CMD ["-jar", "servicediscovery.jar"]

EXPOSE 80
