# Phase 4 - Vulnerability Test Cases

POC objective:

Can GitHub Code Quality + CodeQL replace or partially replace SonarQube?

## Scenario matrix

| Scenario | Expected scanner | Detection expectation |
| --- | --- | --- |
| SQL Injection | CodeQL | Should detect |
| Command Injection | CodeQL | Should detect |
| Hardcoded Secret | Secret Scanning | Should detect |
| Path Traversal | CodeQL | Should detect |
| Weak Random | CodeQL | May detect |
| XSS | CodeQL | Should detect |
| Unsafe Deserialization | CodeQL | Should detect |

## Findings reference sheet

| Issue Type | Severity (expected) | File | Line Number | Remediation Guidance |
| --- | --- | --- | --- | --- |
| SQL Injection | High | src/main/java/com/example/VulnerableServlet.java | 29 | Use parameterized queries (`PreparedStatement`) and strict input validation. |
| Command Injection | Critical | src/main/java/com/example/VulnerableServlet.java | 35 | Avoid shell execution with untrusted input; use allow-lists and APIs that do not invoke command interpreters. |
| Hardcoded Secret (AWS key ID) | High | src/main/java/com/example/SecretFixture.java | 7 | Remove secrets from source control, rotate credentials, and load from secure secret stores. |
| Hardcoded Secret (AWS secret key) | Critical | src/main/java/com/example/SecretFixture.java | 8 | Remove secret immediately, rotate compromised key material, enforce pre-commit secret scanning. |
| Path Traversal | High | src/main/java/com/example/AdvancedVulnerableServlet.java | 33 | Normalize and canonicalize paths, enforce directory allow-list, reject `..` and absolute paths. |
| Weak Random | Medium | src/main/java/com/example/AdvancedVulnerableServlet.java | 37 | Use `SecureRandom` for any security-sensitive token or identifier. |
| Cross-Site Scripting (XSS) | High | src/main/java/com/example/AdvancedVulnerableServlet.java | 42 | HTML-encode untrusted data on output and use templating libraries with contextual escaping. |
| Unsafe Deserialization | High | src/main/java/com/example/AdvancedVulnerableServlet.java | 47 | Do not deserialize untrusted data; use safe formats (JSON) and explicit type allow-lists if unavoidable. |

## Validation workflow

1. Push changes to `main` to trigger `.github/workflows/codeql.yml`.
2. Confirm run success in GitHub Actions.
3. Review CodeQL alerts under Security and quality > Code scanning alerts.
4. Review Secret Scanning alerts under Security and quality > Secret scanning.