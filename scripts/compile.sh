#!/bin/bash

cd $(dirname $0)/..
[ -d bin ] || mkdir -p bin

# Clean bin/ direcory
rm -rf bin/*

javac -d bin/ src/main/java/*/*.java src/test/java/*.java

