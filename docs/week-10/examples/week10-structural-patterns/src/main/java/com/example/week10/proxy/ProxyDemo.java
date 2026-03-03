package com.example.week10.proxy;

/**
 * =============================================================================
 * PROXY PATTERN DEMO
 * =============================================================================
 *
 * Intent:
 *   Provide a surrogate or placeholder for another object to control
 *   access to it.
 *
 * Structure:
 *   Client --> [Subject Interface]
 *                  |            \
 *            [RealSubject]    [Proxy] ---delegates--> [RealSubject]
 *
 * Participants in this demo:
 *   - Subject:     Image (common interface)
 *   - RealSubject: RealImage (expensive to create)
 *   - Proxy:       ProxyImage (virtual/lazy proxy)
 *   - Proxy:       ProtectionProxy (access control proxy)
 *
 * Types of Proxy demonstrated:
 *   1. Virtual Proxy (ProxyImage):
 *      - Delays expensive object creation until actually needed
 *      - Common in image viewers, document editors, large reports
 *
 *   2. Protection Proxy (ProtectionProxy):
 *      - Controls access based on permissions/roles
 *      - Common in security systems, file access control
 *
 * When to Use:
 *   - Lazy initialization (virtual proxy): delay expensive object creation
 *   - Access control (protection proxy): restrict who can use an object
 *   - Local representation of remote object (remote proxy)
 *   - Logging requests (logging proxy)
 *   - Caching results (caching proxy)
 *
 * Real-World Analogy:
 *   A credit card is a proxy for a bank account. It has the same
 *   interface (you can pay with it) but adds access control (PIN)
 *   and defers the actual bank transaction.
 * =============================================================================
 */
public class ProxyDemo {

    public static void demo() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("  PATTERN 7: PROXY");
        System.out.println("  Providing a surrogate or placeholder for another object");
        System.out.println("-------------------------------------------------------------");

        // -------------------------------------------------------
        // Part A: Virtual Proxy (Lazy Loading)
        // -------------------------------------------------------
        System.out.println("  --- Part A: Virtual Proxy (Lazy Loading) ---");
        System.out.println();

        // Creating proxy images is cheap - no disk I/O happens
        System.out.println("  Creating three proxy images:");
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");
        Image image3 = new ProxyImage("photo3.jpg");
        System.out.println();

        // Only photo1 is loaded when we display it
        System.out.println("  Displaying image1 (first time - will load from disk):");
        image1.display();
        System.out.println();

        // Second display of photo1 - uses cached RealImage
        System.out.println("  Displaying image1 (second time - cached):");
        image1.display();
        System.out.println();

        // photo2 and photo3 are NEVER loaded because we never display them
        System.out.println("  Note: image2 and image3 were NEVER loaded from disk!");
        System.out.println("  This saves resources for images that may never be viewed.");
        System.out.println();

        // -------------------------------------------------------
        // Part B: Protection Proxy (Access Control)
        // -------------------------------------------------------
        System.out.println("  --- Part B: Protection Proxy (Access Control) ---");
        System.out.println();

        // Admin user - access granted
        Image adminImage = new ProtectionProxy("confidential-report.png", "ADMIN");
        System.out.println("  Admin trying to view confidential image:");
        adminImage.display();
        System.out.println();

        // Guest user - access denied
        Image guestImage = new ProtectionProxy("confidential-report.png", "GUEST");
        System.out.println("  Guest trying to view same confidential image:");
        guestImage.display();

        System.out.println();
        System.out.println("  Key Takeaway: Proxies control object access transparently.");
        System.out.println("  Virtual proxies delay creation; protection proxies enforce security.");
        System.out.println();
    }
}
