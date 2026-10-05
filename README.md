# Campaign Forms

Campaign and form builder for **Liferay Portal CE 7.4 (GA74)**. Create sign-up forms and landing pages, publish them on any site, receive submissions, notify people by email, and export the results to Excel, CSV or Power BI, without creating a Liferay Object per form.

- **Admin app** (React 18) under *Site Menu › Content & Data › Campaigns*: step-by-step editor, field presets, versioned forms, preview and publication checks.
- **Two modes**: *quick form* (a form with a title) and *custom page* (page builder with hero, text, image, call to action, FAQ and form blocks).
- **Public renderer**: server-side rendered, accessible HTML (labels, `fieldset`/`legend`, `aria-describedby`), dependency-free validation script, honeypot and form token anti-spam, idempotent submissions, UTM capture and analytics events.
- **Notifications**: emails through Liferay's MailService after each submission, with retries and a delivery log per submission.
- **Exports**: clean Excel spreadsheet, full CSV, JSON and a token-protected data feed for Power BI.
- **Optional privacy consent** per form, with an optional link to your privacy policy; the accepted version and date are stored with each submission.
- **Headless APIs**: a public delivery API and an admin API (REST Builder, OpenAPI), with a single error format.
- English and Brazilian Portuguese translations.

## Requirements

| Item | Version |
|---|---|
| Liferay Portal | CE 7.4 GA74 (`portal-7.4-ga74`) |
| JDK | 11 |
| Gradle | 8.5 (wrapper included) |
| Liferay Workspace plugin | 10.1.7 |
| Service Builder | 1.0.450 (pinned) |
| REST Builder | 1.0.293 (pinned, `compatibilityVersion: 2`) |
| Node.js / npm | 18+ (for the admin app in the `web` module) |

## Build

```bash
./gradlew build
```

The `web` module runs `npm ci` and `vite build` for the admin app before packaging. The JARs end up in each module's `build/libs`.

If the `JAVA_TOOL_OPTIONS` environment variable is set on your machine and the Gradle worker fails to start, unset it before running Gradle.

## Installation

1. Deploy the modules in this order (*Control Panel › Apps › App Manager › Upload*, or copy them to `deploy/`): `api`, `service`, `headless-common`, `headless-delivery-api`, `headless-delivery-impl`, `headless-admin-api`, `headless-admin-impl`, `web`. The `CF_*` tables are created on first deploy.
2. On each site, create a *Widget* page (for example `/campaign`) and add the **Campaign** widget. Published campaigns open at `/web/<site>/campaign/-/campaign/<slug>`.
3. Give the admin portlet *Access in Control Panel* permission to the roles that will manage campaigns. Inside the app, every action follows the campaign permissions (`VIEW`, `UPDATE`, `PUBLISH`, `DELETE`, `MANAGE_NOTIFICATIONS`, `MANAGE_INTEGRATIONS`, `VIEW_SUBMISSIONS`, `DELETE_SUBMISSIONS`, `EXPORT_SUBMISSIONS`).
4. For emails, configure the instance sender in *Instance Settings › Email*. A sender on `mail.send.blacklist` (such as the default `noreply@liferay.com`) is reported as a failed delivery, because Liferay would silently drop the message.

Upgrading an existing installation: when `upgrade.database.auto.run` is `false`, run `upgrade:execute io.github.valtairnascimento.campaignforms.service` in the Gogo shell after deploying a new version.

## Using it

### Quick form

Steps: Info → Fields → Notification → Confirmation → Publish.

- **Fields**: presets (name, email, phone, ...) or custom fields (text, textarea, email, phone, number, date, date and time, select, radio, checkbox, multi-select, yes/no, hidden) with options, validation, width and help text. Editing a published form creates a draft; visitors only see the change after it is published again.
- **Privacy consent** (optional): turn on *Ask for consent before submitting* to add a required checkbox. The privacy policy link and the consent text are optional (a default text is used). The consent version is derived from the text and the link unless you set one, so it changes whenever the terms change.
- **Notification**: emails to your team and/or to the respondent, with placeholders such as `${campaign.name}`, `${submission.table}` and `${submission.<fieldKey>}`. *Send test email* sends a sample to yourself.
- **Publish**: lists what still needs fixing, with links to the right step, and shows the public address.

### Custom page

Steps: Info → Template → Page → Fields → Notification → Confirmation → SEO → Preview → Publish.

