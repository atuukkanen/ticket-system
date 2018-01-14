FROM maven:3

WORKDIR /code
ADD pom.xml /code/pom.xml

RUN mvn dependency:resolve
ADD src /code/src
RUN mvn verify clean compile

CMD mvn exec:java

