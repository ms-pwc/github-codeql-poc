# Code Quality and Security Scanning POC Report

**POC lead:** Naveen  
**Scope:** SonarQube versus GitHub CodeQL and GitHub-native security/code-quality capabilities  
**Repository:** `ms-pwc/github-codeql-poc`  
**Status:** POC evidence collected; SonarQube policy, licensing, and governance inputs pending

---

## 1. Executive Summary

This proof of concept compares SonarQube with GitHub CodeQL and GitHub-native code-quality and security-scanning capabilities. It evaluates security detection, developer pull-request experience, operational effort, integration, reporting, and governance considerations.

The POC verified that GitHub CodeQL finds meaningful Java security vulnerabilities and exposes them directly in pull-request checks before merge. Five verified findings were recorded: command injection, SQL injection, path traversal, unsafe deserialization, and cross-site scripting.

GitHub reduces platform overhead because CodeQL runs natively through GitHub Actions and does not require a separate SonarQube server or database. However, GitHub Code Quality could not be enabled in this repository because the applicable Code Security/Advanced Security prerequisite is not currently met. SonarQube therefore remains stronger for validated code-smell analysis, technical-debt reporting, mature quality gates, and dashboards until PwC-specific requirements and licensing are confirmed.

**Current recommendation: adopt a hybrid model.** Use GitHub CodeQL and Secret Scanning for GitHub-native security feedback, while retaining SonarQube for code quality, technical debt, governance, and reporting until equivalent GitHub coverage is proven.

---

## 2. Scope

| SonarQube | GitHub |
| --- | --- |
| Code quality and code smells | CodeQL security analysis |
| Security analysis | GitHub Code Quality |
| Technical debt | Secret Scanning |
| Quality gates | Pull-request integration and branch checks |
| Reporting and dashboards | Security dashboards and Actions results |

---

## 3. Functional Evaluation

| Capability | SonarQube | GitHub | POC result / qualification |
| --- | --- | --- | --- |
| SQL injection | ✅ | ✅ | CodeQL detected a high-severity Java SQL injection. |
| Command injection | ✅ | ✅ | CodeQL detected a critical Java command injection. |
| XSS | ✅ | ✅ | CodeQL detected a high-severity Java XSS finding. |
| Path traversal | ✅ | ✅ | CodeQL detected a high-severity path-injection finding. |
| Unsafe deserialization | ✅ | ✅ | CodeQL detected a critical unsafe-deserialization finding. |
| Secret detection | ✅ | ✅ | Secret Scanning and push protection are enabled; no synthetic-fixture alert was present at check time. |
| Code smells | ✅ Strong | ⚠️ Unvalidated | GitHub Code Quality enablement is blocked by the Code Security/Advanced Security prerequisite. |
| Maintainability | ✅ Strong | ⚠️ Limited / plan-dependent | SonarQube capability is established; GitHub capability is not yet validated in this POC. |
| Technical debt | ✅ | ❌ No equivalent model validated | SonarQube has a mature debt model; no GitHub equivalent was verified. |
| Quality gates | ✅ | Partial | GitHub supports Actions checks and branch protection. |
| Security dashboard | ✅ | ✅ | GitHub Code Scanning alerts are available. |
| PR annotations and checks | ✅ | ✅ | Verified on the POC pull request. |

### GitHub strengths

1. Native GitHub repository, pull-request, branch-protection, and Actions integration.
2. Five verified high/critical Java security findings in this POC.
3. No separate analysis server or database to run.
4. Findings point to source lines and include remediation guidance.
5. Pull-request checks provide pre-merge developer feedback.

### GitHub limitations and risks

1. Code Quality capability is plan, policy, and licensing dependent.
2. No equivalent technical-debt model has been validated.
3. Secret Scanning detects supported secret patterns; a generic hardcoded password is not necessarily an alert.
4. Dashboard depth, custom-quality-rule support, maintainability analysis, and governance coverage remain to be proven.

### SonarQube strengths

1. Mature code-smell, duplication, maintainability, technical-debt, and quality-gate capabilities.
2. Established profiles, custom rules, dashboards, and reporting.
3. Strong option where enterprise governance and historical quality metrics are mandatory.

### SonarQube limitations and risks

1. Requires server, database, backup, patching, monitoring, and upgrade ownership.
2. Requires scanner, plugin, authentication, and integration maintenance.
3. Actual PwC license cost, required rules, quality gates, and reporting requirements must be confirmed by SonarQube SMEs.

---

## 4. Operational Evaluation

| Area | SonarQube | GitHub |
| --- | --- | --- |
| Server management | Required | None |
| Database | Required | None |
| Upgrades | Required | GitHub managed; maintain pinned Action versions |
| Plugin maintenance | Required | Minimal; manage CodeQL workflow/query configuration |
| Infrastructure cost | Additional | No separate tool infrastructure |
| CI integration | Scanner, endpoint, token, and quality-gate configuration | Native Actions workflow and pull-request checks |
| Administration | Projects, profiles, rules, tokens, and permissions | Repository permissions, workflows, and security feature configuration |
| Scaling and availability | Platform team responsibility | GitHub service responsibility |

### Management implication

GitHub materially reduces operational infrastructure. The trade-off is that GitHub may not currently match SonarQube's technical-debt model, quality-profile depth, customized governance, and executive reporting.

---

## 5. POC Results and Screenshot Evidence

### 5.1 Verified CodeQL findings

