package com.example.week10.bridge;

/**
 * =============================================================================
 * BRIDGE PATTERN DEMO
 * =============================================================================
 *
 * Intent:
 *   Decouple an abstraction from its implementation so that the two can
 *   vary independently. The "bridge" is the composition link between them.
 *
 * Structure:
 *   [Abstraction] -----bridge----> [Implementor]
 *        |                               |
 *   [RefinedAbstraction]        [ConcreteImplementor A]
 *                               [ConcreteImplementor B]
 *
 * Participants in this demo:
 *   - Abstraction:           RemoteControl
 *   - RefinedAbstraction:    AdvancedRemoteControl
 *   - Implementor:           Device
 *   - ConcreteImplementors:  TV, Radio
 *
 * When to Use:
 *   - You want to avoid a permanent binding between abstraction and implementation
 *   - Both abstractions and implementations should be extensible via subclasses
 *   - You want to share an implementation among multiple objects
 *   - When a class hierarchy "explodes" because of orthogonal dimensions
 *     (e.g., Shape x Color would produce RedCircle, BlueCircle, RedSquare...)
 *
 * Key Benefit:
 *   Without Bridge, you would need: TVRemote, RadioRemote, TVAdvancedRemote,
 *   RadioAdvancedRemote, etc. (M x N classes). With Bridge: M + N classes.
 * =============================================================================
 */
public class BridgeDemo {

    public static void demo() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("  PATTERN 2: BRIDGE");
        System.out.println("  Separating abstraction from implementation");
        System.out.println("-------------------------------------------------------------");

        // Basic remote controlling a TV
        System.out.println("  --- Basic Remote + TV ---");
        Device tv = new TV();
        RemoteControl tvRemote = new RemoteControl(tv);
        tvRemote.togglePower();       // Turn TV on
        tvRemote.volumeUp();          // Volume: 30 -> 40
        tvRemote.channelUp();         // Channel: 1 -> 2
        tvRemote.printDeviceStatus();
        System.out.println();

        // Advanced remote controlling a Radio
        System.out.println("  --- Advanced Remote + Radio ---");
        Device radio = new Radio();
        AdvancedRemoteControl radioRemote = new AdvancedRemoteControl(radio);
        radioRemote.togglePower();    // Turn Radio on
        radioRemote.volumeUp();       // Volume: 20 -> 30
        radioRemote.goToChannel(101); // Jump to station 101 FM
        radioRemote.mute();           // Mute (volume -> 0)
        radioRemote.printDeviceStatus();
        System.out.println();

        // Advanced remote controlling a TV (any remote works with any device!)
        System.out.println("  --- Advanced Remote + TV ---");
        Device tv2 = new TV();
        AdvancedRemoteControl tvAdvRemote = new AdvancedRemoteControl(tv2);
        tvAdvRemote.togglePower();
        tvAdvRemote.goToChannel(42);
        tvAdvRemote.printDeviceStatus();

        System.out.println();
        System.out.println("  Key Takeaway: Any remote type works with any device type.");
        System.out.println("  Both hierarchies evolve independently via the bridge.");
        System.out.println();
    }
}
