#!/bin/sh

docker build -t $1/padelante_web .. -f ./Dockerfile

# docker push $1/padelante_web