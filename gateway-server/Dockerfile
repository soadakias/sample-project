##
#   build stage
##
FROM maven:3.6.3-openjdk-17 AS build
WORKDIR /opt/demo_app
#if runnig dockerfile from withing gateway-server package
#COPY pom.xml .
#COPY src ./src
COPY gateway-server/pom.xml .
COPY gateway-server/src ./src
RUN mvn clean package -DskipTests

##
#   run stage
##
FROM azul/zulu-openjdk-alpine:17.0.6-jre
VOLUME /tmp
WORKDIR /opt/demo_app
#if runnig dockerfile from withing gateway-server package
#COPY --from=build /opt/demo_app/target/gateway-server-1.0.0.jar ./gateway-server.jar
COPY --from=build /opt/demo_app/target/gateway-server-1.0.0.jar ./gateway-server.jar
ENV JAVA_OPTS="-Xms64m -Xmx128m"
EXPOSE 9999
#if runnig dockerfile from withing gateway-server package
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ./gateway-server.jar" ]
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ./gateway-server.jar" ]



