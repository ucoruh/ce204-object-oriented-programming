---
template: main.html
---

# :material-book-open-variant: CEN206 — Term Project Guideline & Evaluation Manual

<div class="grid cards" markdown>

-   :material-language-java: **Java**

    ---

    Maven + Eclipse IDE · JDK 11/17 · Swing GUI

    [:octicons-repo-16: eclipse-java-maven-template](https://github.com/ucoruh/eclipse-java-maven-template)

-   :material-language-cpp: **C/C++**

    ---

    CMake + CTest · GCC/MSVC · Qt / GTK+

    [:octicons-repo-16: cpp-cmake-ctest-template](https://github.com/ucoruh/cpp-cmake-ctest-template)

-   :material-language-csharp: **C# (.NET Core)**

    ---

    dotnet CLI · xUnit/NUnit · WinForms / WPF

    [:octicons-repo-16: vs-net-core-template](https://github.com/ucoruh/vs-net-core-template)

</div>

!!! abstract "Overview"

    You will develop the **same application in three languages** — Java, C/C++, and C# — across **two phases**: a console application (Midterm) and a desktop GUI extension (Final). Each team maintains **three private repositories** (one per language) forked from the provided templates. Submission dates and instructor information are provided in the term-specific document on Microsoft Teams.

---

## :material-information-outline: 1. Project Description

### :material-transit-connection-variant: Two-Phase Development

=== ":material-console: Midterm — Console Application"

    | Requirement | Details |
    | --- | --- |
    | **Console UI** | Keyboard-navigable menus (arrow keys / ++tab++) — not number-entry only |
    | **Modular Architecture** | Separate library (`lib`) and application (`app`) layers |
    | **Storage Layer** | ALL THREE backends required via `IRepository<T>` + `RepositoryFactory`: Binary File I/O, SQLite, MySQL (Docker Compose) — switchable at runtime |
    | **OOP Principles** | Encapsulation, inheritance, polymorphism, abstraction (LO.1–LO.3) |
    | **Unit Tests** | 100% coverage |
    | **Documentation** | Doxygen — 100% coverage — PDF only |
    | **Design Documents** | PlantUML (Class + Sequence ≥3 + Use-Case + Activity + State + ER + Gantt), C4 Model, UMPLE, Figma (console screens), draw.io (Deployment), ProjectLibre |

=== ":material-monitor: Final — GUI Extension"

    | Requirement | Details |
    | --- | --- |
    | **Desktop GUI** | Extends console app — do NOT rewrite business logic |
    | **MVC Pattern** | Strict separation: View ↔ Controller ↔ Model (lib) |
    | **All Screens** | Every Figma-designed console screen → GUI implementation |
    | **Event Handling** | Input validation + error dialogs |
    | **Design Patterns** | Min. 3 (Creational + Structural + Behavioral) |
    | **Code Smell & Refactoring** | Min. 3 smells identified, refactored, before/after documented |
    | **CI/CD** | GitHub Actions pipeline + CI badge in README |
    | **Cross-Platform** | Windows + WSL/Linux |
    | **Updated Design Docs** | All Midterm documents updated for GUI layer |

### :material-translate: Language-Specific Implementation

=== ":material-language-java: Java"

    | Aspect | Details |
    | --- | --- |
    | **Repository** | `cen206-hw-name-surname-java` |
    | **Build** | Maven — `mvn clean verify` |
    | **Testing** | JUnit 5 + JaCoCo |
    | **GUI (Final)** | Swing (JFrame, JPanel, JTable, JDialog, JMenuBar) |
    | **Storage** | JDBC (SQLite, MySQL) + `ObjectOutputStream` (Binary) |
    | **CI/CD** | GitHub Actions + Maven |

=== ":material-language-cpp: C/C++"

    | Aspect | Details |
    | --- | --- |
    | **Repository** | `cen206-hw-name-surname-cpp` |
    | **Build** | CMake — `cmake --build . && ctest` |
    | **Testing** | Google Test (gtest) + gcov/lcov |
    | **GUI (Final)** | Qt / GTK+ / ncurses |
    | **Storage** | SQLite (C API) + Binary File I/O |
    | **CI/CD** | GitHub Actions + CMake |

=== ":material-language-csharp: C# (.NET Core)"

    | Aspect | Details |
    | --- | --- |
    | **Repository** | `cen206-hw-name-surname-csharp` |
    | **Build** | `dotnet build` + `dotnet test` |
    | **Testing** | xUnit / NUnit + coverlet |
    | **GUI (Final)** | WinForms / WPF |
    | **Storage** | Entity Framework / ADO.NET + Binary I/O |
    | **CI/CD** | GitHub Actions + dotnet CLI |

!!! info "Project Constraints (MÜDEK Ölçüt 5.5 — PÇ.3)"

    Your application must address at least **ONE** realistic constraint: budget limits, time pressure, user accessibility, data security, regulatory compliance, or privacy. Document the chosen constraint and reference an engineering standard where applicable (e.g. IEEE 730, ISO/IEC 25010).

---

## :material-chart-bar: 2. Assessment Structure

The project grade is composed of the following components. MÜDEK v3.0 Ölçüt 3.2 requires Skill-category outcomes to be evidenced by practical project outputs, not solely by written exams.

| Component | Description | Weight | LOs Measured |
| --- | --- | --- | --- |
| **A1** | Midterm Project — Console App + All Design Documents | **60%** | LO.1, LO.2, LO.3 |
| **A2** | Quiz — Theory + Application | **40%** | LO.1–LO.5 |
| | :material-sigma: **MID-SEMESTER 40% · END-OF-SEMESTER 60%** | | |
| **F1** | Final Project — GUI Extension + Updated Design Documents | **70%** | LO.1–LO.7 |
| **F2** | Final Quiz — Comprehensive | **30%** | LO.1–LO.7 |

??? note "MÜDEK v3.0 Evidence Notes"

    - Skill outcomes (LO.2–LO.7): project output is the primary evidence — quiz grade alone is insufficient.
    - A1 and F1 project submissions serve as the primary Skill evidence.
    - Design documents (PlantUML, UMPLE, Figma, draw.io, ProjectLibre) are required at Midterm and must be updated at Final.
    - The project also develops teamwork (PÇ.6), written/oral communication (PÇ.8), and project management (PÇ.12) skills.

---

## :material-school-outline: 3. Learning Outcomes (LO — MÜDEK v3.0)

| LO | Learning Outcome | Bloom | Category | A1 | A2 | F1 | F2 |
| --- | --- | --- | --- | --- | --- | --- | --- |
| **LO.1** | Explains OOP fundamentals (encapsulation, inheritance, polymorphism, abstraction) | Understand | Knowledge | :material-check: | :material-check: | :material-check: | :material-check: |
| **LO.2** | Designs and implements classes, objects, methods (constructors, access modifiers, static) | Apply | Skill | :material-check: | :material-check: | :material-check: | :material-check: |
| **LO.3** | Applies inheritance and polymorphism (abstract classes, interfaces, overriding) | Apply | Skill | :material-check: | :material-check: | :material-check: | :material-check: |
| **LO.4** | Uses exception-handling mechanisms (try-catch) to manage runtime errors | Apply | Skill | — | :material-check: | :material-check: | :material-check: |
| **LO.5** | Creates type-independent, reusable components using Generics | Analyze | Skill | — | :material-check: | :material-check: | :material-check: |
| **LO.6** | Implements pluggable storage (Binary + SQLite + MySQL) via Repository + Strategy pattern | Apply | Skill | — | — | :material-check: | :material-check: |
| **LO.7** | Develops maintainable software using SOLID, design patterns (min. 3), refactoring | Synthesize | Skill | — | — | :material-check: | :material-check: |

---

## :material-wrench: 4. Required Tools

All tools below are **mandatory from Midterm** — do not defer design tools to Final.

| Tool | Purpose | Output |
| --- | --- | --- |
| :material-file-tree: **PlantUML** | ALL UML diagrams + C4 + Gantt + ER | `.puml` source + PNG |
| :material-layers-outline: **C4 Model** | Architecture: Context → Container → Component | C4-PlantUML `.puml` + PNG |
| :material-state-machine: **UMPLE** | Domain class model / state machines | `.ump` file |
| :material-palette-outline: **Figma** | Screen wireframes (Console + GUI) | PNG / PDF |
| :material-drawing: **draw.io** | Deployment diagram | `.drawio` + PNG |
| :material-chart-gantt: **ProjectLibre** | Gantt chart / WBS | `.xml` file |
| :material-test-tube: **Maven / CMake / dotnet** | Build + test + coverage | JaCoCo / gcov / coverlet |
| :material-file-document-outline: **Doxygen** | Source documentation | PDF only |
| :material-robot: **GitHub Actions** | CI/CD pipeline | `.yml` workflow |

!!! tip "Design-First Workflow"

    1. :material-file-tree: PlantUML class diagram **before** writing any class code
    2. :material-layers-outline: C4 Model (Context → Container → Component) at Midterm
    3. :material-palette-outline: Figma wireframes: **two sets** — Console screens (Midterm) + GUI screens (Final)
    4. :material-drawing: draw.io Deployment view: modules, runtime, file system, external deps
    5. :material-state-machine: UMPLE model: domain structure; update when adding Final classes
    6. :material-chart-gantt: ProjectLibre Gantt: both phases from day one; update actuals

---

## :material-clipboard-list-outline: 5. Project Selection

Project ideas are listed in the **Appendix** section below.

!!! info "How to Select"

    1. Browse the **Appendix – Application List** at the bottom of this page
    2. Choose one application idea
    3. Record your selection in the shared team spreadsheet on **Microsoft Teams**

- :material-source-repository-multiple: **Three Repositories Per Team:** Same project in Java, C/C++, and C# using the provided templates
- :material-account-group: **Team Size:** Up to 4 members (individual work also permitted)
- :material-lock: **No Changes:** Team composition cannot be altered after work begins
- :material-account-off: **No Overlap:** Senior and junior students may not form teams together
- :material-content-copy: **No Reuse:** Projects from previous terms or other courses may not be reused
- :material-check-decagram: **Approval:** Submit project selection and Gantt plan to instructor before coding
- :material-shield-alert: **Constraint:** State at least one realistic constraint (see Section 1)

---

## :material-console-line: 6. Midterm Phase — Console Application

### 6.1 Application Requirements

- :material-puzzle: **Modular Architecture:** Separate `lib` and `app` layers
- :material-keyboard: **Console UI:** Keyboard-navigable menus (++arrow-up++ ++arrow-down++ ++tab++) — not number-entry only
- :material-database: **Storage — IRepository Pattern (all 3 required):**
    - `IRepository<T>` interface: `save`, `findById`, `findAll`, `update`, `delete`
    - `BinaryRepository<T>` — Serializable + binary file I/O
    - `SqliteRepository<T>` — SQLite via JDBC / C API / ADO.NET
    - `MySqlRepository<T>` — MySQL via Docker Compose
    - `RepositoryFactory` returns active backend; user switches at runtime
- :material-relation-many-to-many: **ER Diagram:** Mandatory — PlantUML `@startuml entity` notation with PK/FK/cardinality
- :material-shape: **OOP 4 Principles:** Encapsulation, inheritance, polymorphism, abstraction (LO.1–LO.3)
- :material-gauge: **Coverage:** 100% unit test coverage + 100% Doxygen documentation coverage

### 6.2 Design Documents — Required at Midterm

!!! warning "Missing design docs at the first review = criteria not met"

    - [x] PlantUML Class Diagram — all domain classes and relationships
    - [x] PlantUML Sequence Diagrams — at least 3 key use cases
    - [x] PlantUML Use-Case Diagram — all actors and system use cases
    - [x] PlantUML ER Diagram — entities, attributes, PK/FK, cardinality
    - [x] C4 Model — Context + Container + Component (C4-PlantUML library)
    - [x] UMPLE Model — domain class model (`.ump` file)
    - [x] Figma Wireframes — ALL console screens (menus, input, output, error)
    - [x] draw.io Deployment Diagram — modules, runtime, deps
    - [x] ProjectLibre Gantt — both phases; tasks assigned to members
    - [x] `IRepository<T>` + `RepositoryFactory` documented in report

??? example "6.3 Midterm Deliverables Table"

    | # | Deliverable | Format | Rule |
    | --- | --- | --- | --- |
    | 1 | PlantUML Class Diagram — `design/plantuml/class.puml` | `.puml` + `.png` | All domain classes, relationships |
    | 2 | PlantUML Sequence Diagrams (min. 3) — `design/plantuml/seq-*.puml` | `.puml` + `.png` | One per use case |
    | 3 | PlantUML Use-Case Diagram — `design/plantuml/usecase.puml` | `.puml` + `.png` | All actors and use cases |
    | 4 | C4 Model — Context + Container + Component | `.puml` + `.png` | C4-PlantUML library |
    | 5 | UMPLE Domain Model — `design/umple/model.ump` | `.ump` | Entity hierarchy |
    | 6 | Figma Wireframes — Console screens | PNG / PDF | Every screen state |
    | 7 | draw.io Deployment Diagram — `design/drawio/deployment.drawio` | `.drawio` + PNG | Modules, JVM, deps |
    | 8 | PlantUML Gantt Chart — `design/plantuml/gantt.puml` | `.puml` + PNG | Both phases |
    | 9 | ProjectLibre Project Plan — `design/projectlibre/project.xml` | `.xml` | WBS assigned |
    | 10 | Unit Test + Coverage Report (100%) | HTML + tar.gz | JaCoCo / gcov / coverlet |
    | 11 | Doxygen Documentation | PDF only | 100% coverage |
    | 12 | Midterm Report — `report/cen206-hw-name-surname.docx` | `.docx` | GitHub URL on cover |
    | 13 | GitHub Issues & Project Board (Kanban) | GitHub Projects | Labeled, assigned |
    | 14 | GitHub Release (tag: `midterm-v1.0.0`) | Release + tar.gz | All build artefacts |

---

## :material-monitor-dashboard: 7. Final Phase — GUI Extension

### 7.1 Application Requirements

=== ":material-language-java: Java"

    Swing GUI — JFrame, JPanel, JTable, JList, JDialog, JMenuBar with proper layout managers

=== ":material-language-cpp: C/C++"

    Qt / GTK+ / ncurses-based GUI

=== ":material-language-csharp: C#"

    WinForms / WPF desktop application

- :material-layers-triple: **Same Project, New Layer:** Extend console app — do NOT rewrite business logic
- :material-view-split-vertical: **MVC Pattern:** Strict separation of View ↔ Controller ↔ Model
- :material-gesture-tap: **Event Handling:** All user actions handled; input validation + error dialogs
- :material-shape-plus: **Design Patterns:** Min. 3 from different categories (Observer recommended for GUI events)
- :material-magnify: **Code Smell & Refactoring:** ≥ 3 smells identified, refactored, before/after in report
- :material-robot: **CI/CD:** GitHub Actions pipeline; CI badge in README
- :material-desktop-classic: **Cross-Platform:** Must run on Windows and WSL/Linux

??? example "7.2 Final Deliverables Table"

    | # | Deliverable | Format | Rule |
    | --- | --- | --- | --- |
    | 1 | Updated PlantUML Class Diagram (GUI classes) | `.puml` + `.png` | Add view classes |
    | 2 | Updated PlantUML Sequence Diagrams (GUI flows) | `.puml` + `.png` | Min. 2 new |
    | 3 | Updated C4 Model (GUI layer) | `.puml` + `.png` | Show MVC split |
    | 4 | Updated UMPLE Model (GUI transitions) | `.ump` | State transitions |
    | 5 | PlantUML ER Diagram (if schema changed) | `.puml` + `.png` | PK/FK, cardinality |
    | 6 | Figma Wireframes — GUI screens (ALL) | PNG / PDF | Plan-vs-actual annotated |
    | 7 | Updated draw.io Deployment Diagram | `.drawio` + PNG | GUI layer visible |
    | 8 | Updated PlantUML Gantt (actual dates) | `.puml` + PNG | Actual vs planned |
    | 9 | Updated ProjectLibre Plan (actual dates) | `.xml` | Dates recorded |
    | 10 | Design Patterns Report (min. 3) | Report section | Intent + code location |
    | 11 | Code Smell & Refactoring Log (min. 3) | Report section | Before/after snippets |
    | 12 | Unit Test + Coverage (≥80%) | HTML + tar.gz | CI must pass |
    | 13 | GitHub Actions CI/CD — `.github/workflows/ci.yml` | `.yml` + badge | Auto build+test |
    | 14 | Updated Doxygen Documentation | PDF only | 100% coverage |
    | 15 | Final Report — `report/cen206-hw-name-surname.docx` | `.docx` | All sections |
    | 16 | Presentation Deck (max 10 slides) | `.pptx` / `.pdf` | Architecture evolution |
    | 17 | Video Presentation (max 4 min/member) | In Teams ZIP | Do NOT commit to GitHub |
    | 18 | CHANGELOG.md | Markdown | v2.0.0 + v1.0.0 entries |
    | 19 | Ethics & Social Impact Package | Report + LICENSE | IEEE/ACM + AI Declaration |
    | 20 | GitHub Release (tag: `final-v2.0.0`) | Release + tar.gz | All build artefacts |

---

## :material-package-variant-closed: 8. Submission Rules

### 8.1 What Goes Where

!!! info "Submission Rule"

    - :material-github: **GitHub Repository (private):** source code + design docs + report + presentation + coverage. **No videos.**
    - :material-tag: **GitHub Release (`midterm-v1.0.0` / `final-v2.0.0`):** Build artefacts (tar.gz) attached to the release.
    - :material-microsoft-teams: **Microsoft Teams:** ONE ZIP file → release tar.gz + video/ + report/ + presentation/.
    - :material-file-document: The project report **MUST** show GitHub repository URLs on its cover page.

### 8.2 GitHub Repository Structure

Each team has **three repositories** — one per language:

=== ":material-language-java: Java (Maven)"

    ```
    cen206-hw-name-surname-java/
    ├── src/                        # app / lib / test modules
    ├── pom.xml                     # Maven build file
    ├── .github/workflows/          # CI/CD pipeline (.yml)
    ├── README.md                   # CI badge
    ├── .gitignore                  # target/, .class, .jar, IDE
    ├── report/                     # .docx (GitHub URL on cover)
    ├── presentation/               # .pptx or .pdf
    ├── video/                      # ⚠ DO NOT commit — ZIP only
    ├── docs/                       # Doxygen PDF only
    ├── design/plantuml/            # .puml + PNG
    ├── design/c4/                  # C4 .puml + PNG
    ├── design/umple/               # .ump files
    ├── design/figma/console/       # Console wireframes
    ├── design/figma/gui/           # GUI wireframes
    ├── design/drawio/              # .drawio + PNG
    ├── design/projectlibre/        # project.xml
    ├── docker-compose.yml          # MySQL backend
    └── test-coverage/              # JaCoCo HTML report
    ```

=== ":material-language-cpp: C/C++ (CMake)"

    ```
    cen206-hw-name-surname-cpp/
    ├── src/                        # app / lib / test modules
    ├── CMakeLists.txt              # CMake build configuration
    ├── .github/workflows/          # CI/CD pipeline (.yml)
    ├── README.md                   # CI badge
    ├── .gitignore                  # build/, .o, .exe, IDE caches
    ├── report/                     # .docx (GitHub URL on cover)
    ├── presentation/               # .pptx or .pdf
    ├── video/                      # ⚠ DO NOT commit — ZIP only
    ├── docs/                       # Doxygen PDF only
    ├── design/plantuml/            # .puml + PNG
    ├── design/c4/                  # C4 .puml + PNG
    ├── design/umple/               # .ump files
    ├── design/figma/console/       # Console wireframes
    ├── design/figma/gui/           # GUI wireframes
    ├── design/drawio/              # .drawio + PNG
    ├── design/projectlibre/        # project.xml
    ├── docker-compose.yml          # MySQL backend
    └── test-coverage/              # gcov/lcov coverage report
    ```

=== ":material-language-csharp: C# (.NET Core)"

    ```
    cen206-hw-name-surname-csharp/
    ├── src/                        # app / lib / test projects
    ├── *.sln                       # .NET Core Solution file
    ├── .github/workflows/          # CI/CD pipeline (.yml)
    ├── README.md                   # CI badge
    ├── .gitignore                  # bin/, obj/, IDE caches
    ├── report/                     # .docx (GitHub URL on cover)
    ├── presentation/               # .pptx or .pdf
    ├── video/                      # ⚠ DO NOT commit — ZIP only
    ├── docs/                       # Doxygen PDF only
    ├── design/plantuml/            # .puml + PNG
    ├── design/c4/                  # C4 .puml + PNG
    ├── design/umple/               # .ump files
    ├── design/figma/console/       # Console wireframes
    ├── design/figma/gui/           # GUI wireframes
    ├── design/drawio/              # .drawio + PNG
    ├── design/projectlibre/        # project.xml
    ├── docker-compose.yml          # MySQL backend
    └── test-coverage/              # coverlet coverage report
    ```

??? example "8.3 GitHub Release Artefacts"

    Create a GitHub Release (tag `midterm-v1.0.0` or `final-v2.0.0`). Attach all build artefacts for **both platforms**:

    | Release File | Contents |
    | --- | --- |
    | **:material-microsoft-windows: Windows** | |
    | `windows-release-binaries.tar.gz` | Compiled release binaries |
    | `windows-debug-binaries.tar.gz` | Debug build binaries |
    | `windows-publish-binaries.tar.gz` | Published (self-contained) binaries |
    | `windows-doxygen-lib-documentation.tar.gz` | Doxygen — library module |
    | `windows-doxygen-test-documentation.tar.gz` | Doxygen — test module |
    | `windows-lib-doc-coverage-report.tar.gz` | Doc coverage — library |
    | `windows-test-coverage-report.tar.gz` | Unit test coverage |
    | `windows-test-doc-coverage-report.tar.gz` | Combined coverage |
    | `windows-test-results-report.tar.gz` | Test results (XML + HTML) |
    | **:material-linux: Linux (WSL)** | |
    | `linux-release-binaries.tar.gz` | Compiled release binaries |
    | `linux-debug-binaries.tar.gz` | Debug build binaries |
    | `linux-publish-binaries.tar.gz` | Published (self-contained) binaries |
    | `linux-doxygen-lib-documentation.tar.gz` | Doxygen — library module |
    | `linux-doxygen-test-documentation.tar.gz` | Doxygen — test module |
    | `linux-lib-doc-coverage-report.tar.gz` | Doc coverage — library |
    | `linux-test-coverage-report.tar.gz` | Unit test coverage |
    | `linux-test-doc-coverage-report.tar.gz` | Combined coverage |
    | `linux-test-results-report.tar.gz` | Test results (XML + HTML) |

### 8.4 Microsoft Teams Submission — Single ZIP

Clone your GitHub repository, add video files into the `video/` folder, then ZIP the entire project folder. The repository already contains everything (source, design, report, presentation, release artefacts) — you only need to add the videos.

```
cen206-hw-name-surname.zip
└── cen206-hw-name-surname-java/      # GitHub repo clone (gitignore-filtered)
    ├── src/                           # already in repo
    ├── design/                        # already in repo
    ├── report/                        # already in repo
    ├── presentation/                  # already in repo
    ├── docs/                          # already in repo
    ├── release/                       # GitHub Release tar.gz files downloaded here
    ├── video/                         # ⚠ ADD VIDEOS HERE (not committed to GitHub)
    │   ├── video-name1-surname1.mp4   # one per member, max 4 min
    │   ├── video-name2-surname2.mp4
    │   └── ...
    └── ...                            # all other repo files
```

!!! warning "ZIP Submission Steps"

    1. `git clone` your repository (or download as ZIP from GitHub)
    2. Download all tar.gz files from your GitHub Release into `release/` folder
    3. Place each team member's video (`.mp4`, max 4 min) into the `video/` folder
    4. ZIP the entire project folder → `cen206-hw-name-surname.zip`
    5. Upload to Microsoft Teams assignment

    Repeat for all three repositories (`-java`, `-cpp`, `-csharp`) or combine them in one ZIP.

!!! success "GitHub Repository URLs in Report (Mandatory)"

    The first page of your project report **MUST** include all three GitHub repository URLs:

    - `https://github.com/your-username/cen206-hw-name-surname-java`
    - `https://github.com/your-username/cen206-hw-name-surname-cpp`
    - `https://github.com/your-username/cen206-hw-name-surname-csharp`

!!! danger "Submission Will NOT Be Accepted If"

    - [x] GitHub Release is missing or has no tar.gz artefacts
    - [x] GitHub repository URLs not in report
    - [x] Templates not used
    - [x] Repositories not named correctly (`-java`, `-cpp`, `-csharp`)
    - [x] PlantUML `.puml` source files missing (PNG alone insufficient)
    - [x] C4 Model diagrams missing at Midterm
    - [x] UMPLE `.ump` not committed
    - [x] Figma console wireframes missing at Midterm
    - [x] draw.io `.drawio` source not committed
    - [x] ER diagram (`.puml`) missing
    - [x] Runtime storage switching not implemented (all 3 backends)
    - [x] Doxygen HTML folder submitted instead of PDF
    - [x] Compiled binaries committed to repository
    - [x] Video files committed to GitHub
    - [x] No collaborative commits from all members
    - [x] Coverage below 100%
    - [x] Plagiarism detected

### 8.5 GitHub Rules

=== ":octicons-repo-forked-16: Repository Setup"

    Fork each template and name your repositories:

    | Language | Repository Name | Template |
    | --- | --- | --- |
    | :material-language-java: Java | `cen206-hw-name-surname-java` | [eclipse-java-maven-template](https://github.com/ucoruh/eclipse-java-maven-template) |
    | :material-language-cpp: C/C++ | `cen206-hw-name-surname-cpp` | [cpp-cmake-ctest-template](https://github.com/ucoruh/cpp-cmake-ctest-template) |
    | :material-language-csharp: C# | `cen206-hw-name-surname-csharp` | [vs-net-core-template](https://github.com/ucoruh/vs-net-core-template) |

=== ":material-cog: Configuration"

    - :material-lock: **Private:** All three repos must be private
    - :material-account-plus: **Collaborators:** Instructor + all team members
    - :material-account-circle: **GitHub Profile:** Correct name/surname in Git config; public profile
    - :material-script: **Pre-Commit:** Run `1-configure-pre-commit.bat` before first commit
    - :material-tag: **Releases:** `midterm-v1.0.0` and `final-v2.0.0` mandatory for all repos
    - :material-file-hidden: **No Binaries:** Never commit `.jar`, `.class`, `.exe`, `.o`, `.dll` — configure `.gitignore`
    - :material-source-commit: **Commit Frequency:** Each member commits at least weekly
    - :material-source-branch: **Branching:** Feature branches → Pull Requests to main; never commit directly

---

## :material-git: 9. Team Workflow & Engineering Practices

### 9.1 Git Branching Strategy — GitHub Flow

| Branch Type | Naming Convention | Rule |
| --- | --- | --- |
| :material-shield-lock: `main` | `main` | Protected — merge via PR only |
| :material-source-branch: `feature` | `feature/login-screen` | One branch per task; delete after merge |
| :material-bug: `bugfix` | `bugfix/null-pointer-fix` | Branch from main; merge via PR |
| :material-file-document-edit: `docs` | `docs/update-readme` | Documentation-only changes |
| :material-fire: `hotfix` | `hotfix/critical-data-loss` | Urgent — branch, fix, PR, merge same day |

!!! danger "Branch Protection Rules (configure on GitHub)"

    - :material-check-circle: Require PR reviews before merging (min. 1 approval)
    - :material-traffic-light: Require status checks to pass (CI must be green)
    - :material-cancel: No force-push, no direct commits to main
    - :material-cog: Repository → Settings → Branches → Add branch protection rule → `main`

### 9.2 Commit Message Convention — Conventional Commits

```
<type>(<scope>): <description>
```

| Type | Example |
| --- | --- |
| `feat` | `feat(storage): implement IRepository<T> + BinaryRepository` |
| `fix` | `fix(menu): prevent infinite loop when ESC pressed` |
| `test` | `test(student): add unit tests for GPA calculation` |
| `docs` | `docs(c4): add container diagram for console layer` |
| `refactor` | `refactor(repo): extract StudentRepository to reduce God Class` |
| `style` | `style: apply Google Java Format across all source files` |
| `ci` | `ci: add JaCoCo coverage report to GitHub Actions` |
| `chore` | `chore: update Maven dependencies to latest stable versions` |

!!! info "Reference: [conventionalcommits.org](https://www.conventionalcommits.org)"

### 9.3 Pull Request (PR) Process

1. **Create PR** from feature branch to `main`
2. **Title:** Conventional Commits format — `feat(scope): description`
3. **Description:** What changed, how to test, screenshots, related Issue #
4. **Assign reviewer** — min. 1 team member
5. **CI must pass** before merge
6. **Merge strategy:** Squash and Merge or Merge Commit
7. **Delete branch** after merge

!!! tip "PR Checklist — copy into every PR description"

    ```markdown
    - [ ] Code compiles and all tests pass locally
    - [ ] New unit tests added for new functionality
    - [ ] Coverage did not decrease
    - [ ] Doxygen comments added for all new public methods
    - [ ] Design documents updated if architecture changed
    - [ ] No binary files committed
    - [ ] Commit messages follow Conventional Commits
    - [ ] Linked to GitHub Issue: closes #___
    ```

### 9.4 Code Review Guidelines

| :material-magnify: Review Area | What to look for |
| --- | --- |
| Correctness | Does code match issue/PR description? Edge cases? |
| OOP & Design | SOLID applied? God Class? Feature Envy? |
| Tests | Happy path AND error cases covered? |
| Naming | Language conventions? Self-explanatory? |
| Documentation | Doxygen `@param`, `@return`, `@throws`? |
| Security | No hardcoded credentials or secrets? |
| Performance | N+1 loops? Unnecessary I/O? Memory leaks? |

### 9.5 GitHub Issues & Project Board

- :material-plus-circle: **Create Issues for everything:** Features, bugs, docs, design, tests
- :material-label: **Labels:** `feature`, `bug`, `documentation`, `test`, `design`, `devops`, `question`
- :material-account: **Assign** every Issue to a member
- :material-flag: **Milestones:** Midterm and Final (dates announced via Microsoft Teams)
- :material-link-variant: **Close via commit:** Use `closes #12` in PR description

| Kanban Column | Meaning |
| --- | --- |
| :material-inbox: Backlog | Created but not started |
| :material-progress-wrench: In Progress | Branch created, active dev |
| :material-eye: In Review | PR opened, awaiting approval |
| :material-check-all: Done | PR merged, Issue closed |

### 9.6 Code Quality Gates

=== ":material-language-java: Java"

    | Tool | Command | Checks |
    | --- | --- | --- |
    | Checkstyle | `mvn checkstyle:check` | Naming, indentation, imports |
    | SpotBugs | `mvn spotbugs:check` | Null pointers, resource leaks |
    | JaCoCo | `mvn test jacoco:report` | Coverage — Mid: 100%, Final: ≥80% |
    | Doxygen | `mvn doxygen:report` | Doc coverage — 100% |

=== ":material-language-cpp: C/C++"

    | Tool | Command | Checks |
    | --- | --- | --- |
    | cppcheck | `cppcheck --enable=all src/` | Static analysis |
    | gcov/lcov | `cmake --build . && ctest && lcov ...` | Coverage — Mid: 100%, Final: ≥80% |
    | Doxygen | `doxygen Doxyfile` | Doc coverage — 100% |

=== ":material-language-csharp: C#"

    | Tool | Command | Checks |
    | --- | --- | --- |
    | dotnet format | `dotnet format --verify-no-changes` | Code style |
    | coverlet | `dotnet test --collect:"XPlat Code Coverage"` | Coverage — Mid: 100%, Final: ≥80% |
    | Doxygen | `doxygen Doxyfile` | Doc coverage — 100% |

### 9.7 Semantic Versioning & CHANGELOG

- **SemVer:** `MAJOR.MINOR.PATCH` → `1.0.0`, `1.1.0`, `1.1.1`
- **Midterm:** Tag `midterm-v1.0.0`
- **Final:** Tag `final-v2.0.0` (major bump = new UI layer)
- **CHANGELOG.md:** Sections: `[Unreleased]`, `[2.0.0]`, `[1.0.0]`. Each: Added / Changed / Fixed / Removed

### 9.8 Definition of Done

A task (GitHub Issue) is **Done** when ALL are true:

- [ ] Code compiles and runs on Windows + WSL/Linux
- [ ] Unit tests for all new public methods; coverage not decreased
- [ ] Static analysis passes with zero violations
- [ ] Doxygen comments on all new public classes/methods
- [ ] PR opened, reviewed, approved by ≥ 1 team member
- [ ] GitHub Actions CI is green
- [ ] Design documents updated if architecture changed
- [ ] CHANGELOG.md updated under `[Unreleased]`
- [ ] Issue closed via PR
- [ ] Project board card moved to Done

### 9.9 Sprint Planning & Retrospective

- **Sprint 1 (Midterm):** Working console app + all design documents
- **Sprint 2 (Final):** GUI + CI/CD + design patterns + refactoring
- **Weekly sync:** 15 min max at the start of each week
- **Retrospective (in Final Report):** 1 page — What went well? What would you change? What did you learn?

---

## :material-scale-balance: 10. Professional & Ethical Responsibilities

!!! abstract "MÜDEK Mapping"

    | Program Outcome | Project Requirement |
    | --- | --- |
    | **PÇ.9** — Toplumsal Bilinç | Social Impact Analysis; KVKK/GDPR; accessibility; environmental footprint |
    | **PÇ.11** — Etik | IEEE/ACM Ethics; open-source license; AI tool use; authorship integrity |

### 10.1 IEEE & ACM Code of Ethics

- **IEEE clause 1:** Hold paramount safety, health, welfare of the public
- **IEEE clause 3:** Be honest and realistic in claims about your system
- **IEEE clause 7:** Seek and offer honest technical criticism (= code review)
- **ACM 1.2:** Avoid harm — document misuse scenarios
- **ACM 1.6:** Respect privacy — document data storage and protection

### 10.2 Social Impact Analysis

Include in your report (half page minimum):

| :material-magnify: Dimension | Questions to Answer |
| --- | --- |
| :material-thumb-up: Positive Impact | Who benefits and how? |
| :material-alert: Potential Harm | Misuse scenarios? Data loss impact? |
| :material-shield-lock: Privacy & Data | What data stored? Where? KVKK/GDPR? |
| :material-wheelchair-accessibility: Accessibility | Impairment accommodations in GUI? |
| :material-leaf: Environmental Footprint | Resource consumption? Design choices? |
| :material-gavel: Legal Awareness | Regulatory requirements for your domain? |

### 10.3 Open-Source Licensing

Add a `LICENSE` file to all three repository roots before Midterm.

| License | Type | When to Use |
| --- | --- | --- |
| **MIT** | Permissive | :material-star: Recommended default |
| **Apache 2.0** | Permissive + Patent | Good for libraries |
| **GPL v3** | Copyleft | Derivatives must also be GPL |

### 10.4 Responsible AI Tool Use Policy

!!! tip "AI Tool Use Declaration Template"

    | Tool | Used for | Verified by |
    | --- | --- | --- |
    | GitHub Copilot | Method stubs, Javadoc | Manual review + unit tests |
    | ChatGPT-4o | JaCoCo configuration | Running `mvn test jacoco:report` |
    | None | Business logic, OOP architecture | — |

- :material-file-sign: **Declaration required** in Final Report
- :material-account-check: **You own the code** — AI-generated code without understanding = integrity violation
- :material-check: **Acceptable:** Boilerplate, syntax help, test suggestions, doc drafting
- :material-close: **Unacceptable:** Entire feature implementations without understanding

### 10.5 Authorship & Attribution

- :material-source-commit: **Individual contribution:** Git commit history is primary evidence
- :material-link: **Third-party code:** Comment: `// Source: [URL] — [License]`
- :material-cancel: **No ghost commits:** Commits on behalf of others = plagiarism
- :material-table: **Peer contribution table** required in Final Report

### 10.6 Engineering Standards Awareness

| Standard | Application |
| --- | --- |
| ISO/IEC 25010 | Reference ≥ 3 quality characteristics |
| IEEE 829 | Test documentation: purpose, scope, criteria |
| KVKK / GDPR | Purpose limitation, data minimisation, consent |
| WCAG 2.1 | Keyboard navigation, contrast, labels |
| Google Java Style | Enforced by Checkstyle in CI |

### 10.7 Ethics Self-Assessment Checklist

Complete in your Final Report:

- [ ] LICENSE file in all three repos
- [ ] Social Impact Analysis (≥ 3 dimensions)
- [ ] IEEE clause 1: harm scenario documented
- [ ] IEEE clause 3: no exaggeration in report
- [ ] ACM 1.6: privacy/data protection documented
- [ ] Social Impact table — all 6 dimensions
- [ ] KVKK/GDPR compliance documented
- [ ] Accessibility: ≥ 2 WCAG 2.1 features in GUI
- [ ] AI Tool Use Declaration
- [ ] Dependency license table
- [ ] Contributor table
- [ ] ISO/IEC 25010: ≥ 3 quality characteristics referenced

---

## :material-code-braces: 11. OOP Requirements

### 11.0 Storage Architecture — IRepository Pattern

```java
public interface IRepository<T> {
    void save(T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
}
```

- :material-database: Three implementations: `BinaryRepository<T>`, `SqliteRepository<T>`, `MySqlRepository<T>`
- :material-factory: `RepositoryFactory` reads config and returns active backend
- :material-swap-horizontal: **Settings screen:** Both console and GUI expose storage switch: [1] Binary [2] SQLite [3] MySQL
- :material-docker: MySQL requires `docker-compose.yml` in repo root
- :material-test-tube: Each implementation has its own test class

### 11.1 Class Hierarchy

Design a meaningful hierarchy. Inheritance must be semantically justified — prefer composition over inheritance when not a true "is-a" relationship.

### 11.2 Encapsulation

- **Access modifiers:** `private`, `protected`, `public` applied correctly
- **Getters / Setters:** All fields private; expose through accessors

### 11.3 Polymorphism

- :material-arrow-up-bold: **Method overriding:** At least 3 meaningful overrides
- :material-shape: **Runtime polymorphism:** Superclass reference → subclass instance
- :material-puzzle: **Interfaces:** At least 2 defined and implemented

### 11.4 Design Patterns (minimum 3 — one per category)

| Category | Patterns |
| --- | --- |
| :material-plus-box: **Creational** | Singleton, Factory Method, Abstract Factory, Builder, Prototype |
| :material-puzzle: **Structural** | Adapter, Decorator, Facade, Composite, Proxy |
| :material-play: **Behavioral** | Observer :material-star:, Strategy, Command, Template Method, Iterator |

For each: state intent, code location, and justification.

### 11.5 SOLID Principles

| Principle | Rule |
| --- | --- |
| **S** — Single Responsibility | Each class has exactly one reason to change |
| **O** — Open/Closed | Open for extension, closed for modification |
| **L** — Liskov Substitution | Subtypes usable wherever supertype expected |
| **I** — Interface Segregation | Role-specific, focused interfaces |
| **D** — Dependency Inversion | Depend on abstractions, not concretes |

---

## :material-clipboard-check: 12. Evaluation Rubrics

Each criterion scored **1–5**: 5 = Excellent, 4 = Good, 3 = Fair, 2 = Poor, 1 = No Evidence, 0 = Zero.

??? abstract "Midterm Rubric (A1 — 60% of Mid-Semester Grade)"

    | Criterion | LO | Max Pts |
    | --- | --- | --- |
    | PlantUML Diagrams — Class + Seq (≥3) + Use-Case + Activity + State + C4 + Gantt + ER | LO.1,2 | **15** |
    | UMPLE Model — `.ump` committed; meaningful domain model | LO.1,3 | **10** |
    | Figma Wireframes — Console + GUI screen sets; annotated | LO.2,7 | **10** |
    | ProjectLibre Gantt — both phases; tasks assigned; `.xml` committed | LO.2 | **5** |
    | OOP 4 Principles — Encapsulation; Inheritance; Polymorphism; Abstraction | LO.1–3 | **20** |
    | Build + Unit Test Coverage — 100% required | LO.2,4 | **20** |
    | Doxygen Documentation — PDF only; 100% coverage | LO.2 | **10** |
    | Ethics Gate — LICENSE file + initial Social Impact Analysis | PÇ.11 | **5** |
    | Midterm Report — design decisions; constraint; GitHub URL | LO.1–3 | **5** |
    | **TOTAL** | | **100** |

??? abstract "Final Rubric (F1 — 70% of End-of-Semester Grade)"

    | Criterion | LO | Max Pts |
    | --- | --- | --- |
    | Architecture Evolution — Console → GUI; MVC; lib unchanged | LO.2,3 | **10** |
    | GUI — all Figma screens implemented; event handling; validation | LO.2,7 | **15** |
    | Design Patterns — min. 3 categories; correct; documented | LO.3,7 | **15** |
    | Exception Handling — try-catch; custom hierarchy; GUI dialogs | LO.4 | **10** |
    | Generics + IRepository (3 backends switchable) + Collections + ER | LO.5,6 | **10** |
    | Code Smell & Refactoring — min. 3; before/after shown | LO.7 | **10** |
    | Unit Tests + CI/CD — ≥80% coverage; CI badge | LO.2,4 | **5** |
    | PÇ.9 — Social Impact; KVKK; accessibility | PÇ.9 | **5** |
    | PÇ.11 — IEEE/ACM; LICENSE; AI declaration; licenses | PÇ.11 | **5** |
    | Git Workflow — Commits; branches; PRs; Issues; CHANGELOG | LO.2 | **5** |
    | Updated Design Docs — PlantUML + UMPLE + Figma + draw.io | LO.1–7 | **5** |
    | Presentation + Video — each member explains contribution | LO.1–7 | **5** |
    | **TOTAL** | | **100** |

---

## :material-alert-circle: 13. Important Requirements

=== ":material-calendar-clock: Midterm"

    - :material-translate: **Languages:** Java (JDK 11/17), C/C++ (CMake), C# (.NET Core)
    - :material-test-tube: **Testing:** 100% statement coverage
    - :material-hammer-wrench: **Build:** Must pass with zero errors
    - :material-file-document: **Doxygen:** 100% coverage — PDF only
    - :material-database: **Storage:** IRepository with all 3 backends + runtime switching
    - :material-palette: **Design tools (all required):** PlantUML, C4, UMPLE, Figma, draw.io, ProjectLibre
    - :material-scale-balance: **Ethics Gate:** LICENSE + Social Impact Analysis
    - :material-git: **Git:** Feature branches; Conventional Commits; min. 1 merged PR; Issues + Board
    - :material-file-document-edit: **Report:** Constraint; GitHub URLs; LO table; Social Impact

=== ":material-flag-checkered: Final"

    - :material-monitor: **GUI:** Swing / Qt / WinForms — implement ALL Figma screens
    - :material-layers-triple: **Architecture:** Strict MVC; lib unchanged
    - :material-shape-plus: **Patterns:** Min. 3 from different categories
    - :material-magnify: **Refactoring:** Min. 3 code smells; before/after
    - :material-robot: **CI/CD:** GitHub Actions; CI badge; ≥80% coverage
    - :material-desktop-classic: **Platforms:** Windows + WSL/Linux
    - :material-scale-balance: **Ethics:** Full Social Impact (6 dims); AI Declaration; licenses; contributors
    - :material-text-box: **CHANGELOG:** v1.0.0 + v2.0.0 entries

=== ":material-gate: Minimum Pass"

    - :material-numeric-50-box: **Midterm:** At least 50/100 on Midterm Rubric
    - :material-numeric-50-box: **Final:** At least 50/100 on Final Rubric
    - :material-alert: **Ethics Gate:** LICENSE missing at Midterm = Zero
    - :material-star: **Critical:** OOP, Design Patterns, Ethics must each ≥ 50%

---

## :material-link-variant: 14. Resources

<div class="grid cards" markdown>

-   :material-language-java: **Java Maven Template**

    ---

    [:octicons-repo-16: eclipse-java-maven-template](https://github.com/ucoruh/eclipse-java-maven-template)

-   :material-language-cpp: **C/C++ CMake Template**

    ---

    [:octicons-repo-16: cpp-cmake-ctest-template](https://github.com/ucoruh/cpp-cmake-ctest-template)

-   :material-language-csharp: **C# .NET Core Template**

    ---

    [:octicons-repo-16: vs-net-core-template](https://github.com/ucoruh/vs-net-core-template)

-   :material-layers-outline: **C4-PlantUML Library**

    ---

    [:octicons-repo-16: C4-PlantUML](https://github.com/plantuml-stdlib/C4-PlantUML)

</div>

| Resource | Link |
| --- | --- |
| :material-file-document: Project Report Template | [rteu-ceng-project-homework-report-template](https://github.com/rteu-ceng/rteu-ceng-project-homework-report-template) |
| :material-file-tree: PlantUML Online Editor | [plantuml.com](https://www.plantuml.com/plantuml/uml/) |
| :material-state-machine: UMPLE Online | [cruise.umple.org](https://cruise.umple.org/umpleonline/) |
| :material-palette-outline: Figma | [figma.com](https://www.figma.com) |
| :material-chart-gantt: ProjectLibre | [projectlibre.com](https://www.projectlibre.com) |
| :material-monitor: Swing Tutorial (Oracle) | [Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/) |
| :material-microsoft-teams: Team & Project Selection | Shared spreadsheet on Microsoft Teams |

---

## :material-shield-alert: 15. Academic Integrity

!!! danger "Warning — Plagiarism & AI Tools"

    - :material-content-copy: Copying another person's code is **strictly prohibited** — plagiarism detection software will be used
    - :material-robot: AI-generated code may not be submitted as original work — **every line will be questioned** during in-office review
    - :material-account-question: Each team member must explain **any part** of the submitted code
    - :material-gavel: Plagiarism → **zero points** for entire project + disciplinary proceedings

---

## :material-frequently-asked-questions: 16. In-Office Review Questions

During the in-office review each team member will be asked questions **independently**.

??? question "16.1 GitHub & Git Usage"

    - Did you fork all three templates and name repos correctly (`-java`, `-cpp`, `-csharp`)?
    - Are all repositories private? Instructor as collaborator?
    - Are there collaborative commits from all team members?
    - Do commits follow Conventional Commits format?
    - Did you work on feature branches? Show the branch list.
    - Show a merged PR. Who reviewed it? What comments?
    - Is `main` protected? Show branch protection rules.
    - Show GitHub Issues — labeled, assigned, linked to PRs?
    - Show Kanban board. Walk a card from Backlog → Done.
    - Open CHANGELOG.md. Explain entries.
    - Did you create GitHub Releases with all artefacts for all three repos?

??? question "16.2 Submission Checklist"

    - Single ZIP with correct folder structure?
    - Release contains all build artefacts?
    - Each member submitted a separate video?
    - Presentation deck prepared?

??? question "16.3 Development Environment"

    - WSL installed for Linux development?
    - Chocolatey / Scoop installed? Show versions.
    - Application runs on both Windows and WSL?

??? question "16.4 Project Structure & Features"

    - Project organized as `app / lib / test`?
    - Demonstrate CRUD operations — data survives restart.
    - Menus navigated with ++arrow-up++ ++arrow-down++ ++tab++?
    - Show unit tests and coverage report.
    - Show Doxygen PDF — 100% coverage?
    - Walk through C4 diagrams (Context, Container, Component).
    - Walk through PlantUML class and sequence diagrams.
    - Open UMPLE `.ump` file — explain the model.
    - Show Figma wireframes — console and GUI screens.
    - Open draw.io Deployment diagram — explain modules.
    - Open PlantUML Gantt — actual vs planned dates.
    - Open GUI. Show all screens with event handling and validation.

??? question "16.5 OOP & Programming Skills"

    - Explain encapsulation, inheritance, polymorphism, abstraction in your code.
    - Explain design patterns used — where, why.
    - Give a concrete example of each SOLID principle.
    - Show custom exception hierarchy and GUI error dialogs.
    - Show Generics and Collections usage.
    - **Storage Layer:** Open `er.puml` — explain entities. Switch backends live: Binary → SQLite → MySQL.
    - Debug with Eclipse (Java) / Visual Studio (C++/C#) / VS Code.
    - Explain build lifecycle for your language.
    - Show MVC separation in GUI application.
    - Show before/after of a refactored code smell.

??? question "16.5b C/C++ Specific Questions"

    - Explain pointers and arrays. Pointer arithmetic?
    - Structures vs classes in C++?
    - Dynamic memory allocation: `malloc`/`free` (C), `new`/`delete` (C++)?
    - File read/write operations in C/C++?
    - Preprocessor directives: `#include`, `#define`, `#ifdef`?
    - Function parameters: by value, by reference, by pointer?
    - Cross-compilation with CMake?
    - Call stack inspection in Visual Studio / GDB?
    - Debugging — inspect variables and memory?
    - x86/Win32 vs x64 configuration differences?

??? question "16.5c C# .NET Core Specific Questions"

    - Properties vs fields — auto-properties?
    - Value types vs reference types?
    - LINQ usage in your project?
    - `async`/`await` and Task-based programming?
    - Garbage collection — .NET vs Java GC?
    - NuGet package management?
    - .NET Core project structure (`.csproj`, solution)?
    - `dotnet test` with coverage?

??? question "16.6 Professional Ethics & Social Impact (PÇ.9 + PÇ.11)"

    | # | Question | Maps to |
    | --- | --- | --- |
    | 1 | Open LICENSE. Which license? Compatible with deps? | PÇ.11 |
    | 2 | Walk through Social Impact Analysis — all 6 dimensions | PÇ.9 |
    | 3 | IEEE clause 1: harm scenario documented? | PÇ.11 |
    | 4 | IEEE clause 3: honesty in report? | PÇ.11 |
    | 5 | Personal data stored? KVKK/GDPR principles? | PÇ.9 |
    | 6 | Show 2 WCAG 2.1 accessibility features in GUI | PÇ.9 |
    | 7 | AI Tool Use Declaration — tools, purposes, verification? | PÇ.11 |
    | 8 | Dependency license table — compatibility? | PÇ.11 |
    | 9 | Contributor table — your features, commits, Issues? | PÇ.11 |
    | 10 | ISO/IEC 25010 quality characteristic in your design? | PÇ.11 |

    !!! danger "Ethics Review Rule"

        If a team member cannot answer questions 1–4, PÇ.11 criterion is scored **Zero (0)** for that individual — regardless of what is in the report.

---

## :material-star-shooting: 17. Bonus Features

!!! success "Core requirements must be fully met before attempting bonus features"

??? example "17.1 :material-database: Database Integration with Docker"

    - Replace binary storage with MySQL/PostgreSQL in Docker container
    - Define tables, relationships, constraints; provide schema SQL
    - Use JDBC / JPA / Hibernate with connection pooling
    - Provide `Dockerfile` + `docker-compose.yml` with volumes

??? example "17.2 :material-shield-key: Authentication & Authorisation with Keycloak"

    - Add Keycloak to Docker Compose — realm, client, roles
    - Login/register screens in GUI
    - Role-based access control; token refresh; logout

??? example "17.3 :material-chart-line: System Monitoring & Logging"

    - Prometheus for metrics; Grafana dashboards
    - Loki (or ELK) for centralized logging
    - SLF4J + Logback with structured output; log rotation

??? example "17.4 :material-api: Microservices with Spring Boot"

    - Spring Boot REST API with Swagger/OpenAPI
    - GUI calls REST API instead of lib directly
    - Each service in own Dockerfile; docker-compose orchestration
    - Health checks for startup order

!!! info "Bonus Evaluation Criteria"

    | Criterion | What we look for |
    | --- | --- |
    | :material-layers-triple: Architecture Quality | Well-defined layers, separation of concerns |
    | :material-docker: Container Management | Correct images, volumes, networking |
    | :material-shield-lock: Security | Credentials in env vars — never in source |
    | :material-monitor-eye: Observability | Monitoring dashboards demonstrated live |
    | :material-arrow-expand-all: Scalability | Horizontal scaling design explained |
    | :material-file-document: Documentation | README covers all advanced setup steps |

---

## :material-format-list-numbered: Appendix – Application List

Choose one project from the list below. Each project includes key features, common requirements, and language-specific implementation details.

---

??? example "01 — :material-swap-horizontal: Book Exchange Platform"

    **Key Features:** Listing books for exchange · Managing exchange requests · Rating system for users · Tracking exchange history

    **Common Features:**

    - :material-account-key: **User Authentication:** Login system with secure account creation
    - :material-database: **Book Database:** File storage for book info (title, author, genre, owner)
    - :material-menu: **Menu System:** Console menu with keyboard navigation
    - :material-magnify: **Search & Listing:** Search by title/author/genre; list own books
    - :material-swap-horizontal: **Exchange Requests:** Send/receive/accept/decline exchange requests
    - :material-star: **Rating System:** Rate and review users after successful exchanges
    - :material-history: **History Tracking:** Record of all past transactions

    === ":material-language-cpp: C/C++"

        - Use file handling to store book information and transaction history in binary files
        - Implement a text-based UI for the console menu

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using Console class or Scanner

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class

    Optimize the code for efficient data storage and retrieval, error handling, and user experience. Enhance functionality based on your specific requirements.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZPD1Jm8n48Nl_HMJFQa9yHyOC9XuaCkYtiVkPBU1Jh6TglnxhKL2O1aypdjVvht9RgT9C4hsQvDMZZSOqOCF7EmGWoUGcCcOgvjhc4pke5cWOrWGvyQOWw6QBuaYpB8CnE8iYWlSc56rya-XTphHKk-IKBoq9cHH4RNsVGWhcAFW6oPgpD6yot19e1Zf333hkb__ZDsI3n-a4Mh59M6q0o1tK9euxXip9XxIDKp1oWIBRJrB0zLg3rzsGEu9MdhFbAHiEj7snRZxqnJirT7dVzRDhAMDtCx9hXsJllZo8IsMas6vJKA-CKVpdY4elzcuNvjN8uqZUeT7boJ4BJn7jAlwbK-aWwITTvxImdxUj9aITzcllm40)

---

??? example "02 — :material-clock-outline: Personal Time Tracker"

    **Key Features:** Activity logging · Time spent analysis · Productivity reports · Break reminders

    **Common Features:**

    - :material-account-key: **User Authentication:** Secure login with user profiles
    - :material-pencil: **Activity Logging:** Log activities with name, start time, end time
    - :material-chart-bar: **Time Spent Analysis:** Total time per activity (daily + long-term)
    - :material-file-chart: **Productivity Reports:** Graphs/charts visualizing time distribution
    - :material-coffee: **Break Reminders:** Configurable break intervals with notifications

    === ":material-language-cpp: C/C++"

        - Use file handling to store activity logs and user data in binary files
        - Create a text-based interface for logging activities and viewing reports

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using Console class or Scanner

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class

    Consider adding data export (CSV), customizable break reminders, and activity categorization for detailed analysis.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZPD1QiCm44NtEiNWLLl85KfI2jqa4EAsUs4FoX1hP4QZ5j--0jMX45lDqb_lVpyRj8rgHPCVJBmGZrQi1nyuT6S97a4beJ4FJu-msJn3Sx34S41EZJ5_7ihXHqI1NT8pib9dbG8tPZ4j_3uugXCdT1GLvR-PaBIALBLtdT8NwGHvA0kvnjn6rpV1VBHEVH9-pnXXN0UhLk_a4TeHMM77TfWYvQN5jF2lbeO9skIzbQbAxWCx53YYKEZlQbH5ZnBwD7_W2SSWcasNuz9uGqQnYhzn5Iv_ywxPgkMBeBtaWYVkKVBIJLAu5lMQhK6pKmaXLvJOhM_MnFBDCblaFldX1m00)

---

??? example "03 — :material-notebook-outline: Digital Journal/Diary"

    **Key Features:** Daily entry logging · Search and filter entries · Password protection · Mood tracking

    **Common Features:**

    - :material-account-key: **User Authentication:** Accounts with usernames and passwords for privacy
    - :material-pencil: **Daily Entry Logging:** Date, time, thoughts, experiences, notes
    - :material-magnify: **Search & Filter:** Find entries by date, keyword, or mood
    - :material-lock: **Password Protection:** Secure access to journal entries
    - :material-emoticon-happy-outline: **Mood Tracking:** Record mood per entry (happy, sad, stressed, relaxed)

    === ":material-language-cpp: C/C++"

        - Use file handling to store journal entries and user data in binary files
        - Create a text-based interface to add, search, and read entries

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using Console class or Scanner

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class

    Consider adding image/file attachments, keyword tagging, and summary reports by mood or date range.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/bPFTQiCm38Nl_HGYLzkWhp0wset15YZxsRtcYCGqiOQiKFBsIsh2LWXgUscTJyV7SBnEYg9zrxgq3_471JleEB9lX3i2bPwSkxcxXTNg7eeIGuIIObyuz--GnSz40W-zDXGrUDJ0iN2Bqyo_SXriuetga9Jad0ttQY9cx2s6Te37g3B0w3LcgWktECtyHWYLicI2Nu4EHo5GCa4ptZkX-0OmLl0KMYNvipIqaztl0RQeTDN22mq7bkggdPBvaWNpkJjCQReOTi9AFfTeSJhVhZ2h9hXfCDPq6Micd5u97u9-dvjoUfwBwoVZILecSZcEmdD8oZ8iSsQKyLkwDSLg_Bs_)

---

??? example "04 — :material-cash-multiple: Expense Sharing Among Friends"

    **Key Features:** Expense recording and splitting · Balance tracking · Notifications for settlements · Summary of shared expenses

    **Common Features:**

    - :material-account-key: **User Authentication:** Accounts with usernames and passwords for accurate expense tracking
    - :material-calculator-variant: **Expense Recording & Splitting:** Record expenses with amount, description, and friends to split with
    - :material-scale-balance: **Balance Tracking:** Track who owes whom; auto-update on expense add or settle
    - :material-bell-outline: **Settlement Notifications:** Notify users of pending or completed settlements
    - :material-file-chart: **Expense Summary:** Overview of contributions, shared expenses, and balances per user or group

    === ":material-language-cpp: C/C++"

        - Use file handling to store expense data, user accounts, and balances in binary files
        - Create a text-based interface for the console application to record expenses, view balances, and settle debts

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding expense categories, group management for shared expenses, and exporting reports for record-keeping.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/bPD1JiGm34NtEONL5I3D5T20n8vXCGNsLiTjeqcSod44t9wALAW8g65f_-UVxqJPPqNH4eFBPyyJ2aQ8YLCtIee4AeMSkxYwXDtk6fe3UeO3SMcSMnLLVCeaS5Dq95RVeVh4ZVknM_dxDFXjuaY3pqfYsQIY6uWH--vr8iu4H-gId3mFW7o2TWfUTQuQPnBLfwgmm7yU-wZ_EMLiT8i1kIDu5EpETOLlhKe-Utg1HSk_OSPf3qbzlzntXZu9jAGQA1Bhx6degnnVG7lGJjQM657U8FNGZYZq-JYp-uQuphHG9cz4cJ-GsnEVIWpl)

??? example "05 — :material-timer-outline: Virtual Study Timer (Pomodoro Technique)"

    **Key Features:** Customizable work/break intervals · Progress tracking · Reminder alarms · Statistics on study patterns

    **Common Features:**

    - :material-account-key: **User Authentication:** Optional accounts to save study stats and preferences
    - :material-tune: **Customizable Intervals:** Set custom durations for work sessions and breaks
    - :material-progress-clock: **Progress Tracking:** Countdown timer for work sessions and break periods
    - :material-bell-ring-outline: **Reminder Alarms:** Configurable alerts at session/break end with sound options
    - :material-chart-line: **Study Statistics:** Track completed sessions, total study time, and averages with charts

    === ":material-language-cpp: C/C++"

        - Use file handling to store user preferences and study statistics in binary files
        - Create a text-based interface for the console application to start, pause, and customize study sessions

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding long-term study goals, historical data analysis, and statistics export for future reference.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/bPFFIWGn38VlVOhGamgBRo2xUX31XMN7F-Su4sR3J5D9KaMVNcKMHd0fux7vlgI_5BesH_MInc03ooig9aXPSdlGd0XS2uLmTd4EgzKLn2som9Qan11-7IRuQAImANuWSMxHEKiC9wkJVvzxhXjxwjcSj2hT5ZA7RUwefjMZtnJpdFWJNqQ2vwp3vRKI3d0dJlg6eyMmm9bcDUG_U0R_x9t9ukO56-uqzqfcyA3O3Ynz32TgnnIEwj1uwJwW8JFEKh5tM8mMstkoaXRhzPtsb5WwKjYCgCbY-5EPNxMkrEzf79tDkJN8Seo-GtTIiHZgU9hmnFH-9BEzhBMMxFjVXZL9LzBu1G00)

---

??? example "06 — :material-food-apple-outline: Diet Planner"

    **Key Features:** Meal planning and logging · Calorie and nutrient tracking · Personalized diet recommendations · Shopping list generator

    **Common Features:**

    - :material-account-key: **User Authentication:** Optional accounts to personalize diet plans and track progress
    - :material-silverware-fork-knife: **Meal Planning & Logging:** Plan daily/weekly meals and log foods with portions
    - :material-fire: **Calorie & Nutrient Tracking:** Calculate intake and nutritional values; set daily goals
    - :material-heart-pulse: **Diet Recommendations:** Personalized suggestions based on age, gender, weight, and dietary preferences
    - :material-cart-outline: **Shopping List Generator:** Auto-generate ingredient lists from selected meal plans

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, meal plans, and diet data in binary files
        - Create a text-based interface for the console application to plan meals, log food, and view nutrition information

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding a food database with nutritional info, weight/fitness goal tracking, and meal plan sharing with friends or nutritionists.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/dPD1I_j04CNl-oc6dFv_eLz1Aehrq4XfrVkI3EdGx4oOdKNqqxlfUgYWQ_2sa_TxmtiCMKVpQYcCBfw89wy-G12MxgWI44mJElVlwZ-iLjVGj9uOMkJKE7Sn5F4vei9DiYEoKUUDX1ltxTV2RsMWEh77WQAXLg5DmcZGIeyrh1wzHJ_2RlJCn0DuxY676_AxSHMjU6S9PYWkWFC4zoBz0lI5y1NEq0D54tshUPQKlFMZAE7PzfWiFzdWINrtAaNhUjbnG8EDvAwB-5AYYFCfO8-Jg2tpriliKAEm7-aTUxWZjBoxan2G-_FLSy05p4N8lzkhEGz7cQRvM5kA1XjaL6-YZVjHARxF6U4BzgkjbYN_nMwTmwSmVW00)

---

??? example "07 — :material-briefcase-outline: Personal Career Tracker"

    **Key Features:** Job application tracker · Skill development progress · Interview preparation notes · Career milestone logging

    **Common Features:**

    - :material-account-key: **User Authentication:** Secure accounts to personalize career tracking and store data
    - :material-briefcase-outline: **Job Application Tracker:** Log company, position, date, and status (pending, rejected, interview scheduled)
    - :material-lightbulb-outline: **Skill Development Progress:** List skills, track progress, set goals, and log achievements
    - :material-note-text-outline: **Interview Preparation Notes:** Store notes per application with company details and common questions
    - :material-flag-checkered: **Career Milestone Logging:** Record promotions, certifications, and completed projects

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, job application data, skill progress, interview notes, and career milestones in binary files
        - Create a text-based interface for the console application to add, edit, and view career-related information

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding goal setting for career development, report export for applications and skills, and job search website integration.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZPDDQiCm44RtEiMWLLl85Kf22gKbBgK_tQlnXoCYQyneb5o_8Gh4YyRoqdxlcS_6MYPrehaF9krz79ouddgElDq9zo2L36Fk7kvfiNWaspWVgK7CrfZHHO7V2KAhh3j4zLkddgCr_zuj_eOxFsryeFD98PFISqPIQhZ5X5QP_ig_j1g6S4deIzns3x7c9YdTgct7jEh_U1p7E5MAokpFlG-1dd10uA57L7eNxWGfMJC5BpMKpj8i-pnhxlFhqr-YGWuUnrE5mKbvzpTM96icQUdN0gUO7vHFNzTdZLyx0OGQ7v2K8sZ3NUTZPyrDTFtdhwocbz5Nc2f1PVRf59ibOflxy0S0)

---

??? example "08 — :material-lock-outline: Password Manager"

    **Key Features:** Secure storage of passwords · Password generator · Auto-login feature · Multi-platform compatibility

    **Common Features:**

    - :material-account-key: **User Authentication:** Master password to encrypt/decrypt all stored passwords
    - :material-shield-lock-outline: **Secure Storage:** Encrypted password storage organized by categories or accounts
    - :material-key-plus: **Password Generator:** Create strong, unique passwords with configurable length and complexity
    - :material-login: **Auto-Login Feature:** Auto-fill credentials for selected websites or applications
    - :material-devices: **Multi-Platform Compatibility:** Support for Windows, macOS, Linux, and mobile devices

    === ":material-language-cpp: C/C++"

        - Use file handling to store encrypted password data in binary files
        - Create a text-based interface for the console application to manage passwords, generate passwords, and enable auto-login

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider a CLI version for cross-platform compatibility. Follow industry best practices for encryption and security.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/bPH1Jm8n48Nl_HLDJsgoVy6m0VN2HXA2deVj01FR3ccdGV-zn4RaG6htsFU-rtaxsNIM1A9axrJwe72425vx3ZmU8dlK4ZCgTVTmhxlkKPi1AEW1GpPANHsAkKaOTP_bW45e124EHjrK2p-F289wW2HdP0KfdJZQQcJ9Umh_4PobXjG_OerZZgZNmX7sg7btAPQCghebtrjxqPe2RuIdYJCsHmk2qyOiqE74J7rHlx1-mO0HphSOTKiiT3bV5QZ0zRbz5kv-_WFzZ20veb6tn48_1TWwr7z-3LvGQgVhBOViXBgL0zbnz7hE_WX2Mt8aNqRLxNB3EmNBfzG6UnXVrsteaaB-R4FxO2EJROEfhK1zR-U7HiqmsEpTDm00)

--- 

??? example "09 — :material-yoga: Yoga/Meditation Scheduler"

    **Key Features:** Session scheduling · Pose and technique library · Progress tracking · Reminder for daily practice

    **Common Features:**

    - :material-account-key: **User Authentication:** Optional accounts to personalize practice and track progress
    - :material-calendar-clock: **Session Scheduling:** Schedule sessions with date, time, duration, and practice type
    - :material-book-open-outline: **Pose & Technique Library:** Browse yoga poses, meditation techniques, and breathing exercises
    - :material-chart-line: **Progress Tracking:** Record practice history, sessions completed, duration, and improvements
    - :material-bell-ring-outline: **Daily Practice Reminder:** Set notifications at preferred practice times

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, session data, and progress tracking in binary files
        - Create a text-based interface for the console application to schedule sessions, view progress, and set reminders

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding custom sessions, mood/stress tracking before and after practice, and guided audio/video instructions.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/bPJHIkj048Rlzoc6N9qZz1Ma5aM51afR1I_NP4o6PdVhpAorRsyqNGbOrdYPVxwF_9CDEnUrhD5rHdRazvQj0nTyg5eE3a4veZ7_Blx3R7O9HMd9Gua-5iQC7eRXWo33LTGMlL9bbO8lpDbqu9UXeJonneP4aRFGRKHHA4EDMInRVOCYFGgRgiKwTkIRmfm94pq424-XiN329WabrgHVhQPeZuI7b6AT3Cae-IrNGH2ihs6BLUlfDI8iwPaj7mkJ6mxsWiEXHpuvcSwFLqxad_m5ezMMV0CtxyWLJNnx_aDmQ1X5OCkssWsd_JCR7SAgpvGgX3iI3NpyZRztUmvlwD2hPDXioJKwyZKol0I6QqlTyRj6ORBJz4_hQP3Ol3Rkdp2PfEJMwAyECqTVHzTz0000)

---

??? example "10 — :material-code-braces: Coding Snippet Manager"

    **Key Features:** Storing and categorizing code snippets · Search functionality · Sharing snippets with others · Integrations with popular IDEs

    **Common Features:**

    - :material-account-key: **User Authentication:** Optional accounts for personalized snippet management
    - :material-code-tags: **Snippet Storage & Categorization:** Store snippets by language, framework, or project with titles, descriptions, and tags
    - :material-magnify: **Search Functionality:** Search by keywords, tags, or programming languages
    - :material-share-variant: **Snippet Sharing:** Share publicly or via link with visibility options (public, private, specific users)
    - :material-puzzle-outline: **IDE Integrations:** Connect with VS Code, IntelliJ IDEA, or Visual Studio for import/export

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, code snippets, and sharing settings in binary files
        - Create a text-based interface for the console application to manage snippets, search, and share

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider creating IDE plugins/extensions, and allowing rating and comments on shared snippets for collaborative coding.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/dPHHIyD0383VxrU4Fgcmlo0RsvJXXhAfxx4NheTjhkHopFdhNQaR4yPH-tZ9zoLDhjmqAegcfZRnqtEBWWqqWKDPIMW8L18PStDt2vF9FHGRz0mRubGOSt7eas-H16P9AsBr9QeFN9YhqPvV1-Vpn9QSZqgIXHuJHOLDi9J5if--qo2U7I1RcAEIEnw_ky0yM88T-xObZOKP2lPLPzQUGlzLtptjH_TTMgzZ6o-e9gMnTdx9X59My92uxEu5Qw-7mbmD_pK-3l14XtqG6uVoh-W6isjabz1HdkzlfCzlAQPQOrx8xwB2Rgld_S7UQmNFMf5qPNFfYmfq0eOf_GYTPy_O83C_ougLd60t_w_x4jfKey1giUmQv85JbQODSlvjXrhBhn5MTfpZMsUcn3OrzGy0)

---

??? example "11 — :material-home-edit-outline: Home Renovation Planner"

    **Key Features:** Project and budget planning · Task assignment and tracking · Cost analysis and reporting · Supplier and contractor database

    **Common Features:**

    - :material-account-key: **User Authentication:** Optional accounts to personalize renovation projects and track progress
    - :material-clipboard-text-outline: **Project & Budget Planning:** Define scope, timeline, budget, milestones, and goals for renovations
    - :material-checkbox-marked-outline: **Task Assignment & Tracking:** Assign tasks to team members/contractors with status and deadlines
    - :material-cash: **Cost Analysis & Reporting:** Track materials, labor, and expenses; generate budget breakdown reports
    - :material-database: **Supplier & Contractor Database:** Store contact details, reviews, and project history for providers

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, project data, task assignments, and cost records in binary files
        - Create a text-based interface for the console application to plan projects, assign tasks, and view cost reports

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding document management for plans/contracts, a calendar for scheduling, and report export (PDF, CSV).

    ![Class Diagram](https://www.plantuml.com/plantuml/png/fPJFJiCm3CRlUGgh9q2QU0EqiKsxC6dQ7-wciRgmrgbYHy3R0mtLTa0XQCVMlz_NV9RIgIW6ZLrhvEIunu0TT9vzVGo-8z0GoPYRkrkOJ1wWMgDZM1F7ofYBXpGy20MOHJqIgwjHdUVA_FesyK--SNbYIuqJfP25Lf54OUqjPR7iqJV1lrAjW6pXCTg653OjCZjkAfET9dyU29NW1onIdXszZO8K6Klhz5-VM51BnQSgsD8UvGGp4TTmHvnYzW7hKrfJTfmIqXI-oH8XRMd0fOm_z1QLO21tYXhBl7plkHU56MFx8Ku6Uqkz3vfgPwPZQsjX-TuJ2nNmgVGeI96n8gQ0If06HLA-yoxsVUie3ERSimQirGTOeE8B3aN-HCxjH_J-Z1K7f7MCh5n4I6b6kZlNdI9TfcioyXl_-iUQAR6DNVi9)

---

??? example "12 — :material-car-wrench: Car Maintenance Log"

    **Key Features:** Service history tracking · Maintenance reminders · Expense logging · Fuel efficiency reports

    **Common Features:**

    - :material-account-key: **User Authentication:** Optional accounts for personalized vehicle maintenance tracking
    - :material-history: **Service History Tracking:** Log service date, type (oil change, tire rotation), provider, and cost
    - :material-bell-ring-outline: **Maintenance Reminders:** Notifications for scheduled tasks based on mileage or time intervals
    - :material-calculator-variant: **Expense Logging:** Categorize fuel, repair, and maintenance costs; generate reports over time
    - :material-gas-station: **Fuel Efficiency Reports:** Calculate MPG or L/100km and display fuel efficiency trends

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, vehicle data, service records, expense logs, and reminders in binary files
        - Create a text-based interface for the console application to log maintenance, view reminders, and generate reports

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding vehicle specifications storage, service invoice uploads, and charts for maintenance/fuel efficiency trends.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZPJDRi8m48JlVefLJcqbNg6YKbDwA1VwSxUS8Qn8riXUj-NjYqG85S8cnynyivhiIfv7jK5JtvcuPTdPO7lglNYt2Ru7QKWmvkxXdcQpHwgMbeMMa5GPy-_ZQ7v617fAke4eEwlifJBNr85_yosNYHLQZefGX1O9KMdf6nInOlLtX6zse5UEwiEUFe9rMvQsCdbh_0aQaDlm5-E7BfZRgRfX7LErw9JCCpeefgVAEnfDXLXne1LwbWOXLYQZd_QgerP4XzrCdLiiMl_k815qE4rxlE6bSZxTu6IOeTLWnIorW21O1Pti5NO-Q3vGxF-Iq56zNhDZYDkVXsMTYnRNiPlvKgd32s3ca2RrtHy0)

---

??? example "13 — :material-music-note-outline: Music Practice Scheduler"

    **Key Features:** Instrument practice logging · Set goals and track progress · Reminder for practice sessions · Music theory reference

    **Common Features:**

    - :material-account-key: **User Authentication:** Optional accounts for personalized practice tracking and monitoring
    - :material-pencil: **Instrument Practice Logging:** Record practice date, instrument, duration, and exercises/pieces practiced
    - :material-target: **Goal Setting & Progress Tracking:** Set practice goals (new songs, techniques) and view statistics over time
    - :material-bell-ring-outline: **Practice Reminders:** Configure notifications for scheduled practice sessions
    - :material-music-note: **Music Theory Reference:** Built-in reference for theory concepts, scales, chords, and more

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, practice session data, practice goals, and progress records in binary files
        - Create a text-based interface for the console application to log practice sessions, set goals, and view progress

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding a metronome tool, session recording/playback, and sharing with teachers or fellow musicians.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/dPHHImCn48JVyokMUrAXVq6gA4NmG6hrFUIsrz1Bjkmc5F-zAq5EyGoXZ-tCDpUp1xUMP3dbC1av-dYoR0C4YkGEJ04XSKPZhcwkORMwXQwtFaAFCNV6_FXHn3T1XhkS3XYJTpPvYfrP_BVudsdqTSSMHoy9kMhQP9G4FGrOizMhFqNHfK4XU67h54NGUjflx4nDdQVCqYkAU8fDuBl7ynzIcj37mQVB7lg04oQyYAsVSKDsqhIKr0ys3Xf28wC8x3JjMAxPO2ffwfbRV57IIi-NBM83sBXmYy770Hdsn8llhMvOMlPDjD9bMJlLCglFuXti3aZyeT5xP8mEE_EVKAXxfhCW_FBiwEITrCbw6VtOc3N68OVf4m00)

---

??? example "14 — :material-broom: Household Chore Scheduler"

    **Key Features:** Chore assignment for family members · Schedule and reminder setup · Progress tracking · Reward system for completed chores

    **Common Features:**

    - :material-account-key: **User Authentication:** Family member accounts/profiles for personalized chore tracking
    - :material-clipboard-text-outline: **Chore Assignment:** Assign chores to family members with due dates and frequencies (daily, weekly)
    - :material-calendar-clock: **Schedule & Reminders:** Set completion schedules and receive notifications when chores are due
    - :material-progress-check: **Progress Tracking:** Record completed chores and show participation levels per family member
    - :material-star: **Reward System:** Earn points for completed chores; redeem for family-determined rewards

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, chore assignments, progress records, and reward data in binary files
        - Create a text-based interface for the console application to assign chores, set schedules, and track progress

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding a leaderboard, family messaging system, and customizable rewards based on family preferences.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZPFDQiCm48JlUeh5fxQGLoW9_UcbXkAqlGjhi4KiAUok27dxbhg2b3HMZZlp3PeLqbhKikOm6TdvkBTi0uKKKpzo2Y3b36DkxcvfjRgdfhK-KekO6sDEXjdy431jiew8wdkhFiN6_AlE_6iQ_3BHOV2Yu4Ne9KEKskImW5MgFuo9GHiHFyI0g8qvbsPo5kZ7lKnzUXn-LRWPbilqa_DArrTun0J5TO7wwjj-XCiJo4P77OAF3anRQDutPiajUPJkXQt7oWqLHsgH-Xfld0Q62BspxNS-3eqvrqxFRezK_1hwR8EVQdYzOeU3PKVReoX2O_xEvIbOZZGRSW7fu82m0DMAVFzciqPqEKnV)

---

??? example "15 — :material-flash: Personal Energy Consumption Tracker"

    **Key Features:** Home energy monitoring · Consumption reduction tips · Cost calculation · Carbon footprint analysis

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for energy tracking and historical data access
    - :material-meter-electric-outline: **Energy Monitoring:** Track electricity and gas usage via utility bills or smart meters
    - :material-lightbulb-on-outline: **Reduction Tips:** Energy-saving recommendations based on usage patterns and history
    - :material-currency-usd: **Cost Calculation:** Compute energy costs from user-defined utility rates over time
    - :material-leaf: **Carbon Footprint Analysis:** Show environmental impact and suggest ways to reduce emissions

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, energy consumption data, and cost calculations in binary files
        - Create a text-based interface to input data, view energy usage, and receive tips and reports

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding energy-saving goals, historical consumption trends, and threshold-based alerts to help users make informed decisions about sustainability.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZPJDZjD03CVlUGgh9q3gAw1TTM6fH47AjjpDn9krcB6Z6KzGUNgcIH2iQASzZltx-pkvIuRHSl1D-i4oOCG0GKNTIzH0O35JqxnvznRMw_UmsY4Bx4Zogcd-USpEGw88zzbUI8mT6gkichFMcV-iFTU9bdfEHh4AFMPA1Zljg8fLIz-fi6c43qAnFy8XOL-YdJVFYgqCsU1WxDcEy826TNwZ8kGCjhg71nhPKQeBlZ7zX9RGh_SSw7g6UeDx7X8y5s5BNNOiFPI2KWx3igMwVuunvrm_uNZodnGt2ZSvcGR-HLsXd8P0qashkJL0jRMD9eCDUfVzSdF_cyxjh4MZT1cUvZ_vleuKuRI4ot2zG8pVLU2ZgWsHnU1Uq1yJb-GLtv-PZnnL0ecXXsqOqDaDigVSzvIc4V-TxUbPrrxlPRcxHqLVod_zN5AJmUjBgS3Ja3znCetiqCC-adGNs9OSyKZmHOsVbs_x0bfheVpBchkI9GV_6m00)

---

??? example "16 — :material-human-child: Kids' Activity Planner"

    **Key Features:** Educational resource integration · Activity and playdate scheduling · Development milestone tracking · Parental notes and reminders

    **Common Features:**

    - :material-account-key: **User Authentication:** Parent/caregiver accounts for personalized activity planning
    - :material-calendar-clock: **Activity Scheduling:** Calendar system for planning activities, playdates, and events with date, time, and location
    - :material-school-outline: **Educational Resources:** Integrated games, videos, and articles for child development
    - :material-chart-timeline-variant: **Milestone Tracker:** Monitor developmental progress in language, motor skills, and social interactions
    - :material-note-text-outline: **Parental Notes & Reminders:** Add notes, instructions, appointments, and important dates

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, activity schedules, milestone records, and notes in binary files
        - Create a text-based interface to plan activities, track milestones, and view reminders

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding a child-friendly interface, growth charts for development visualization, and sharing capabilities with family members or caregivers.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/bLLDYnin3BtxLuWvjOMGVr0sJNT3eIaXs_Okncf6h3y6MPwGVzzbParIIBqpH-czP-j9Cdv8YgBPEvDUEFGewC774DjEeYTGoMJCXqyVORNw3CqUES2UGcwCkGacy5SYWKtMZe9oYyenDEPkTk9_ZoUkCuvqugGaLT8kKrBOHqirMhtqvxOZcnt1fbKUM9aIOB1mS7Yng9GQCuCpdRL5Hy6YB91ih2sKoxg02_I_cSvGo1OU1mgwfEHgRnvjdXg93ewKOfPsxCVz_AJv8l6SQ3RzkKEPpwvM-vK6Sh7t51Jsx2Xf30G_1TkNSO_gU5cxDegzmZDaPGfNCCrKLTqSK2WeElWHzMrsH_8SB4bgJ1M-hjQ8hWimGrQyZF1wuOLLFzkEdLqz2LEmxWBVWfBymPOQyr-e-3ZHQjFt4djXrDjbULSwnjWg3mGxz7CaLHCxYMVjODkXQ6F-3M-wzvO_I3m9fNIVMRceF8iplsgUqBExhBSeTE9X74OTlZdX-leNgSh2pbNLhBn-8EQ1WitU_GK0)

---

??? example "17 — :material-briefcase-outline: Freelance Client Manager"

    **Key Features:** Client information storage · Project tracking and deadlines · Payment reminders · Communication log

    **Common Features:**

    - :material-account-key: **User Authentication:** Freelancer accounts for personalized client management and project tracking
    - :material-card-account-details-outline: **Client Information:** Store contact details, project history, and client preferences
    - :material-clipboard-check-outline: **Project Tracking:** Track ongoing projects with names, descriptions, deadlines, and progress status
    - :material-cash-clock: **Payment Reminders:** Notifications for upcoming payment deadlines based on project terms
    - :material-message-text-outline: **Communication Log:** Record emails, messages, and notes related to projects or clients

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, client data, project details, payment records, and communication logs in binary files
        - Create a text-based interface to manage client information, track projects, and set payment reminders

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding invoice generation, expense tracking, and exportable reports for financial analysis to help freelancers stay organized.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/bPJHQkim38Rl_HGYL-SSw2iSMhPI2YsCjjkzYRNKQomNMzdMjzyocs2MHMHN8Ry-YL_AJvQHTU3EjIfUB5rrq0wS9r-VWtS873fKwi-_lx1O_8Tghot17gchb2fUKl4nOe1LnsSajhLcwwbIFwg9t_d6oiG16niPWmXjEemCUszGmcJhTwr5OjZIim_kgmkEx8Dki59ICNMlZ844pQ7Nnl8ly2UBRncFStXnpuVWNx1cE0LTNomreCd0FMhJMi9-l0mK-sPmNajQUdvFiNacPtkJrzStXyHmG6V9OEYdZhN47f7XUqt0UxFot5avzCvrb8CBEzzKQgoLgIjh0fj2L1QZG8iMDqWOD2DiwTNRka_DM1jks68zV7U17Ea2AhfRlr-H8WuPgTH8AiwNIvDGkbkknGbiXYKV-XpqZpoa5CfJPH5EjojEaSDTaZo1YPu-_-TgYMGwrtu0)

