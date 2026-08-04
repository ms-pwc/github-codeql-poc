# Code Quality and Security Scanning Tool POC

## SonarQube versus GitHub CodeQL and GitHub-native capabilities

**POC lead:** Naveen  
**Client objective:** Compare SonarQube and GitHub-native code-quality and security-scanning toolsets  
**Repository:** `ms-pwc/github-codeql-poc`  
**Assessment date:** 4 August 2026  
**Output:** Neutral evaluation of two alternatives: SonarQube or GitHub-native tooling

## 1. Executive comparison

The POC proves that GitHub CodeQL is a strong, low-operations security scanner for GitHub-hosted development. The expanded Java benchmark evaluated 240 rules and produced 19 actionable pull-request findings: 5 critical, 8 high, 4 medium, and 2 maintainability notes. Across the full POC, CodeQL demonstrated 17 unique rule IDs, including SQL injection, command injection, SSRF, XXE, XPath injection, JNDI injection, Zip Slip, insecure randomness, unsafe deserialization, XSS, path traversal, weak cryptography, regex injection, URL redirection, cookie security, and unread variables.

The POC does not establish that GitHub fully replaces SonarQube for code quality. The CodeQL `security-and-quality` suite detected one of four designed quality categories, while the newly enabled GitHub Code Quality page reports Excellent maintainability, Excellent reliability, 0 findings, and 0 open rules for this repository. Technical-debt tracking, broad maintainability coverage, coverage/duplication governance, portfolio dashboards, and mature quality gates remain SonarQube comparison points that require broader portfolio validation.

The evidence supports two viable approaches. SonarQube emphasizes mature code-quality governance, technical-debt metrics, coverage, duplication, and portfolio reporting. GitHub emphasizes native security scanning, pull-request integration, and lower infrastructure overhead. The following sections present both alternatives without selecting one.

## 2. Evaluation highlights

| Measure | Result |
| --- | --- |
| Planned security scenarios | 16 |
| Planned scenarios detected | 14 (87.5%) |
| Final PR findings | 19 |
| Severity distribution | 5 critical, 8 high, 4 medium, 2 notes |
| Unique PR rule IDs | 14 |
| Unique rules across full POC | 17 (16 security, 1 quality) |
| Rules evaluated | 240 |
| Earlier default-suite rules | 76 |
| Secure negative controls flagged | 0 of 4 |
| Final workflow | Success in 1m 28s |
| Secret Scanning | Enabled; 0 unresolved secrets |
| GitHub Code Quality | Enabled; Excellent maintainability and reliability; 0 findings |

## 3. Scope and method

The GitHub side was tested directly through source changes, Maven compilation, GitHub Actions, CodeQL SARIF results, repository dashboards, pull-request checks, Secret Scanning, and authenticated screenshots. SonarQube was not connected because no client SonarQube endpoint or token was supplied; its evaluation uses published product capabilities and explicit comparison assumptions rather than fabricated runtime results.

The benchmark included intentionally vulnerable Java/Jakarta Servlet fixtures and four secure negative controls. Results were counted by unique rule ID as well as total finding count. The final workflow used CodeQL v4, Java 17, manual Maven build mode, and the `security-and-quality` query suite.

## 4. Adopted client requirements and assumptions

To avoid leaving evaluation fields unanswered, the report uses the following governance baseline:

1. Critical and high-confidence new security findings block merge until remediated or formally risk-accepted.
2. New code should achieve at least 80% test coverage when coverage data is available.
3. New-code duplication should remain at or below 3%.
4. Security hotspots require documented review before release.
5. Pull requests must display analysis status and source-level remediation guidance.
6. Teams require maintainability, reliability, security, coverage, and duplication trends.
7. Management requires monthly portfolio reporting; audit stakeholders require exportable evidence and exception history.
8. Production repositories are assumed to be private organization repositories on GitHub.
9. Both commercial SonarQube capabilities and GitHub private-repository security products require paid entitlement; actual PwC contract prices are excluded rather than invented.
10. A SonarQube Server choice includes server, database, backup, patching, monitoring, upgrade, and plugin ownership.

## 5. Security benchmark results

