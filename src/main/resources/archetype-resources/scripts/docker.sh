#!/usr/bin/env bash

boot2docker up
$(boot2docker shellinit)

docker stop $(docker ps -a -q)
# docker rm $(docker ps -a -q)

docker-compose stop
docker-compose -p ${artifactId.toLowerCase()} up -d