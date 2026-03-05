package com.example.week11.observer;

/**
 * Observer Pattern - Concrete Subject
 *
 * The Editor is a concrete subject that uses EventManager (composition)
 * to manage its observers. When the editor opens or saves a file, it
 * notifies all subscribed listeners via the EventManager.
 *
 * Structure:
 *   Editor (Concrete Subject)
 *       - events: EventManager
 *       + openFile(filename): void
 *       + saveFile(filename): void
 *
 * Note: Uses composition with EventManager rather than inheritance,
 * following the "favor composition over inheritance" principle.
 */
public class Editor {

    /** The event manager that handles subscriptions and notifications */
    public final EventManager events;

    /** The file currently open in the editor */
    private String currentFile;

    /**
     * Creates an Editor with support for "open" and "save" events.
     */
    public Editor() {
        this.events = new EventManager("open", "save");
    }

    /**
     * Opens a file and notifies all "open" event listeners.
     *
     * @param filename the name of the file to open
     */
    public void openFile(String filename) {
        this.currentFile = filename;
        System.out.println("    [Editor] Opening file: " + filename);
        events.notifyListeners("open", filename);
    }

    /**
     * Saves the current file and notifies all "save" event listeners.
     */
    public void saveFile() {
        if (currentFile != null) {
            System.out.println("    [Editor] Saving file: " + currentFile);
            events.notifyListeners("save", currentFile);
        } else {
            System.out.println("    [Editor] No file to save.");
        }
    }
}