---

??? example "18 — :material-book-open-page-variant: Personal Reading Challenge Tracker"

    **Key Features:** Reading goal setting · Book tracking · Reviews and ratings · Reading statistics

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for reading challenge tracking and community sharing
    - :material-flag-checkered: **Reading Goals:** Set yearly, monthly, or custom goals for number of books or reading time
    - :material-book-check-outline: **Book Tracking:** Log titles, authors, genres, and completion dates for books read
    - :material-star-half-full: **Reviews & Ratings:** Write and share book reviews and ratings with the community
    - :material-chart-bar: **Reading Statistics:** Charts showing books read, reading time, genres explored, and progress

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, reading goals, book records, reviews, and statistics in binary files
        - Create a text-based interface to set goals, log books, write reviews, and view reading statistics

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding a book recommendation system, group reading challenges with friends, and social media export for reading statistics.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/bPJTQiCm38Nl_HGcLzkWhp3wmoYC1KQx7tOf4f6O9jAGvPMz_KnCIs6jtLxgdEy8dGGyzmgYOHoCtpdw1e7HZapSzC8ZMfM0njmztDlPxD5MDJYoDLAeZ3aPaldkKUmYQ8-ahW5rJ9KvgoR-XJkN9pROEQyeMMWTqAkjkSKCLZXzYset2AsZpguP1b-PCrfYln1a-9sa35KpQL_6Li4hZoKgU_kRGBEpI-QTdyAL-IybSj6satYPU6gT5f0FX_kaNc2Atxe7mKZ_n4NU0hLs0neRntqPBsK_nUd1lmAF4wOw9JRrEjW5l5XnZOG2YiUVkTKOzkgQk3hhdjpoAjm9-XiI4HESl6AetE6P7HsZgnw60Qd3kFYYat9BuRt76nB9FQf5V2K8YbS5oZNZosRcI6qOXpy0)

