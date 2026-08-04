# SonarQube vs GitHub CodeQL and GitHub-Native Capabilities

## 1. Executive Summary

Naveen conducted a proof of concept comparing SonarQube with GitHub CodeQL and GitHub-native security and code-quality capabilities. The POC evaluated security detection, developer pull-request experience, operational overhead, integration, code-quality coverage, and reporting.

The GitHub implementation proved that CodeQL detects high-value Java security vulnerabilities and exposes the results in pull-request checks before merge. Five verified CodeQL findings were recorded: command injection, SQL injection, path traversal, unsafe deserialization, and cross-site scripting. GitHub materially reduces operating infrastructure because no SonarQube server or database is required.

The POC also exposed an important limitation: GitHub Code Quality could not be enabled in this repository because the applicable Advanced Security/Code Security prerequisite is not currently satisfied. SonarQube therefore remains the stronger option for proven code-smell breadth, technical-debt management, mature quality gates, and dashboards until PwC requirements and GitHub licensing are confirmed.

**Current recommendation: adopt a hybrid model first.** Use GitHub CodeQL and Secret Scanning for GitHub-native security workflows, while retaining SonarQube for established quality, technical-debt, governance, and reporting needs.

## 2. Scope

| SonarQube scope | GitHub scope |
| --- | --- |
| Code quality and code smells | CodeQL security analysis |
| Security analysis | GitHub Code Quality |
| Technical debt | Secret Scanning |
| Quality gates | Pull request integration and branch checks |
| Reporting and dashboards | Security dashboards and Actions results |

## 3. Functional Evaluation

| Capability | SonarQube | GitHub | POC evidence / qualification |
| --- | --- | --- | --- |
| SQL injection | ✅ | ✅ | CodeQL detected a high-severity Java SQL injection. |
| Command injection | ✅ | ✅ | CodeQL detected a critical Java command injection. |
| XSS | ✅ | ✅ | CodeQL detected a high-severity Java XSS finding. |
| Path traversal | ✅ | ✅ | CodeQL detected a high-severity path-injection finding. |
| Unsafe deserialization | ✅ | ✅ | CodeQL detected a critical Java unsafe-deserialization finding. |
| Secret detection | ✅ | ✅ | Secret Scanning is enabled; no alert has yet been raised for the committed synthetic fixtures. |
| Code smells | ✅ Strong | ⚠️ Unvalidated | GitHub Code Quality could not be enabled because Code Security/Advanced Security prerequisite is unmet. |
| Maintainability | ✅ Strong | ⚠️ Limited / plan-dependent | SonarQube provides mature maintainability analysis; GitHub evaluation is blocked. |
| Technical debt | ✅ | ❌ No equivalent debt model | GitHub does not offer the same debt metric model. |
| Quality gates | ✅ | Partial | GitHub supports Actions checks and branch protection, not equivalent SonarQube quality profiles/gates. |
| Security dashboard | ✅ | ✅ | GitHub Code Scanning alerts are available. |
| PR annotations and checks | ✅ | ✅ | CodeQL check is visible on the POC pull request. |

### GitHub strengths

1. Native repository, pull-request, branch-protection, and Actions integration.
2. Verified detection of five representative security vulnerability classes.
3. No separate server or database to operate.
4. Security findings link directly to exact source locations and remediation guidance.
5. Public repositories can use CodeQL code scanning without separate server infrastructure.

### GitHub limitations / risks

1. Code Quality availability is dependent on GitHub product, licensing, and policy configuration.
2. No SonarQube-equivalent technical-debt model was validated.
3. Secret Scanning detects supported secret patterns; generic hardcoded passwords are not necessarily findings.
4. The depth of dashboards, maintainability analysis, custom quality profiles, and governance needs validation.

### SonarQube strengths

1. Mature code-smell, maintainability, duplication, and technical-debt capabilities.
2. Established quality gates, profiles, reporting, and ecosystem support.
3. Broad governance controls for organizations already invested in SonarQube.

### SonarQube limitations / risks

1. Requires a server, database, upgrades, backups, monitoring, and operational ownership.
2. Plugin, scanner, authentication, and integration maintenance are additional work.
3. Requires PwC-specific license, usage, and governance confirmation before cost can be compared.

## 4. Operational Evaluation

| Area | SonarQube | GitHub |
| --- | --- | --- |
| Server management | Required | None |
| Database | Required | None |
| Upgrades | Required | GitHub managed; update pinned Action versions as needed |
| Plugin maintenance | Required | Minimal; manage workflow/query configuration |
| Infrastructure cost | Additional | No separate tool infrastructure |
| CI integration | Scanner, endpoint, token, and quality gate configuration | Native GitHub Actions workflow and PR checks |
| Administration | Projects, profiles, rules, tokens, permissions | Repository permissions, workflows, and security feature enablement |

## 5. POC Results

The POC verified the following CodeQL findings on the repository's `main` branch. Direct links are in the [evidence register](04-evidence-register.md).

| Finding | Severity | Location |
| --- | --- | --- |
| Uncontrolled command line | Critical | `VulnerableServlet.java:35` |
| Query built from user-controlled input | High | `VulnerableServlet.java:30` |
| Uncontrolled path expression | High | `AdvancedVulnerableServlet.java:34` |
| Unsafe deserialization | Critical | `AdvancedVulnerableServlet.java:47` |
| Cross-site scripting | High | `AdvancedVulnerableServlet.java:42` |

The POC pull request also demonstrated that CodeQL analysis appears as a pull-request check before merge.

## 6. Questions for SonarQube SMEs

See the complete [SME questionnaire](03-sonarqube-sme-questionnaire.md). Priority questions are:

### Licensing

1. What SonarQube edition and license model does PwC currently use?
2. What is the annual license and operating cost?

### Usage and governance

1. Which teams and repositories use SonarQube today?
2. Which quality profiles, rules, quality gates, and compliance controls are mandatory?
3. Which technical-debt, coverage, audit, or executive reports are required?

### Stakeholder input

Alex Popps and Jim Jones, from Chris Greg's team, should provide PwC policy and SonarQube-specific information. The Microsoft team should not be treated as the SonarQube authority; Dan is aware of this skill boundary.

## 7. Recommendation

### Option A - Keep SonarQube

Choose this when mandatory quality gates, technical-debt metrics, dashboards, custom rules, or compliance reporting are not covered by GitHub.

### Option B - Move to GitHub

Choose this when repositories are already on GitHub, verified CodeQL/Secret Scanning coverage meets requirements, GitHub Code Quality is available, and lower operational overhead outweighs SonarQube-specific features.

### Option C - Hybrid (recommended)

Use CodeQL and Secret Scanning for native GitHub security scanning and pre-merge feedback. Retain SonarQube for code quality, technical debt, governance, and reporting until a benchmark proves GitHub provides acceptable equivalent coverage.

## Decision gates and immediate actions

1. Capture the five verified CodeQL finding screenshots from the evidence register.
2. Ask Alex and Jim to complete the SonarQube SME questionnaire.
3. Confirm whether GitHub Advanced Security/Code Security can be licensed and enabled for the target private repositories.
4. Create a benchmark of 10-15 intentional issues and record which tool finds each issue, severity, false positives, and remediation value.
5. Run a 30-minute decision workshop with Naveen, Alex, Jim, Dan, and relevant PwC governance stakeholders.