#!/usr/bin/env sh
mkdir dist
# echo "scala_version=${{sbt about | grep 'The current project is built against Scala' | sed  's/[^0-9.]//g'}}" >> $GITHUB_ENV

SCALA_VERSION=$(sbt about | grep "The current project is built against Scala" | sed  "s/[^0-9.]//g")
cp js/target/scala-${SCALA_VERSION}/gol-opt/* ./dist/
cp index.html dist/
