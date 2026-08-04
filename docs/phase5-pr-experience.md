# Phase 5 - Pull Request Experience

## Goal

Show that developers can see security findings directly in the pull request before merge.

## Steps executed

1. Created branch `feature/security-test`.
2. Added intentionally vulnerable code sample for PR analysis.
3. Created pull request to `main`.
4. Checked PR checks and CodeQL results.

## Evidence

1. Pull request: https://github.com/ms-pwc/github-codeql-poc/pull/1
2. Pull request checks: https://github.com/ms-pwc/github-codeql-poc/pull/1/checks
3. CodeQL workflow run for the PR: https://github.com/ms-pwc/github-codeql-poc/actions/runs/30886258331

## Outcome

Can developers see issues before merge?

Yes.

## Screenshots to capture

1. Pull request overview with failing/passing CodeQL check.
2. Pull request checks tab showing CodeQL job.
3. Pull request security/code scanning annotation view.