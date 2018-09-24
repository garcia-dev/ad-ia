#!/bin/sh

cd $(dirname $0)/..
[ -d build ] || mkdir build
javac -d "build" -cp "build" src/representations/*.java
javac -d "build" -cp "build" src/examples/*.java
javac -d "build" -cp "build" src/ppc/BackTracking.java
read
