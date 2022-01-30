#!/usr/bin/env bash
set -e
mkdir dist

STRING_OF_INTEREST="The current project is built against Scala"
GREPPED=$(sbt about | grep $STRING_OF_INTEREST)
echo $GREPPED
SCALA_VERSION=$(echo $GREPPED | sed "s/[^0-9.]//g")
cp js/target/scala-${SCALA_VERSION}/gol-opt/* ./dist/
cp index.html dist/
