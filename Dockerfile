FROM openjdk:17
EXPOSE 8090
# ADD target/demo-api-1.jar demo-api-1.jar
ENTRYPOINT ["java","-jar","/demo-api-1.jar"]
