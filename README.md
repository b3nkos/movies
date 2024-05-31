# Back-end Homework 57Blocks

The detailed instructions and requirements for this test are defined in the file [CHALLENGE.md](CHALLENGE.md) file.

# Project local installation

## Requirements
- Java 17
- Maven
- Docker

## Execute the following commands
In the project root path
```bash
mvn clean package
```
```bash
mkdir target/extracted
```
```bash
java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted
```
```bash
docker compose up
```

### API documentation
http://localhost:8080/api/docs/swagger-ui/index.html