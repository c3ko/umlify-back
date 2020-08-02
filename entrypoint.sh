#!bin/bash
apk add --update ttf-dejavu
apk add graphviz

mvn install
mvn spring-boot:run