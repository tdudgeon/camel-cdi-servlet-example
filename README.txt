Example of using Weld for CDI in a Camel Servlet environment.
Includes configuring a custom thread pool.


Run using:
./gradlew appRunWar

Access a simple GET route using:
curl "http://localhost:8080/camel-cdi-servlet-example/camel/say/hello"