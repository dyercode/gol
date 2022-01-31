#!/usr/bin/env bash
set -e
mkdir dist


GREPPED=$(sbt about | grep "The current project is built against Scala")
echo $GREPPED
SCALA_VERSION=$(echo $GREPPED | sed -r "s/.*The current project is built against Scala ([0-9.]+)$/\1/")
cp js/target/scala-${SCALA_VERSION}/gol-opt/* ./dist/
cp index.html dist/
