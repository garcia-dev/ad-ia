#!/bin/sh

cd $(dirname $0)/..
sh script/compile.sh
java -cp "build" src.main

read
