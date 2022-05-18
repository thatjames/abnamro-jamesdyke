#!/bin/bash
java -jar -Djdbc.url=$JDBC_URL -Djdbc.username=$JDBC_USERNAME -Djdbc.password=$JDBC_PASSWORD ./app.jar