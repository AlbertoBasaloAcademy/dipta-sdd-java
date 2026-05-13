# Accessibility Report 

## Perceivable

|File|Issue|Description|Recommendation|
|-|-|-|-|
|App.vue|Active View Indicator|The active navigation view is only indicated by a background color Change.|Add a visual indicator that doesn't rely solely on color (e.g., underline) and use `aria-current="page"` or `aria-pressed="true"`.|
|LaunchStatusBadge.vue|Color Contrast|Background colors for status badges (e.g., `#e3f2fd` for confirmed) may have low contrast against white text or surrounding elements.|Verify contrast ratios against WCAG AA standards (4.5:1) and adjust colors if necessary.|

## Operable

|File|Issue|Description|Recommendation|
|-|-|-|-|
|LaunchForm.vue|Missing Label Association|Labels and inputs are not programmatically linked (missing `for` and `id` attributes).|Add `id` to every input/select and corresponding `for` attribute to labels.|
|RocketForm.vue|Missing Label Association|Labels and inputs are not programmatically linked.|Add `id` to every input/select and corresponding `for` attribute to labels.|
|App.vue|Keyboard Navigation|Navigation buttons lack ARIA attributes to indicate state to screen readers.|Use `aria-pressed` to indicate the currently active view.|

## Understandable

|File|Issue|Description|Recommendation|
|-|-|-|-|
|LaunchForm.vue|Date Input|`datetime-local` format can vary between browsers and might be difficult for some users.|Provide a clear label or hint about the expected date format, or use a more accessible date picker component.|

## Robust

|File|Issue|Description|Recommendation|
|-|-|-|-|
|N/A|No major issues|The code uses standard HTML elements which are generally robust.|Continue using semantic HTML elements.|

> Report generated on 2026-05-13.
