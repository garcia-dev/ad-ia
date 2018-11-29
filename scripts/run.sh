#!/bin/bash

cd $(dirname $0)/..
sh scripts/compile.sh
java -cp "bin" ExtractionTests