---

??? example "19 — :material-sprout: Greenhouse Management Tool"

    **Key Features:** Plant growth monitoring · Watering and fertilization scheduling · Pest and disease logging · Climate control settings

    **Common Features:**

    - :material-account-key: **User Authentication:** Greenhouse manager accounts for personalized management and record-keeping
    - :material-chart-line: **Plant Growth Monitoring:** Track growth stages, health status, and observations for each plant type
    - :material-water-outline: **Watering & Fertilization:** Schedules based on plant type, soil moisture, and growth stage with notifications
    - :material-bug-outline: **Pest & Disease Log:** Document issues, treatments applied, and outcomes to identify recurring problems
    - :material-thermostat: **Climate Control:** Manage temperature, humidity, and ventilation settings with anomaly alerts

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, plant growth data, watering schedules, pest logs, and climate control settings in binary files
        - Create a text-based interface to monitor plant growth, set schedules, and record pest and disease occurrences

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding growth trend charts, plant-specific care guides, and data export for sharing with gardening communities.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/dPHDJiD038NtSmehAq2gk05grK9P455HAEiXOoKMyrDvF5HmUaej18ioH5t6_fxZzs9bciMobE1DVgMujMm3X1HJDt0A2C85ZJcxE8V9v0gQrbA45cDfZFdreCsdZ0op8WD6eSuAfTYOerNbxr9FTU81Uyg2N8MM1RD0cnpMiFhgROeaYM7bRHHOSjh9q9ZZPLLi27TQXuKLMuTdpi7zIqP-qusgyBKZ6KtNRJrRGQROWuqERf25F7qSXiAw6z0LZuqPHUcyDSe9GlLqab9pFarPJMQ5MGuZ5fJHPeJzCJRcUFdx5hl43hw8I-rYLQ2ULLK3rMBb_MFip3q5AmZp58MJXpMAKEnpO_vk_NpIHmnRP2k5SPpWjWHo9E_Zw0r68Q-t_9z0mnY_VYsL_L_BJ36w4lmd)

