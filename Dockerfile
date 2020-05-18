FROM adoptopenjdk/openjdk11
VOLUME /tmp
ADD target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]