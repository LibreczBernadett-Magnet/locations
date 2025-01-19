#FROM openjdk:17-jdk-slim
#RUN mkdir /opt/apt
#COPY target/locations-0.0.1-SNAPSHOT.jar /opt/apt/locations.jar
#CMD ["java", "-jar", "/opt/apt/locations.jar"]

FROM openjdk:17-jdk-slim as builder
WORKDIR application
COPY target/locations-0.0.1-SNAPSHOT.jar locations.jar
RUN java -Djarmode=tools -jar locations.jar extract  --layers --launcher


FROM openjdk:17-jdk-slim
WORKDIR application
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh .
RUN chmod +x ./wait-for-it.sh
COPY --from=builder application/locations/dependencies/ ./
COPY --from=builder application/locations/spring-boot-loader/ ./
COPY --from=builder application/locations/snapshot-dependencies/ ./
COPY --from=builder application/locations/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]