---

??? example "20 — :material-music-circle-outline: Music Festival Planner"

    **Key Features:** Band and artist management · Performance scheduling · Ticket sales tracking · Vendor and sponsor coordination

    **Common Features:**

    - :material-account-key: **User Authentication:** Festival organizer accounts for personalized planning and record-keeping
    - :material-account-music-outline: **Band & Artist Management:** Store band names, genres, contacts, and performance contracts
    - :material-calendar-clock: **Performance Scheduling:** Schedule performances, set stage times, and create festival lineups
    - :material-ticket-outline: **Ticket Sales Tracking:** Track tickets sold, revenue generated, and attendee demographics
    - :material-handshake-outline: **Vendor & Sponsor Coordination:** Manage vendor applications and track sponsor agreements

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, band/artist data, performance schedules, ticket sales records, and vendor/sponsor information in binary files
        - Create a text-based interface to manage festival details, schedule performances, and track ticket sales

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding marketing tools, a budget tracker, festival grounds map, and communication features for coordination with bands, vendors, and sponsors.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/bPJFRk8m4CRlVefHJhiheNs35InjkO1K0UK-YgTXH3oEnkFszGkO0mVgf8SSuldzHj-d_9adGxKSUfTEB0Cg1WXHOdlK60XCCpdtwyzlcCt-GRD15jYGvCQvkvioV4kaiCXs935kqJXAunwU5duTEwuJM-eu6MaLMcLA1flegORLe_z7yN2v5cfysOQ27GKIQrnjMEo5zt21_XPWYlBisNxg79W-xnrOZulZrNVjaNpkMJfu9NsF6b1QIetxTbAyfH8QmGsYCRuqdagNhY-KZ3-mFnSLoiEeLEsvv_P41ZliAS5UiJsnT8rxV7wVeaoUq724Nf6GeX5iQOXggOxNqnv8VDJhKzqDKL9KMCQedkNsNOqIPKzv5w30QR9sZLg0wLgzqlaVukOaFeV-2m00)

---

??? example "21 — :material-bus-clock: Public Transportation Scheduler"

    **Key Features:** Bus and train schedules · Route planning · Fare calculation · Delay and disruption alerts

    **Common Features:**

    - :material-account-key: **User Authentication:** User accounts for personalized transportation planning and tracking
    - :material-timetable: **Bus & Train Schedules:** Real-time or updated schedules with route search and departure times
    - :material-map-marker-path: **Route Planning:** Enter start and end points for efficient route suggestions with transfers
    - :material-cash-multiple: **Fare Calculation:** Compute fare based on routes, ticket types, and applicable discounts
    - :material-alert-outline: **Delay & Disruption Alerts:** Notifications about delays, disruptions, or service changes on planned routes

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, route data, fare information, and transportation alerts in binary files
        - Create a text-based interface to plan routes, calculate fares, and receive alerts

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding favorite routes, real-time vehicle tracking, and payment system integration for purchasing tickets or passes.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZPD1Ry8m38Nl-HLMJzia_i840yPbI8YotQFKQoCIfx8TJVpxKJeaj8t0iNxVKrvVdAcePSqn6DbxwYtR235HSXsdYA2SqPY7fqUOJ9wXMbjFi4RAbJ4N7wFuBiWmozeXgNTMVQBA_3iT-RVK-ZAnnTQB8XUXLKPHMAS6YrWn-aiMiDJ0ZWUXTXqsEQ1Kvgeo-cgqx3huJ0pRbFKEnuV7BrXWFvJE23iVRvlAsKy7moPO8azjPNuFHcf9YWprSDd78MoI9ospIsfkaYjK68KwjotAqBTSWuj_yMePOMw3o-6yENz6vyu3kfzZhrFpBYKP-dLxLDWT-WAx5FNHAi9HBs35_0iCzd2wmeKNpltWWrb0LgbCKRtOYxlvSfZZSpLJf2R7y0q0)

---

??? example "22 — :material-storefront: Local Farmer's Market Directory"

    **Key Features:** Local vendor & product listing · Seasonal produce guide · Price comparison · Market hours and locations

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for saving favorite vendors and tracking purchases
    - :material-store: **Vendor & Product Directory:** Browse local farmers, vendors, and their product listings with detailed profiles
    - :material-sprout: **Seasonal Produce Guide:** Highlights seasonal fruit and vegetable availability throughout the year
    - :material-scale-balance: **Price Comparison:** Compare prices for similar products across different vendors for informed purchasing
    - :material-clock-outline: **Market Hours & Locations:** View farmer's market hours, locations, and special events or promotions

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, vendor and product data, seasonal produce guides, and market information in binary files
        - Create a text-based interface to browse vendors, view produce guides, compare prices, and access market details

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding a map with directions to markets, user reviews and ratings for vendors and products, and shopping list creation based on selected produce to promote local farming and sustainable food choices.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/dPF1JZf13CRlynJDdlv_4rx1W1xmi9iGZTphReM6sIbfEnBVNiA08L56zBZzVb_tQzEPcgDw7cEm3QSjAeumIf9-hJ8Ik6OAuT_DVvXCRg7fa1Dqb78Jmjb74P-D56RPrvISUtIMr8HlguLlPSLruf5MR4vQXUQPpA6JWMfOFNhBvfnM8A_GIeyHbfG6KGDC0onKXjoxDU5AhlIyKzaPdOXVcfy8jL__yTULMKjhauJnQ2AOPpxixe9GN4kc7NmIipVaY2ySsTz_SbR3B9Jt-BsCMrIsmy5yAPrYH-hzDEbbhXG83h9TvggnEjGDEJn8FYwubN9C-uOryMnNXRgAhqNPFxqmfJJaCNu0)

---

??? example "23 — :material-music-box-multiple: Personal Music Library Organizer"

    **Key Features:** Music collection cataloging · Playlist creation & management · Metadata editing · Preference-based recommendations

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for music library organization, playlists, and recommendations
    - :material-album: **Music Cataloging:** Catalog songs, albums, and artists by importing music files or entering details manually
    - :material-playlist-music: **Playlist Management:** Create and manage themed playlists, add songs from catalog, and reorder tracks
    - :material-tag-edit-outline: **Metadata Editing:** Edit and update artist names, album titles, and genres for accurate organization
    - :material-thumb-up-outline: **Music Recommendations:** Suggestion engine based on listening history and preferences for discovering new music

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, music library data, playlist information, metadata changes, and recommendation history in binary files
        - Create a text-based interface to catalog music, create playlists, edit metadata, and receive recommendations

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding album art display, song rating, online music database integration for automatic metadata retrieval, and sharing options for playlists and recommendations with friends or social media.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/dPBHIeCn38Nl-nJXlzh6zWgoCMGt_Z0cUf-ryL-nJQLD4TzUjg28IErssPpl7993rqKmIut1b5VFRvWnGaoSx2cdI22vaZ6pnHnMgpiOHlGC8t4TZFdnKF6fK8PDbHEnU8lY4m_cpwdo3sdoRU90aoz2kGdjAXM1CJbgOitLjoWOpkjCCDRYBMnJ26JrXAQg_erpy9XuAjtq9Xnhl0BFuejqyUrBzm4_WYy2sqoNUW3PmOYC4qLY6Sp_Y2PTL88lkj-eWszZwNSsBnj9qA4Wt3ilvtfcMjfIkrx2ZZZJV33TfEQU8L3W9gyADrbrqc_jgKa_pO5iYf7OQTj7BEGWCUmplL0cjbG6SmMhsS-Utd_XryUqzvyjvcPDx6eCdm00)

---

??? example "24 — :material-bookshelf: Virtual Bookshelf Organizer"

    **Key Features:** Digital book cataloging · Book lending & return tracking · Wish list management · Reading history-based recommendations

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for bookshelf organization, lending, and recommendations
    - :material-book-open-page-variant: **Digital Cataloging:** Catalog personal book collections with titles, authors, genres, and cover images; ISBN scanning supported
    - :material-book-arrow-right: **Lending & Return Tracking:** Track book loans to friends or family with due dates and return notifications
    - :material-heart-outline: **Wish List Management:** Create and manage wish lists of desired books, marking them as acquired when purchased or borrowed
    - :material-star-outline: **Book Recommendations:** Suggestion engine based on reading history and preferences for discovering new titles and authors

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, book catalog data, lending and return records, wish lists, and recommendation history in binary files
        - Create a text-based interface to catalog books, manage lending, handle wish lists, and receive book recommendations

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding book rating and reviewing, search by various criteria, online database import for book details, and sharing options for recommendations with friends or social media.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/dPJF3jCm3CRlUGgh9q3QAw2D3S5XbT34dxDflCvgOqFYW7XxsaNL49f2wQLIxT_tnR6jx9DXj1n6bmQMRnWnG53HxXOr45ZCvDoBLozXjtiDJOiiq9Banha_VahoKw88Xsmt4kCEZLKQzpHQ-BFsN2Skr7CoYbNeNQPaqAgdAbOj_SGz6uvmnEaxbzM3Nk43nQGoXT-e3d3KSQIktEf_1UMKW_VtJDfgy9dfvwBOwl7MivNKYGnvt5pCYKOokYStMjHdSfUSIVpiXUBXGfQZmCU8tJ356lTlP5cZJgE7GYlADcKXLclBi1RiFIVJ-6kzkjgQBvnkSEPvxL6mfq1YZNiUVgoUAIn4bIwbhsLRZ0DWWaFtFNCaNwSl5FG7mJLgM770oYMPfnGcXKTZbGHVCP47bIc1Jxg_ddzqus_D9glwNQP7rkr9V0xZRm00)

---

??? example "25 — :material-family-tree: Basic Genealogy Tracker"

    **Key Features:** Family tree creation & editing · Family history record keeping · Birthday & anniversary reminders · GEDCOM import/export

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for genealogy tracking and record-keeping
    - :material-sitemap-outline: **Family Tree Management:** Create and edit family trees with members, relationships, birth and death dates
    - :material-notebook-outline: **Family History Records:** Record stories, photos, documents, and historical records related to family members
    - :material-bell-ring-outline: **Reminders:** Notification system for upcoming family birthdays and anniversaries with configurable alerts
    - :material-file-import-outline: **GEDCOM Import/Export:** Support for GEDCOM file format to share genealogical data with other software and users

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, family tree data, family history records, reminder settings, and GEDCOM files in binary or text format
        - Create a text-based interface to create and edit family trees, add history records, manage reminders, and import/export GEDCOM files

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding a family member search function, charts and visual representations of family trees, and the ability to generate reports or family history books to preserve genealogical information.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/dP3TJW8n48Nl-nIJlG8IuXiO41SqkZ71d_k6ZZ1nsvBfhCBRosvZH6DgqwjcUiutFsSMHBDqjbNXZTnUixPWlVER7NkB8DoXKgF96ARJIwWQJGuQT5sbr5aHnUU03FDETkY4Dbh8kqhz-HlzztvBQSSQjnG4EMbQTHW46cymPKklljIMsYCyCI8i60SEj3DG6n9osqhzRucT1WdXp9tFzbKP-KBuKKOcOrdZnhE1EyJzYO1Nprz3RYY8vsEbCZonrzoONm94DBj7FBDyW_xmyXLoyheYbftHno7dkNFqZXpqCCQICwUYKZccsEqHfGYCCHMHoUjkxTwpNDI7_e5LVRruQ619BVOZ4k8t_LF9eUf32PKwOpGPgnawqzds4m00)

