# Tigger User Guide

Welcome to Tigger — a small, friendly chatbot that helps you keep a simple, bouncy checklist.

This guide explains how to run the application, how to use the GUI and text commands, and how data and images are handled. Read the "Commands" section for concrete, copy‑paste examples and expected responses.

---

## Quick start — running the app

From the project root (the repository root that contains `gradlew.bat`) you can run the application directly with the Gradle wrapper.

PowerShell (Windows):

    .\gradlew.bat run

To build a runnable JAR and run it:

    .\gradlew.bat shadowJar
    java -jar build\libs\Tigger.jar

Run the unit tests:

    .\gradlew.bat test

If you use an IDE (IntelliJ, Eclipse, etc.) open the project and run the `tigger.Main` class (or use the `Launcher` class as the entry point if your IDE needs a wrapper).

---

## GUI overview

When the GUI opens you will see:

- A title bar reading `Tigger chatbot` (this is set by the app).
- An optional icon in the title bar loaded from the bundled resource `/images/tigger.jpeg` (if present on your system/OS the icon will show; otherwise the app continues with no icon).
- A dialog area that shows the conversation history.
- A text input box at the bottom where you type commands.
- A send button next to the input box.

If the app cannot find the storage file on startup, Tigger will include a short message in the initial welcome text: `Failed to load from file, make sure your file exists!`.

If an avatar image is missing or fails to load, the dialog will not show an avatar (the image view is hidden) — the app does not crash.

---

## Commands (syntax, examples, expected output)

All commands are typed into the text input (the GUI) or used in the text-based UI. Commands are case-insensitive. Below are the important commands and examples of how the chat responds.

Notes about response formatting: the GUI wraps Tigger's responses with decorative lines. The examples below show the core text Tigger will display.

1) List tasks

- Syntax: `list`
- Example input: `list`
- Expected behaviour: Tigger lists all tasks with numeric indices and their done status.

Example snippet of expected output:

    Tigger's bouncy list o' tasks:
    1. [T][ ] read book
    2. [D][X] submit report (by: Feb 20 2026)

2) Add a todo

- Syntax: `todo <description>`
- Example: `todo buy milk`
- Expected behaviour: A new ToDo is added to the end of the list and Tigger confirms.

Expected output:

    Tigerrific! Tigger's added a bouncy task:
    [T][ ] buy milk
    Now you have 3 bouncy tasks in the list — bounce on!

3) Add a deadline

- Syntax: `deadline <description> /by <due>`
- Example: `deadline submit report /by 2025-01-01`
- Expected behaviour: A Deadline task is created. The `/by` separator and keyword are required.

Expected output:

    Tigerrific! Tigger's added a deadline task — tick-tock:
    [D][ ] submit report (by: Jan 1 2026)
    Now you have 4 bouncy tasks in the list — bounce along!

4) Add an event

- Syntax: `event <description> /from <start> /to <end>`
- Example: `event project meeting /from 2025-02-10 14:00 /to 2025-02-10 15:00`
- Expected behaviour: Creates an Event with the given from/to values. Both `/from` and `/to` parts are required and must be separated by `/`.

Expected output:

    Tigerrific! Tigger's added an event — how exciting!:
    [E][ ] project meeting (from: 2025-02-10 14:00 to: 2025-02-10 15:00)
    Now you have 5 bouncy tasks in the list — whoopee!

5) Mark a task as done / unmark

- Syntax: `mark <index>` or `unmark <index>` (index is the integer from `list`)
- Example: `mark 2`
- Expected behaviour: Toggles the done state. `mark` sets as done; `unmark` clears the done state.

Expected output for `mark 2`:

    Tigerrific! I bounced this one as done — hip hooray!
        [D][X] submit report (by: 2025-01-01)

Expected output for `unmark 2`:

    Oh, tiddly — it's unbounced now (not done):
        [D][ ] submit report (by: 2025-01-01)

6) Delete a task

- Syntax: `delete <index>`
- Example: `delete 3`
- Expected behaviour: Removes the task at the given index and shows the removed task.

Expected output:

    Oh my! Tigger removed this bouncy task:
    [T][ ] buy milk
    Now you have 4 bouncy tasks in the list — all set!

7) Find tasks by keyword

- Syntax: `find <keyword>`
- Example: `find report`
- Expected behaviour: Shows tasks whose descriptions contain the keyword.

Expected output (if matches found):

    Here are the matching bouncy things — ta-da!:
    1. [D][X] submit report (by: 2025-01-01)

If no matches are found, Tigger will say: `No matches, oh bother! Tigger couldn't find any.`

8) Trivia

- Syntax: `trivia`
- Example: `trivia`
- Expected behaviour: Tigger asks a trivia question. The next user message is interpreted as an answer. If the answer matches the expected answer (case-insensitive), Tigger replies positively; otherwise Tigger reveals the correct answer.

Example flow:

    User: trivia
    Tigger: Ooh! Trivia time — bounce and guess, tee-hee!: 
    When was 'The Tigger Movie released?
    User: 2000
    Tigger: Tigerrific! That's right — Tigger's proud of you!

9) Exit the app

- Syntax: `bye`
- Expected behaviour: Tigger says goodbye and the GUI exits.

Expected output:

    Bye. Hope to see you again soon!

---

## Storage file format and location

By default, the application loads a plain text file at `src/main/java/Tigger/tigger.txt` (this default location is created in the example `tigger.Main` class). The storage format is simple: each saved task is one line with fields separated by `|`.

- ToDo example line: `T | 0 | buy milk`
- Deadline example line: `D | 1 | submit report | 2025-01-01`
- Event example line: `E | 0 | project meeting | 2025-02-10 14:00 | 2025-02-10 15:00`

Field meanings:
- First column: Task type (`T` for ToDo, `D` for Deadline, `E` for Event)
- Second column: Done flag (`1` = done, `0` = not done)
- Remaining columns: description, and date/time fields for deadline/event as required

If the storage file cannot be found at startup, the app will include the message `Failed to load from file, make sure your file exists!` in the welcome text. To fix it either create the file at the expected path or change the storage path used by `tigger.Main` when constructing the `Tigger` instance.

---

## Images and avatars

Avatars used in the GUI are taken from the packaged resources under `src/main/resources/images/` (for example `ferb.jpg` and `tigger.jpeg`).

- If an avatar image file is missing or fails to load, the app will quietly hide the avatar and continue (no error is thrown).
- The window icon (title bar icon) is loaded from `/images/tigger.jpeg` if present.

If you want to customize images, replace the files in `src/main/resources/images/` and rebuild the JAR.

---

## Credits

This README was generated by GitHub Copilot and edited by Chan Jan Hoe.

---