package com.example.week10.proxy;

/**
 * Subject Interface - Image
 *
 * Declares the common interface for both the RealSubject and the Proxy.
 * The Proxy can be used anywhere the RealSubject is expected because
 * they share this interface.
 *
 * In the Proxy pattern:
 *   - This is the "Subject" role
 *   - Both RealSubject and Proxy implement this interface
 *   - The client works with this interface, unaware of the proxy
 */
public interface Image {

    /**
     * Displays the image.
     */
    void display();

    /**
     * Returns the file name of this image.
     *
     * @return the image file name
     */
    String getFileName();
}
