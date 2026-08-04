# Code Quality and Security Scanning Tool POC

## SonarQube versus GitHub CodeQL and GitHub-native capabilities

**POC lead:** Naveen  
**Client objective:** Select a strategic code-quality and security-scanning toolset  
**Repository:** `ms-pwc/github-codeql-poc`  
**Decision date:** 4 August 2026  
**Recommendation:** Hybrid adoption, with GitHub CodeQL for security and SonarQube retained for mature quality governance

## 1. Executive decision

The POC proves that GitHub CodeQL is a strong, low-operations security scanner for GitHub-hosted development. The expanded Java benchmark evaluated 240 rules and produced 19 actionable pull-request findings: 5 critical, 8 high, 4 medium, and 2 maintainability notes. Across the full POC, CodeQL demonstrated 17 unique rule IDs, including SQL injection, command injection, SSRF, XXE, XPath injection, JNDI injection, Zip Slip, insecure randomness, unsafe deserialization, XSS, path traversal, weak cryptography, regex injection, URL redirection, cookie security, and unread variables.

The same POC does not support replacing SonarQube for code quality today. The CodeQL `security-and-quality` suite detected only one of four designed quality categories, while the separate GitHub Code Quality product is not enabled for this repository. Technical-debt tracking, broad maintainability coverage, coverage/duplication governance, portfolio dashboards, and mature quality gates remain SonarQube strengths.

**Decision:** adopt a hybrid model. Standardize GitHub CodeQL and Secret Scanning for native pre-merge security feedback. Retain SonarQube for code quality and governance for an initial 6-12 month transition. Reconsider SonarQube retirement only after GitHub Code Quality is licensed, enabled, and benchmarked against the adopted quality baseline.

## 2. Decision highlights

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
| GitHub Code Quality | Not enabled |

## 3. Scope and method

The GitHub side was tested directly through source changes, Maven compilation, GitHub Actions, CodeQL SARIF results, repository dashboards, pull-request checks, Secret Scanning, and authenticated screenshots. SonarQube was not connected because no client SonarQube endpoint or token was supplied; its evaluation uses published product capabilities and explicit decision assumptions rather than fabricated runtime results.

The benchmark included intentionally vulnerable Java/Jakarta Servlet fixtures and four secure negative controls. Results were counted by unique rule ID as well as total finding count. The final workflow used CodeQL v4, Java 17, manual Maven build mode, and the `security-and-quality` query suite.

## 4. Adopted client requirements and assumptions

To avoid leaving decision fields unanswered, the report uses the following governance baseline:

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

CodeQL detected one of four designed quality categories (25%). This does not measure the separate GitHub Code Quality product, because that feature is not enabled. The result demonstrates that CodeQL's expanded query suite adds useful quality signals but is not a complete SonarQube quality replacement.

## 7. Secret Scanning result

Secret Scanning and push protection are enabled. The authenticated repository page shows 0 open and 0 closed alerts and “No secrets found.” The fixtures used synthetic, invalid provider-shaped values and a generic password; no real credential was committed. The result proves feature operation and safe test handling, but also shows that a generic hardcoded password is not guaranteed to match default provider patterns. Organization-specific secrets require approved custom patterns or generic/AI secret capabilities where licensed.

## 8. Functional comparison

| Capability | SonarQube | GitHub CodeQL / native tools | POC conclusion |
| --- | --- | --- | --- |
| Taint-based security analysis | Strong | Strong | GitHub proven with 14/16 planned scenarios |
| Pull-request annotations | Supported | Native and proven | GitHub has lower integration friction |
| Code smells | Broad | Limited in CodeQL suite; Code Quality not enabled | SonarQube advantage |
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

The final run initially experienced a transient Maven Central HTTP 429, then succeeded unchanged on rerun. This is an external dependency availability risk, not a CodeQL analysis failure. Recommended mitigation is Maven caching, an approved artifact proxy, and retry policy.

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

- GitHub Code Quality is entitlement-dependent and not enabled in this repository.
- CodeQL quality-query coverage was limited in the designed benchmark.
- No mature technical-debt model was proven.
- Security query coverage depends on supported languages/framework models.
- Response-splitting fixture demonstrated a modeling gap.
- Workflow execution depends on Actions, network availability, and package repositories.

## 11. Weighted decision score

Scores are 1-5. GitHub scores use measured POC evidence; SonarQube scores use published capability under the adopted assumptions.

