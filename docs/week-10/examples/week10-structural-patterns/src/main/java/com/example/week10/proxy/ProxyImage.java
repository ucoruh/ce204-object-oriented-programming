package com.example.week10.proxy;

/**
 * Virtual Proxy - ProxyImage (Lazy Loading)
 *
 * Controls access to the RealImage by deferring its creation until
 * it is actually needed (lazy initialization). The image is only
 * loaded from disk when display() is first called.
 *
 * In the Proxy pattern:
 *   - This is the "Proxy" role (specifically a "Virtual Proxy")
 *   - It maintains a reference to the RealSubject (created on demand)
 *   - It controls access to the RealSubject
 *   - It provides the same interface as the RealSubject
 *
 * Types of Proxy:
 *   1. Virtual Proxy (this class) - delays expensive object creation
 *   2. Protection Proxy - controls access based on permissions
 *   3. Remote Proxy - represents an object in a different address space
 *   4. Caching Proxy - stores results of expensive operations
 *   5. Logging Proxy - logs requests before passing to the real subject
 */
public class ProxyImage implements Image {

    private final String fileName;
    private RealImage realImage; // Created lazily on first use

    /**
     * Creates a proxy for an image. The real image is NOT loaded yet.
     * This is the key benefit - creation is cheap.
     *
     * @param fileName the image file name
     */
    public ProxyImage(String fileName) {
        this.fileName = fileName;
        System.out.println("      [ProxyImage] Proxy created for: " + fileName
                + " (image NOT loaded yet)");
    }

    /**
     * Displays the image. On the first call, the real image is loaded
     * from disk (lazy initialization). On subsequent calls, the cached
     * real image is used directly.
     */
    @Override
    public void display() {
        // Lazy initialization - only load when actually needed
        if (realImage == null) {
            System.out.println("      [ProxyImage] First access - creating RealImage now...");
            realImage = new RealImage(fileName);
        } else {
            System.out.println("      [ProxyImage] Using cached RealImage...");
        }
        realImage.display();
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
