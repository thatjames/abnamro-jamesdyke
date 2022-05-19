# Hungry Tales recipe server

A simple java RESTful API server to create user authorised recipes

## Installation


### From source

Clone this repo and build with apache maven using `mvn clean package`. The runnable artifact is found under
`application/target/application-{version}.jar`

### Docker

Clone this repo and build the docker image with `docker build -t <your tag> .`

## Running

A public docker image exists under `smokeycircles/hungrytales:latest`. Just define the envvars below to run it.

Requires a postgresql installation to operate. Server must be pointed at this installation using java system properties

`java -jar -Djdbc.url=$JDBC_URL -Djdbc.usern ame=$JDBC_USERNAME -Djdbc.password=$JDBC_PASSWORD ./application-{version}.jar`

Where

- JDBC_URL: is the JDBC url to the postgres instance (e.g `jdbc:postgresql://localhost:5432/hungrytales`)
- JDBC_USERNAME: is the postgres user's designated username
- JDBC_PASSWORD: is the postgres user's designated password

There is also the following 2 options 

- server.port: define the listening port for the server
- cors.hosts: CSV string for CORS Allowed Origins

Documentation

[High Level Architecture](documentation/Architecture.md)

[API](documentation/api/API%20Documentation.md)