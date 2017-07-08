#Hello Spring Annotation

Copyright (C) 2014 Rahul Agarwal
http://www.irahul.com
This work is licensed under the Creative Commons Attribution-NonCommercial 4.0 International License. To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/4.0/deed.en_US.

Sample bean wirings using Spring (4.x) and corresponding JUnit tests

HelloWorld becomes multi-lingual here and based on a specific language Greeter implementation it saysHello.

Adds dependency to hello-spring.

Note that all beans have the `@Component` annotation. There are two ways the beans are scanned:
- In XML `<context:component-scan base-package="com.irahul"/>`
- In `TestHelloWorldSpringNoXML` via annotation 

`HelloWorldSpring` is a simple POJO
`spring-beans.xml` defines the componet scan

`TestHelloWorldSpring`, `TestHelloWorldSpringIntegrationCustomBean` load the above context in two different ways and load the `TestGreeter`

`TestHelloWorldSpringNoXml` uses no XML at all

`AppConfig` and `TestAppConfig` show the use of annotations instead of XML