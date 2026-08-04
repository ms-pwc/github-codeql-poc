# POC Evidence Register

## Verified CodeQL findings

| # | Finding | Severity | Source location | Live evidence | Screenshot action |
| --- | --- | --- | --- | --- | --- |
| 1 | Uncontrolled command line | Critical | `src/main/java/com/example/VulnerableServlet.java:35` | https://github.com/ms-pwc/github-codeql-poc/security/code-scanning/1 | Capture alert details and remediation panel. |
| 2 | SQL injection | High | `src/main/java/com/example/VulnerableServlet.java:30` | https://github.com/ms-pwc/github-codeql-poc/security/code-scanning/2 | Capture alert details and code flow. |
| 3 | Path traversal | High | `src/main/java/com/example/AdvancedVulnerableServlet.java:34` | https://github.com/ms-pwc/github-codeql-poc/security/code-scanning/3 | Capture alert details and code flow. |
| 4 | Unsafe deserialization | Critical | `src/main/java/com/example/AdvancedVulnerableServlet.java:47` | https://github.com/ms-pwc/github-codeql-poc/security/code-scanning/4 | Capture alert details and remediation panel. |
| 5 | Cross-site scripting | High | `src/main/java/com/example/AdvancedVulnerableServlet.java:42` | https://github.com/ms-pwc/github-codeql-poc/security/code-scanning/5 | Capture alert details and code flow. |

## Pull-request evidence

| Evidence | URL | Result |
| --- | --- | --- |
| Phase 5 pull request | https://github.com/ms-pwc/github-codeql-poc/pull/1 | CodeQL check displayed before merge. |
| Pull-request checks | https://github.com/ms-pwc/github-codeql-poc/pull/1/checks | Shows CodeQL analysis status for the PR. |
| Latest verified PR CodeQL workflow | https://github.com/ms-pwc/github-codeql-poc/actions/runs/30887257049 | Analyze job completed successfully. |

## Secret Scanning evidence

| Item | Result |
| --- | --- |
| Secret Scanning | Enabled |
| Push protection | Enabled |
| Current synthetic fixture alerts | 0 at the time of API check |
| Interpretation | Generic passwords are not necessarily provider-secret patterns; additional valid test material must follow PwC policy and should never be a real credential. |

## Code Quality evidence

| Item | Result |
| --- | --- |
| Code-smell test fixture | Added to the POC branch |
| Enablement attempt | Blocked by GitHub Code Security / Advanced Security prerequisite |
| Recorded API response | HTTP 422: Code Security can only be enabled if Advanced Security is enabled under the applicable billing model |

## Screenshot checklist

1. Capture the five linked CodeQL alert pages above.
2. Capture the pull-request Checks page showing `Analyze (java-kotlin)`.
3. Capture Security and quality > Code scanning alerts summary.
4. Capture Secret Scanning enablement and alert-state page.
5. Capture Code Quality enablement state and prerequisite message, if visible to the repository administrator.