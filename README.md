# Group2_Project — Conference Management System

A Java-based conference management system built for **TCSS360 (Spring 2016)** at the University of Washington Tacoma. The application supports the manuscript submission, assignment, and review workflow used by an academic conference, with distinct roles for authors, reviewers, subprogram chairs, and the program chair.

## Features

- **Role-based menus** — separate UIs for Author, Reviewer, SubProgram Chair, and Program Chair
- **Manuscript lifecycle** — submit, edit, withdraw, assign reviewers, submit reviews, and accept/reject decisions
- **Business rules** enforced by the model, including:
  - Manuscript submission deadlines per conference
  - Authors cannot review their own manuscripts
  - Limits on the number of manuscripts an author may submit and a reviewer may be assigned
- **Persistence** — the entire system state is serialized to `save.ser` between sessions

## Project Structure

```
Group2_Project/
├── src/
│   ├── model/        # Domain entities (Conference, Manuscript, Author, Reviewer, ...)
│   ├── view/         # Console UIs and the ManagementSystem entry point
│   ├── testModel/    # JUnit 4 tests for the model layer
│   └── testView/     # JUnit 4 tests for the view layer
├── UML/              # Class diagrams (PNG, GIF, PDF, .ucls)
├── 2015 IEEE International Cyber Security Conference/
├── 2016 IEEE NetSoft Conference/   # Reference papers used during design
├── group2.jar        # Pre-built runnable JAR
├── save.ser          # Serialized system state
├── .classpath
└── .project
```

### Key Classes

| Class | Responsibility |
|-------|---------------|
| `model.Conference` | Owns manuscripts, authors, reviewers, subprogram chairs, and the submission deadline |
| `model.Manuscript` | Submission with title, authors, file path, and reviews |
| `model.RegisteredUser` | Base user; specialized into `Author`, `Reviewer`, `SubProgramChair`, `ProgramChair` |
| `model.Review` | A single review attached to a manuscript |
| `view.ManagementSystem` | Top-level controller — login, conference selection, role dispatch |
| `view.SetUp` | Bootstraps state and handles serialization to/from `save.ser` |

## Requirements

- **JDK 8+** (developed against the Eclipse JDT Java builder)
- **JUnit 4** (referenced via the Eclipse `JUNIT_CONTAINER/4` classpath entry) — only required to run the tests

## Running

### From the pre-built JAR

```bash
java -jar group2.jar
```

### From source

```bash
javac -d bin src/model/*.java src/view/*.java
java -cp bin view.ManagementSystem
```

### In Eclipse

1. *File → Import → Existing Projects into Workspace*
2. Select the `Group2_Project` directory
3. Run `view.ManagementSystem` as a Java Application

## Running the Tests

Run `testModel.AllModelTests`, `testView.AllViewTests`, or `testModel.AllTests` from Eclipse's JUnit runner, or with `java -cp bin:junit-4.x.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore testModel.AllTests`.

## Authors

Group 2 — TCSS360, Spring 2016
- Enkhamgalan Baterdene
- Shaun Coleman
- Tyler Brent

See the `UML/` directory for the class diagram and design documentation.
