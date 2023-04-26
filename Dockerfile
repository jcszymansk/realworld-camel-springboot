FROM nginx:1.23.4-alpine-slim

LABEL maintainer="Jacek Szymański <jacek.p.szymanski@gmail.com>"

ARG JAR_FILE

# prepare nginx
EXPOSE 80
RUN rm /etc/nginx/conf.d/default.conf
COPY etc/reverse-proxy.conf /etc/nginx/conf.d/reverse-proxy.conf
COPY etc/start-nginx.sh /docker-entrypoint.d/99-start-nginx.sh
RUN chmod +x /docker-entrypoint.d/99-start-nginx.sh
COPY etc/docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod +x /docker-entrypoint.sh

# prepare java
ENV JAVA_HOME=/opt/java/openjdk
COPY --from=eclipse-temurin:11-jdk-alpine $JAVA_HOME $JAVA_HOME
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# get frontend
RUN apk add --no-cache git sed

RUN mv /usr/share/nginx/html /usr/share/nginx/html.bak
WORKDIR /usr/share/nginx
RUN git clone -n https://github.com/mits-gossau/event-driven-web-components-realworld-example-app.git html
RUN cd html && git checkout f3adc98 -- .
RUN cp -v html.bak/[45]* html/
WORKDIR /usr/share/nginx/html
RUN sed -i -Ee 's,https://conduit.productionready.io/api,/api,g' src/es/helpers/Environment.js

# put backend - before building run "mvn package" in the project root
RUN mkdir /app
WORKDIR /app
COPY ${JAR_FILE} /app/app.jar
COPY src/test/resources/jwt.key.txt /app/etc/jwt.key.txt
COPY etc/conduit.h2.mv.db.gz /app/data/conduit.h2.mv.db.gz
RUN gunzip /app/data/conduit.h2.mv.db.gz

ENV CLASSPATH=/app/app.jar:/app/etc

CMD ["java", "-jar", "/app/app.jar", "--server.port=8080"]
