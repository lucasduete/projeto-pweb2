#!/usr/bin/env bash

while ! nc -z servicediscovery 8761 ; do
    echo "Waiting for upcoming ServiceDiscovery:"
    sleep 2
done

echo "ServiceDiscovery is UP"

java -jar api-gateway.jar
