#!/bin/sh

docker build -t $1/padelante_web .

docker push $1/padelante_web