| Scenario | CodeQL result | Severity | Interpretation |
| --- | --- | --- | --- |
| SQL injection | Detected | High | Strong taint-flow result with prepared-statement remediation |
| Command injection | Detected | Critical | Direct user input to process execution detected |
| Path traversal | Detected | High | User-controlled path detected; normalized-path control clean |
| Cross-site scripting | Detected | High | Direct HTML output flow detected |
| Unsafe deserialization | Detected | Critical | User-controlled Java object stream detected |
| Insecure randomness | Detected | High | Predictable session token detected; `SecureRandom` control clean |
| SSRF | Detected | Critical | Two Java HTTP-client flow findings |
| XXE | Detected | Critical | Unsafe XML parser configuration detected; hardened control clean |
| XPath injection | Detected | Critical | User-controlled XPath expression detected |
| JNDI injection | Detected | Critical | User-controlled lookup name detected |
| Regex injection | Detected | High | User-controlled regular expression detected |
| Open redirect | Detected | Medium | User-controlled redirect detected; allow-list control clean |
| Zip Slip | Detected | High | Unsanitized archive path detected |
| Weak cryptography | Detected | High | DES and ECB usage produced two findings |
| HTTP response splitting | Not detected | N/A | Query was present, but no result for the Jakarta Servlet 6 fixture; recorded modeling gap |
| Hardcoded credential | Not in built-in suite | N/A | Low-precision Java query removed from built-in suites; synthetic secret values produced no Secret Scanning alert |

Two additional cookie-security rules were discovered without being explicitly targeted: failure to use `Secure` and failure to use `HttpOnly`.

## 6. Quality benchmark results

| Designed quality category | CodeQL `security-and-quality` result |
| --- | --- |
| Unused local variables | Detected twice |
| Redundant boolean comparison | Not detected |
| Duplicate methods | Not detected |
| Large/complex method | Not detected |

CodeQL detected one of four designed quality categories (25%). Separately, the enabled GitHub Code Quality page reports Excellent maintainability, Excellent reliability, 0 findings, 0 open rules, and 0 AI findings for this repository. This is positive product evidence, but a zero-finding score on a small synthetic POC is not equivalent to proving SonarQube feature parity; the same benchmark should be repeated on representative production repositories.

## 7. Secret Scanning result

Secret Scanning and push protection are enabled. The authenticated repository page shows 0 open and 0 closed alerts and “No secrets found.” The fixtures used synthetic, invalid provider-shaped values and a generic password; no real credential was committed. The result proves feature operation and safe test handling, but also shows that a generic hardcoded password is not guaranteed to match default provider patterns. Organization-specific secrets require approved custom patterns or generic/AI secret capabilities where licensed.

## 8. Functional comparison

| Capability | SonarQube | GitHub CodeQL / native tools | POC conclusion |
| --- | --- | --- | --- |
| Taint-based security analysis | Strong | Strong | GitHub proven with 14/16 planned scenarios |
| Pull-request annotations | Supported | Native and proven | GitHub has lower integration friction |
| Code smells | Broad | Code Quality enabled; no open findings in this POC | Broader portfolio validation required |
| Maintainability rating | Mature | Not proven | SonarQube advantage |
| Technical debt | Mature debt model | No equivalent proven | SonarQube advantage |
| Coverage governance | Native metric and gates | Requires external coverage plus checks | SonarQube advantage |
| Duplication analysis | Native | Not proven | SonarQube advantage |
| Quality gates | Mature configurable gates | Checks/rulesets can block merge | SonarQube richer; GitHub simpler |
| Secret detection | Supported | Native Secret Scanning | GitHub native advantage for GitHub repos |
| Custom security queries | Custom rules/plugins | Open-source CodeQL query packs | Both extensible; different skillsets |
| Portfolio dashboards | Strong | Basic repository/org security views | SonarQube advantage |
| Audit evidence | Strong in enterprise editions | Check runs, alerts, APIs, audit features by plan | Depends on entitlement |
| Language breadth | Broad, edition-dependent | CodeQL supported-language list | Must validate application portfolio |

## 9. Operational comparison

| Area | SonarQube Server | GitHub-native |
| --- | --- | --- |
| Application server | Required | None |
| Database | Required | None |
| Backups and disaster recovery | Client responsibility | GitHub-managed service |
| Upgrades | Planned server/plugin upgrades | Managed platform; maintain workflow action versions |
| Plugins | Compatibility and maintenance required | Query packs/workflow configuration |
| CI integration | Scanner, URL, token, quality gate | Native GitHub Actions and checks |
| Availability/scaling | Client platform responsibility | GitHub-managed |
| Dependency risk | Server, DB, plugins | Actions/SaaS/network and package repositories |
| Observed run | Not tested | 1m 28s successful final run |

The final run initially experienced a transient Maven Central HTTP 429, then succeeded unchanged on rerun. This is an external dependency availability risk, not a CodeQL analysis failure. Practical mitigation includes Maven caching, an approved artifact proxy, and retry policy.

## 10. Pros and cons

### SonarQube

**Pros**

- Mature code-smell, maintainability, duplication, coverage, and technical-debt model.
- Configurable quality profiles and quality gates.
- Strong portfolio dashboards and governance options.
- Suitable when quality metrics and compliance reporting are mandatory.
- Supports deployment outside GitHub and across mixed SCM estates.

**Cons**

