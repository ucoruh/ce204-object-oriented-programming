package com.example.week11.template;

/**
 * Template Method Pattern - Concrete Implementation: CSV Processor
 *
 * Provides concrete implementations of the abstract steps defined
 * in DataProcessor, specialized for processing CSV data. The overall
 * algorithm (read -> parse -> analyze) stays the same; only the
 * details of each step change.
 */
public class CSVProcessor extends DataProcessor {

    /**
     * Reads CSV data from a simulated data source.
     * In a real application, this would read from a .csv file.
     *
     * @return raw CSV data
     */
    @Override
    protected String readData() {
        String data = "Name,Age,Grade\nAlice,20,A\nBob,21,B\nCharlie,19,A";
        System.out.println("    [CSVProcessor] Reading CSV data...");
        System.out.println("    Raw: " + data.replace("\n", " | "));
        return data;
    }

    /**
     * Parses CSV data by splitting rows and counting records.
     *
     * @param data the raw CSV string
     * @return summary of parsed data
     */
    @Override
    protected String parseData(String data) {
        String[] rows = data.split("\n");
        int recordCount = rows.length - 1; // Exclude header
        String[] headers = rows[0].split(",");
        String parsed = recordCount + " records with " + headers.length + " fields each";
        System.out.println("    [CSVProcessor] Parsed: " + parsed);
        return parsed;
    }

    /**
     * Analyzes the parsed CSV data.
     *
     * @param data the parsed data summary
     */
    @Override
    protected void analyzeData(String data) {
        System.out.println("    [CSVProcessor] Analysis: CSV contains " + data);
    }

    /**
     * Overrides the hook to provide CSV-specific start logging.
     */
    @Override
    protected void logStart() {
        System.out.println("    [CSVProcessor] === CSV Processing Started ===");
    }

    /**
     * Overrides the hook to provide CSV-specific end logging.
     */
    @Override
    protected void logEnd() {
        System.out.println("    [CSVProcessor] === CSV Processing Finished ===");
    }
}
