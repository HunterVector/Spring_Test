FROM java:8
VOLUME /tmp
ADD *.jar app.jar
EXPOSE 8020
RUN mkdir /usr/local/upload/
RUN chmod 777 /usr/local/upload/
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar" ]