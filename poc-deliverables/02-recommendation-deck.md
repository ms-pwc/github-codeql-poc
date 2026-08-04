# SonarQube vs GitHub: POC Recommendation Deck

## Slide 1 - Objective

**Can GitHub CodeQL and GitHub-native security/code-quality capabilities replace or partially replace SonarQube?**

- POC owner: Naveen
- Scope: functionality, developer experience, operations, integration, and findings
- Key decision: replace, retain, or adopt a hybrid model

## Slide 2 - What the POC tested

- Security: SQL injection, command injection, path traversal, unsafe deserialization, and XSS
- Developer experience: CodeQL results in a pull request before merge
- Secret Scanning: enablement and synthetic credential-pattern test
- Code Quality: code-smell sample and enablement validation
- Operations: infrastructure, upgrades, maintenance, and administration

## Slide 3 - Verified CodeQL results

| Finding | Severity | Result |
| --- | --- | --- |
| Command injection | Critical | Detected |
| Unsafe deserialization | Critical | Detected |
| SQL injection | High | Detected |
| Path traversal | High | Detected |
| XSS | High | Detected |

- CodeQL also appeared as a PR check before merge.
- Use the five URLs in the evidence register as screenshot sources.

## Slide 4 - Functional comparison

| Area | SonarQube | GitHub |
| --- | --- | --- |
| Security vulnerability detection | Strong | Strong, verified in POC |
| Pull-request feedback | Yes | Native, verified in POC |
| Code smells and maintainability | Strong | Not validated; Code Quality prerequisite blocked |
| Technical debt | Strong | No equivalent model validated |
| Secret detection | Yes | Enabled; synthetic fixtures have no alert yet |

## Slide 5 - Operational comparison

| SonarQube | GitHub |
| --- | --- |
| Server and database required | No separate server or database |
| Upgrades, backups, plugins, monitoring | GitHub-managed service; workflow maintenance only |
| Additional platform administration | Native repository, Actions, and PR integration |

## Slide 6 - Decision options

1. **Keep SonarQube**: preserve established quality, debt, dashboard, and governance capabilities.
2. **Move to GitHub**: use when requirements are fully covered and GitHub Code Quality is available.
3. **Hybrid (recommended)**: CodeQL/Secret Scanning for security; SonarQube for code quality and technical debt until exit criteria are met.

## Slide 7 - Recommendation and next steps

**Recommend hybrid adoption.**

1. Adopt CodeQL and Secret Scanning for GitHub-native pre-merge security feedback.
2. Keep SonarQube while Alex and Jim confirm mandatory rules, quality gates, reports, licensing, and compliance requirements.
3. Enable and benchmark GitHub Code Quality where licensing/policy permits.
4. Run a 10-15 issue comparison benchmark and make a retirement decision only after coverage and governance criteria are met.