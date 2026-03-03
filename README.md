# CEN206 Object-Oriented Programming

CEN206 Object-Oriented Programming course notes and examples for Recep Tayyip Erdogan University, Computer Engineering Department.

Website: [RTEU CEN206 Object-Oriented Programming Course](https://ucoruh.github.io/ce204-object-oriented-programming/)

## Course Information

| | |
|---|---|
| **Instructor** | Asst. Prof. Dr. Uğur CORUH |
| **Semester** | Spring 2025-2026 |
| **Language** | English |
| **Credits** | 3 |
| **Lecture Hours** | Friday 13:00-16:00 |
| **Classroom** | İİBF-402 |

## Course Topics

| Week | Topic |
|------|-------|
| Week 1 | Course Introduction, Software Engineering Fundamentals, OOP with Java Part-I |
| Week 2 | OOP with Java Part-II (Inheritance, Polymorphism, Encapsulation) |
| Week 3 | OOP with Java Part-III (Interfaces, Lambda, Generics, Reflection) |
| Week 4 | UML - Unified Modelling Language |
| Week 5 | PlantUML - UML Diagram Generation from Text |
| Week 6 | UMPLE Part-1 - Model-Driven Development |
| Week 7 | UMPLE Part-2 - Advanced Features, State Machines |
| Week 8 | Midterm Exam Week |
| Week 9 | Design Patterns - Creational (Factory Method, Abstract Factory, Builder, Prototype, Singleton) |
| Week 10 | Design Patterns - Structural (Adapter, Bridge, Composite, Decorator, Facade, Flyweight, Proxy) |
| Week 11 | Design Patterns - Behavioral (Chain of Responsibility, Command, Iterator, Mediator, Memento, Observer, State, Strategy, Template Method, Visitor) |
| Week 12 | Code Smells and Refactoring Fundamentals |
| Week 13 | Refactoring Techniques |
| Week 14 | Case Studies |
| Week 15 | Final Project Review and Summary |
| Week 16 | Final Exam Week |

## Repository Structure

```
docs/
├── syllabus/          # Course syllabus (EN/TR)
├── week-1/            # Week 1 lecture notes and examples
├── week-2/            # Week 2 lecture notes and examples
├── ...
├── week-16/           # Week 16 lecture notes
└── project-guide/     # Project guide for students
```

## Example Projects

Each week folder contains an `examples/` directory with runnable projects:

- **Weeks 1-4, 9-14**: Maven/Java projects (Java 17, run with `mvn compile exec:java`)
- **Week 5**: PlantUML `.puml` diagram files
- **Weeks 6-7**: UMPLE `.ump` model files

## Markdown Document Converter

The `convert_all_markdown.py` script converts all markdown files under the `docs/` folder into multiple output formats using multi-threaded parallel processing.

### Supported Output Formats

| Tool | Output | File Suffix |
|------|--------|-------------|
| Marp | HTML slide | `*_slide.html` |
| Marp | PDF slide | `*_slide.pdf` |
| Pandoc | PDF document | `*_doc.pdf` |
| Pandoc | Word document | `*_word.docx` |

### Prerequisites

- **Python 3.6+** with `psutil` and `tqdm` packages
- **Marp CLI**: `npm install -g @marp-team/marp-cli`
- **Pandoc**: [pandoc.org/installing.html](https://pandoc.org/installing.html)
- **XeLaTeX**: Required for PDF document output (MiKTeX or TeX Live)

### Usage

```bash
# Install Python dependencies
pip install -r requirements.txt

# Convert all markdown files (cleans old outputs first)
python convert_all_markdown.py

# Clean only (remove previously generated files)
python convert_all_markdown.py --clean-only

# Specify thread count
python convert_all_markdown.py --threads 4
```

For detailed information see [README-markdown-converter.md](README-markdown-converter.md).

## Building the Course Website

```bash
mkdocs serve    # Local preview
mkdocs build    # Build static site
```
