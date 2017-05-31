FROM JAVA:8
EXPOSE 8080
ADD /target/cerebro-1.0-SNAPSHOT.war cerebro.war
ENTRYPOINT ["java", "-jar", "cerebro.war"]
