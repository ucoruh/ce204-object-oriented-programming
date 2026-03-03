package com.example.week10.proxy;

/**
 * Protection Proxy - ProtectionProxy (Access Control)
 *
 * Controls access to the RealImage based on user permissions.
 * Only users with the "ADMIN" role can display the image.
 * This demonstrates how a proxy can enforce access control.
 *
 * In the Proxy pattern:
 *   - This is a "Protection Proxy" variant
 *   - It checks permissions before delegating to the RealSubject
 *   - It can deny access entirely if permissions are insufficient
 *
 * Real-World Analogy:
 *   A security guard (proxy) at a building entrance. They check your
 *   badge before allowing you to enter the building (real subject).
 */
public class ProtectionProxy implements Image {

    private final String fileName;
    private final String userRole;
    private RealImage realImage;

    /**
     * Creates a protection proxy for an image with role-based access.
     *
     * @param fileName the image file name
     * @param userRole the role of the current user (e.g., "ADMIN", "GUEST")
     */
    public ProtectionProxy(String fileName, String userRole) {
        this.fileName = fileName;
        this.userRole = userRole;
    }

    /**
     * Displays the image only if the user has the ADMIN role.
     * Access is denied for other roles.
     */
    @Override
    public void display() {
        if ("ADMIN".equalsIgnoreCase(userRole)) {
            System.out.println("      [ProtectionProxy] Access GRANTED for role: "
                    + userRole);
            if (realImage == null) {
                realImage = new RealImage(fileName);
            }
            realImage.display();
        } else {
            System.out.println("      [ProtectionProxy] Access DENIED for role: "
                    + userRole);
            System.out.println("      [ProtectionProxy] You need ADMIN privileges "
                    + "to view: " + fileName);
        }
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
