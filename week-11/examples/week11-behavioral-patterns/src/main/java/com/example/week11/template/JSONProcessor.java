package com.example.week11.template;

/**
 * Template Method Pattern - Concrete Implementation: JSON Processor
 *
 * Provides concrete implementations of the abstract steps defined
 * in DataProcessor, specialized for processing JSON data. Uses the
 * same algorithm skeleton as CSVProcessor but with different step
 * implementations -- that is the essence of the Template Method pattern.
 */
public class JSONProcessor extends DataProcessor {

    /**
     * Reads JSON data from a simulated data source.
     * In a real application, this would read from a .json file or API.
     *
     * @return raw JSON data
     */
    @Override
    protected String readData() {
        String data = "{\"students\":[{\"name\":\"Alice\"},{\"name\":\"Bob\"},{\"name\":\"Charlie\"}]}";
        System.out.println("    [JSONProcessor] Reading JSON data...");
        System.out.println("    Raw: " + data);
        return data;
    }

    /**
     * Parses JSON data by counting objects (simplified).
     * In a real application, this would use a JSON library like Jackson or Gson.
     *
     * @param data the raw JSON string
     * @return summary of parsed data
     */
    @Override
    protected String parseData(String data) {
        // Simple counting of JSON objects by counting "name" occurrences
        int count = 0;
        int index = 0;
        while ((index = data.indexOf("\"name\"", index)) != -1) {
            count++;
            index++;
        }
        String parsed = count + " student objects found";
        System.out.println("    [JSONProcessor] Parsed: " + parsed);
        return parsed;
    }

    /**
     * Analyzes the parsed JSON data.
     *
     * @param data the parsed data summary
     */
    @Override
    protected void analyzeData(String data) {
        System.out.println("    [JSONProcessor] Analysis: JSON contains " + data);
    }

    // Note: JSONProcessor uses the DEFAULT hook methods from DataProcessor
    // (logStart and logEnd) without overriding them.
    // This demonstrates that hooks are optional.
}
