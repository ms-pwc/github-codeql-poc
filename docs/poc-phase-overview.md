# POC Phase Overview

| Phase | Purpose | Evidence produced | Management value |
| --- | --- | --- | --- |
| 4 - Vulnerability test cases | Test whether GitHub detects representative security issues. | Vulnerable Java samples, expected-detection matrix, remediation guidance. | Establishes security coverage. |
| 5 - Pull request experience | Test whether developers see findings before merge. | Open PR, CodeQL checks, PR screenshots and links. | Demonstrates shift-left feedback. |
| 6 - Secret scanning | Test detection of committed credential patterns. | Secret-like fixtures and Secret Scanning enablement/results record. | Measures leaked-secret protection. |
| 7 - Code quality | Test code-smell detection and feature availability. | Code-smell fixture and Code Quality enablement result. | Identifies maintainability coverage and licensing gaps. |
| 8 - SonarQube comparison | Compare functional coverage and trade-offs. | Feature comparison matrix. | Supports tool-selection decisions. |
| 9 - Operational effort | Compare platform operating cost and administration. | Operational-effort matrix. | Quantifies management and platform impact. |

## Key result so far

CodeQL successfully detected SQL injection and command injection, and CodeQL checks are visible in pull requests before merge. Secret Scanning is enabled but has not reported an alert for the committed test fixtures. Code Quality cannot be enabled in this repository until the applicable Advanced Security/Code Security requirement is met.