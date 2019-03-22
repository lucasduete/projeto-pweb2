#!/usr/bin/env bash

while ! nc -z servicediscovery 8761 ; do
    echo "Waiting for upcoming ServiceDiscovery:"
    sleep 2
done

echo "ServiceDiscovery is UP"

echo "Started Download Api-Gateway"

wget https://codeload.github.com/lucasduete/projeto-pweb2/zip/master -O master.zip

echo "Finished Download Api-Gateway"

echo "Started UnZip Api-Gateway"

unzip master.zip

echo "Finished UnZip Api-Gateway"

rm master.zip
cd projeto-pweb2-master/api-gateway/

./mvnw package spring-boot:repackage

java -jar target/api-gateway-0.0.1-SNAPSHOT.jar