| Criterion | Weight | SonarQube | GitHub-only | Hybrid |
| --- | ---: | ---: | ---: | ---: |
| Security coverage | 25% | 4 | 5 | 5 |
| Code quality depth | 20% | 5 | 2 | 5 |
| Pull-request experience | 15% | 4 | 5 | 5 |
| Governance and reporting | 15% | 5 | 3 | 5 |
| Operational simplicity | 15% | 2 | 5 | 3 |
| Cost/lock-in flexibility | 10% | 3 | 3 | 2 |
| **Weighted score / 100** | **100%** | **79** | **78** | **88** |

The hybrid option scores highest because it combines measured GitHub security and PR strengths with SonarQube quality/governance depth. Its disadvantages are dual licensing and temporary administrative overlap.

## 12. Final recommendation and roadmap

### Recommendation: Hybrid, then evidence-based consolidation

1. **Now:** standardize CodeQL advanced setup, Secret Scanning, push protection, and security merge blocking for GitHub repositories.
2. **Now:** retain SonarQube for quality gates, maintainability, technical debt, coverage, duplication, and portfolio reporting.
3. **Within 90 days:** enable GitHub Code Quality on representative private repositories if entitlement is approved; rerun the same quality benchmark.
4. **Within 6 months:** compare finding coverage, false positives, remediation time, developer acceptance, license cost, and administration effort across representative teams.
5. **At 6-12 months:** retire SonarQube only if GitHub meets the adopted quality/governance baseline and migration costs are justified.

### Go/no-go criteria for GitHub-only

- GitHub Code Quality detects at least 80% of mandatory quality-rule categories.
- Coverage and duplication controls are enforceable in required repositories.
- Portfolio and audit reporting meet stakeholder needs.
- Required languages and frameworks are supported.
- Total commercial cost is lower after including GitHub security/quality entitlements.
- No mandatory SonarQube custom rule or report remains uncovered.

## 13. Evidence gallery

[[IMAGE:01-pr-checks-overview.png|Figure 1 — Pull request checks are visible before merge.]]

[[IMAGE:04-codeql-severity-summary.png|Figure 2 — CodeQL check reports 19 new alerts: 5 critical, 8 high, 4 medium, and 2 notes.]]

[[IMAGE:02-branch-alert-dashboard.png|Figure 3 — Authenticated branch alert dashboard with all 19 findings.]]

[[IMAGE:03-workflow-success.png|Figure 4 — Final CodeQL workflow rerun completed successfully in 1m 28s.]]

[[IMAGE:05-secret-scanning.png|Figure 5 — Secret Scanning shows 0 open and 0 closed alerts.]]

[[IMAGE:06-code-quality.png|Figure 6 — GitHub Code Quality is not enabled for this repository.]]

## 14. Evidence links

- Pull request: https://github.com/ms-pwc/github-codeql-poc/pull/1
- Final workflow: https://github.com/ms-pwc/github-codeql-poc/actions/runs/30892230825
- CodeQL check: https://github.com/ms-pwc/github-codeql-poc/runs/91936994594
- Branch alerts: https://github.com/ms-pwc/github-codeql-poc/security/code-scanning?query=pr%3A1+tool%3ACodeQL+is%3Aopen
- Benchmark matrix: `docs/extended-benchmark-matrix.md`
- Benchmark source: `src/main/java/com/example/ExtendedSecurityBenchmarkServlet.java`
- Secure controls: `src/main/java/com/example/SecureNegativeControls.java`

## 15. Product references

- GitHub CodeQL code scanning: https://docs.github.com/en/code-security/code-scanning/introduction-to-code-scanning/about-code-scanning-with-codeql
- GitHub Secret Scanning: https://docs.github.com/en/code-security/secret-scanning/introduction/about-secret-scanning
- GitHub Code Quality: https://docs.github.com/code-security/code-quality/concepts/about-code-quality
- SonarQube documentation: https://docs.sonarsource.com/sonarqube-server/latest/
- SonarQube plans and pricing: https://www.sonarsource.com/plans-and-pricing/

## 16. Limitations

1. SonarQube was not runtime-tested because no client instance/token was supplied; the recommendation compensates with conservative assumptions and retains SonarQube for its strongest capability area.
2. The benchmark is Java/Jakarta-focused and must be repeated for other portfolio languages.
3. Intentional vulnerabilities must remain isolated to this POC and must never be deployed.
4. No real secrets were committed; Secret Scanning behavior was tested safely with synthetic values.
5. Pricing conclusions require contract review, but the technical decision does not depend on invented customer-specific prices.