
[![Stories in In%20Progress](https://badge.waffle.io/rashmishrm/cmpe202-UMLParser.png?label=In%20Progress&title=In%20Progress)](https://waffle.io/rashmishrm/cmpe202-UMLParser)

[![Stories in Ready](https://badge.waffle.io/rashmishrm/cmpe202-UMLParser.png?label=Ready&title=Ready)](https://waffle.io/rashmishrm/cmpe202-UMLParser)

# UML Generator

UML Generator takes source folder as input and generates class diagram as output

## Environment setup
  1. Requires Java 8 version
  2. Requires Graphviz installed on machine
  
  Windows:
  ```
  http://www.graphviz.org/Download..php
  ```
  Mac Command:
   ```
  brew install libtool
  brew link libtool
  brew install graphviz
  brew link --overwrite graphviz
  ```
  Linux Command
  ```
  sudo apt-get install graphviz
  ```
## How to Build this Application.

 Create Jar file.
 ```
  mvn clean package
 ```

## How to Run this Application.
   ## Run Manually.
   ```
      Download Executable-Jar folder. 
      1. cd <PATH_TO_EXECUTABLE-JAR_FOLDER> 
      2. sh umlparser.sh <input-folder> <outputfile_name>
   ```
   
 
  ## With Docker
    
a) Build Docker Image
    
 ```
      docker build -t umlparser:latest
```
    
 b) Run With Docker.
    
```
      docker run umlparser test-case-folder image_name.jpg
```

## Latest Release
https://github.com/rashmishrm/cmpe202-UMLParser/tree/2.0
