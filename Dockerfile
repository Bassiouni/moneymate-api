FROM openjdk:20
WORKDIR /app

COPY pom.xml pom.xml
COPY *mvn* .
COPY .mvn .mvn
RUN mkdir src
RUN mkdir src/main/
RUN mkdir src/main/java/
RUN mkdir src/main/java/bm/
RUN mkdir src/main/java/bm/bookstore/

COPY ./src/main/java/ai/moneymate/api/MoneymateApplication.java ./src/main/java/ai/moneymate/api/MoneymateApplication.java

RUN ./mvnw clean verify

COPY ./src/test ./src/test
COPY ./src/main ./src/main

EXPOSE 8099

CMD [ "./mvnw", "spring-boot:run" ]
