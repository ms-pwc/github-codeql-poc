# Final Decision Assumptions

These assumptions close the POC decision model without presenting unverified PwC contract or policy details as facts. They are the baseline used for the final recommendation.

## Evidence boundary

1. GitHub CodeQL, GitHub Actions, Code Scanning, pull-request checks, and Secret Scanning were exercised directly in this repository.
2. SonarQube was not connected to this repository because no SonarQube instance, token, or PwC configuration was supplied. Its assessment is based on published product capabilities and the operating model described by the client.
3. Contracted PwC prices are confidential/not supplied. The cost comparison uses relative total-cost-of-ownership bands, not invented currency values.

## Adopted governance baseline

1. Critical and high-confidence new security findings must block merge until fixed or formally risk-accepted.
2. New code should meet at least 80% test coverage when coverage data is available.
3. New-code duplication should remain at or below 3%.
4. Security hotspots require documented review before release.
5. Pull requests must display analysis status and actionable source-level findings.
6. Teams require maintainability, reliability, security, coverage, and duplication trends.
7. Management requires a monthly portfolio summary; audit stakeholders require exportable evidence and exception history.

## Licensing and deployment assumptions

1. Target production repositories are private organization repositories on GitHub.
2. GitHub Code Security/Secret Protection licensing is required for equivalent private-repository functionality.
3. SonarQube private-project and enterprise-governance capabilities require a paid plan; exact PwC pricing is excluded from this technical POC.
4. If SonarQube Server is selected, PwC owns server/database availability, backup, patching, upgrades, monitoring, and plugin compatibility.
5. If SonarQube Cloud is selected, infrastructure operations reduce, but licensing, configuration, quality profiles, and governance administration remain.

## Decision interpretation

The recommendation is valid under the assumptions above. If PwC later confirms that technical-debt reporting, quality gates, coverage, duplication, or portfolio dashboards are not required, the case for GitHub-only becomes stronger. If those controls are mandatory, a hybrid or SonarQube-led quality model remains necessary.