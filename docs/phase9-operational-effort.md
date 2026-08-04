# Phase 9 - Measure Operational Effort

## Purpose

Compare the ongoing operational effort required to run SonarQube versus GitHub-native security and quality capabilities.

| Operational area | SonarQube | GitHub CodeQL / Code Quality |
| --- | --- | --- |
| Server | Required; deploy, size, monitor, back up, and patch. | No server to operate. |
| Database | Required; operate, back up, upgrade, and recover. | No database to operate. |
| Upgrade effort | Plan version upgrades and validate compatibility. | GitHub manages the service; pin and update Actions versions in workflow. |
| Plugin maintenance | Assess, install, update, and support plugins. | Native integrations; maintain the CodeQL workflow and query configuration. |
| License cost | Depends on edition, user count, and commercial features. | CodeQL is available for public repositories; private-repository capability depends on GitHub plan and Advanced Security/Code Security licensing. |
| Administration | Manage users, projects, quality profiles, rules, tokens, and permissions. | Manage repository permissions, Actions policy, workflow configuration, and GitHub security feature enablement. |
| CI integration | Configure scanner, server URL, authentication, and quality-gate checks. | Native pull-request and Actions integration through `.github/workflows/codeql.yml`. |
| Availability and scaling | Team operates capacity and resilience. | Managed by GitHub Actions and GitHub services. |

## POC conclusion

GitHub materially reduces infrastructure operations. The trade-off is that quality-gate depth, technical-debt reporting, and code-quality feature availability can be less extensive or plan-dependent than SonarQube.

## Recommended measurement for decision makers

Track for one or two representative teams:

1. Hours per month spent on SonarQube infrastructure and maintenance.
2. Hours per month spent maintaining CodeQL workflows and resolving workflow failures.
3. Licensing cost per active developer/repository.
4. Finding volume, false-positive rate, and time-to-remediate by tool.