- SonarQube Server requires infrastructure, database, backup, patching, monitoring, upgrades, and plugin maintenance.
- Separate UI and administration model from GitHub.
- Commercial private-project and enterprise features add license cost.
- PR integration requires configuration and credentials.
- Duplicates some GitHub-native security capabilities.

### GitHub CodeQL and native tools

**Pros**

- Excellent measured security coverage in this POC.
- Native PR annotations, code flows, checks, and branch blocking.
- No separate server or database.
- Open-source query ecosystem and customizable query packs.
- Native Secret Scanning and push protection.
- Low adoption friction for repositories already on GitHub.

**Cons**

- GitHub Code Quality is now enabled and reports Excellent maintainability and reliability with 0 findings in this small POC.
- CodeQL quality-query coverage was limited in the designed benchmark.
- No mature technical-debt model was proven.
- Security query coverage depends on supported languages/framework models.
- Response-splitting fixture demonstrated a modeling gap.
- Workflow execution depends on Actions, network availability, and package repositories.

## 11. Alternatives and implementation considerations

### Alternative A: SonarQube-led approach

1. Use SonarQube as the primary code-quality and security-governance platform.
2. Apply SonarQube quality gates for maintainability, technical debt, coverage, duplication, reliability, and security.
3. Use pull-request decoration and CI quality-gate checks to provide developer feedback.
4. Best fit when portfolio dashboards, audit reporting, custom quality profiles, and cross-SCM governance are priority requirements.
5. Trade-offs: server and database operations for SonarQube Server, separate administration, integration configuration, and commercial licensing.

### Alternative B: GitHub-native approach

- Use CodeQL advanced setup, Secret Scanning, push protection, rulesets, and pull-request checks.
- GitHub Code Quality is enabled for this repository; validate its ratings and rule coverage on representative production repositories before rollout.
- Integrate external test-coverage and duplication reporting where GitHub-native findings do not provide the required controls.
- Best fit when repositories are already on GitHub and native developer workflow plus low infrastructure overhead are priorities.
- Trade-offs: entitlement-dependent features, limited quality coverage demonstrated in this POC, and no mature technical-debt model.
- Validate all required languages, frameworks, reports, and commercial costs against the application portfolio.

## 12. Evidence gallery

[[IMAGE:01-pr-checks-overview.png|Figure 1 — Pull request checks are visible before merge.]]

[[IMAGE:04-codeql-severity-summary.png|Figure 2 — CodeQL check reports 19 new alerts: 5 critical, 8 high, 4 medium, and 2 notes.]]

[[IMAGE:02-branch-alert-dashboard.png|Figure 3 — Authenticated branch alert dashboard with all 19 findings.]]

[[IMAGE:03-workflow-success.png|Figure 4 — Final CodeQL workflow rerun completed successfully in 1m 28s.]]

[[IMAGE:05-secret-scanning.png|Figure 5 — Secret Scanning shows 0 open and 0 closed alerts.]]

[[IMAGE:06-code-quality.png|Figure 6 — GitHub Code Quality is enabled: Excellent maintainability, Excellent reliability, and 0 open findings.]]

## 13. Evidence links

- Pull request: https://github.com/ms-pwc/github-codeql-poc/pull/1
- Final workflow: https://github.com/ms-pwc/github-codeql-poc/actions/runs/30892230825
- CodeQL check: https://github.com/ms-pwc/github-codeql-poc/runs/91936994594
- Branch alerts: https://github.com/ms-pwc/github-codeql-poc/security/code-scanning?query=pr%3A1+tool%3ACodeQL+is%3Aopen
- Benchmark matrix: `docs/extended-benchmark-matrix.md`
- Benchmark source: `src/main/java/com/example/ExtendedSecurityBenchmarkServlet.java`
- Secure controls: `src/main/java/com/example/SecureNegativeControls.java`

## 14. Product references

- GitHub CodeQL code scanning: https://docs.github.com/en/code-security/code-scanning/introduction-to-code-scanning/about-code-scanning-with-codeql
- GitHub Secret Scanning: https://docs.github.com/en/code-security/secret-scanning/introduction/about-secret-scanning
- GitHub Code Quality: https://docs.github.com/code-security/code-quality/concepts/about-code-quality
- SonarQube documentation: https://docs.sonarsource.com/sonarqube-server/latest/
- SonarQube plans and pricing: https://www.sonarsource.com/plans-and-pricing/

## 15. Limitations

1. SonarQube was not runtime-tested because no client instance or token was supplied; the assessment therefore uses published capabilities and conservative assumptions without presenting runtime equivalence as proven.
2. The benchmark is Java/Jakarta-focused and must be repeated for other portfolio languages.
3. Intentional vulnerabilities must remain isolated to this POC and must never be deployed.
4. No real secrets were committed; Secret Scanning behavior was tested safely with synthetic values.
5. Pricing conclusions require contract review; the comparison intentionally avoids inventing customer-specific prices.