#!/usr/bin/env bash

read -p "Project Name: "  projectname

cd ..
mvn clean install

cd $HOME/Desktop
rm -rf $projectname

mvn archetype:generate \
        -DarchetypeGroupId=com.github.suzel.spring-mvc-archetypes \
        -DarchetypeArtifactId=spring-mvc-quickstart \
        -DarchetypeVersion=1.0.0 \
        -DgroupId=com.company \
        -DartifactId=$projectname \
        -Dversion=1.0.0 \
        -DprojectName=$projectname \
        -DdatabaseType=postgresql \
        -DinteractiveMode=false

cd $HOME/Desktop/$projectname
mvn clean install tomcat7:run