FROM openjdk:17
EXPOSE 8083

COPY build/libs/*.jar ./
CMD java -jar *.jar
