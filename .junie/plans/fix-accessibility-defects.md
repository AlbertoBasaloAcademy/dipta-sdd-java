---
sessionId: session-260513-120846-1m3m
isActive: true
---

# Requirements

### Overview & Goals
The goal is to fix accessibility defects identified in the `astro-bookings.accessibility.report.md` for the AstroBookings frontend. These changes will ensure compliance with WCAG AA standards, particularly in the areas of perceivability (contrast, visual indicators) and operability (form associations, keyboard navigation).

### Scope
- **In Scope**:
    - `App.vue`: Navigation state indicators (visual and ARIA).
    - `LaunchForm.vue`: Label-input associations and date format hints.
    - `RocketForm.vue`: Label-input associations.
    - `LaunchStatusBadge.vue`: Color contrast and visibility.
- **Out of Scope**:
    - Migration to a full accessibility-first UI library (keeping changes surgical).
    - Back-end changes.


# Technical Design

### Current Implementation
- Navigation in `App.vue` uses buttons that only change background color when active, with no ARIA state.
- Forms in `LaunchForm.vue` and `RocketForm.vue` have labels that are not programmatically linked to their inputs.
- `LaunchStatusBadge.vue` uses some light-on-light color combinations that fail WCAG AA contrast requirements.

### Proposed Changes

#### Navigation Indicators (`App.vue`)
- Update navigation buttons to include `aria-pressed` attribute.
- Update CSS to include an underline for the active button.

#### Form Accessibility (`LaunchForm.vue`, `RocketForm.vue`)
- Implement explicit label-input associations using `id` and `for`.
- Add instructions for the `datetime-local` input in `LaunchForm.vue` to assist users with varying browser implementations.

#### Status Badges (`LaunchStatusBadge.vue`)
- Adjust colors to ensure at least 4.5:1 contrast ratio.
- Add a border for better boundary visibility.

### File Structure
- `front/astro-bookings/src/App.vue`
- `front/astro-bookings/src/components/LaunchForm.vue`
- `front/astro-bookings/src/components/RocketForm.vue`
- `front/astro-bookings/src/components/LaunchStatusBadge.vue`


# Testing

### Validation Approach
- **Visual Check**: Verify that the active navigation item is underlined.
- **Label Focus**: Clicking any form label should focus the corresponding input field.
- Optional, do not it right now:
  - **Contrast Verification**: Use an accessibility tool (like Axe or Lighthouse) to confirm that badge colors meet the 4.5:1 ratio.
  - **Screen Reader Check**: Verify that navigation buttons announce "pressed" when active and labels are correctly read when inputs are focused.


# Delivery Steps

###   Step 1: Improve Navigation Accessibility in App.vue
Update the navigation buttons in `App.vue` to be more accessible.
- Add `:aria-pressed` attribute to navigation buttons to indicate the active state to screen readers.
- Add `text-decoration: underline` to the `.active` button CSS class to provide a visual indicator that does not rely solely on color.

###   Step 2: Enhance Form Accessibility in LaunchForm and RocketForm
Fix label-input associations and provide instructions for date inputs.
- Assign unique `id`s to all inputs and selects in `LaunchForm.vue` and `RocketForm.vue`.
- Add `for` attributes to all corresponding `<label>` tags.
- Add a helper text hint "Format: YYYY-MM-DD HH:mm" near the `launchTime` input in `LaunchForm.vue`.

###   Step 3: Fix Status Badge Contrast and Visibility
Improve the visibility and contrast of status badges.
- Darken the text color for the `.status-created` class in `LaunchStatusBadge.vue` to meet WCAG AA contrast standards.
- Add a border to the `.badge` class to improve visibility against the page background.