---

??? example "26 — :material-hand-heart: Volunteer Event Coordinator"

    **Key Features:** Event creation & scheduling · Volunteer registration & assignment · Hours & contribution tracking · Communication platform

    **Common Features:**

    - :material-account-key: **User Authentication:** Accounts for organizers and volunteers for personalized event coordination and participation
    - :material-calendar-plus: **Event Creation & Scheduling:** Create and schedule volunteer events with details, dates, times, locations, and roles needed
    - :material-account-group: **Volunteer Registration & Assignment:** Register for events and assign volunteers to specific roles or tasks within events
    - :material-clock-check-outline: **Hours & Contribution Tracking:** Track volunteer hours and contributions; volunteers can log their hours and work details
    - :material-message-text-outline: **Communication Platform:** Send updates, reminders, and alerts to registered volunteers about event changes or important information

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, event data, volunteer registrations, hours logged, and communication records in binary files
        - Create a text-based interface to create events, manage volunteer registrations, track hours, and send alerts

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding event performance reports, volunteer performance recognition, and a volunteer rating system to facilitate efficient communication and coordination between organizers and volunteers.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/fPJ1JiCm38RlUGgh9q3QAw1DqmG79Y76TW-DrqPhdCbnuFL1IQ4JGg7HZel__ulzE-ioY6Q9Rb3XPEciMJjmddpRitS8mX6LkhcxXSNY7fgjjWHRfDWeTV4Z5_S16LPHUYInhHRhgL5_dcRza-ziMV62dGs2N1GzH0m2Ms-mA2ksldb74bWp9WTeCh1hUpHniDGrgbmV6QcAaAGpBHjZfSfmiFXHPIXEVV13945ao47p15g5O3jo-Q9PkbyhWwFdt6aT88_uJK6J6Q4IaW_WnrBfByVsw2E7v5jx4hPlCO5UMRUd_5p-KKpl7wQcacUkDwSqcI-bio7bATVUkKZZVmYU1or7powjTNysMZ3pLmDoMa-TFj-nGneNF6gk0KrnNDDKEPMl3w5Q8fdeXay0)

---

??? example "27 — :material-finance: Personal Finance Advisor"

    **Key Features:** Budget planning & tracking · Investment portfolio management · Financial goal setting · Debt reduction strategies

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for financial planning, investment management, and goal tracking
    - :material-calculator-variant-outline: **Budget Planning & Tracking:** Create budgets, categorize expenses, track income and expenditures with spending limit alerts
    - :material-chart-line: **Investment Portfolio Management:** Track investments, view portfolio performance, and receive insights and recommendations
    - :material-target: **Financial Goal Setting:** Set goals like saving for a home, retirement, or vacation with progress tracking and suggestions
    - :material-trending-down: **Debt Reduction Strategies:** Create payoff plans, track debts, and optimize repayment strategies with progress visualization

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, budget data, investment portfolio information, goal progress, and debt reduction strategies in binary files
        - Create a text-based interface to plan budgets, manage investments, set goals, and track debt reduction

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding expense analysis, investment risk assessment, bank account sync for automatic tracking, and educational resources on financial planning and investment strategies.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/bPHFQ_Cm3CRl_XGYf_STz2kCTdzM1WkKTjjTYzLKD967BM_isy-j3sbXyznBG7f-Zv0KuNbGz1h7mOGzopjw765quhgTToE1-aZ6_Blw3xFPDJGjia1B4XjZJe8ilWJoi8Yw8r7kKDb9OtxCPlx9zLmcrjHpKF95Q1afABJEKWahjtuJRKyAgm55M7f0iV3iiTkde35bELUuzOHAaCawoxrblSZmolHPPwYP-L4-AEX8aceuhriti8CM1Niw91lp9p5rDUdLjeMrC96rjhoqottbLJome7IC0onT-cn8DV_uNuJiIj4n7SfWxhcApBiiapNptD6Rmffix9ITm4OzAlLCgMf1EsdX09NXiwjVuPVRRe-dM64w7YcSKKLRnSJfWJ9p4Xl7uHi0)

---

??? example "28 — :material-dumbbell: Custom Workout Routine Planner"

    **Key Features:** Personalized workout creation · Exercise demonstration library · Progress tracking & reporting · Injury prevention tips

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for workout routines, progress tracking, and injury prevention tips
    - :material-weight-lifter: **Personalized Workout Creation:** Create routines based on fitness goals, preferences, and equipment; select exercises, set reps and sets
    - :material-play-circle-outline: **Exercise Demonstration Library:** Library of exercise demonstrations with instructions for safe and effective performance
    - :material-chart-bar: **Progress Tracking & Reporting:** Record sets, reps, and weights lifted with reports and visualizations showing progress over time
    - :material-shield-check-outline: **Injury Prevention & Recovery:** Tips on injury prevention, warm-up/cool-down techniques, and recovery strategies

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, workout routines, exercise data, progress records, and injury prevention information in binary files
        - Create a text-based interface to create workouts, track progress, access exercise demonstrations, and read injury prevention tips

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding workout scheduling, nutrition tracking, fitness goals and milestones, proper form guidance, and adaptive workout recommendations based on user progress.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/dPJDJiCm383lUGgh9q3QAw3nCqr8cuIc0UVGMfrPQq-Eip6UdcebQ0VMPHmTVvyTkrN6mRnQR1iNriGRhxw5LbZAbKgBO1hHkQkRQnYDRg6OUsAO8yV2kQEWJxu4LBYBja8sAhsHSE7-FEtvcTGqJ2omfc2eWz0qOZ2OIuKdi8ohFwC6OT_G5rRm9hgMQF2Wc8OOogS6Xn1JDbEQL6GNAg-4ksmbO_R99sf90U4HM-5WUZ1XHk_gTL-uSq1VvLvbro693LbUckQ8D4zDZfpnJLLgnH1WgRvS4zVWkO85RaIjYmftbaW_OIdwkzeyBGsMq6nhYepg3NyIEMB6Afxu8-g-kmrkaGzxx4kKiiNkV4cRhb4MTQBUHVO_-cOCsRrHRenSnRRv1W00)

---

??? example "29 — :material-library: Local Library Search Tool"

    **Key Features:** Catalog search for books, movies & music · Reservation & renewal system · Event & workshop schedule · Library locations & hours

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for saving favorite books, managing reservations, and receiving event notifications
    - :material-magnify: **Catalog Search:** Search for books, movies, and music by title, author, genre, or other relevant criteria
    - :material-calendar-check: **Reservation & Renewal:** Reserve library materials and renew borrowed items with due date notifications
    - :material-calendar-star: **Event & Workshop Schedule:** View upcoming library events, workshops, and programs with registration and reminders
    - :material-map-marker-outline: **Library Locations & Hours:** Browse library addresses, hours of operation, and contact details to find nearest branches

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, catalog data, reservation records, event schedules, and library location information in binary files
        - Create a text-based interface to search the catalog, manage reservations, view event schedules, and access library location details

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding user reviews and ratings, e-book lending integration, a virtual library card, directions to locations, and reading recommendations based on user interests.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZPD1IyGm48Nl-HL3JYhiNv1L4HNQInVr7DEn3MqooqnIyT-xj8X1TfiUPzxtmdiXsKiq79CVbGmk70qR3vu2sPx98qHEgDJLpJNiThUWQ-C2r1YILYeR5l5LaE4knHv3TDP4Hq6hizk5hwXpwqI3dPE8l0ez9PG8DRMuWXMYFvXeHkhWW8PjhzN_nK8j0zmJ3L9WQfeS5g4apfvXodaR5EHfhWScjD1Wm2ypQdLHoNq8Bn6z5EbvsiY-kHEQ8GL7gU3ZX27EfdVYGNewmi7ssAOHjLfJypWp9aK-VsdmIRpHLovHkGys_0qLsRzBUAR4ejMAc5VuLRSxokTlY7xwy6gFeKr-_040)

---

??? example "30 — :material-tent: Camping and Hiking Trip Planner"

    **Key Features:** Trail database & recommendations · Gear checklist management · Weather forecasts & alerts · Emergency contact storage

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for saving favorite trails, managing gear lists, and accessing weather forecasts
    - :material-hiking: **Trail Database & Recommendations:** Browse trails by difficulty, length, elevation gain, and ratings with location-based recommendations
    - :material-checkbox-marked-outline: **Gear Checklist Management:** Pre-made checklists for camping, backpacking, and day hikes with customization and saving options
    - :material-weather-partly-cloudy: **Weather Forecasts & Alerts:** Current conditions, forecasts, and alerts for selected hiking locations with notifications
    - :material-phone-alert: **Emergency Contact Storage:** Store names, phone numbers, and medical information accessible during trip emergencies

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, trail data, gear checklists, weather forecasts, and emergency contact information in binary files
        - Create a text-based interface to search trails, manage gear lists, check weather forecasts, and access emergency contacts

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding GPS tracking, trail maps, wildlife and plant identification guides, community forums for sharing experiences, and safety and outdoor ethics guidelines.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/fPJBQiCm44Nt-eh1gxQGNoW9RXeAyIRfOpsLfhQ89PNHYDA_Rv0JI1y8cIxjEUTQSp5QHa6Mv4OL3zQ_8wC35tpG0mT789n8gQkRQrWiRg7fq7heoAT6gOk7QVWKYM6LP20lLgFOu1lrvzk9tuRUbeaTzJOASH7Q98e2NJ1Kmif7VsIq8zoXu2j60lG6TgI3S-HD3ecDge6cj3qXwm4oFqjyjlG19vfahft1_voxMD66aA4TI1z66oMdTEYn9qTU6bMUN-xt7OoLQik4Gj2cAC5D6TiZN6clZPNPKh6E5q8PYE4-C6cC4dF6QYJEAnN7KqB-EhI9cRp0IQXJSuy_kJfrJt8sPvssMCRQ4VVazIUqmGjgWGV_5jYTxe3o_7AdlGGcSwPYfReo1dvnTMx-C-RB5QKSxqor96-I6xy0)

---

??? example "31 — :material-weather-cloudy: Simple Weather Station"

    **Key Features:** Local weather updates · Temperature, humidity & wind tracking · Severe weather alerts · Historical data analysis

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for weather tracking and historical data access
    - :material-weather-partly-cloudy: **Local Weather Updates:** Real-time weather updates including current conditions, forecasts, and radar imagery via APIs
    - :material-thermometer: **Environmental Tracking:** Track temperature, humidity, and wind speed with historical trends and current readings
    - :material-alert-outline: **Severe Weather Alerts:** Alerts and warnings for storms, hurricanes, or extreme temperatures from official sources
    - :material-chart-timeline-variant: **Historical Data Analysis:** Access historical weather data with reports and visualizations for trend analysis

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, weather data, historical data, and alert records in binary files
        - Create a text-based interface to display weather updates, track environmental data, receive alerts, and access historical analysis

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding customizable weather widgets, personalized forecasts, location-based weather maps, and educational content on weather phenomena and climate science.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZPJBpjem48NtVefHLcsb_nMg7_K2bM33fQn7oL4O4O-ZyPYAjozjHCMYc2mzytt9EOdaz-niNk8GybbqPED8CMbgJvOYoAqWX0-VFjBRssTgDYnA6sXfGdWuJCj3Xj6Y-0dgqh9BqYRyTphnwzHBdTYYb-omAhGio4wRrA66rQElKyi37S4tqUWmTkp8JNWodvmlnGpgz-qB-ZxzdWmjPt-1RxbZen-HUzYrpjQRVTEBMD88THxeAplJthWzY_PDg6ud_wVW5-qHHnXxCSoNLYLA9twTRnn5EzgDG3VBgVRUuGB3lo-s662Uc_1aFZb_3xI3ksXVXwV4ynvRpRcIxCdauUzENHVAmpLBRi9Bv9w-RP4pFT1_gFd-4WfZ1sqn9lFvRwuMl5qTuHtQbJZy1W00)

---

??? example "32 — :material-chef-hat: Culinary Technique Tutorial"

    **Key Features:** Step-by-step cooking & baking techniques · Ingredient substitution guide · Utensil & equipment reference · Recipe improvisation tips

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for saving favorite techniques, accessing tips, and receiving updates
    - :material-food-variant: **Step-by-Step Cooking & Baking Techniques:** Library of culinary techniques with detailed instructions and visual aids for chopping, sauteing, baking, and more
    - :material-swap-horizontal: **Ingredient Substitution Guide:** Suggests ingredient substitutions for common and uncommon ingredients based on availability or dietary preferences
    - :material-silverware-fork-knife: **Utensil & Equipment Reference:** Reference section with information on cooking utensils and equipment including uses, care, and maintenance
    - :material-lightbulb-on-outline: **Recipe Improvisation Tips:** Tips and suggestions for improvising recipes, adjusting flavors, and creating new dishes from existing ones

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, technique data, substitution guides, utensil references, and improvisation tips in binary files
        - Create a text-based interface to browse techniques, access substitution guides, reference utensils, and get improvisation tips

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding recipe collections, a cooking timer, a meal planning tool, and interactive quizzes to reinforce culinary knowledge and empower users to become more skilled and creative in the kitchen.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/dPJTJiCm38NlynHMhm2fhu0s1aoJ61VxUO2izLgpnkbYPuYtfrN500b2nrNadFDPFb6IYQWD6brZv43Ssc0TEC--g8Dt21eY6dDrSmrvVWlPqXB34Zbcndmf1d4h660QjKPMAgoIvypyk3ludtn5QSSAAnB5a3JD8uh2qfUOj2L7Noks-UujxrVOO54p7IDAPdvL1cxc_O6ukWIvivSIoSaNN0Ki2LbX7NUYfB4F3VD8_O6azNCAvpp3frL6Wbik_ekcRv8Y2pLWkOI7OwJMzRrMkCU0N7In_dHywoEZWRCobaZds50hiFUXemfg4HQk3Vv4yl4sqlhGux6nfyunBL-YQ3-2Z0Hd0RkIAtZ6LxWdgN4ScSxKVGLcWbn6rxm3)

---

??? example "33 — :material-chart-line: Basic Stock Market Tracker"

    **Key Features:** Stock price monitoring · Portfolio management · News & market trend analysis · Personalized alerts for stock movement

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for stock portfolio management, alerts, and news access
    - :material-finance: **Stock Price Monitoring:** Real-time or delayed stock price updates via market APIs with search and tracking for individual stocks
    - :material-briefcase-outline: **Portfolio Management:** Tools to create and manage stock portfolios with add, edit, remove, and performance viewing capabilities
    - :material-newspaper-variant-outline: **News & Market Trend Analysis:** Access to financial news, market analysis reports, and charts displaying market trends
    - :material-bell-ring-outline: **Personalized Stock Alerts:** Custom alerts for specific stock price movements such as price levels or percentage changes with notifications

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, stock portfolio data, stock price history, news articles, and alert settings in binary files
        - Create a text-based interface to monitor stock prices, manage portfolios, access news and analysis, and set alerts

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding stock performance analysis tools, historical price charting, integration with financial data providers, and educational content on stock market basics and investment strategies.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZTD1IyDG30Vm-_iKeISLzXLaOw0NAqEdz_2QjQ5zYUIb3Ftq5ZkcX-tPO__v_I6bwJexcezf2Bbd-K3314b5OsUQ2Dn62k7cxXPMgtkeQcI1ccIiGlZpC0_VCXbiHkz8d2CwgrJXOZhx9ssvB5wevUnaHVGuKdQejQ4IAw_-wXfxs1b7Wbg5NOsbhSArmQb5QB637_EVtHl6VX4jRxfJyuCEh52ZO4k9nAjmEPuRMoDqWhCewpbOgj-PZhyMTcG7jOGIgTmhlk8p7JEWD5FDUdBO20wVcNCLhe_cvWCBIcGSO88B_2dT6qcpY9S-35dMoVCNDR0Po3nNuL9wFXwV3s0n9eiaZYt1jaDf5nLBAqz_TLYJD6CQlW40)

---

??? example "34 — :material-meditation: Personal Mindfulness and Meditation Guide"

    **Key Features:** Guided meditation sessions · Mindfulness exercises · Mood & stress level tracking · Customizable meditation timer

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for saving meditation progress, tracking mood, and accessing recommendations
    - :material-head-heart-outline: **Guided Meditation Sessions:** Library of guided sessions led by experienced instructors with themes like relaxation, focus, or stress reduction
    - :material-yoga: **Mindfulness Exercises:** Mindfulness exercises and practices for cultivating awareness in daily life, brief and integrated into routines
    - :material-emoticon-outline: **Mood & Stress Tracking:** Tools to track mood and stress levels over time, recording emotional states before and after sessions
    - :material-timer-outline: **Customizable Meditation Timer:** Set meditation duration with options like interval chimes, background sounds, and visual cues

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, meditation session data, mood and stress records, and timer settings in binary files
        - Create a text-based interface to access guided sessions, practice mindfulness exercises, track mood, and use the meditation timer

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding progress tracking, meditation history analysis, wearable device integration for physiological data, and educational content on mindfulness concepts and their benefits.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/dLJDJiCm3BxdAQAU06clW0OR3AJrietTGsCwYpH1ZZqsdfwqPTAGfj1oQLL_F_wSf9b6DiJIE1NVqNyOCerkWW_LZa83cab0gQkRQpsPtEgYDEXr2LuAfSu-Ul0b0kaxuHruniem1b-eYzMUlmmrvXahg34oK9Qq48Yios0XHyj7NmXQiAbYaJkHNaECwHqBbGDFCPqvTCWGzcEeP93jcYaLz0giL0FDyrEMwEsRED-wFXo0AepG9hbSxpLpW-weLq4OFUIvbm9dwFci6p9LEf2rBaEmsdYhVzoNi0UNKl_9EErD5SXsdB6Q_pNS8dpsZ2UC7EWuJlaB66kHNzAP9BC6lyohW_CZjC6c_SNo-6cBWVLSg0D6I9uz0-sDqxDTGk88vNqQkwOWxS3jSunsYr7QdZAy8nlYCt2WBBViwUvKK_1M6lSD)

---

??? example "35 — :material-book-open-variant: Comic Book Collection Manager"

    **Key Features:** Cataloging comic book collection · Wishlist & trade list management · Value estimation based on market trends · Comic book events & conventions info

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for managing comic book collections, wishlists, and trade lists
    - :material-bookshelf: **Cataloging Comic Book Collection:** System for cataloging collections with title, issue number, condition, and cover art, organized by series or publisher
    - :material-format-list-checks: **Wishlist & Trade List Management:** Create and manage wishlists for desired comics and trade lists for available ones, tracking series completion progress
    - :material-currency-usd: **Value Estimation:** Integration with market databases or pricing guides to provide estimated values based on market trends and conditions
    - :material-calendar-star: **Events & Conventions Info:** Information about upcoming comic book events, conventions, signings, and releases for planning attendance

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, comic book collection data, wishlist, trade list, value estimations, and event information in binary files
        - Create a text-based interface to catalog comic books, manage wishlists and trade lists, access value estimations, and view event details

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding comic book cover scanning with image recognition, social sharing of collections, a grading guide, and access to online marketplaces for buying, selling, and trading comics.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/fPJ1Ji9048Rl-nIJdjI4Lp0G5692Bw3UntPi9xApPcSNvEqjjK1DpARKsxB_ztzi9zjEDQ0FqMwDVh1yeaSBreahQkyiGV2HZBcvkuN9v1wo75aW9ucPCRqVF-6BaeTP33L9u083EydCNxiTlt8LfuarLQo1V19QHj80kIifYIMFlc8DB1Ky1R8Axyx3urTHerG4pNh9Ey8z7Z8p5BnOM_8N-1_B-BFaADW4NKUlhoUcjo7qHGqe9Inu6yZ38afnd7shRVyWCyEmld5ikylV3Z3ttAmOh-eFkELhR4TtbArfnxI7pK4RGsRIUUTeOkg7nyx0wkk9xq3jaJhGaNo-yoMOLPx8aeJManErphbplcGvlTrNq9t_WRM8gfVBzapUwYZLUOjEr2XDQXpDbyzCISfejzy0)

---

??? example "36 — :material-swap-horizontal-bold: Second-hand Goods Exchange Platform"

    **Key Features:** Listing items for exchange or giveaway · Search & filter for items · User rating & review system · Exchange agreement & meeting coordination

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for managing exchange listings, tracking reviews, and coordinating exchanges
    - :material-tag-outline: **Item Listings:** System for listing items for exchange or giveaway with details, photos, and exchange preferences
    - :material-magnify: **Search & Filter:** Search and filter options by location, item type, and other criteria to find specific items or browse categories
    - :material-star-outline: **Rating & Review System:** Rate and review exchange partners to build trust within the community and encourage responsible exchanges
    - :material-handshake-outline: **Exchange Coordination:** Tools for discussing exchange terms, agreeing upon conditions, and coordinating meeting times and locations securely

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, exchange listings, reviews, and exchange agreements in binary files
        - Create a text-based interface to list items, search for items, manage ratings and reviews, and coordinate exchanges

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding messaging and chat functionality, item verification mechanisms, and a reputation system based on successful exchanges to promote a safe and friendly environment.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/fPJ1JiCm38RlUGgh9q3QAw0L2NPX4XgmUzHOhRN5gHm7sDjJhKIA46mP71j__k8_RRECQaMJtvku8nwjM0y-SEW62Hv19Q4nLpVNi5ZSGhEsnB16JeqnnSDSV8qeq2OTa9KwgnIuCJ-kplnZwEaqiS6UegASX5O9ey8wEFm5gnZz0T4Hzs3PJSlkgnEUkm5TscDip7aaRyEsHyXqBbygA8aDUcA7KZD5JTWjAWgnbGEqunY8rIChl8ZjTfExCMU9sPCtXGalkMh1bl3zUtEiwAx8-8GAznye7KKy7jPAGl1JStcQDLJEMfARa9GOBnRaOdvTzOQgl7SaEZXxE6fQ5yRZfz4oqzjaiN9eJ2MN_xukY8EYvK-6_yrI4NwwdSmIsIM__mG0)

---

??? example "37 — :material-translate: Basic Language Translator"

    **Key Features:** Text input & translation · Language learning tips · Common phrase library · Pronunciation guide

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for saving translation history, accessing learning resources, and customizing preferences
    - :material-translate: **Text Input & Translation:** Text input interface for entering text and receiving translations in a chosen target language via translation APIs
    - :material-school-outline: **Language Learning Tips:** Tips and resources including grammar lessons, vocabulary building exercises, and cultural insights
    - :material-book-open-page-variant: **Common Phrase Library:** Library of common phrases and expressions in different languages for everyday communication
    - :material-microphone-outline: **Pronunciation Guide:** Pronunciation guide with audio samples to help users correctly pronounce words and phrases

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, translation history, language learning resources, phrase library data, and pronunciation guides in binary files
        - Create a text-based interface to input text, receive translations, access language learning tips, and practice pronunciation

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding language detection, language quizzes, interactive exercises, and access to online learning courses and forums for language enthusiasts to connect and practice.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/bLFDJiCm3BxdAQoU06clWCPs64YTDA71tMkjrg9r9YSMxEt9sY9nAEauvlk9_SNEEaJKQ7tdmWVB6HLxwBtugbNV4qGrSkxkuHvMgqSeziW2Un8hdFjr6CctG0fF5bkIo1L6zbAuMNJKbxxXlEAL6WwHD2lQ6OK8UrzJLfPj_KXV4LxaR15GQZWgIkX-4cJ8oJqX15VfVnm9WUSKqUlb5bisGudI63O49Q4AImD7FeTqxH_4z7ffu3rguaBmiYX_znMUh4EzmCQugnEzPDbQ5qsnIya2DsrSh95DkVLzMayuj8eXsVcaEBpTF3vwDke_4pXoOK4yT7IZDjlfGRsOL3nEUMSyBEWSEXM-hheCapyeLcbzy_fSyVHXtPgaJl_w6m00)

---

