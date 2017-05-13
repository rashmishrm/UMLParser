
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
   
