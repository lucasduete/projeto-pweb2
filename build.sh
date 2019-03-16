#!/usr/bin/env bash

cd servicediscovery
mvn package spring-boot:repackage
cd ..

cd api-gateway
mvn package spring-boot:repackage
cd ..
