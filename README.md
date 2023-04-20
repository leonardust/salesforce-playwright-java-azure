If setting results directory in allure plugin doesn't work

```xml

<plugin>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-maven</artifactId>
    <version>2.12.0</version>
    <configuration>
        <resultsDirectory>${project.build.directory}/allure-results</resultsDirectory>
    </configuration>
</plugin>
```

create ```allure.properties``` with

```properties
allure.results.directory=target/allure-results
```

**File encoding**

To avoid Maven build message
```[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!```

add to POM.xml

```xml

<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
</properties>
```