#!/bin/sh

cd ../../frontend

# npm install

ng build --prod --base-href="/new/"

rm -r ../backend/src/main/resources/public/new/*

cp -r dist/frontend/* ../backend/src/main/resources/public/new/

cd ../backend/docker

docker build -t $1/padelante_web .. -f ./Dockerfile

docker push $1/padelante_web

