package com.example.week11.observer;

/**
 * Observer Pattern - Concrete Observer: Log Writer
 *
 * Logs event information to a file (simulated with console output).
 * In a real application, this would write to a log file or logging
 * framework; here we simulate the behavior with console output.
 */
public class LogListener implements EventListener {

    /** The log file path (simulated) */
    private final String logFile;

    /**
     * Creates a LogListener that writes to the specified log file.
     *
     * @param logFile the path of the log file (simulated)
     */
    public LogListener(String logFile) {
        this.logFile = logFile;
    }

    /**
     * Handles the event by "writing" a log entry.
     *
     * @param eventType the type of event
     * @param data      the event data (e.g., filename)
     */
    @Override
    public void update(String eventType, String data) {
        System.out.println("    [LogListener] Writing to " + logFile
            + ": Event '" + eventType + "' occurred with data '" + data + "'");
    }
}
