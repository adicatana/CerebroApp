<<<<<<< HEAD
FROM java:8
ADD /target/cerebro-1.0-SNAPSHOT.war /cerebro.war
=======
FROM 146.169.47.92:5000/jagent
#FROM JAVA:8
ADD /target/cerebro-1.0-SNAPSHOT.war /cerebro.jar
>>>>>>> d872e3c34bec187f4a9cd1b264261504f0609886

ARG published_port=8080
EXPOSE $published_port

ENV JAVA_OPTS $JAVA_OPTS -Dserver.port=$published_port
<<<<<<< HEAD
CMD java $JAVA_OPTS -jar /cerebro.war
=======
CMD java $JAVA_OPTS -jar /cerebro.jar
>>>>>>> d872e3c34bec187f4a9cd1b264261504f0609886
