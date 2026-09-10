# Modernization Plan: Security Remediation

**Project**: gestion-oficinas-java

## Technical Framework

- **Language**: Java 25
- **Framework**: Spring Boot 3.2.4 / Spring Security 6
- **Build Tool**: Maven
- **Database**: H2 for the application profile; PostgreSQL 15 in Docker Compose
- **Key Dependencies**: Spring Web, Thymeleaf, Spring Security, Spring Data JPA,
  PostgreSQL JDBC driver, Microsoft Graph, Azure Identity

## Assessment Scope

This plan is derived from assessment report `report-20260910093107` and is scoped
exactly to these selected categories:

- Scan and resolve CWE-259 vulnerabilities for this project.
- Scan and resolve CWE-778 vulnerabilities for this project.
- Scan and resolve CWE-798 vulnerabilities for this project.
- Scan and fix CVE vulnerabilities for this project.

The selected findings remain evidenced in the current workspace. The report
identifies hard-coded or placeholder credentials in `application.yml`,
`import.sql`, and `docker-compose.yml`; missing security-event logging in
`SecurityConfig.java` with an empty `log4j2.xml`; and PostgreSQL JDBC
`42.6.2` affected by CVE-2026-42198.

## Overview

The modernization will remove embedded authentication material, add auditable
logging for security-critical events, and remediate the reported dependency
vulnerability. The application behavior and existing functional scope should be
preserved while configuration secrets become externally supplied and security
signals become observable.

The work is phased as sequential remediation tasks so changes to shared
configuration, security behavior, and dependency metadata do not conflict.

## Migration Impact Summary

| Area | Current State | Target State | Authentication | Comments |
|------|---------------|--------------|----------------|----------|
| Credentials | Embedded values | External configuration | Existing auth preserved | CWE-259 and CWE-798 |
| Security events | Not consistently logged | Security events recorded | Existing form login | CWE-778 |
| PostgreSQL driver | 42.6.2 | Patched release | Existing DB auth preserved | CVE-2026-42198 |

## Execution Phases

1. Remove hard-coded passwords and equivalent embedded credential material.
2. Implement security-critical event logging with appropriate detail and hygiene.
3. Remove remaining hard-coded credential artifacts and verify configuration paths.
4. Scan and remediate reported and newly identified dependency CVEs, then build
   and run the existing unit tests.

The plan is for remediation planning only. No task execution is included.

## Open Questions & Questionnaire

No clarification questions were raised. No integration testing, infrastructure,
containerization, deployment, or runtime upgrade scope was requested.

## Blockers and Assumptions

- The assessment report recommends PostgreSQL JDBC `42.7.11` or later for
  CVE-2026-42198; the exact patched version will be selected during execution
  based on dependency compatibility.
- Secret storage provider and deployment environment were not specified. The
  remediation must use an approved external secret/configuration mechanism and
  must not commit replacement secrets into the repository.
- Seed credentials in `import.sql` require an explicit development/test policy;
  production credentials must not be represented by repository-seeded values.
