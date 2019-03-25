#!/usr/bin/env bash

while ! nc -z api-gateway 8080 ; do
    echo "Waiting for upcoming Api-Gateway:"
    sleep 5
done

echo "Api-Gateway is UP"

echo "Started HorarioService"

java -jar horarioservice.jar
