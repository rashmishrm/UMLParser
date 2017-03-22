

UML Generator

UML Generator takes source folder as input and generates class diagram as output


How to Build this Application.

1. Create
mvn clean package

With Docker. Build Docker Image.
2. docker build -t umlparser:latest .

Run With Docker.
3. docker run umlparser test-case-folder image_name.jpg
