FROM amazoncorretto:11 AS builder
RUN yum install -y tar gzip
RUN mkdir /opt/maven && \
  cd /opt/maven && \
  curl -L "https://dlcdn.apache.org/maven/maven-3/3.8.5/binaries/apache-maven-3.8.5-bin.tar.gz" | tar -xzv --strip-components=1
ENV M2_HOME=/opt/maven
WORKDIR /build
COPY . .
RUN $M2_HOME/bin/mvn clean package

FROM amazoncorretto:11
WORKDIR /app
COPY --from=builder /build/application/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./app.jar"]