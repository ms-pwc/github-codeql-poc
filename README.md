# github-codeql-poc

A minimal Java console application.

## Prerequisites

Install a Java Development Kit (JDK) 17 or later.

## Run

From the repository root, compile and run the application:

```powershell
javac -d out src/main/java/com/example/App.java
java -cp out com.example.App GitHub
```

The application prints a greeting. The name argument is optional and defaults to `World`.