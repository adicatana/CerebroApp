FROM 146.169.47.92:5000/jagent
#FROM JAVA:8
ADD /target/cerebro-1.0-SNAPSHOT.war /cerebro.jar

ARG published_port=8080
EXPOSE $published_port

ENV JAVA_OPTS $JAVA_OPTS -Dserver.port=$published_port
CMD java $JAVA_OPTS -jar /cerebro.jar
