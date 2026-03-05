package com.example.week10.composite;

/**
 * =============================================================================
 * COMPOSITE PATTERN DEMO
 * =============================================================================
 *
 * Intent:
 *   Compose objects into tree structures to represent part-whole hierarchies.
 *   Composite lets clients treat individual objects and compositions of
 *   objects uniformly.
 *
 * Structure:
 *              [Component]
 *              /         \
 *          [Leaf]    [Composite]
 *                     - children: List<Component>
 *                     - add(Component)
 *                     - remove(Component)
 *
 * Participants in this demo:
 *   - Component:  FileSystemComponent (common interface)
 *   - Leaf:       File (no children)
 *   - Composite:  Directory (contains children, delegates operations)
 *
 * When to Use:
 *   - You want to represent part-whole hierarchies of objects
 *   - You want clients to be able to ignore the difference between
 *     compositions and individual objects
 *   - Tree-structured data (file systems, UI components, org charts)
 *
 * Real-World Analogy:
 *   A file system: a directory can contain files and other directories.
 *   You can ask for the "size" of a file (direct) or a directory
 *   (sum of all contents). The operation is uniform.
 * =============================================================================
 */
public class CompositeDemo {

    public static void demo() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("  PATTERN 3: COMPOSITE");
        System.out.println("  Treating individual objects and compositions uniformly");
        System.out.println("-------------------------------------------------------------");

        // Build a file system tree structure:
        //
        //  root/
        //    |- readme.txt (1000 bytes)
        //    |+ src/
        //    |   |- Main.java (2500 bytes)
        //    |   |- Utils.java (1800 bytes)
        //    |   |+ resources/
        //    |       |- config.xml (500 bytes)
        //    |       |- data.json (3200 bytes)
        //    |+ docs/
        //        |- guide.pdf (15000 bytes)

        // Create leaf nodes (files)
        File readme = new File("readme.txt", 1000);
        File mainJava = new File("Main.java", 2500);
        File utils = new File("Utils.java", 1800);
        File config = new File("config.xml", 500);
        File data = new File("data.json", 3200);
        File guide = new File("guide.pdf", 15000);

        // Create composite nodes (directories)
        Directory root = new Directory("root");
        Directory src = new Directory("src");
        Directory resources = new Directory("resources");
        Directory docs = new Directory("docs");

        // Build the tree
        root.add(readme);
        root.add(src);
        root.add(docs);

        src.add(mainJava);
        src.add(utils);
        src.add(resources);

        resources.add(config);
        resources.add(data);

        docs.add(guide);

        // Display the entire tree - uniform treatment of files and directories
        System.out.println("  File system tree:");
        root.display("    ");
        System.out.println();

        // Demonstrate uniform operation on both File and Directory
        System.out.println("  Uniform getSize() calls:");
        System.out.println("    Single file  - readme.txt:  "
                + readme.getSize() + " bytes");
        System.out.println("    Subtree      - src/:         "
                + src.getSize() + " bytes");
        System.out.println("    Subtree      - resources/:   "
                + resources.getSize() + " bytes");
        System.out.println("    Entire tree  - root/:        "
                + root.getSize() + " bytes");

        System.out.println();
        System.out.println("  Key Takeaway: getSize() works the same on File and Directory.");
        System.out.println("  The client code does not need to distinguish between them.");
        System.out.println();
    }
}
