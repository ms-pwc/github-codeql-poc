# Phase 7 - GitHub Code Quality

## Enablement

Location:

1. Security and quality
2. Code quality

Status in this POC:

Enablement is controlled in GitHub settings UI. If disabled, it must be turned on by a repository admin or org policy owner.

## Test setup

Added code smell examples in src/main/java/com/example/CodeQualitySmells.java:

1. `if (flag == true)`
2. Unused variable
3. Duplicate methods (`calculateOne`, `calculateTwo`)
4. Large method (`largeMethod`)

## Validation flow

1. Ensure Code quality is enabled in GitHub UI.
2. Push commit and open PR checks/findings.
3. Record findings reported by GitHub Code Quality.

## Recorded result

Pending verification after feature is enabled.