#!/usr/bin/env bash

while ! nc -z api-gateway 8080 ; do
    echo "Waiting for upcoming Api-Gateway:"
    sleep 5
done

echo "Api-Gateway is UP"

echo "Started Download HorarioService"

wget https://codeload.github.com/lucasduete/projeto-pweb2/zip/master -O master.zip

echo "Finished Download HorarioService"

echo "Started UnZip HorarioService"

unzip master.zip

echo "Finished UnZip HorarioService"

rm master.zip
cd projeto-pweb2-master/horarioservice/

./mvnw package spring-boot:repackage

java -jar target/horarioservice-0.0.1-SNAPSHOT.jar
