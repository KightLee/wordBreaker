FROM openjdk:17.0.1-jdk-slim
LABEL maintainer="leeluode"
#RUN mkdir "./app"
#WORKDIR ./app
COPY *.jar /app.jar
Expose 9000
CMD java -jar /app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
