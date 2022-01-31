#!/usr/bin/env bash
set -e
mkdir dist


GREPPED=$(sbt about | grep "The current project is built against Scala")
echo $GREPPED
SCALA_VERSION=$(echo $GREPPED | sed "s/[^0-9.]//g")
cp js/target/scala-${SCALA_VERSION}/gol-opt/* ./dist/
cp index.html dist/
