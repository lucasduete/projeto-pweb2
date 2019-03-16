#!/usr/bin/env bash

while ! nc -z api-gateway 8080 ; do
    echo "Waiting for upcoming Api-Gateway:"
    sleep 5
done

echo "Api-Gateway is UP"

echo "Started Download CursoService"

wget https://codeload.github.com/lucasduete/projeto-pweb2/zip/master -O master.zip

echo "Finished Download CursoService"

echo "Started UnZip CursoService"

unzip master.zip

echo "Finished UnZip CursoService"

rm master.zip
cd projeto-pweb2-master/cursoservice/

./mvnw package spring-boot:repackage

java -jar target/cursoservice-0.0.1-SNAPSHOT.jar
