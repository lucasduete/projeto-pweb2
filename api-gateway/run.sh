#!/usr/bin/env bash

while ! nc -z servicediscovery 8761 ; do
    echo "Waiting for upcoming ServiceDiscovery:"
    sleep 2
done

echo "ServiceDiscovery is UP"

echo "Started Api-Gateway"

java -jar api-gateway.jar
