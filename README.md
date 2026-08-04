# github-codeql-poc

A minimal Java console application.

## Prerequisites

Install a Java Development Kit (JDK) 17 or later and Apache Maven 3.9 or later.

## Run

From the repository root, compile and run the application:

```powershell
mvn clean compile
java -cp target/classes com.example.App GitHub
```

The application prints a greeting. The name argument is optional and defaults to `World`.

## CodeQL

The workflow at `.github/workflows/codeql.yml` runs CodeQL analysis for Java and Kotlin on pushes and pull requests targeting `main`.