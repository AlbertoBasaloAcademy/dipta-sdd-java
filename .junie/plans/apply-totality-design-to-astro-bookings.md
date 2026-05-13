---
sessionId: session-260513-123403-1uza
isActive: true
---

# Requirements

### Overview & Goals
Apply the "Totality Festival Design System" (Cosmic Premium) to the `astro-bookings` frontend to transform its basic appearance into a polished, high-contrast, space-themed experience.

### Scope
- **In Scope**:
  - Implementation of design tokens (colors, typography, spacing).
  - Glassmorphism effects for panels and cards.
  - Redesign of all existing Vue components (`App`, `Forms`, `Catalogs`, `Badges`).
  - Integration of external fonts (Space Grotesk, Inter).
- **Out of Scope**:
  - Changes to the backend API.
  - Adding new functional features not present in the current UI.
  - Complex animations (keeping it to "Surgical Changes" as per guidelines).


# Technical Design

### Current Implementation
The current frontend is a standard Vue 3 application with minimal styling, using default fonts (Avenir/Helvetica) and standard HTML elements (tables, buttons) with basic CSS.

### Key Decisions
- **CSS Variables over Framework**: Since no CSS framework is currently used, we will implement a lightweight token system using CSS variables to remain consistent with the "Simplicity First" principle.
- **Backdrop Filter for Glassmorphism**: Use `backdrop-filter: blur(20px)` and semi-transparent backgrounds to achieve the Level 2 depth effect defined in the spec.
- **Card-based Catalog**: Transition from tables to a card grid in the catalogs to better showcase the glassmorphism and depth effects.

### Proposed Changes

#### Foundation
- **`index.html`**: Add Google Fonts links.
- **`design-system.css`**: Centralize tokens for:
  - Colors: Primary (Amber), Secondary (Cyan), Tertiary (Indigo), Neutral (Obsidian).
  - Typography: Space Grotesk (Headers/Labels), Inter (Body).
  - Glassmorphism: Level 1 (Base), Level 2 (Panels), Level 3 (Interactive).

#### Components
- **`App.vue`**: Set a radial gradient background moving from Obsidian to Deep Indigo.
- **`LaunchStatusBadge.vue`**: Apply `badge-celestial` styles (rounded-full, specific color pair).
- **`RocketForm.vue` / `LaunchForm.vue`**:
  - Container: Glassmorphism Level 2.
  - Inputs: Deep background, cyan focus.
  - Buttons: Primary (Amber glow) and Secondary (Cyan ghost).
- **`RocketCatalog.vue` / `LaunchCatalog.vue`**:
  - Replace table structure with glassmorphism cards.
  - Use Space Grotesk for names and labels.

### File Structure
- `front/astro-bookings/src/assets/design-system.css` (New)
- `front/astro-bookings/public/index.html` (Modified)
- `front/astro-bookings/src/main.js` (Modified)
- `front/astro-bookings/src/App.vue` (Modified)
- `front/astro-bookings/src/components/*.vue` (Modified)


# Testing

### Validation Approach
Verification that the implemented UI matches the "Totality Festival Design System" specification.

### Key Scenarios
- **Visual Check**: Background has a radial gradient; fonts are Space Grotesk and Inter.
- **Interaction Check**: Hovering on primary buttons triggers an amber glow; hovering on interactive cards shows the "Ambient Glow".
- **Glassmorphism Check**: Panels show backdrop blur and 1px inner stroke for refraction effect.
- **Responsiveness**: Ensure the 12-column logic transitions gracefully (as per spec).


# Delivery Steps

###   Step 1: Foundation: Tokens and Typography
Integrate the design system foundation into the project.

- Add Google Fonts (Space Grotesk and Inter) to `front/astro-bookings/public/index.html`.
- Create `front/astro-bookings/src/assets/design-system.css` containing CSS variables for colors, typography, and utility classes for glassmorphism.
- Import the CSS file in `front/astro-bookings/src/main.js`.


###   Step 2: Shell: Global Layout and Navigation
Update the main application shell with the cosmic theme.

- Apply the radial gradient background to `body` or `#app` in `front/astro-bookings/src/App.vue`.
- Style the navigation buttons with Space Grotesk and the specific interactive states (glow/underline).
- Set up the main layout container with appropriate margins and max-width.


###   Step 3: Forms: Cosmic Input and Actions
Redesign the registration and update forms.

- Apply Glassmorphism Level 2 to the containers in `RocketForm.vue` and `LaunchForm.vue`.
- Style input fields with deep dark backgrounds and cyan focus borders.
- Apply primary amber button styles for main actions and secondary cyan for cancel/secondary actions.


###   Step 4: Catalog: Glassmorphism Lists and Badges
Apply the design system to the lists and badges.

- Refactor `RocketCatalog.vue` and `LaunchCatalog.vue` to use glassmorphism card layouts instead of basic tables.
- Update `LaunchStatusBadge.vue` to use the `badge-celestial` design (tertiary-container background, rounded-full).
- Use Space Grotesk for headers and Inter for detail text.
