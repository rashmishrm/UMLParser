

# UML Generator

UML Generator takes source folder as input and generates class diagram as output


## How to Build this Application.

 Create Jar file.
  mvn clean package


## How to Run this Application.

  ### 1. Run Manually.
  
  java -jar umlgenerator.jar
  usage: umlparser
 -d <arg>   directory containing java files to be parsed
 -f <arg>   output file name
 -t <arg>   which type of diagram? 1-> class, 2-> sequence

 Sample Command:
>  java -jar umlgenerator.jar -d /Users/rashmisharma/Documents/Spring_2017/cmpe202/personal-project/umlparser/uml-parser-test-1 -f first -t 1

  ### 2. With Docker. 
    #### a) Build Docker Image
    docker build -t umlparser:latest


  3. Run With Docker.
    docker run umlparser test-case-folder image_name.jpg
