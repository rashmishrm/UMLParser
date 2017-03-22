

UML Generator

UML Generator takes source folder as input and generates class diagram as output


How to Build this Application.

1. Create
  mvn clean package


2. With Docker. Build Docker Image
  docker build -t umlparser:latest


3. Run With Docker.
    docker run umlparser test-case-folder image_name.jpg
