# Extended CodeQL Benchmark Matrix

The benchmark targets unique rule IDs rather than duplicate findings. All vulnerable samples are intentionally unsafe and must never be deployed.

| # | Test case | Expected CodeQL rule | Expected severity | Control/evidence |
| ---: | --- | --- | --- | --- |
| 1 | SQL injection | `java/sql-injection` | High | Existing verified alert |
| 2 | Command injection | `java/command-line-injection` | Critical | Existing verified alert |
| 3 | Path traversal | `java/path-injection` | High | Existing verified alert + normalized-path control |
| 4 | XSS | `java/xss` | High | Existing verified alert |
| 5 | Unsafe deserialization | `java/unsafe-deserialization` | Critical | Existing verified alert |
| 6 | Insecure session randomness | `java/insecure-randomness` | High | `SecureRandom` control |
| 7 | Server-side request forgery | `java/ssrf` | Critical | Request parameter to Java HTTP client |
| 8 | XML external entity expansion | `java/xxe` | Critical | Hardened XML factory control |
| 9 | XPath injection | `java/xml/xpath-injection` | Critical | Request parameter in XPath expression |
| 10 | JNDI injection | `java/jndi-injection` | Critical | Request parameter to JNDI lookup |
| 11 | Regex injection | `java/regex-injection` | High | Request parameter to `Pattern.compile` |
| 12 | Unvalidated redirect | `java/unvalidated-url-redirection` | Medium | Fixed-destination control |
| 13 | HTTP response splitting | `java/http-response-splitting` | Medium | Request parameter in response header |
| 14 | Zip Slip | `java/zipslip` | High | Unvalidated `ZipEntry` output path |
| 15 | Weak cryptographic algorithm | `java/weak-cryptographic-algorithm` | High | DES cipher construction |
| 16 | Hardcoded database credential | `java/hardcoded-credential-api-call` | High | Literal password in JDBC call |

The workflow runs the `security-and-quality` suite so the same analysis also evaluates CodeQL maintainability and reliability queries. This is separate from the GitHub Code Quality product, whose enablement remains license/policy dependent.