| Finding | Severity | Source location | Evidence URL |
| --- | --- | --- | --- |
| Uncontrolled command line | Critical | `VulnerableServlet.java:35` | https://github.com/ms-pwc/github-codeql-poc/security/code-scanning/1 |
| SQL injection | High | `VulnerableServlet.java:30` | https://github.com/ms-pwc/github-codeql-poc/security/code-scanning/2 |
| Uncontrolled data used in path expression | High | `AdvancedVulnerableServlet.java:34` | https://github.com/ms-pwc/github-codeql-poc/security/code-scanning/3 |
| Deserialization of user-controlled data | Critical | `AdvancedVulnerableServlet.java:47` | https://github.com/ms-pwc/github-codeql-poc/security/code-scanning/4 |
| Cross-site scripting | High | `AdvancedVulnerableServlet.java:42` | https://github.com/ms-pwc/github-codeql-poc/security/code-scanning/5 |

### 5.2 Figure 1 - Code Scanning dashboard

**SME-provided screenshot evidence:** GitHub Security and quality > Code scanning shows **5 open** CodeQL findings. The dashboard reports that all tools are working as expected. Visible findings include critical unsafe deserialization, critical uncontrolled command line, high XSS, and high path injection.

**Interpretation:** GitHub CodeQL detected the intentionally vulnerable POC patterns and published them to the repository security dashboard.

### 5.3 Figure 2 - Pull-request check experience

**SME-provided screenshot evidence:** Pull request checks show:

1. `Code scanning results / CodeQL` failed because new security alerts were introduced.
2. `CodeQL Analysis / Analyze (java-kotlin) (pull_request)` succeeded.
3. The pull request has no merge conflicts, but the CodeQL result makes the new alerts visible before merge.

**Interpretation:** Developers can see security findings in the pull-request workflow before merging. This is the core shift-left outcome of Phase 5.

### 5.4 Figure 3 - CodeQL workflow execution

**SME-provided screenshot evidence:** The CodeQL `Analyze (java-kotlin)` job completed successfully. The job shows setup, checkout, Java setup, CodeQL initialization, build, and analysis steps succeeding. The log confirms that Java queries were run.

**Interpretation:** The workflow implementation is operational and produces CodeQL analysis results through GitHub Actions.

### 5.5 Secret Scanning result

| Item | Result |
| --- | --- |
| Secret Scanning | Enabled |
| Push protection | Enabled |
| Synthetic-fixture alerts at check time | 0 |
| Interpretation | Generic passwords are not always provider-secret patterns. Test only with approved synthetic patterns; never commit a real credential. |

### 5.6 GitHub Code Quality result

Code-smell test cases were added, including `if (flag == true)`, unused variables, duplicate methods, and a large method. The GitHub Code Quality enablement API returned HTTP 422 because Code Security can only be enabled when the applicable Advanced Security prerequisite is met. Quality findings are therefore **not validated** in this POC.

---

## 6. Questions for SonarQube SMEs

Alex Popps and Jim Jones, from Chris Greg's team, should provide PwC-specific SonarQube policy, licensing, governance, and reporting input. Dan is aware that the Microsoft team does not have SonarQube subject-matter expertise.

### Licensing

1. What SonarQube edition and version are currently used?
2. What is the license model, annual cost, renewal date, and commercial-feature scope?
3. What server, database, hosting, backup, monitoring, and support cost applies?

### Usage

1. Which PwC teams, applications, repositories, languages, and build systems use SonarQube?
2. Which rules, profiles, custom plugins, and integrations are mandatory?
3. Which security and quality findings are used to block merges or releases?

### Governance and compliance

1. Which quality gates are mandatory?
2. Which audit, regulatory, client, or compliance requirements depend on SonarQube data?
3. Are false positives, suppressions, exceptions, and rule changes governed centrally?

### Metrics and reporting

1. Is technical-debt tracking required for engineering or executive reporting?
2. Are coverage, duplication, maintainability, reliability, or security ratings mandatory?
3. Which executive dashboards or audit exports must be retained?

---

## 7. Recommendation

### Option A - Keep SonarQube

Use when PwC requires existing SonarQube quality gates, technical-debt tracking, dashboards, custom rules, and audit reporting that GitHub cannot yet match.

### Option B - Move to GitHub

Use when repositories are already on GitHub, CodeQL and Secret Scanning cover requirements, GitHub Code Quality is enabled, and reduced operational overhead outweighs SonarQube-specific functionality.

### Option C - Hybrid model (recommended)

Use GitHub CodeQL and Secret Scanning for native security scanning and pre-merge feedback. Retain SonarQube for code quality, technical debt, governance, and reporting until GitHub coverage is benchmarked and approved.

### Recommended next actions

1. Use the five verified alert URLs above to capture the final evidence screenshots for the leadership deck.
2. Schedule a 30-minute discussion with Alex Popps and Jim Jones using the questions in Section 6.
3. Confirm GitHub Advanced Security/Code Security licensing and policy for target private repositories.
4. Build a 10-15 issue benchmark, record which tool detects each issue, and measure false-positive rate and remediation quality.
5. Set formal exit criteria before retiring SonarQube for any team.

---

## Appendix - POC phase value

| Phase | Why it matters |
| --- | --- |
| Phase 4 | Proves vulnerability-detection coverage. |
| Phase 5 | Proves pre-merge pull-request experience. |
| Phase 6 | Tests credential-leak protection. |
| Phase 7 | Tests code-quality availability and limits. |
| Phase 8 | Compares functional coverage with SonarQube. |
| Phase 9 | Compares operating effort and management impact. |