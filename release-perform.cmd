@echo off
mvn release:perform -Darguments="-Dmaven.deploy.skip=true"