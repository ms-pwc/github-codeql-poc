# Code Quality and Security Scanning POC Deliverables

This folder contains the management-facing output for the SonarQube versus GitHub-native tooling POC.

| Document | Audience | Purpose |
| --- | --- | --- |
| [Final client decision report](FINAL-SonarQube-vs-GitHub-CodeQL-POC-Decision-Report.docx) | Client sponsors and tool-selection decision makers | Canonical 18-page Word report with measured benchmark results, weighted recommendation, and six authenticated screenshots. |
| [Final report source](FINAL-Client-POC-Decision-Report.md) | POC maintainers | Version-controlled source for the canonical Word report. |
| [Decision assumptions](final-decision-assumptions.md) | Governance and procurement stakeholders | Explicit policy, licensing, and evidence assumptions used to close the decision model without inventing client contract facts. |
| [Executive POC report](01-executive-poc-report.md) | Sponsors and decision makers | Full functional, operational, and recommendation analysis. |
| [Recommendation deck](02-recommendation-deck.md) | Leadership presentation | Seven-slide Markdown deck ready to move into PowerPoint. |
| [SonarQube SME questionnaire](03-sonarqube-sme-questionnaire.md) | Alex Popps, Jim Jones, and SonarQube stakeholders | Collect policy, licensing, governance, and usage inputs. |
| [Evidence register](04-evidence-register.md) | Engineering and audit stakeholders | Verified CodeQL results, PR checks, and screenshot links. |

## POC owner and inputs

Naveen leads the POC for code-quality and security-scanning tools across SonarQube and CodeQL. Alex Popps and Jim Jones, from Chris Greg's team, are the requested contacts for PwC policy, SonarQube licensing, governance, and mandatory-rule input. Dan is aware that the Microsoft team does not provide SonarQube subject-matter expertise.

## Current recommendation

Adopt a hybrid model initially: use GitHub CodeQL and Secret Scanning for GitHub-native security feedback, while retaining SonarQube until SonarQube rule, technical-debt, reporting, and quality-gate requirements have been confirmed and covered.