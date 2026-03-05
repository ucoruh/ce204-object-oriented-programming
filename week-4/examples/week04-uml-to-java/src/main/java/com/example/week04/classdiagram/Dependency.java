package com.example.week04.classdiagram;

/**
 * CEN206 - Week 4: Dependency Relationship
 *
 * In UML a <b>dashed arrow</b> from class A to class B means
 * "A depends on B".  Unlike association, A does <i>not</i> store
 * a permanent reference to B.  Instead, B appears as:
 * <ul>
 *   <li>a method parameter,</li>
 *   <li>a local variable, or</li>
 *   <li>a return type.</li>
 * </ul>
 *
 * <pre>
 * ┌────────────┐           ┌────────────┐
 * │  PDFExporter│- - - - ->│  Document   │   (dependency)
 * └────────────┘           └────────────┘
 *
 * ┌────────────┐           ┌────────────┐
 * │  Logger     │- - - - ->│  Formatter  │   (dependency)
 * └────────────┘           └────────────┘
 * </pre>
 */
public class Dependency {

    // ----------------------------------------------------------------
    // Document -- the class that is depended upon
    // ----------------------------------------------------------------

    /** Simple document that can provide its content. */
    public static class Document {
        private final String title;
        private final String body;

        public Document(String title, String body) {
            this.title = title;
            this.body = body;
        }

        public String getTitle() {
            return title;
        }

        public String getBody() {
            return body;
        }

        @Override
        public String toString() {
            return "Document(\"" + title + "\")";
        }
    }

    // ----------------------------------------------------------------
    // PDFExporter -- depends on Document (parameter dependency)
    // ----------------------------------------------------------------

    /**
     * PDFExporter uses Document only as a method parameter.
     * It does NOT store a reference to Document as a field.
     * This is a classic UML dependency.
     */
    public static class PDFExporter {

        /**
         * Exports the given document to a (simulated) PDF.
         *
         * @param document the document to export (dependency)
         * @return a string representing the PDF output
         */
        public String exportToPdf(Document document) {
            // Document is used only within this method scope
            return "[PDF] Title: " + document.getTitle()
                    + " | Body: " + document.getBody();
        }
    }

    // ----------------------------------------------------------------
    // Formatter & Logger -- another dependency example
    // ----------------------------------------------------------------

    /** Formats log messages. Used by Logger as a local-variable dependency. */
    public static class Formatter {
        public String format(String level, String message) {
            return "[" + level + "] " + message;
        }
    }

    /**
     * Logger depends on Formatter.
     * A new Formatter is created locally each time -- Logger does not
     * hold a persistent reference.
     */
    public static class Logger {

        /**
         * Log a message.  Internally creates a Formatter (local dependency).
         */
        public void log(String level, String message) {
            // Formatter is created as a local variable -- dependency, not association
            Formatter formatter = new Formatter();
            String formatted = formatter.format(level, message);
            System.out.println("  " + formatted);
        }
    }

    // ----------------------------------------------------------------
    // Demo
    // ----------------------------------------------------------------

    /** Runs the dependency demonstration. */
    public static void demo() {
        // PDFExporter depends on Document (parameter)
        Document doc = new Document("CEN206 Syllabus",
                "Object-Oriented Programming course materials.");
        PDFExporter exporter = new PDFExporter();
        String pdf = exporter.exportToPdf(doc);
        System.out.println("  " + pdf);

        // Logger depends on Formatter (local variable)
        System.out.println();
        Logger logger = new Logger();
        logger.log("INFO", "Application started.");
        logger.log("WARN", "Low disk space detected.");
        logger.log("ERROR", "Unable to connect to database.");
    }
}
