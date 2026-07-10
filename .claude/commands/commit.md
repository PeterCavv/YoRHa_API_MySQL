---
allowed-tools: Bash(git status:*), Bash(git diff:*), Bash(git log:*), Bash(git branch:*), Bash(git add:*), Bash(git commit:*)
argument-hint: [optional context or ticket reference]
description: Generate a Conventional Commits message and (if needed) a matching branch name from the staged changes
---

# Context

- Current branch: !`git branch --show-current`
- Git status: !`git status`
- Staged diff: !`git diff --staged`
- Unstaged diff (for reference only, do not commit if nothing is staged): !`git diff`
- Recent commit history (style reference): !`git log --oneline -10`

# Task

Analyze the staged changes above and create a commit following **Conventional Commits**.

## Commit message format

```
<type>(<optional scope>): <short summary in imperative mood, English, lowercase, no period>

<optional body explaining the "why", not the "what", only if the change isn't self-explanatory>
```

### Allowed types

- `feat`: new feature or capability for the user
- `fix`: bug fix
- `chore`: maintenance, tooling, dependencies, config — no production code change
- `refactor`: code change that neither fixes a bug nor adds a feature
- `docs`: documentation only
- `test`: adding or fixing tests, no production code change
- `style`: formatting, whitespace, missing semicolons — no logic change
- `perf`: performance improvement
- `ci`: CI/CD pipeline changes
- `build`: build system or external dependencies

### Rules

- Pick the type that matches the **dominant** change. If the diff mixes concerns (e.g. a fix plus unrelated formatting), flag it and suggest splitting into separate commits instead of picking one type and hiding the rest.
- Scope is optional; use it when the change is clearly limited to one module/context (e.g. `feat(order): ...`, `fix(customer): ...`).
- Summary in **English**, imperative mood ("add", not "added" or "adds"), no trailing period, ideally under 72 characters.
- Only add a body if the diff's intent isn't obvious from the summary alone (e.g. explain a non-trivial bug fix or a business rule). Do not restate the diff.
- Never invent scope, ticket numbers, or claims not supported by the diff.
- If nothing is staged, say so and stop — do not commit unstaged or untracked changes without being told to.
- If $ARGUMENTS is provided, use it as extra context (e.g. a ticket reference like `AUTH-123`) but still follow the format above.

## Branch naming

If the current branch is `main`, `master`, `develop`, or otherwise doesn't match the pattern below, suggest a branch name using the same prefixes as commit types, kebab-case, no scope prefix:

```
<type>/<short-kebab-case-description>
```

Examples: `feature/order-combined-lots`, `fix/bid-increment-validation`, `chore/update-larastan`.

Note: use `feature/` (not `feat/`) for branch names to match this team's existing convention, even though commit messages use `feat:`.

## Steps

1. Review the staged diff and summarize what changed in your own words (1-2 lines), before writing the commit message — this is your sanity check that the type/scope you're about to pick actually matches the diff.
2. Propose the commit message following the format above.
3. If nothing is staged yet, run `git add` only for files you can clearly justify from `git status` and ask for confirmation first if it's ambiguous which files belong to this change.
4. If the branch name doesn't follow the `<type>/<description>` convention, suggest one but don't rename the branch automatically — ask first.
5. Run `git commit` with the approved message.
6. Show the resulting commit (`git log -1`) to confirm.