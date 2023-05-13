FROM openjdk:11
EXPOSE 8081
ADD ./target/excel.jar excel.jar
ENTRYPOINT ["java", "-jar", "excel.jar"]
