# Phase 8 - SonarQube vs GitHub Comparison

| Feature | SonarQube | GitHub |
| --- | --- | --- |
| SQL Injection | ✅ | ✅ |
| Command Injection | ✅ | ✅ |
| XSS | ✅ | ✅ |
| Secret Detection | ✅ | ✅ |
| Code Smells | ✅ Strong | ✅ Limited (depends on Code Quality enablement and rule coverage) |
| Technical Debt | ✅ Strong | ❌ No equivalent debt model |
| Quality Gates | ✅ | Partial (branch protection + checks) |
| PR Decoration | ✅ | ✅ |
| Dashboarding | ✅ Better | Basic |
| Infrastructure | Server needed | None |

## POC notes

1. CodeQL provides strong security query coverage and PR-time visibility.
2. Secret scanning is pattern-based and excellent for leaked credentials.
3. SonarQube remains stronger for deep maintainability metrics and debt tracking.
4. GitHub can partially replace SonarQube for security-focused workflows, but not full code-quality governance in all teams.