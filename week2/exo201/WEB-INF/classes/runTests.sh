#!/bin/bash
test -f junit-4.12.jar || wget http://central.maven.org/maven2/junit/junit/4.12/junit-4.12.jar
test -f hamcrest-core-1.3.jar || wget http://central.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar
test -d ../lib || mkdir ../lib
test -f ../lib/sqlite-jdbc-3.23.1.jar || wget https://bitbucket.org/xerial/sqlite-jdbc/downloads/sqlite-jdbc-3.23.1.jar -O ../lib/sqlite-jdbc-3.23.1.jar
javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar Test*.java auth/*.java
javac user/*.java
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar:../lib/sqlite-jdbc-3.23.1.jar org.junit.runner.JUnitCore TestUserJDBC

java -jar code-check*.jar exo201_jdbc