- **Templates**: Regional event, Interest list or Blank.
- **Page**: blocks on the left and a live preview on the right. Blocks can be added, edited, duplicated, hidden, removed and reordered. A theme key (`default`, `light`, `dark`, `brand`, `accent`, `neutral`) becomes the CSS class `campaign-forms--theme-<key>`, so your site theme can style it.
- **Formatted text**: a small Markdown subset (`**bold**`, `*italic*`, `[link](https://...)`, lists and `##` headings). All text is escaped, and raw HTML is not allowed.
- **Images**: from the site's Documents and Media. They must be visible to guests, and SVG is not accepted.
- **SEO**: search title and description, social sharing text and image, and *noindex*. Only tags that Liferay does not already emit are added.
- **Preview**: `.../-/campaign/<slug>/preview?p_l_mode=preview`, with the draft page and form. Only users with UPDATE permission can open it, and submitting is disabled.

### Results and exports

*Results* lists submissions with their details (values, UTMs, consent and email delivery status). Users with *Export submissions* also get:

- **Download spreadsheet (Excel)**: an `.xlsx` with only the visible fields, the question labels as headers and option texts as values. Numbers and dates are typed, the header is frozen and filterable, and text is never interpreted as a formula. It is written in streaming by a small built-in Office Open XML writer, so no extra library is needed.
- **Full export (CSV)**: UTF-8 with BOM, `;`, `,` or tab separator, optional metadata (campaign, series, instance, site, form version, status, language, source, UTMs, consent). Cells starting with `= + - @` are prefixed to prevent formula injection.
- **Power BI**: one URL per dashboard (*Get data › Web*), covering the campaign or the whole series on the site. Details:
  - The token (256 bits) is shown only once; only its SHA-256 hash is stored. It can be sent in the `X-Feed-Token` header.
  - Tokens can be rotated, revoked or given an expiration date.
  - The feed returns CSV, or JSON paginated with `afterId`/`pageSize`.
  - Data is filtered by the permissions of the user who created the URL.

Exports and data feed changes are recorded in the audit log.

### Email delivery

Each submission creates one delivery per enabled notification. It is sent right after the submission is committed. Temporary failures are retried after 5, 10, 20 and 40 minutes (5 attempts). Permanent failures, such as a removed notification or no valid recipient, stop immediately. A scheduled job (every 5 minutes) resumes overdue deliveries. Templates only replace `${...}` placeholders: all text is HTML-escaped, and subject and sender name are kept on one line.

## APIs

The OpenAPI specs are in each `*-impl/rest-openapi.yaml` and are served at `.../openapi.json`.

### Public: `/o/campaign-forms/v1.0`

No login required. Access is granted by the `CAMPAIGN_FORMS_DELIVERY` Service Access Policy, which is created automatically on each instance and limited to this API.

| Method | Path |
|---|---|
| GET | `/sites/{siteId}/campaigns/by-slug/{slug}` (published campaign with form and page) |
| GET | `/sites/{siteId}/campaigns/by-slug/{slug}/form` |
| GET | `/sites/{siteId}/campaigns/by-slug/{slug}/page` |
| POST | `/sites/{siteId}/campaigns/by-slug/{slug}/submissions` (201; optional `Idempotency-Key` header) |
| GET | `/data-feeds/{feedKey}/submissions` (Power BI feed: `format`, `token` or `X-Feed-Token`, `modifiedSince`, `afterId`, `pageSize`) |

A submission body carries `values` (by `fieldKey`), the `formToken` and honeypot field from the form GET, and `consent: {accepted, version}` when the form asks for consent.

### Admin: `/o/campaign-forms-admin/v1.0`

Requires authentication (session + CSRF token, OAuth2 or Basic) and the campaign permissions.

| Resource | Operations |
|---|---|
| Campaigns | `GET/POST /sites/{siteId}/campaigns` · `GET /sites/{siteId}/campaigns/by-external-reference-code/{erc}` · `GET/PUT/DELETE /campaigns/{id}` |
| Actions | `POST /campaigns/{id}/publish` · `/archive` · `/reopen` · `/duplicate` · `/convert-to-custom-page` · `GET /campaigns/{id}/publication-issues` |
| Form | `GET/PUT /campaigns/{id}/form` · `POST /campaigns/{id}/form/draft` · `GET /campaigns/{id}/form-versions` · `GET /form-versions/{id}` |
| Fields | `POST /campaigns/{id}/form/fields` · `PUT /campaigns/{id}/form/fields/order` · `GET/PUT/DELETE /form-fields/{id}` · `PUT /form-fields/{id}/position` |
| Catalogs | `GET /field-types` · `GET /field-presets` · `GET /page-templates` · `GET /campaigns/{id}/block-types` |
| Page | `GET/PUT /campaigns/{id}/page` · `POST /campaigns/{id}/page/apply-template` · `POST /campaigns/{id}/page/blocks` · `PUT /campaigns/{id}/page/blocks/order` · `GET/PUT/DELETE /page-blocks/{id}` · `PUT /page-blocks/{id}/position` · `POST /page-blocks/{id}/duplicate` |
| Notifications | `GET/POST /campaigns/{id}/notifications` · `GET/PUT/DELETE /notifications/{id}` · `POST /notifications/{id}/test` |
| Integrations | `GET/POST /campaigns/{id}/integrations` · `GET/PUT/DELETE /integrations/{id}` |
| Submissions | `GET /campaigns/{id}/submissions` · `GET/DELETE /submissions/{id}` · `GET /submissions/{id}/event-deliveries` |
| Export | `GET /campaigns/{id}/submissions/export?format=csv\|json\|xlsx` (+ `labels`, `includeMetadata`, `separator` for CSV; send `Accept: */*`) |
| Data feeds | `GET/POST /campaigns/{id}/data-feeds` · `DELETE /data-feeds/{id}` · `POST /data-feeds/{id}/regenerate-token` |

