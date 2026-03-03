package com.example.week11.template;

/**
 * Template Method Pattern - Abstract Class
 *
 * Defines the skeleton (template) of the data processing algorithm
 * in the process() method. The overall algorithm structure is fixed,
 * but individual steps are deferred to subclasses via abstract methods.
 *
 * Structure:
 *   DataProcessor (Abstract Class)
 *       + process(): void             [TEMPLATE METHOD - final]
 *       # readData(): String          [abstract - subclass provides]
 *       # parseData(data): String     [abstract - subclass provides]
 *       # analyzeData(data): void     [abstract - subclass provides]
 *       # logStart(): void            [hook - optional override]
 *       # logEnd(): void              [hook - optional override]
 *
 * Key Concepts:
 *   - Template Method: final method defining the algorithm skeleton
 *   - Abstract Methods: steps that subclasses MUST implement
 *   - Hook Methods: optional steps with default behavior that subclasses CAN override
 */
public abstract class DataProcessor {

    /**
     * The Template Method - defines the algorithm skeleton.
     * This method is final to prevent subclasses from changing
     * the overall algorithm structure. Only the individual steps
     * (readData, parseData, analyzeData) can be customized.
     */
    public final void process() {
        logStart();
        String rawData = readData();
        String parsedData = parseData(rawData);
        analyzeData(parsedData);
        logEnd();
    }

    /**
     * Step 1: Read raw data from the source.
     * Subclasses provide the specific reading logic.
     *
     * @return the raw data as a string
     */
    protected abstract String readData();

    /**
     * Step 2: Parse the raw data into a usable format.
     * Subclasses provide the specific parsing logic.
     *
     * @param data the raw data to parse
     * @return the parsed data as a string
     */
    protected abstract String parseData(String data);

    /**
     * Step 3: Analyze the parsed data.
     * Subclasses provide the specific analysis logic.
     *
     * @param data the parsed data to analyze
     */
    protected abstract void analyzeData(String data);

    /**
     * Hook method: called at the start of processing.
     * Subclasses can override this to add custom logging.
     * Default implementation provides basic logging.
     */
    protected void logStart() {
        System.out.println("    [DataProcessor] Starting data processing...");
    }

    /**
     * Hook method: called at the end of processing.
     * Subclasses can override this to add custom logging.
     * Default implementation provides basic logging.
     */
    protected void logEnd() {
        System.out.println("    [DataProcessor] Data processing complete.");
    }
}
