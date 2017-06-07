# spring-jms-remoting

### Create files jars
```
mvn clean install
```

### Run server:
```
cd spring-jms-server
mvn clean compile exec:java -Dexec.mainClass=com.spring.jms.server.Server
```

### Run client:
```
cd spring-jms-client
mvn clean compile exec:java -Dexec.mainClass=com.spring.jms.client.Client
```
