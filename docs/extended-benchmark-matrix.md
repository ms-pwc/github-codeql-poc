# Extended CodeQL Benchmark Matrix

The benchmark targets unique rule IDs rather than duplicate findings. All vulnerable samples are intentionally unsafe and must never be deployed.

| # | Test case | Expected CodeQL rule | Actual result | Evidence / interpretation |
| ---: | --- | --- | --- | --- |
| 1 | SQL injection | `java/sql-injection` | Detected - High | Verified default-branch alert |
| 2 | Command injection | `java/command-line-injection` | Detected - Critical | Verified default-branch alert |
| 3 | Path traversal | `java/path-injection` | Detected - High | PR and default-branch alerts; normalized-path control was not flagged |
| 4 | XSS | `java/xss` | Detected - High | PR and default-branch alerts |
| 5 | Unsafe deserialization | `java/unsafe-deserialization` | Detected - Critical | Verified default-branch alert |
| 6 | Insecure session randomness | `java/insecure-randomness` | Detected - High | `SecureRandom` control was not flagged |
| 7 | Server-side request forgery | `java/ssrf` | Detected - Critical | Two data-flow results in the Java HTTP client flow |
| 8 | XML external entity expansion | `java/xxe` | Detected - Critical | Hardened XML factory control was not flagged |
| 9 | XPath injection | `java/xml/xpath-injection` | Detected - Critical | User input reached the XPath expression |
| 10 | JNDI injection | `java/jndi-injection` | Detected - Critical | User input reached `InitialContext.lookup` |
| 11 | Regex injection | `java/regex-injection` | Detected - High | User input reached `Pattern.compile` |
| 12 | Unvalidated redirect | `java/unvalidated-url-redirection` | Detected - Medium | Fixed-destination control was not flagged |
| 13 | HTTP response splitting | `java/http-response-splitting` | Not detected | Query was included in the 240-rule suite; the Jakarta Servlet 6 fixture did not produce a result |
| 14 | Zip Slip | `java/zipslip` | Detected - High | Unvalidated `ZipEntry` output path detected |
| 15 | Weak cryptographic algorithm | `java/weak-cryptographic-algorithm` | Detected - High | Two DES findings detected |
| 16 | Hardcoded database credential | `java/hardcoded-credential-api-call` | Not run by suite | GitHub removed this low-precision query from built-in suites; Secret Scanning reported no alert for synthetic values |

## Measured outcome

1. Planned security scenarios detected: **14 of 16 (87.5%)**.
2. Additional unplanned security rules detected: insecure cookies and missing `HttpOnly`.
3. PR results: **19 total** — 5 critical, 8 high, 4 medium, and 2 notes.
4. Unique PR rule IDs: **14**.
5. Unique CodeQL rule IDs across the full POC: **17** — 16 security and 1 maintainability rule.
6. Secure negative controls flagged: **0 of 4**.
7. Rules evaluated: **240**, compared with 76 in the earlier default-suite run.

The workflow runs the `security-and-quality` suite so the same analysis evaluates CodeQL maintainability and reliability queries. This is separate from the GitHub Code Quality product, which is not enabled in this repository.