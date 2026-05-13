# Quality Report 

## Structural complexity

|File|Issue|Description|Recommendation|
|-|-|-|-|
|App.vue|Large Component|Manages state and logic for both Rockets and Launches views.|Extract into separate View components (e.g., `RocketsView.vue`, `LaunchesView.vue`) or use a router.|
|LaunchForm.vue|Complex Watcher|The watcher handles both formatting for edit and resetting for new entries.|Separate the logic into a dedicated initialization method or use a factory function for the form state.|

## Naming and readability

|File|Issue|Description|Recommendation|
|-|-|-|-|
|App.vue|Inconsistent Naming|`onEdit` for rockets vs `onLaunchEdit` for launches.|Use consistent naming patterns like `onEditRocket` and `onEditLaunch`.|
|Services|Inconsistent API URLs|`launchService.js` uses `/api` prefix while others do not.|Centralize API configuration in a shared config file and ensure consistency across all services.|
|Components|Missing Prop Types|Most components use array syntax for props: `props: ['rockets']`.|Use object syntax to define types, requirements, and default values for better documentation and debugging.|

## Redundancy

|File|Issue|Description|Recommendation|
|-|-|-|-|
|Services|Hardcoded Constants|`API_URL` is hardcoded in every service file.|Use environment variables and a centralized API client instance (e.g., using Axios or a fetch wrapper).|
|Forms|Shared Logic|Common form handling logic (submit/cancel) is repeated across forms.|Consider using a composable (if moving to Vue 3 Composition API) or a mixin to share common form behavior.|

> Report generated on 2026-05-13.
