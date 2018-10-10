#!/bin/sh

cd $(dirname $0)/..
script/./compile.sh
java -cp "build" examples.Main

read
