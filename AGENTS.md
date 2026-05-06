# Agents Instructions

### Behavior Guidelines

- When using templates, replace {placeholders} with actual values.
- English: `English`
- Chat responses should be in the user's language.
- Code and documentation should be in English.
- Be concise and clear in communication. Sacrifice grammar for concision.
- When in doubt, ask questions one by one with closed answers.

### Environment

- **.junie**: Folder for agent-related files such as skills, prompts, and specs.
- **product**: Folder for product-related files such as specs, plans, and documentation.
- **back, front**: Array of source code folders relevant to the project.
- **OS dev**: `Windows`
- **Terminal**: `PowerShell`
- **Git remote**: `https://github.com/AlbertoBasaloAcademy/dipta-sdd-java`
- **Default branch**: `main`

### Folder structure
````text
.                         # Project root  
├── AGENTS.md             # This file with the main guidelines for agents
├── .junie/               # Files related to agents (skills, commands, etc)
│   ├── memory/           # Agent session memory
│   ├── plans/            # Execution plans
│   └── prompts/          # Reusable prompts directory
├── product/              # Files related to the product (specs, plans, etc)
├── CHANGELOG.md          # Project history and updates
├── README.md             # Human-friendly project summary
├── back/                 # Backend source code folder
├── front/                # Frontend source code folder
└── docs/                 # Documentation and ecosystem files
````

### Naming Conventions

— Use conventional commit messaging
— Code branches prefixes must be: `feat/` | `fix/` | `chore/`
— Generate short slugs for artifacts and names (less-than-20-chars).

## Product

AstroBookings is a REST API and a web application for a fictional space tourism company.

- Manage rocket fleet
- Plan, confirm or cancel launches
- Record passenger bookings without overbooking
- Integrate with fictional payment gateway

## Technology

### back Stack

- **Tier**: Backend
- **Language**: Java 21
- **Framework**: Spring Boot 3.5.0
- **Testing**: JUnit
- **Storage**: None (MVP)
- **Security**: None (MVP)
- **Logging**: Standard Spring Boot

- **Development workflow**:
  - Build: `./mvnw clean install`
  - Run: `./mvnw spring-boot:run`
  - Test: `./mvnw test`

- **Folder structure**:
````text
back/                             # Tier source root
├── pom.xml                       # Main tech file
└── src                           # Source code folder
````

### front Stack

- **Tier**: Frontend
- **Language**: JavaScript (Vue 3)
- **Framework**: Vue 3
- **Testing**: Vitest
- **Storage**: None (MVP)
- **Security**: None (MVP)
- **Logging**: Console

- **Development workflow**:
  - Init: `npm install`
  - Build: `npm run build`
  - Run: `npm run serve`
  - Test: `npm run test`
  - Lint: `npm run lint`

- **Folder structure**:
````text
front/astro-bookings/             # Tier source root
├── package.json                  # Main tech file
└── src                           # Source code folder
````

- **Agent Skills**:
  - initialize: Initialize the environment by loading all necessary commands, rules, and skills.
  - codify: Implement a feature, including writing unit tests for critical modules.

## Principles

1. Think Before Coding
Don't assume. Don't hide confusion. Surface tradeoffs.
2. Simplicity First
Minimum code that solves the problem. Nothing speculative.
3. Surgical Changes
Touch only what you must. Clean up only your own mess.
4. Goal-Driven Execution
Define success criteria. Loop until verified.

> last updated: May 2026
