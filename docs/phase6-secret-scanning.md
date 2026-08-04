# Phase 6 - Secret Scanning

## Test setup

Added intentionally secret-like values in:

1. src/main/java/com/example/SecretScanningFixture.java
2. src/main/java/com/example/SecretFixture.java

Patterns included:

1. Hardcoded password (`Admin123`)
2. GitHub token-like pattern (`ghp_...`)
3. AWS key-like patterns

## Validation flow

1. Commit and push changes.
2. Open Security and quality > Secret scanning.
3. Record whether alerts are raised and their state.

## Recorded result

Pending verification in repository UI/API after push.