package com.example.week10.proxy;

/**
 * Real Subject - RealImage
 *
 * The real object that the proxy represents. Loading an image from disk
 * is an expensive operation, so we want to delay it until actually needed.
 *
 * In the Proxy pattern:
 *   - This is the "RealSubject" role
 *   - It defines the real object that the proxy represents
 *   - It does the actual heavy work (loading from disk)
 */
public class RealImage implements Image {

    private final String fileName;

    /**
     * Creates a RealImage and immediately loads it from disk.
     * This is the EXPENSIVE operation that the proxy helps defer.
     *
     * @param fileName the image file name to load
     */
    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(); // Expensive operation happens at construction time
    }

    /**
     * Simulates the expensive operation of loading an image from disk.
     */
    private void loadFromDisk() {
        System.out.println("      [RealImage] Loading image from disk: " + fileName);
        System.out.println("      [RealImage] (This is an expensive operation...)");
    }

    @Override
    public void display() {
        System.out.println("      [RealImage] Displaying: " + fileName);
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
