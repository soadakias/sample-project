##
#   build stage
##
FROM maven:3.6.3-openjdk-17 AS build
WORKDIR /opt/demo_app
#if runnig dockerfile from withing eureka-server package
#COPY pom.xml .
#COPY src ./src
COPY demo_app/pom.xml .
COPY demo_app/src ./src
RUN mvn clean package -DskipTests

##
#   run stage
##
FROM azul/zulu-openjdk-alpine:17.0.6-jre
VOLUME /tmp
WORKDIR /opt/demo_app
#if runnig dockerfile from withing eureka-server package
#COPY --from=build /opt/demo_app/target/eureka-server-1.0.0.jar ./demo_app.jar
COPY --from=build /opt/demo_app/target/demo_app-1.0.0.jar ./demo_app.jar
ENV JAVA_OPTS="-Xms64m -Xmx128m"
EXPOSE 9001
#if runnig dockerfile from withing eureka-server package
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ./demo_app.jar" ]
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ./demo_app.jar" ]