??? example "38 — :material-paw: Pet Care Reminder System"

    **Key Features:** Feeding & medication schedules · Veterinary appointment tracking · Exercise & grooming reminders · Pet birthday & adoption celebrations

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for pet care reminders, medical records tracking, and preference settings
    - :material-food-drumstick-outline: **Feeding & Medication Schedules:** Create and manage feeding schedules with meal times and portions, plus medication reminders with dosage instructions
    - :material-hospital-box-outline: **Veterinary Appointment Tracking:** Calendar or appointment system for scheduling and tracking vet visits, vaccinations, and check-ups
    - :material-run: **Exercise & Grooming Reminders:** Tools to set exercise and grooming routines with reminders for walks, playtime, and grooming sessions
    - :material-cake-variant-outline: **Birthday & Anniversary Celebrations:** Record and celebrate pet birthdays and adoption anniversaries with reminders and customizable celebrations

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, pet care schedules, veterinary appointment data, exercise and grooming reminders, and celebration records in binary files
        - Create a text-based interface to manage pet care schedules, track appointments, set reminders, and celebrate pet milestones

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding pet health record keeping, behavior tracking, integration with pet supply stores, and educational content on pet care, nutrition, and training tips.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/fPJ1JiCm38RlUGgh9q3QAw0L2NPX4XgmUzHOhRN5gHm7sDjJhKIA46mP71j__k8_RRECQaMJtvku8nwjM0y-SEW62Hv19Q4nLpVNi5ZSGhEsnB16JeqnnSDSV8qeq2OTa9KwgnIuCJ-kplnZwEaqiS6UegASX5O9ey8wEFm5gnZz0T4Hzs3PJSlkgnEUkm5TscDip7aaRyEsHyXqBbygA8aDUcA7KZD5JTWjAWgnbGEqunY8rIChl8ZjTfExCMU9sPCtXGalkMh1bl3zUtEiwAx8-8GAznye7KKy7jPAGl1JStcQDLJEMfARa9GOBnRaOdvTzOQgl7SaEZXxE6fQ5yRZfz4oqzjaiN9eJ2MN_xukY8EYvK-6_yrI4NwwdSmIsIM__mG0)

---

??? example "39 — :material-flower-outline: Indoor Plant Care Guide"

    **Key Features:** Plant species information · Watering & fertilization schedule · Sunlight & temperature requirements · Pest & disease management tips

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for plant care information, tracking indoor plants, and setting reminders
    - :material-sprout: **Plant Species Information:** Database of indoor plant species with common names, scientific names, growth habits, and care requirements
    - :material-water-outline: **Watering & Fertilization Schedule:** Customized watering and fertilization schedules based on plant type with adjustable frequency and quantity
    - :material-white-balance-sunny: **Sunlight & Temperature Requirements:** Guidance on sunlight and temperature preferences for various indoor plant species and ideal conditions
    - :material-bug-outline: **Pest & Disease Management:** Advice on identifying and managing common pests and diseases that affect indoor plants

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, plant species data, watering and fertilization schedules, sunlight and temperature requirements, and pest management tips in binary files
        - Create a text-based interface to access plant care information, set schedules, receive reminders, and access pest management tips

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding photo uploads for plant identification, a plant care journal, integration with local weather data, and educational content on indoor gardening techniques.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/fP9HIyCm4CVVyocEFcs3VGMP8ehWGDQfpw7zsnusIRrS6FZfnGMA0wcHFYRt-_rp5suNrOecDvZupdwqOXsvu4FJIt0WbGHZLfirRRTNLEqjUzh3fygO7uTSV8uGkaxQmoitLZduolnwc_d7qF4ySK37KI6pq7r2LDg75dFOVEjFW_LAzOY64Ud1lmLnMJOpjUpUI3X5q0LMeDMmqlH5Ml4OhrOXx3kolgKxYFB0dsURwgP7cmPKfeIQ7lEs0qt2af15xHJCNYS_SDVhEUO8DqAi9W4Ty95OuE0rLgQGovalZ1DDmjAWvKqLB39Y3hdb21j1-VU_VJho62lp9tAnrl-KybANIWK3hJPhiuDlank-0000)

---

??? example "40 — :material-bicycle: Bicycle Maintenance and Route Planner"

    **Key Features:** Bicycle maintenance log · Cycling route planning & tracking · Performance statistics (speed, distance) · Gear & equipment checklist

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for maintenance records, saved routes, and cycling performance tracking
    - :material-wrench-outline: **Bicycle Maintenance Log:** Record and track maintenance activities like tire changes, brake adjustments, and oiling with reminders for upcoming tasks
    - :material-map-marker-path: **Cycling Route Planning & Tracking:** Plan routes by entering addresses or selecting points of interest, and track progress during rides using GPS data
    - :material-speedometer: **Performance Statistics:** Real-time and historical statistics including speed, distance, elevation, and cycling time with goal setting
    - :material-format-list-checks: **Gear & Equipment Checklist:** Create and manage checklists for ride gear including helmets, water bottles, spare tubes, and more

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, maintenance logs, route data, performance statistics, and gear checklists in binary files
        - Create a text-based interface to record maintenance, plan and track routes, view performance data, and manage gear checklists

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding weather forecasts for route planning, integration with cycling tracking devices, social sharing of routes and achievements, and educational content on maintenance best practices and safety tips.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/dLJ1IWCn4BtdAuQULEWlIBL8FRXGjkfzo8xRq6HI9mdIlpVPjL31PbkFC--zOURnaaM8A34vgyBEy1u57JZFNc_5Eu8eYPIwkRk5-VmUPWqQXeOupPGwAKRmBP30GufRucWqHkDvflxiZlmNtviwewNUX4XI9QqIXGYDxwXAgwx-QFH1MuBIZyJ8cY2lbmUUGuuBQY_TAJWfUJVqzGjwpYTwEKmADnH_8Iqvmnr9c5HMJrzceU4UMfyYmTeYSocHEtWLr3aKnOPBMEEyWesqgsH32QtfhfCDRXRLYOsNgwl6h4a-lBZ1oqtCKGevKSNiCy0n4MZdqRWHcgIFM-Uasilu9GJ7-rOUxQIWUkAAK0PVdZwJsRlyPc2v9RspUKGUN8L_qYE4sUGhLGqozlzGdJyb_r1gGTpbZ-mR)

---

??? example "41 — :material-book-account: Book Club Management System"

    **Key Features:** Member management · Reading schedule tracking · Meeting planner · Discussion forum

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for book club participation, reading schedule management, and discussion engagement
    - :material-account-group-outline: **Member Management:** Tools to add, update, and delete member details including names, contact information, and reading preferences
    - :material-calendar-check: **Reading Schedule:** Organize and track reading schedules for selected books with goals, progress tracking, and reminders
    - :material-calendar-clock: **Meeting Planner:** Scheduling system for meetings with date, time, location, agenda details, RSVP, and notifications
    - :material-forum-outline: **Discussion Forum:** Forum for posting topics, sharing thoughts, engaging in discussions, responding to posts, and following topics of interest

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, member data, reading schedules, meeting details, and discussion forum posts in binary files
        - Create a text-based interface to manage member details, reading schedules, meeting planning, and access the discussion forum

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding book recommendations, voting on book selections, e-book platform integration, and tools for tracking reading statistics such as reading speed and favorite genres.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZT71Qjmm40RWkvvYy2a9v1LAGfVq4jF5c-IkMd-yOgMPPKOYqASlhPsbu5hRe__vh9cH7gnuBJKdPwV8Pwy-KnQMyQYIGKKhdBkvkwNx-o_KzJuozU3QEVVNnrnyDIWzrd84bpZw4eKxzyzqziyonROOC4Khq2RwLc65UWbeiVReFV8FAFMU_OGCBfrRPujz387cMWEzdeClM1pjK7nCrl0x95nymxr5V2oAzYhlCS4QlBdq01yYJtGOZmWreNFhQ9RVTV8SVuB-_vSNzUFfulSgay9isnzGBdf0ZXoW3NxbDL7-T4sU6TgvLJAxppDfAM_2-Lu_cMswuV2sftVHfhm-_oxQMCsYC3s9rjovTJJBlLYX5pd7qRRH03iB1of2ltr3FiSJwAla36wn9qb9FgxqRUzuStVh7i2XvlGB)

---

??? example "42 — :material-calendar-check-outline: Basic Task Scheduler"

    **Key Features:** Task creation & categorization · Deadline setting · Reminder system · Task prioritization by importance

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for task list management, reminders, and preferences
    - :material-plus-box-outline: **Task Creation:** Create tasks with names, descriptions, categories, and due dates, organized by work, personal, and more
    - :material-clock-alert-outline: **Deadline Setting:** Assign due dates and times to each task for tracking and scheduling
    - :material-bell-outline: **Reminder System:** Notifications for upcoming task deadlines via email, SMS, or in-app alerts
    - :material-sort-ascending: **Task Prioritization:** Mark tasks as high, medium, or low importance and reorder within categories based on priority

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, task data, deadline information, reminder settings, and task priorities in binary files
        - Create a text-based interface to create and manage tasks, set reminders, prioritize tasks, and view upcoming deadlines

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding recurring tasks, task progress tracking, calendar integration, and tools for generating task reports including completed and overdue tasks.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/XPFHIWCn44NVynN3FgdGNv2M2YAu8blrFMp6xR3Df4nc4VlrPjsks6hInzmvkLpcCikeLdJmEnDxuhqLwy473krMWaTG6T2OgvjhM2nkeQejCTJ8GsNChySqV8qei1fqYwpKMgN0bVbNdVZ7q56PQB2Zg2X5w7x0g507XqMi6Ft5nXxMWdFeq_V4h9o3KS-CrrQn2q87B57557TetOuOOOEgn5to-2CTiyH87SCypODlX1y_MCnpnLmDUcAN5hlvJ6tuv7AkJ5ooX7aIizHJK7e_TZEdoDENQtiMIcjNEfoKTwREPj9_jmCFVXz4BRTO_D1Wa37IICGBrlcSwNpC4jcbA_i2)

---

??? example "43 — :material-home-lightning-bolt-outline: Home Utility Tracker"

    **Key Features:** Utility logging (electricity, water, gas) · Expense calculation · Trend analysis · Bill payment reminders

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for utility tracking, expense viewing, trend analysis, and bill payment reminders
    - :material-counter: **Utility Logging:** Log utility consumption for electricity, water, gas, and other utilities with regular data entry
    - :material-calculator-variant-outline: **Expense Calculation:** Calculate utility expenses based on consumption data and current rates with summaries by utility type
    - :material-chart-line-variant: **Trend Analysis:** Charts and graphs to analyze utility usage patterns over time, identify trends, and reduce consumption
    - :material-bell-ring-outline: **Reminder Setup:** Set reminders for bill payments based on billing cycles or custom dates with pre-due-date notifications

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, utility consumption data, expense calculations, trend analysis results, and reminder settings in binary files
        - Create a text-based interface to log utility data, view expense calculations, analyze trends, and set bill payment reminders

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding cost projection based on usage trends, energy-saving tips, utility provider integration for automated bill updates, and provider comparison tools.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/XPF1QiCm38RlVWgHqpReAuoMKdfPsMZNxMmSaPZPSf1bjkpfPnR2IccqM_J_Nw8l6A-pM9OIWyaVdWRBDa9Cb5pFAI885pJcxk4ULgj7Q0xM4noGIcFCnJ2LvumCco8zadXdnITgpCrqufzIvtNYY9tFWgn2-u9Pu91QL35TNNpmCa9LgavTOwwJFsNO1NJ2tjLMvTwj8Al4tcQrVvPU-OBwGxkl0IaZR6rm9SndSIkT-3cfNSgILVRDuoVCzQd4Q7bKNr1DNncfXGtPC6PV5ry5bpktlMLPH7x7RuGNAu9CYvngSyJegKM64qeP6dCTJ5Hzhcl6Y2Gm0ta1djJFWqkrwpJyVxrw3yqQgIqn_000)

---

??? example "44 — :material-gas-station-outline: Vehicle Fuel Efficiency Tracker"

    **Key Features:** Fuel purchase logging · Mileage tracking · Fuel efficiency analysis · Total cost analysis

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for fuel efficiency tracking, mileage calculations, trend analysis, and cost assessment
    - :material-fuel: **Fuel Log:** Log fuel purchases with date, amount, price, and odometer reading whenever refueling
    - :material-map-marker-distance: **Mileage Tracker:** Calculate and display mileage based on fuel consumption and distance with MPG or KPL statistics
    - :material-chart-areaspline: **Efficiency Analysis:** Charts and graphs to analyze fuel efficiency trends over time, identify patterns, and improve fuel economy
    - :material-cash-multiple: **Cost Analysis:** Evaluate total fuel expenditures over specified periods to track spending on vehicle fuel

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, fuel purchase data, mileage calculations, efficiency trend data, and cost analysis results in binary files
        - Create a text-based interface to log fuel purchases, calculate mileage, analyze efficiency trends, and view cost analysis reports

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding maintenance tracking, oil change and tire rotation reminders, GPS integration for distance tracking, and multi-vehicle fuel efficiency comparison tools.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/VP9FIyGm4CNl-HH3JohiLv0jYtgn8ExgVKXdss7pHp8JfD_UfL4iskQMUU-Nl1UOlIWcpTuPkN3um8GUV0pHZYbw0asPZBcvkuNTxXwQ3Zb0Ho4tnYo6Ohu99MYpZXIKBIh7q9XLjV3FSU0wyKe3YrAgGa-PHA6BFTMmUlN7J0wkVHhpUonEs_SmIoyvsH65_YFlJ9-m-K8zk6D7E12S4jhB_D6_Ik4Ew6nsg0JV_XPPslnOHqLbKRQonTVhFPpFR9c2dQ0Dw2PXQSoQkWnVsAT4eQzUgUSVekWYkSnhcRDnpDvZcZQuMj1rmSsUGf-z-m80)

---

??? example "45 — :material-trophy-outline: Local Sports Team Manager"

    **Key Features:** Team roster management · Game scheduling · Player performance statistics · Team communication tool

    **Common Features:**

    - :material-account-key: **User Authentication:** Personalized accounts for team management, game scheduling, statistics tracking, and team communications
    - :material-account-group: **Team Roster:** Manage player profiles with names, positions, contact information, and statistics with add, edit, and remove capabilities
    - :material-calendar-month: **Game Scheduler:** Scheduling system for games with dates, times, opponents, locations, upcoming games, and past results
    - :material-chart-bar: **Statistic Tracker:** Record and analyze player performance metrics such as goals scored, assists, saves, and more
    - :material-message-text-outline: **Communication Tool:** Coordinate team meetings, practices, and announcements with messages and notifications to team members

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, team rosters, game schedules, player statistics, and communication records in binary files
        - Create a text-based interface to manage team rosters, schedule games, track statistics, and communicate with team members

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding player availability tracking, automatic game reminders, weather forecast integration for outdoor games, and tools for generating performance reports and team statistics.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/XPFFIWCn4CRlUOfXJohq5QH5f1SNfAtUXsJO3Tra99aelhqbJMNHxlIOx_k5cT_-MMV192LC9Xzz_C240G97jcFYG22fa35t3_UmMZr2rwEFq5CidJ6pmxbyovHWKsIaADwYU8wTkPOs_eK7hnCx6dmMIYgqBPG5UdQaOkhg1y80EswpPgVMltlwqlgDS_0wuVT2_UoyQFsE0d-IHgZRRp4GxEr8hapr_fzWvd095w0gSYeM-sgY0Kr5GNINlQ3uBDx28Q4zLft_sMMMvUJWjyuAM-tF3xXUkCdMTw2GjrMUE8GIsrS81-QfCzV29axHGKyvuq0QLvThN5P0TRVJ3stM55q9qmy0)

---

??? example "46 — :material-cash-register: Recipe Cost Calculator"

    **Key Features:** Ingredient management · Recipe costing · Price adjustment · Budget planner

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts to personalize ingredient management, recipe costing, and meal budgeting
    - :material-food-apple: **Ingredient Management:** Log, price, categorize, add, and update ingredients used in recipes
    - :material-calculator: **Recipe Costing:** Create recipes with ingredient quantities/units and calculate total cost per serving
    - :material-currency-usd: **Price Adjustment:** Adjust ingredient costs based on market changes, individually or globally
    - :material-wallet: **Budget Planner:** Plan meals within a specified budget with recommendations based on preferences

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, ingredient data, recipe details, price adjustments, and budget plans in binary files
        - Create a text-based interface for managing ingredients, recipes, costs, and meal budget planning

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding recipe sharing, shopping list generation from selected recipes, dietary preference tracking, and tools for analyzing and optimizing recipes for cost and nutritional value.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZTF1IWCn40RWUvvYs9CAVGLfBIA22xAWzx3vsKQJIKac-lfQvs2f9kqn_tnP_irkraLibXg3AU-UzpRRI35ncdSvHP3a2cEkRgvfjRgbORIUQGJNmPYZXGvV2Z9jgkp0ucShFl5W_atLFwN5zyM4nHT1xgA7YY8q9eSUwrT_v2N3UR3GQDakY60PJ2FNFQyUd_GBoXc-SUv8dy7tpilbUa9C7xZOzuzYmkpte6qgudaPp6cWQfjX1QIp1jAtw9Ej3NCDLZHlgMxXv-ndqCQzrIBwsVv6AZKuEYVwWUqD4meKeRHLjzrTTSlX6S4o8m_cD51rMD486qf3wD-canRfzVY-mMODTZM6Bm00)

---

??? example "47 — :material-flower: Garden Planner"

    **Key Features:** Plant database · Gardening schedule · Maintenance reminders · Garden layout

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts to personalize garden plans, schedules, reminders, and plant management
    - :material-sprout: **Plant Database:** Database of plant types with care instructions (planting, watering, sunlight); add, edit, and remove plants
    - :material-calendar-clock: **Gardening Schedule:** Track planting and harvesting times per plant type with custom schedules and expected dates
    - :material-bell-ring: **Maintenance Reminders:** Reminders for watering, pruning, fertilizing, and pest control based on user schedules
    - :material-grid: **Garden Layout:** Design garden bed layouts, assign plants to locations, and visualize the garden

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, plant database, gardening schedules, reminders, and layouts in binary files
        - Create a text-based interface for managing plants, scheduling tasks, setting reminders, and planning layouts

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding garden journaling, weather forecasts for activity planning, plant nursery integration, and tools for tracking plant growth and health with gardening tips.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZTB1QW8n483X-pp5a9CAla9HQF6YKBJjVRePtE1cKf99Iz--q80huWvxpCntoB-RJL7AMkC0vHBaYp95Z4bIr-SK6JLN1bYibhXUFw4xK10yi5G7S7Deo_V26RTLUnOD7Mb8uk3XjFbzEWTR7FaSYd8sqQvoKJmapoOph_uwa2W-az8d5NPmFsZg8_0F_c_A5DbwtyGKUF51RN7acBxPDcRCZh9d2NB6KzUphmCxU3IyYRBXcFP6vMAOLcUZQw39xCQsL1QIZl78COZdN1nCpCVIsVZ4UfrPj4NFjgrzXfxxct5FlwcgWxlp-AvDqJwA9LgUAQnhBbPBsB3u6eS_)

---

??? example "48 — :material-bookshelf: Personal Library Catalog"

    **Key Features:** Book cataloging · Loan management · Wishlist · Reading tracker

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts to personalize library catalog, loans, wishlists, and reading progress
    - :material-book-plus: **Book Cataloging:** Add, update, and delete book entries with title, author, ISBN, genre, and cover images
    - :material-swap-horizontal: **Loan Management:** Track lent and borrowed books with due dates and borrower/lender details
    - :material-heart-outline: **Wishlist:** Maintain a list of desired books; add and remove when acquired
    - :material-bookmark-check: **Reading Tracker:** Log reading progress, mark books as read, maintain history with notes and ratings

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, book catalog data, loan records, wishlists, and reading progress in binary files
        - Create a text-based interface for managing book entries, loans, wishlists, and reading progress

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding book recommendations based on history, search and filtering, online book database integration, and CSV/Excel import/export for catalogs.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/XTF1IWCn40RWUvvYs9CAVGLfgw27BaYnUXwwupRi9bCcinRVteQmMAePRvlvFvO_8LacHT4fZ2uDFXvHC43WoFk3S21GcSYvcxjRM2pkeMdHHsWfJerp5uiIxX89h2OzK5I_H_KS6_VdjFWDzzuMM-fzKX8JFKsK55hko6HczJNp00-eEEPEVUD-JOfxytIQfrMqwZh8IHNiZXqgsUQHHZBC_rlQC9xN6B6dG54RTpsP7SLIjohMBC8dqvI3oL6g4Rljkq-7qQTSS_wy_7CjBeUjR80j1Vua-10EXZBBRGax7tju5TmF-JPUJtukDBm8zq8fLL6BCW0co6aDbKq_-wGiNnLaTpw_MBUas4rX_0O0)

---

??? example "49 — :material-palette: Simple Inventory Management for Crafters"

    **Key Features:** Material inventory · Project tracking · Expense logging · Sales tracker

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts to personalize inventory, projects, expenses, and sales tracking
    - :material-package-variant: **Material Inventory:** Track crafting materials by type, quantity, and purchase details; add, edit, and remove items
    - :material-clipboard-check: **Project Tracking:** Organize craft projects, associate materials, set goals, and monitor progress
    - :material-receipt: **Expense Logging:** Record material costs linked to specific projects or the general inventory
    - :material-cash-multiple: **Sales Tracker:** Track items sold with quantities, prices, dates, and calculate profits vs. expenses

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, material inventory, project details, expense records, and sales data in binary files
        - Create a text-based interface for managing inventory, projects, expenses, and sales

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding a crafting calendar, low-stock alerts, e-commerce integration for online sales, and tools for financial reports and profit analysis.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZTFDQW8n40VmUvvYs5DRy1MAKgGKkY3sunwIwPgQ3vbCRFlsjJ4MHHFtcFd_3lDVPgUH9N5oLiIryHj9qe4BFgWL1OV0b528cxjRc4pkeUkbyT2ZJvqGHuzz-1QHO9PuXPwDaco2xyIbQV7FOJ1jiSJ1H4PgeiU4aQ4F6bkilNel6Sb82qz-Yvu3_NJYmh3eTuDVu-HCQpY4BJVNXaV19RgmnJOTKtX1uHCLmoj9jJP-wCJvwAXioMALvQObggDSyweg7Q-wPi7vzmPzHDWTsP3xdKt-Zn1AKZNv4vGeLbLpdHTfCUuBv7C-VHzwgq0Q_gAAo8lae08M53uCmuEqAjby_HNOMdRtjuifUfsS_GK0)

---

??? example "50 — :material-translate: Basic Language Learning Tool"

    **Key Features:** Vocabulary builder · Grammar exercises · Progress tracking · Language resources

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts to personalize vocabulary, exercises, progress, and resource access
    - :material-alphabetical: **Vocabulary Builder:** Add words with translations, definitions, examples, and pronunciation; practice and review
    - :material-pencil-ruler: **Grammar Exercises:** Create and complete grammar tests by topic with performance feedback
    - :material-chart-line: **Progress Tracking:** Monitor learning milestones, vocabulary size, and grammar exercise performance
    - :material-link-variant: **Language Resources:** Curated collection of online courses, dictionaries, forums, and learning links

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, vocabulary data, grammar exercises, progress records, and resource links in binary files
        - Create a text-based interface for managing vocabulary, exercises, progress, and resources

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding pronunciation practice with audio, flashcards, proficiency quizzes, learning goal setting, and progress report generation.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZTF1Ri8m30RWUvx2wccRn2jCm3HneH82nivUwXMB9aPEChOz_LPL20wLuTW_Nsl_LJjD6JMNqBbqu7X4nG11elYzIY38MiYvXwT7c4oUeQgH8zGKI-NSrSLm-9P8OLRodc9cZvabLcuq7VnIMhR5cbfEcTH4Yq8fGoqDMSnUVISUFqg7-WFpmbtJZnmB1prh6dWNRIopKlIPFS55M7nDNqod-x1TPQ4O0YgyVfDwJfGgDv8DTaCT-GpduwqSsHlyHSAneqntFNlNzrcHVee6Z9uCQlPPgRHAAS5MqHyujfKRoQvcrjnHoX8ftP9NBtZ3ltIBdzU_b39kiKijCRO5Mu8r9Idg-rt7ma7FLKw9OCdnOBZXk-oHOSor_dzpDwNObD3z0G00)

---

