FROM openjdk:11
ADD target/rest-unit-test.jar rest-unit-test.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "rest-unit-test.jar"]
