FROM java:8
ADD UMLGenerator/target/umlgenerator-0.0.1-SNAPSHOT-jar-with-dependencies.jar umlgenerator-0.0.1-SNAPSHOT-jar-with-dependencies.jar
ENTRYPOINT ["java","-jar","umlgenerator-0.0.1-SNAPSHOT-jar-with-dependencies.jar"]