??? example "51 — :material-heart-pulse: Personal Health Record Keeper"

    **Key Features:** Health logs · Appointment scheduler · Health trend analysis · Emergency information

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts to personalize health records, appointments, trends, and emergency info
    - :material-clipboard-pulse: **Health Logs:** Record medical visits, medications, symptoms with detailed entries, dates, and descriptions
    - :material-calendar-check: **Appointment Scheduler:** Track upcoming doctor appointments with dates, times, providers, and reminders
    - :material-chart-timeline-variant: **Health Trend Analysis:** Charts and graphs to review health changes over time for symptoms, medications, and vitals
    - :material-alert-circle: **Emergency Information:** Store allergies, medications, blood type, and emergency contacts for quick access

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, health logs, appointment schedules, trend data, and emergency info in binary files
        - Create a text-based interface for managing health logs, appointments, trends, and emergency information

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding health goal tracking, fitness tracker integration for data sync, health report generation for providers, and ensuring data privacy compliance.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZPJ1JiCm38RlUGgh9q3QAw1D0sr8xBB1xb5ZLel6hXn7g6zF1QteeMeu-k_tsxzLAzjaNYt7rgLFuiwhZn25fMvK8e9fHkVk7kvXitc4wkI9uOISA-TcnU_7zuGAksmDib7jZOGhjwIE_AlSQ9quuusIeQv2Xup9u2G1Lx12z2FwrXeO4gMXoRoQWjIYON24eMaBLqfa1N2Qla9T-jYPnEMHLy8l6EKZ9HFj5xZ2Nhkk4sABo0QNki6GMzJABUlpmJCW5VYfddjA5ZGuOoGEg8Ns__flRueSOCU-xHCD3PVr-NxxngkLo0CogZU4CtPImWj9doFg3RdkuOK_HEDq78lwv0bay0ViXStNbbPTqtvAzdEgO-0dRxxWMui_F01kYnnoRBy1)

---

??? example "52 — :material-account-group: Hobby Club Organizer"

    **Key Features:** Member registration · Event calendar · Resource sharing · Activity log

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts to personalize club management, members, events, resources, and activities
    - :material-account-plus: **Member Registration:** Add and manage club members with names, contact information, and hobbies
    - :material-calendar-month: **Event Calendar:** Schedule and manage meetings/events with dates, times, locations, and reminders
    - :material-share-variant: **Resource Sharing:** Exchange hobby resources, tips, and recommendations within the club
    - :material-notebook: **Activity Log:** Track club activities, past events, attendance, and achievements

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, member info, event schedules, resources, and activity logs in binary files
        - Create a text-based interface for managing registration, events, resource sharing, and activity recording

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding discussion forums, polls for event planning, social media integration for promotion, and tools for club reports and participation statistics.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/VTDFIyGm40NmUpx5q9CA-nLa5rsyR45sqNji7krWayXaikAtTp46_DFcEE_zeA-5RfDQqUndaruvl5cnddmCSPWaUf1AXZ5NDzUqsTnIrriEr2FapfWVHocV4eHsMIS4vS4gnz2PnRJuGnovBOuOEIcaYHuoab8V7LgiFRs7Vu5GUPxKwKjfyJldgBHDrjjWHtpBrABjUVipWjATdH6SbSxy3OfwPhpJLvFMo6cOuFACmjRKth6snJrcADhc1AKZF0S7MHtKVEqZKimoW4wJ5GvZP_v7HJx6f5IxLLQ-KEtIkckksWtAPzOFEiJBejzdtJr4SLIR9LIsLC98owWrvF9JcYs2otx-1000)

---

??? example "53 — :material-airplane: Travel Expense Tracker"

    **Key Features:** Trip planning · Expense recording · Budget management · Summary report

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts to personalize travel planning, expenses, budgets, and reports
    - :material-map-marker-path: **Trip Planning:** Organize trip details and itineraries with destinations, dates, accommodations, and activities
    - :material-receipt-text: **Expense Recording:** Log travel expenses by category (accommodation, transport, meals, entertainment) with dates and amounts
    - :material-wallet-outline: **Budget Management:** Set travel budgets with recommendations based on trip details and preferences
    - :material-file-chart: **Summary Report:** Compile trip expenses, highlights, and memorable moments into comprehensive reports

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, trip details, expense records, budget info, and reports in binary files
        - Create a text-based interface for planning trips, recording expenses, managing budgets, and generating reports

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding currency conversion, travel booking integration for auto-tracking, photo uploads, and expense chart visualization for spending patterns.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/XPFDIWCn58NtUOfBLYhq5QJ-b6uSa5RTXyvX6fpS3CaDwjjRlKMaRIRBdFEHETz0bbbSaX96azyzJowvG25otErJ32191SPStLpJOd5BjdEUgGCNQyo_WvQl6OdkYkp1udTEV6HhBgRAFyN1pnCR33uBqYoqBiX2NUnHmzhJNvAVw7bqp9u7QqxEoZmaE07zL1NYpUFZjyyLODLxcRlW4IEgdsXRh3udS0Pji8kfLvFpxE_dqx6hKsfqB7ETKx7sTUhN11kQzwKV8DGvTWC2MAova2cxXP1sTKWj6vGwDgZMycq9mQMlW-uKarXp6YYr1YCvmJocgpN9vqnxpU5HcoMuBs7y1W00)

---

??? example "54 — :material-gavel: Simple Auction Tracker"

    **Key Features:** Item catalog · Bidding system · Auction results · Participant management

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts to personalize auction management, bids, results, and participant profiles
    - :material-tag-multiple: **Item Catalog:** List items for auction with descriptions, starting prices, and end times
    - :material-hammer: **Bidding System:** Track bids and bidders; place bids, view highest bids, and get outbid notifications
    - :material-trophy: **Auction Results:** Record and analyze outcomes including final prices, winning bidders, and auction duration
    - :material-account-switch: **Participant Management:** Manage bidder/seller profiles, registration, and auction history

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, item catalog, bid records, auction results, and participant data in binary files
        - Create a text-based interface for managing catalogs, placing bids, recording results, and managing profiles

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding automatic auction notifications, a rating/feedback system for participants, search/filtering for the catalog, and easy auction creation tools for sellers.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/bPFFQiCm3CRlVWgHqpReAunsVzW62vIMxYvYBHMpvMBBZBxzqgM7BhGgErg_dxzy4ScyA6QjCRZonRB3Z15YajHjSue4cYivTtDt2xFPFJGjia1BKXldJWuZt1JAiAYw9L7kK3b9uyvEH_yzzMmRA-gvA6LJUgbK5DhaoTBiwczA4HvHCQI-SN-Fnx9S50xpSfullF_5v-cpPpNm4mLICWIp_0DxpzB3Ub_6WCbWj3wOliSNk6Gi0tO40p8Z1d8fmQwvg9ro4bXHgK67ZEdaz2b7P5jMrFK-IypA7UzG55eKx2cIQECkWCad2LZWi0FA_xcnfX2ktzZiF2h1CgTF3cJPqtt1AnTDUM_ViHSp_EnkJk9h33y0)

---

??? example "55 — :material-hand-heart: Volunteer Management System"

    **Key Features:** Volunteer profiles · Event scheduling · Hours tracking · Recognition

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts to personalize volunteer management, events, hours, and recognitions
    - :material-account-details: **Volunteer Profiles:** Register and manage volunteers with names, contact info, skills, and availability
    - :material-calendar-star: **Event Scheduling:** Plan and assign volunteer events with details, dates, locations, and volunteer count needed
    - :material-clock-check: **Hours Tracking:** Record volunteer hours and activities; administrators can approve and verify logged hours
    - :material-medal: **Recognition:** Acknowledge contributions with certificates, badges, or thank-you messages for outstanding volunteers

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, volunteer data, event schedules, hours records, and recognition data in binary files
        - Create a text-based interface for managing profiles, scheduling events, tracking hours, and providing recognitions

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding role assignments, event notification tools, activity report generation, and a volunteer dashboard for upcoming events, logged hours, and recognition status.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZPF1Ri8m44Jl_efLJe34BrGWGEsXI1LjkRlEDgnmriXUm-zNmX6D2B4vUkPbDPwi5q6qbzXQ5Ox49-rr2wrZPmxUjGZY8oercKrXFd-1Qgk9OOiSAwLwXsH-1_ImZ7905Z9Qo76b7gg9VtSDvOaTDXG4VHPwZHW4jgx679QFldSsiY1w-F3kboo6IZqGxsB1ZSZHcve41cxkYpLQ57nk-fxmylpqOX6RCxB0fpbW7IrnKwcXbCXEGBZwuzmo16huFs8OHLDJXN4hpGPjWKgj96-Deic6SJRRnvkBFi2Nr-QOshWJRflS-mTNP0nTdaxUdR6CzovIPiiNsQ5n3LFtAFkdBfSvihjOh1k4bMFnz1D7uHrUjA_x2pg4KlIU78PK8VHaDbKBv3gszWy0)

---

??? example "56 — :material-briefcase-outline: Basic Career Planning Tool"

    **Key Features:** Goal setting · Skill tracker · Job search organizer · Interview preparation

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts to personalize career planning, goals, skills, job searches, and interviews
    - :material-target: **Goal Setting:** Define career objectives, milestones, timelines, and action plans
    - :material-school: **Skill Tracker:** Log skills, certifications, courses, and track professional development progress
    - :material-file-search: **Job Search Organizer:** Track applications with job titles, companies, dates, responses, and follow-up reminders
    - :material-microphone: **Interview Preparation:** Compile interview questions, tips, resources, and strategies for review

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, goals, skills, job applications, and interview data in binary files
        - Create a text-based interface for goal setting, skill tracking, job search organizing, and interview prep

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding a networking tracker for contacts, a resume builder, job platform integration for auto-tracking, and career progress report generation.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/XPJ1Ri8m38RlUGgBqpQ9LvX0eA5Dgs30TW-jAHbfKZa9I7lwkIaQCBNfqUxtk_wTE1Fd9VbGQU6EojIIP0MLDJO_aAqGF0KKuk7f4SRZPnXbKXd8q8IH43T1-_7J8S4q-0CQhtBfbJKZqPbj-NTRgZInnb8vZvI45W6TXymMcCIIhI-irB11xvKfkSfTs18SmKmISldbxXjphqxekj4vxfL1o9JcngpfmxuKdg79Ev2c6CIJ_M-EIclOaioFSLhtyT-u8UPT3n0ZM97TetCyE6vXZYVKjgvucZsYwA8j6ssml2JMz-195szsnwObvGTOKYcD-ebcEjEtBJV0jAxrvS1TMjESv8lLsfx7eOOrLieKI0EYQFFVVs3ZfGy3mgJXLyEN-zIKNX4sktVPcUvygvdPgkQh0bVcerc0Y--KSY5vBUX6k5Nra2RwlW9hT3PGZaEob6j-JCG4JS5lpYy0)

---

??? example "57 — :material-home-city: Small Scale Rental Management"

    **Key Features:** Property listing · Tenant records · Rent tracking · Maintenance log

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts to personalize property management, tenants, rent, and maintenance
    - :material-home-outline: **Property Listing:** Manage rental properties with type, address, rent amount, and availability status
    - :material-account-box: **Tenant Records:** Track tenants with lease terms, start/end dates, and contact information
    - :material-cash: **Rent Tracking:** Record rent payments, due dates, payment methods, and generate receipts
    - :material-wrench: **Maintenance Log:** Schedule and track property maintenance tasks with history and reminders

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, property listings, tenant records, rent data, and maintenance logs in binary files
        - Create a text-based interface for managing properties, tenants, rent tracking, and maintenance

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding expense tracking, lease renewal reminders, payment gateway integration, and tools for financial and occupancy reports.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/VPJ1Ri8m38RlUGghfsqIhu10siGEL4ACxHubLclH9YXrD_7sIvEobj7a6F-VxTzEsat7qd7VDg8xaxb89rjehR6gThP5ODUZ44ylpx1QhQ4e9HaeqVI54BD353yxTB3jkKR3f2IJDOLOZ4P-RolA4qUig6DqMMZNOyTGMerPB6lzuEm57LzXx_kHgNobXr0ajrh3JKfJRvhu3uDNP4bDbyQ_27_WJakpsJbEQAHXEAAoJWyD_mMc6QAG8eB_KPdSBwB1-wYCpXUvhE-ZlouuEQdESVdtvnipb8CW7UIrnM1_YGoMvXoyUsFMNHFy3WqwoHXpV1-aIwfsTepXpCFm2i6_xa8yH2BteMhKVOCmavFmEFTSt2gcRmeFS3adR60kJGjOJiYDvRz-iK6Z_K_Y5m00)

---

??? example "58 — :material-run-fast: Personal Fitness Challenge Tracker"

    **Key Features:** Challenge creation · Progress logging · Motivational reminders · Achievement record

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts to personalize challenges, progress, reminders, and achievements
    - :material-trophy-outline: **Challenge Creation:** Set personal fitness challenges with goals, durations, and specific activities/exercises
    - :material-chart-bar: **Progress Logging:** Record daily or weekly progress with exercise details, duration, and repetitions
    - :material-bell-alert: **Motivational Reminders:** Scheduled alerts and motivational messages to keep users on track
    - :material-star-circle: **Achievement Record:** Celebrate milestones, mark completed challenges, view achievements, and set new goals

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, challenge data, progress records, reminders, and achievements in binary files
        - Create a text-based interface for managing challenges, logging progress, reminders, and achievements

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding challenge sharing with friends, fitness tracker/wearable integration, fitness report generation, and workout plan tools.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZPJFJiCm3CRlUGgh9q3QAw1D0uq3HMW8E4UjrLhB7-IuGtjxqevLbUXCZ_dyY-FFJh9EObawxqmwKFYoR3tu66BLSlG8mXqQStDt2wlLFHIbfG0bXgumPh8u1zyJCcmwQJ48LLOeXiBCgcV-EJQa4tjiA0co2kqwJ09bh575rDAthNKEGuEmPRnKFYF-qlqIuGM_OMGq_h4cMKP-q3Hdqb3Lpol7XZ4bo3tEJMvoeZ_IE0HuiEHES05qzXFni0WUh8onfpp7oAVhk6gdZ4B7mRnri4TFeKRkpxuIEEzwGncr_zXXH4lXmThYCbHdcwebFABFrpLlh2BNETsCE3bu4aqQtDllh-WkMgU2Me7vvPirXZf_43y0)

---

??? example "59 — :material-account-supervisor: Study Group Coordinator"

    **Key Features:** Group management · Session scheduling · Resource sharing · Discussion board

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts to personalize study group coordination, sessions, resources, and discussions
    - :material-account-multiple: **Group Management:** Create and manage study groups with names, descriptions, and membership criteria
    - :material-calendar-edit: **Session Scheduling:** Plan study sessions with dates, times, locations (virtual/physical), and agendas
    - :material-file-document-multiple: **Resource Sharing:** Distribute study materials, documents, links, and notes within groups
    - :material-forum: **Discussion Board:** Facilitate group discussions with topics, questions, and threaded conversations

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, group data, session schedules, resources, and discussion data in binary files
        - Create a text-based interface for group creation, session scheduling, resource sharing, and discussions

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding automatic session reminders, polls for topic selection, video conferencing integration, and tools for study progress and attendance tracking.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZTFFIiGm40RmUvvYw4c5VGLP_yXUx6MhtaFpqGusoP9C4D_UrQpGfTlHOxxv3Sm7xJQBJLByPF8xXuDDrfEF8VPZYXuagS2OgvjhscnkgMaj1seHIcFCx567hnc9xegC2CAz5OwXCHVJwf_ZmBhOO-0iI2hQ5MIXDZgeJ3rzbs8vK6k37U0Hf37BgCg712kWJehxf1-pRfySo__a6-DZBlCgrNjqo9bZeAuVuSh4OMZCfV2aQm0w2StMBd-xskHy2cL1xxD7ZYNre6wqgRPPH5L-lq2j5IIsKrx2UH7TpOiS8pZw9GfNQpnoxaljVnzjSerPHcUVrspw4W_ShotKCnSyhtdzpkDlRhO8hlZf2m00)

---

??? example "60 — :material-clipboard-account: Attendance Management System for Schools"

    **Key Features:** Student/teacher profiles · Attendance tracking · Reporting · Summary

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts for admins, teachers, and staff to manage attendance workflows
    - :material-account-school: **Student and Teacher Profiles:** Register, update, and delete profiles with names, contacts, and class assignments
    - :material-checkbox-marked: **Attendance Tracking:** Daily attendance recording per class; teachers mark students present or absent
    - :material-file-chart: **Reporting:** Generate monthly attendance reports (individual, class-wise, subject-wise)
    - :material-chart-arc: **Summary:** Overview of attendance trends, average rates, frequently absent students, and anomalies

    === ":material-language-cpp: C/C++"

        - Use file handling to store profiles, attendance records, monthly reports, and summary data in binary files
        - Create a text-based interface for managing profiles, recording attendance, generating reports, and viewing summaries

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding automated parent notifications for absences, student info system integration, tardiness/leave tracking, and visual attendance charts.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZPFHQiCm34NV_HMYJzkWlp3QmJO62vHs-m3XYCGqbeCiG_VtSt0e8sHk7trrP5-kv5rK54r-CF7iU4H13pvmiBq4Jw2Io9Y7fqVORfzXqw1ZQ8ZJnfW_XrByZYImJzeJgxEeBl36hAg5_moTgnD7wbnKaYhqdYWgDA6bAbQr_YN84Us4mv5iaDPnbwzRrw_suODjJkaEyFKo4aUADz2goJV7oDRXaDl685eihgbpCCGag9IpOUs7dnc8zpNjcTErNk-fkdv9RKSAXm6PY-EbKhXJbahbNoGdPy-teFgSyG87eMdDv_LSAeiKfl8fUO_Yol1MyTgR-U-O7N6RlzWl)

---

??? example "61 — :material-calculator-variant: Small Business Accounting Software"

    **Key Features:** Transaction recording · Financial reporting · Budget planning · Tax preparation

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts for business owners and accountants to personalize accounting workflows
    - :material-bank-transfer: **Transaction Recording:** Log income and expenses with date, amount, category, and payment method
    - :material-file-document-outline: **Financial Reporting:** Generate monthly/annual P&L statements, balance sheets, and cash flow statements
    - :material-chart-pie: **Budget Planning:** Create budget categories, allocate funds, and compare actual vs. budgeted expenses
    - :material-file-percent: **Tax Preparation:** Summarize financial data for tax purposes with reports and filing summaries

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, transactions, financial reports, budgets, and tax data in binary files
        - Create a text-based interface for recording transactions, generating reports, planning budgets, and tax prep

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding automated expense categorization, bank reconciliation, multi-currency support, and financial graphs for performance visualization.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZPFHQiCm34NV_HMYJzkWlp3QmJO62vHs-m3XYCGqbeCiG_VtSt0e8sHk7trrP5-kv5rK54r-CF7iU4H13pvmiBq4Jw2Io9Y7fqVORfzXqw1ZQ8ZJnfW_XrByZYImJzeJgxEeBl36hAg5_moTgnD7wbnKaYhqdYWgDA6bAbQr_YN84Us4mv5iaDPnbwzRrw_suODjJkaEyFKo4aUADz2goJV7oDRXaDl685eihgbpCCGag9IpOUs7dnc8zpNjcTErNk-fkdv9RKSAXm6PY-EbKhXJbahbNoGdPy-teFgSyG87eMdDv_LSAeiKfl8fUO_Yol1MyTgR-U-O7N6RlzWl)

---

??? example "62 — :material-calendar-star: Local Event Planner"

    **Key Features:** Event details · Attendee management · Schedule organizer · Feedback collection

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts for event organizers to personalize planning, attendees, and feedback
    - :material-calendar-text: **Event Details:** Create and manage events with names, dates, locations, descriptions, and details
    - :material-account-check: **Attendee Management:** Register and track attendees with ticket details and payment status
    - :material-timetable: **Schedule Organizer:** Plan event timelines with sessions, workshops, performances, and activities
    - :material-message-draw: **Feedback Collection:** Gather post-event feedback on satisfaction, sessions, and improvement suggestions

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, event data, attendee records, schedules, and feedback in binary files
        - Create a text-based interface for managing events, attendees, schedules, and feedback

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding ticketing/payment processing, event promotion tools, calendar integration for reminders, and event report/feedback analysis tools.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZTFDJiCm303WUvx2wWcGzWferVYvh0B1U02JMgsr_A34sO6d9pIjnAGsucZdK-mapZuA1adMg7XczuK1BLZll1w3jmGI4YbrStSBkzqzD1soWuvSQfJw4vJ5ZqW1sYGZEM6DmjurQZLR_D4FN1TlD70K2bNqd2WATBwdAgks_dZ97Xv8a4tCsrp7nHm2eH1CInkaGuV3JEAcgVJHYf3hYQ3iP4kfjUpr1S52OfMV0khpFssrptSzKfyCmKiOqF7tz4GhoQ9V3JeuiIN3RggwAPSOMYryOU4wh_RuHDH__fxtu8qXFO_ZMdQU8ynJ4s01LLihd3-BskTBpN_g1m00)

---

??? example "63 — :material-clipboard-list: Simple Project Management Tool"

    **Key Features:** Project setup · Task assignment · Progress tracking · Reporting

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts for project managers and team members to personalize workflows
    - :material-folder-cog: **Project Setup:** Define project scope, objectives, names, descriptions, and timelines
    - :material-account-arrow-right: **Task Assignment:** Allocate tasks to team members with deadlines and responsible parties
    - :material-progress-check: **Progress Tracking:** Monitor task completion and deadlines; update statuses and view timelines
    - :material-chart-box: **Reporting:** Generate project status reports with timelines, task statuses, and remaining work

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, project data, task assignments, progress records, and reports in binary files
        - Create a text-based interface for project setup, task assignment, progress tracking, and reporting

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding Gantt chart generation, calendar integration for reminders, task priorities/dependencies, and performance metrics dashboards.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/XTFFQiCm30Rmkvz2-BGD-WgZPOTU5XZzixj8XEibbeCaizVVXiTQriQznVf-GHy9NgkXM4cJqp7oZ889KkRSdoGd0fD2pZqyFS9gzGo-myZG4HVltCMXXaSbWKsn4x757YrczkxcjFhN769Rx2X4DP8csXPIWouFr6Bjwc-IFwWts9ELsRj_vsfUXD08VgC5r25Z-4Ewe9fT3gWZR5HZu4HitbrDggi3-0br4TK-Tr7xqmGXLJW8zcFauDsDMRN7UK2hss1lQ4KR-3tI9vp3c6YAJ8qNcZLtD6UnMk_yNBCjCSd5luCAz9esTtpV0hSc7agQlW00)

---

??? example "64 — :material-account-tie: Basic CRM (Customer Relationship Management)"

    **Key Features:** Customer data · Interaction logging · Sales tracking · Customer service

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts for sales reps and support agents to personalize CRM workflows
    - :material-card-account-details: **Customer Data:** Store and manage customer profiles with contact details, demographics, and preferences
    - :material-message-text: **Interaction Logging:** Record phone calls, emails, meetings, and notes with follow-up actions
    - :material-cart-arrow-up: **Sales Tracking:** Monitor leads, opportunities, quotes, orders, and invoices per customer
    - :material-headset: **Customer Service:** Log and track inquiries, assign to agents, and document resolutions

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, customer data, interaction logs, sales records, and service data in binary files
        - Create a text-based interface for managing customer data, logging interactions, tracking sales, and handling service

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding follow-up task assignment, contact history timelines, lead conversion tracking, email/calendar integration, and customer satisfaction surveys.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZPHHJy903CVVzoaQdjI4hs0WaYW92yhKzsQhumAxOg-7yTjR7E1KTl3OzjUc_xPtew0e6fjr5bRERr2mWOOzbqlXXa0bKfPTtLp3S7WBWnoTXvny76HPnsY3Bu44nb6NvDMLg8xz83lfRVaProvDBAXsGKcIq7sae91pHKaisVfT36fo1IQeQ6L-stj629NW67eKVdDhwg4BGYcNHxg7oj5Z_RzcwCKJ6gRU9eNbJZBOQ6ssjPKvvJsE7phH9FlgwACBmuFjXUKpcPJija2J2S-MkMeR_UDegMyJPeHLI2FppORjZBrJHsVGfyXwZfbBHNA6cVejkxA_qcK7Lv1ihKftuGVNpvhs7bZG--xO--5n2Axs1ow0CenhUmgXFsN2PMmCWOBNSRV519kIP7z4DY9VsLVo1G00)

---

