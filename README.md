# Camel Servlet examples

Cookbook examples of using Weld for CDI in a Camel Servlet environment.
Includes configuring a CamelContext e.g using a custom thread pool.


Run using:
```
$ ./gradlew appRunWar
```

## Simple get using CDI to configure

Access a simple GET route using:
```
$ curl "http://localhost:8080/camel-cdi-servlet-example/camel/say/hello"
```

## Handle multipart mime 

Example of handling multipart mime content that is posted using curl.
The content comes from 2 simple files.
Once running open a shell in the root directly of this repo and:

```bash
$ curl -X POST \
 -F "item1=@data/simple.json;type=application/json;filename=first.json"\
 -F "item2=@data/simple.txt;type=text/plain"\
 -H "Content-Type: multipart/mixed"\
 "http://localhost:8080/camel-cdi-servlet-example/camel/multi/"
```

In the console running Camel you should see:

```
Body Content-Type: application/json
Body:
{"name":"superman","speciality":"flying","weakness":"kryptonite"}

Attachments: 1
simple.txt text/plain simple.txt
name=spiderman
speciality=climbing
```

You will notice that the first form field becomes the body of the exchange and the second form field becomes the first
attachment and so on.
