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

Recorded on 2026-08-04:

1. Secret scanning is enabled.
2. Secret scanning push protection is enabled.
3. Current alert list returned no results (`[]`) at check time.

Notes:

1. Generic passwords such as `Admin123` are usually not detected by secret scanning alone.
2. Provider-pattern tokens may require additional indexing time after push.