??? example "65 — :material-account-star: Employee Performance Review System"

    **Key Features:** Employee profiles · Performance metrics · Review scheduling · Feedback compilation

    **Common Features:**

    - :material-account-circle: **User Authentication:** Optional accounts for HR managers, supervisors, and employees to personalize reviews
    - :material-badge-account: **Employee Profiles:** Add, update, and delete employee data with names, positions, departments, and contacts
    - :material-gauge: **Performance Metrics:** Track KPIs relevant to each employee's role and responsibilities
    - :material-calendar-clock: **Review Scheduling:** Organize periodic reviews with dates, reminders, and participant invitations
    - :material-comment-multiple: **Feedback Compilation:** Aggregate feedback from supervisors, peers, and self-assessments with scores and reports

    === ":material-language-cpp: C/C++"

        - Use file handling to store user profiles, employee data, metrics, review schedules, and feedback in binary files
        - Create a text-based interface for managing profiles, metrics, reviews, and feedback

    === ":material-language-java: Java"

        - Utilize Java's file I/O for data storage and retrieval
        - Implement a user-friendly text-based interface using the Console class or Scanner for input

    === ":material-language-csharp: C#"

        - Use C#'s file handling capabilities to manage data storage
        - Create a well-structured console application using the Console class for user interactions

    Consider adding goal tracking, 360-degree feedback, performance improvement plans, HR system integration, and performance dashboards with trend analysis.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/bTJHJi8m50RW-pv5mvMQy0e68ag8BY4WtjVsPsjOs-NqJEFRkpaW6CX1B_VtQtTESz99PaFSXbhbdO-D8HDqI37PYbA0PcgXrCtThHwFx_MeC3xg0h4TANNoCInkCaXFMwuGsLl3FiMHkfWE_YMLNXOhb3upI4Jp5fbraHn49fR-69ewVG5wIMdhQ-JkgFDii5Fdz75jPjW8TjCum_Wpdw76F_WQXcnrv88Kcr-2jec2YHPTpEHjt_wbTF2lPEnEFoyNCbk3hoEni1K-F3xrsbPmRUrZsHrrdkt_DWJGUr00BEzuwzFVYn2uMEaJuDxxMtb8eV7rOVOlneVUR2Ad3qB6qvnz6RkQ4lsCfa3dYA1-vaxlV8Kc4L_PAJNOFG1gWkYwT-8R)

---

??? example "66 — :material-dumbbell: Fitness Center Membership Management"

    **Key Features:** Member data management · Subscription tracking · Class scheduling · Payment processing

    **Common Features:**

    - :material-account-key: **User Authentication:** Optional accounts for staff and administrators to personalize membership management, subscriptions, class scheduling, and payment processing.
    - :material-account-group: **Member Data Management:** Register and update member profiles with names, contact info, membership types, and fitness goals.
    - :material-card-account-details: **Subscription Tracking:** Monitor membership status, track start/end dates, send renewal reminders, and manage membership tiers.
    - :material-calendar-clock: **Class Scheduling:** Organize fitness classes, specify instructors, set capacities, and manage member registrations.
    - :material-credit-card: **Payment Processing:** Process payments for memberships, renewals, and class registrations with secure payment methods.

    === ":material-language-cpp: C/C++"

        - Store user profiles, member data, subscription records, class schedules, and payment transactions in binary files.
        - Text-based console interface for managing members, subscriptions, classes, and payments.

    === ":material-language-java: Java"

        - Use Java file I/O for data storage and retrieval.
        - Text-based interface using Console class or Scanner for input.

    === ":material-language-csharp: C#"

        - Use C# file handling for data storage.
        - Console application using the Console class for user interactions.

    Consider adding attendance tracking, membership card generation, waitlist management, fitness device integration, financial reports, membership statistics, and class utilization reports.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/bPJHojim38Nl_HGYLzlWVuNnbuwDmJ9Aqpw0wgY9g2q7MwRqxTVDAMIiTTXbZhwJ-2W7loV5gDaxaoui4qRqu8C4EyRW2JHcCkR3fu_mzlOPcXPPe2N9ZJ6BXp8y9egmoJgIA5jK3jAOfshXVuI1wyIU1avAiGfzovGKsj1J3QiVlIL_eWXVK15Q51p8asXZNWt-FXxCr2w6CpjQinsd7fN-qzGHHZlEfWgydhNBfsGZJyf1u13HNbY6nhpGvnS6OGsFe6da2Jf5pMd5joUXAphOasVfAQxn9SdoCmTUTrNZRXsc19qTgS-k9Fr7AkGi47nb5ReJVpXA5ST4biyt-2v9OxOQOWqcXJBQueIMbQb2RvmBrqT7Ij5YgVDBItKDExnv4lrTDqifbKKy4Giz2p03gSR-fElYXrNHkGvLfeHR9gxYjT3tQykya_JPkry0)

---

??? example "67 — :material-file-document-multiple: Personal Document Organizer"

    **Key Features:** Document categorization · Indexing · Secure storage · Search function

    **Common Features:**

    - :material-account-key: **User Authentication:** Optional accounts to personalize document organization, categorization, indexing, secure storage, and search functionalities.
    - :material-folder-multiple: **Document Categorization:** Sort documents by type, date, or custom categories using folders or tags based on user preferences.
    - :material-format-list-numbered: **Indexing:** Automatic metadata generation and content-based indexing for quick document retrieval.
    - :material-lock: **Secure Storage:** Encrypt and save documents with access control to protect sensitive information from unauthorized access.
    - :material-magnify: **Search Function:** Locate documents using keywords that scan content, titles, tags, and metadata.

    === ":material-language-cpp: C/C++"

        - Store user profiles, document data, indexing information, and encryption keys in binary files.
        - Text-based console interface for managing categorization, indexing, secure storage, and search.

    === ":material-language-java: Java"

        - Use Java file I/O for data storage and retrieval.
        - Text-based interface using Console class or Scanner for input.

    === ":material-language-csharp: C#"

        - Use C# file handling for data storage.
        - Console application using the Console class for user interactions.

    Consider adding document versioning, sharing, expiration reminders, cloud storage synchronization, document reports, type statistics, and access history logs.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZTFHIkmm40RW-pp5qAjpXCKta2rTLqHxOzS76DEX6smoosIYrgTt8QpAQbFlslc_m9-XNKT5qUH74vzTEA2W1y-1xLxO4wWaCkRVnNzOhQwWQj45Q2caofXlBpbyZ2HG9zrJK6TH7OVA_3hD_fu7LnOFDBYe94LqaoWgjDnJaHMhRzWcJq6XGQM1nRsVwiyb-LmZX4gmvR4dYPSx7EBYcOv5uMc2tNIWF-CDwZ9kKbJsdnaLkomlvJRqzER2K9clnvpKITcZEWlpfiMGS2o0Zb3i_dYvRCx0KhcER1A2JbbmeCgS3xAw3bQcWy9f2t4ErjPIZD1mKE5nJfKRvVjiKx3vs_anoUwEfbUM7d8wo-h-1OEbWZjVOqkAFIhEq7BzuozkrXJwvCSF)

---

??? example "68 — :material-cash-register: Retail Sales Tracker"

    **Key Features:** Product catalog · Sales recording · Inventory management · Revenue analysis

    **Common Features:**

    - :material-account-key: **User Authentication:** Optional accounts for store managers and sales staff to personalize retail tracking, catalog management, sales recording, inventory monitoring, and revenue analysis.
    - :material-shopping: **Product Catalog Management:** Create and update product catalog with names, descriptions, categories, prices, and stock levels.
    - :material-receipt: **Sales Recording:** Log daily sales transactions including product names, quantities sold, prices, and customer information.
    - :material-warehouse: **Inventory Management:** Track stock levels, automatically update quantities based on sales, and generate reorder alerts when stock is low.
    - :material-chart-line: **Revenue Analysis:** Generate sales performance reports with trends, revenue by category, and profit margins.

    === ":material-language-cpp: C/C++"

        - Store user profiles, product catalog, sales transactions, inventory data, and reports in binary files.
        - Text-based console interface for managing catalog, recording sales, tracking inventory, and analyzing revenue.

    === ":material-language-java: Java"

        - Use Java file I/O for data storage and retrieval.
        - Text-based interface using Console class or Scanner for input.

    === ":material-language-csharp: C#"

        - Use C# file handling for data storage.
        - Console application using the Console class for user interactions.

    Consider adding sales order management, CRM integration, barcode/POS system support, automatic invoice generation, financial statements, sales forecasts, and product performance reports.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/bPHHIyCm58NVyoikVL9XVq5M5APWOLJdUsoENR2z6JStIl-z7TKncGtROyxvpkrj3cGPrOgclZFnm_FEYksf3noQhOGUf99WpDNDDIqMjrHKrZDLu5GOStIOp4s4K9bq2rRVMFM12_ElEl5FeVLveaRheqAoq2eXAbN18OVbLrzBSAbHkhDgkz1IPTcswC5Qc9mvfLyzlkY7YkS4IkT--NFmpSvPnIM9UtIuA96_dcVR8LAD9eZpt1RcH9cuwKnxSuwPBcko7dpK8CCikW93h88U-JFuPdPcVlan3Dv_vs-h1tccpuEVPsi4SH0gEuZ6B9jViyQOIw2IRJT4FuuwbOxRU16mcwSEyjkmBnrja64UNajuzqgLbTPpPcZk3yP7mYp1BlNTDm00)

---

??? example "69 — :material-account-tie: Freelance Client Manager"

    **Key Features:** Client information storage · Project tracking and deadlines · Payment reminders · Communication log

    **Common Features:**

    - :material-account-key: **User Authentication:** Optional accounts for freelancers to personalize client management and project tracking.
    - :material-account-box: **Client Information Storage:** Store client contact details, project history, and specific preferences or requirements.
    - :material-clipboard-check: **Project Tracking and Deadlines:** Track ongoing projects with names, descriptions, deadlines, and progress status across multiple projects simultaneously.
    - :material-bell-ring: **Payment Reminders:** Notify freelancers of upcoming payment deadlines or milestones with configurable reminders based on project terms.
    - :material-message-text: **Communication Log:** Record and store client communication including emails, messages, and project-related notes.

    === ":material-language-cpp: C/C++"

        - Store user profiles, client data, project details, payment records, and communication logs in binary files.
        - Text-based console interface for managing clients, tracking projects, and setting payment reminders.

    === ":material-language-java: Java"

        - Use Java file I/O for data storage and retrieval.
        - Text-based interface using Console class or Scanner for input.

    === ":material-language-csharp: C#"

        - Use C# file handling for data storage.
        - Console application using the Console class for user interactions.

    Consider adding invoice generation, freelance expense tracking, and financial report exports to help freelancers stay organized and manage client relationships effectively.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/bPJHQkim38Rl_HGYL-SSw2iSMhPI2YsCjjkzYRNKQomNMzdMjzyocs2MHMHN8Ry-YL_AJvQHTU3EjIfUB5rrq0wS9r-VWtS873fKwi-_lx1O_8Tghot17gchb2fUKl4nOe1LnsSajhLcwwbIFwg9t_d6oiG16niPWmXjEemCUszGmcJhTwr5OjZIim_kgmkEx8Dki59ICNMlZ844pQ7Nnl8ly2UBRncFStXnpuVWNx1cE0LTNomreCd0FMhJMi9-l0mK-sPmNajQUdvFiNacPtkJrzStXyHmG6V9OEYdZhN47f7XUqt0UxFot5avzCvrb8CBEzzKQgoLgIjh0fj2L1QZG8iMDqWOD2DiwTNRka_DM1jks68zV7U17Ea2AhfRlr-H8WuPgTH8AiwNIvDGkbkknGbiXYKV-XpqZpoa5CfJPH5EjojEaSDTaZo1YPu-_-TgYMGwrtu0)

---

??? example "70 — :material-gavel: Basic Legal Case Tracker"

    **Key Features:** Case management · Client tracking · Hearing scheduler · Document storage

    **Common Features:**

    - :material-account-key: **User Authentication:** Optional accounts for legal professionals to personalize case management, client tracking, hearing scheduling, and document storage.
    - :material-briefcase: **Case Management:** Add, update, and delete legal cases with details such as case numbers, titles, types, and parties involved.
    - :material-account-search: **Client Tracking:** Record client details and case history, associate clients with cases, track contact info, statuses, and interactions.
    - :material-calendar-alert: **Hearing Scheduler:** Manage court dates, schedule hearings, set reminders for important dates, and receive notifications.
    - :material-file-cabinet: **Document Storage:** Upload, categorize, and search legal documents by case information for organized retrieval.

    === ":material-language-cpp: C/C++"

        - Store user profiles, case data, client info, hearing schedules, document metadata, and files in binary files.
        - Text-based console interface for managing cases, clients, hearings, and documents.

    === ":material-language-java: Java"

        - Use Java file I/O for data storage and retrieval.
        - Text-based interface using Console class or Scanner for input.

    === ":material-language-csharp: C#"

        - Use C# file handling for data storage.
        - Console application using the Console class for user interactions.

    Consider adding task assignment for case activities, legal research tools, deadline tracking, secure document sharing, case summaries, legal reports, and document tracking reports.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/XTJDIWCn5CNnVPxYafKAVGLfQK4NpgQrxY-Jmqpe92dvGF3fRPr6XJ7NPUxvBVhlOjOfSyp5JIgTh3zpP4Sk-D2FCJXGZWLAtJtSqshrIBfZwwc3BrgfNuzvF2P4sfGymcVRSxR1Q_NdTVOlOR2os6EmAIEAwAaWPUg2WSZ4dxxb1EhOym07dxLQN6QtCOQkInCSpuOpPBF3XF_CcyNxbqXD8WTDzYBfDN9/idxGQd7v2PgN9gb9igfHWgfPyvHQI0vx1iSBfKC_mfG9KQlbxMRhcsv0eiYqXxFU8AQMgm6tKN1omoxqnLt33pb47g3LybJ_-ocmeRgsuPOpXX3j1qHSOpdsupTCBIbdN3uAQWrlYfi-0G00)

---

??? example "71 — :material-food-apple: Recipe and Nutrition Tracker"

    **Key Features:** Recipe storage · Nutritional calculator · Meal planner · Shopping list generator

    **Common Features:**

    - :material-account-key: **User Authentication:** Optional accounts to personalize recipe storage, nutritional analysis, meal planning, and shopping list generation.
    - :material-book-open-variant: **Recipe Storage:** Add and manage recipes with names, ingredients, quantities, instructions, and preparation times.
    - :material-calculator: **Nutritional Calculator:** Analyze recipes for calories, carbohydrates, proteins, fats, vitamins, and minerals.
    - :material-silverware-fork-knife: **Meal Planner:** Organize daily and weekly meals by selecting recipes, specifying servings, and planning breakfast, lunch, dinner, and snacks.
    - :material-cart: **Shopping List Generator:** Automatically generate grocery lists by aggregating ingredients from selected meal plan recipes.

    === ":material-language-cpp: C/C++"

        - Store user profiles, recipe data, nutritional results, meal plans, and shopping lists in binary files.
        - Text-based console interface for managing recipes, nutrition analysis, meal planning, and shopping lists.

    === ":material-language-java: Java"

        - Use Java file I/O for data storage and retrieval.
        - Text-based interface using Console class or Scanner for input.

    === ":material-language-csharp: C#"

        - Use C# file handling for data storage.
        - Console application using the Console class for user interactions.

    Consider adding dietary preference tracking (vegetarian, vegan, gluten-free), recipe sharing, nutritional database integration, nutrition reports, meal prep schedules, and shopping list cost estimates.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/XTF1QiCm30RWkvz2vBGD-WgZPOLUcZ5Qkfr5yfEQEdAmPKRtz6jmAUrOdAEaJ_0hmSke73JrpiIJbO43zzHxySqn-1wa8S6OfvTdMYnUgQhP2jMGL1bpL-JX8IBGAkaHehPXjLugysyt-wtlR5diqDce24MqIOXAjMzHOkNeEpHs0EtL1-vGcR-Dh5PjItam9Gv3ouePj8R3BFgq-BwIE6MA9xqd3NQydnsziMkIO_MXCbE3wud2xlnp5wwaSyYRcDF5k3KkAn-EHSRV_LXcCLQqPklED4vZGNq1foBBoM_z2LGCl3_wOR3IqTP6fGq48N_WoKdUkzQW1pQtbWC_a3YxKyf_UT9c2MbJxtu1)

---

??? example "72 — :material-translate: Language Learning Companion"

    **Key Features:** Vocabulary builder · Grammar exercises · Progress tracker · Daily practice reminders

    **Common Features:**

    - :material-account-key: **User Authentication:** Optional accounts to personalize vocabulary building, grammar exercises, progress tracking, and daily practice reminders.
    - :material-card-text: **Vocabulary Builder:** Store and review new words and phrases with categorization, flashcards, and quizzes.
    - :material-pencil-ruler: **Grammar Exercises:** Interactive exercises covering sentence structure, verb conjugation, tenses, and more.
    - :material-chart-areaspline: **Progress Tracker:** Monitor performance in vocabulary, grammar, and overall language proficiency.
    - :material-bell-outline: **Daily Practice Reminders:** Customizable reminders with configurable frequency and timing to fit user schedules.

    === ":material-language-cpp: C/C++"

        - Store user profiles, vocabulary items, exercise data, progress records, and reminder settings in binary files.
        - Text-based console interface for managing vocabulary, grammar exercises, progress, and reminders.

    === ":material-language-java: Java"

        - Use Java file I/O for data storage and retrieval.
        - Text-based interface using Console class or Scanner for input.

    === ":material-language-csharp: C#"

        - Use C# file handling for data storage.
        - Console application using the Console class for user interactions.

    Consider adding pronunciation guides, proficiency assessments, learning goals, progress reports, vocabulary usage statistics, and grammar exercise scores.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/ZTFTJa8n30Vm-pr5ioiruHKC-15k919KxklMGCC-IBUXlhtAOi1uRCxbsjzE-k_9fgMYr1Q3AJjEUnIC47FARYiv4bHfPCp5rILC9jTW5yW95fIQDURiq9ilXGHchMufLNPOEITh1glTF-GDws956owLH4NpHgN28dlIc3xwEZjyRG7b0suQ1qzYpL2nwvdtS6fgSaK7fhTn-Bon57IL7IbS3pGNZ145xjz971SgrWpKkhtDSH-eqYYy_iwpbBmHAehJf_kv3y-2RdTSzfzIbyVJh_tfie-ZVF3nG88Xd4SObdg2E-Hm+asmeiZ9anHh_c_rcqzKn_E-x_5Ud_hh6Pif9TzY-0G0)

---

??? example "73 — :material-car: Personal Vehicle Log"

    **Key Features:** Vehicle details · Mileage tracker · Fuel log · Service reminders

    **Common Features:**

    - :material-account-key: **User Authentication:** Optional accounts to personalize vehicle management, mileage tracking, fuel logging, and service reminders.
    - :material-car-info: **Vehicle Details Management:** Record vehicle profiles with make, model, year, registration number, and insurance information.
    - :material-speedometer: **Mileage Tracker:** Log odometer readings, track distances traveled, and view mileage trends.
    - :material-gas-station: **Fuel Log:** Record fuel purchases with type, price, gallons/liters, and calculate fuel efficiency.
    - :material-wrench-clock: **Service Reminders:** Schedule maintenance checks with reminders for oil changes, tire rotations, inspections, and other tasks.

    === ":material-language-cpp: C/C++"

        - Store user profiles, vehicle data, mileage records, fuel logs, service reminders, and maintenance history in binary files.
        - Text-based console interface for managing vehicles, mileage, fuel data, and service reminders.

    === ":material-language-java: Java"

        - Use Java file I/O for data storage and retrieval.
        - Text-based interface using Console class or Scanner for input.

    === ":material-language-csharp: C#"

        - Use C# file handling for data storage.
        - Console application using the Console class for user interactions.

    Consider adding maintenance expense tracking, service history reports, GPS trip tracking integration, fuel efficiency reports, cost analysis, and upcoming service task reminders.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/XPFDIWCn58NtUOfBLYhq5QJ-b6uSa5RTXyvX6fpS3CaDwjjRlKMaRIRBdFEHETz0bbbSaX96azyzJowvG25otErJ32191SPStLpJOd5BjdEUgGCNQyo_WvQl6OdkYkp1udTEV6HhBgRAFyN1pnCR33uBqYoqBiX2NUnHmzhJNvAVw7bqp9u7QqxEoZmaE07zL1NYpUFZjyyLODLxcRlW4IEgdsXRh3udS0Pji8kfLvFpxE_dqx6hKsfqB7ETKx7sTUhN11kQzwKV8DGvTWC2MAova2cxXP1sTKWj6vGwDgZMycq9mQMlW-uKarXp6YYr1YCvmJocgpN9vqnxpU5HcoMuBs7y1W00)

---

??? example "74 — :material-typewriter: Freelance Writer's Organizer"

    **Key Features:** Article tracking · Idea notebook · Submission log · Income tracker

    **Common Features:**

    - :material-account-key: **User Authentication:** Optional accounts for freelance writers to personalize article tracking, idea notebook, submission log, and income tracking.
    - :material-newspaper-variant-outline: **Article Tracking:** Manage writing assignments with article titles, publishers, submission deadlines, and progress status.
    - :material-lightbulb-outline: **Idea Notebook:** Store and categorize writing ideas by genre or topic with descriptions.
    - :material-send-check: **Submission Log:** Track submissions to publishers with dates, details, statuses (pending, accepted, rejected), and responses.
    - :material-currency-usd: **Income Tracker:** Monitor earnings with payment dates, amounts, and sources from writing assignments.

    === ":material-language-cpp: C/C++"

        - Store user profiles, article data, idea profiles, submission records, income data, and progress status in binary files.
        - Text-based console interface for managing articles, ideas, submissions, and income.

    === ":material-language-java: Java"

        - Use Java file I/O for data storage and retrieval.
        - Text-based interface using Console class or Scanner for input.

    === ":material-language-csharp: C#"

        - Use C# file handling for data storage.
        - Console application using the Console class for user interactions.

    Consider adding expense category insights, expense history reports, automated budget alerts, savings progress charts, budget analysis reports, and financial goals achievement tracking.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/XPHDJiCm48NtFiKegu9KZb2r_9K5aH1HsLl9ABKQsv5dNE3scCG1McfdEVC-lSmREruJIyTUTu8EolJIIGtQ6blldTK8x3mAiRY-WkLo1KKfbO4IZI-4-5ECuexGmSRp7WshMhAofX0NkoF_R5kL9rwnLSJeij3M8p6Kji4ibXtzrZSjCjmvdCO-xunS5LgZaaJU5BxFCRk-aOnpr3rsE4zbSpryz6W88QmwxBeD9kUTdwE0g2IP8LYKA4d5Q7DcCTiSbWrNoMCW2RPMTi7XfFmzhhGw1FgkwR8UewGDOeWqa1t_KHbfQYKxgBpMqdq6Z_-za_jvf1lOrEo7xwNOQOuNPrk7H1bqMlfJ-CjiTe1Sal0wY3MQ9ZmYNm00)

---

??? example "76 — :material-hammer-wrench: DIY Project Planner"

    **Key Features:** Project catalog · Material list · Step tracker · Budget manager

    **Common Features:**

    - :material-account-key: **User Authentication:** Optional accounts to personalize project planning, material tracking, step logging, and budget management.
    - :material-bookshelf: **Project Catalog:** Store and organize DIY project ideas with names, descriptions, images, and categories (woodworking, home improvement, etc.).
    - :material-format-list-checks: **Material List:** Track materials and tools needed with quantities, prices, and purchase links or stores.
    - :material-step-forward: **Step Tracker:** Log progress on ongoing projects with completed steps, notes, images/videos, and completion dates.
    - :material-cash-multiple: **Budget Manager:** Monitor project expenses, input costs for materials and tools, calculate totals, and compare against set budgets.

    === ":material-language-cpp: C/C++"

        - Store user profiles, project data, material lists, step logs, and budget information in binary files.
        - Text-based console interface for managing project catalog, materials, steps, and budget.

    === ":material-language-java: Java"

        - Use Java file I/O for data storage and retrieval.
        - Text-based interface using Console class or Scanner for input.

    === ":material-language-csharp: C#"

        - Use C# file handling for data storage.
        - Console application using the Console class for user interactions.

    Consider adding progress visualization (Gantt charts), timeline tracking, priority setting, project cost reports, material shopping lists, and completion certificates.

    ![Class Diagram](https://www.plantuml.com/plantuml/png/XTJDQiCm3C3nkvz2-BGD-WgZtGUxD33MTNSHYzIhOmTRPdlyhKo1TZZgKTBla3y1hmjZvZe6Kuu-JfXnX375r1zo6WauLpBcvkuMLgjxi1tw21t5Qeqv6_wE-q8PDfKF5Ddto3v5QrfRyTiqU5syqU0BKrRHIwN2q2L7AbC__JMdJ-eP7f4nfC6Q_njn7vw-G4vbKMsScz6YsKyEcQwo9mgaCpLEZapPOu2jBspDvNmUDXzA2qdNl6yJoLALL5rNQjIEQOBtZFsHiZKNO-jVjScE0lKyeuQHwDEwUPNU9I2fAb0h7weRYA73YCEfytBHB6sWHcjJIUtpzqInq1AHP5r9jwxq-Dz7nwmfkZg67m00)

---