### Errors

Both APIs use the same format, localized by `Accept-Language`:

```json
{ "code": "FORM_VALIDATION_ERROR", "message": "Some fields need to be fixed.",
  "errors": [ { "field": "email", "code": "INVALID_EMAIL", "message": "Enter a valid email address." } ] }
```

| HTTP | `code` |
|---|---|
| 400 | `INVALID_REQUEST`, `UNKNOWN_FIELD`, `INVALID_FORM_TOKEN`, `VALIDATION_ERROR` |
| 401 / 403 | `AUTHENTICATION_REQUIRED` / `FORBIDDEN` |
| 404 | `CAMPAIGN_NOT_FOUND`, `NOT_FOUND` |
| 409 | `DUPLICATE_SLUG`, `CAMPAIGN_CLOSED`, `LIMIT_REACHED`, `VERSION_LOCKED`, `INVALID_STATUS`, `PUBLICATION_BLOCKED`, `CAMPAIGN_HAS_SUBMISSIONS` |
| 422 | `FORM_VALIDATION_ERROR` |
| 500 | `INTERNAL_ERROR`, with an `errorId` that matches the server log |

## Extending

The `api` module exposes OSGi extension points:

| SPI | Purpose |
|---|---|
| `field.FieldType` / `field.FieldPreset` | New field types and presets (`campaignforms.field.type`, `campaignforms.field.preset`) |
| `submission.SubmissionStep` | Extra steps in the submission pipeline, ordered by `service.ranking` |
| `submission.CaptchaVerifier` | CAPTCHA provider (`campaignforms.captcha.provider`) |
| `page.BlockType` | New page blocks (`campaignforms.block.type`); the HTML lives in the `web` module (`BlockRenderer`) |

## Modules

| Module | Contents |
|---|---|
| `campaign-forms-api` | Service Builder API, exceptions, constants, input objects, SPIs and domain services |
| `campaign-forms-service` | Service Builder implementation, permissions, validation, upgrades, field types and presets, submission pipeline, notifications, exports and data feeds |
| `campaign-forms-headless-common` | Shared error format for both APIs (`ExceptionMapper`s) |
| `campaign-forms-headless-delivery-api` / `-impl` | Public API and its Service Access Policy |
| `campaign-forms-headless-admin-api` / `-impl` | Admin API |
| `campaign-forms-web` | Admin portlet and React app, public renderer portlet (FreeMarker), friendly URL, public form script and CSS |
| `campaign-forms-test` | Integration tests (Arquillian), including HTTP tests of both APIs |

## Development

```bash
./gradlew :modules:campaign-forms:campaign-forms-service:test        # unit tests
./gradlew :modules:campaign-forms:campaign-forms-web:test            # admin (Vitest) and public script (node --test)
./gradlew :modules:campaign-forms:campaign-forms-test:testIntegration # integration tests, needs a running portal
```

- **Local portal**: `./gradlew initBundle` downloads Liferay into `bundles/`. Then `deploy` any module. `configs/local` holds a developer `portal-ext.properties` (Gogo shell on `localhost:11311`, used by the integration tests).
- **Windows scripts**: `scripts/start-liferay.ps1` and `scripts/stop-liferay.ps1` start and stop that bundle.
- **End-to-end tests**: need a running portal and Edge/Chrome. Run `cd modules/campaign-forms/campaign-forms-web/src/main/frontend/admin && npm run e2e`. They create a test admin user, a `/campaign` page on the `guest` site and a non-blacklisted email sender.
- **Admin app**: `npm run dev` in the same folder rebuilds it on every change.

Service Builder is pinned to 1.0.450 and REST Builder to 1.0.293 because newer versions generate code for newer portals than GA74. Schema changes must bump `Liferay-Require-SchemaVersion` in `campaign-forms-service/bnd.bnd` and register an upgrade step in `CampaignFormsServiceUpgradeStepRegistrator`.

## License

[MIT](LICENSE)
