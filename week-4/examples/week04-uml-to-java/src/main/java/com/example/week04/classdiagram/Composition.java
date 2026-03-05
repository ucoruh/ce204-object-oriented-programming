package com.example.week04.classdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CEN206 - Week 4: Composition Relationship
 *
 * Composition is a <b>strong "has-a"</b> relationship represented in UML
 * by a <b>filled (black) diamond</b> on the container side.
 *
 * Key point: the contained objects <b>cannot</b> exist independently.
 * When the House is destroyed, its Rooms are destroyed too.
 * The container is responsible for the lifecycle of the parts.
 *
 * <pre>
 * ┌──────────┐        ┌──────────┐
 * │   House   │◆──────│   Room   │
 * └──────────┘  1  *  └──────────┘
 *       (composition: filled diamond)
 *
 * ┌──────────┐        ┌──────────┐
 * │   Room    │◆──────│  Window  │
 * └──────────┘  1  *  └──────────┘
 * </pre>
 */
public class Composition {

    // ----------------------------------------------------------------
    // Window -- part of a Room (cannot exist alone)
    // ----------------------------------------------------------------

    /**
     * A Window is part of a Room.
     * It is created inside the Room and has no public constructor
     * that would let it float freely.  This enforces composition.
     */
    public static class Window {
        private final String type;   // e.g., "double-pane", "skylight"
        private final double width;  // metres
        private final double height;

        // Package-private constructor: only Room should create Windows
        Window(String type, double width, double height) {
            this.type = type;
            this.width = width;
            this.height = height;
        }

        public String getType() {
            return type;
        }

        public double getWidth() {
            return width;
        }

        public double getHeight() {
            return height;
        }

        @Override
        public String toString() {
            return "Window(" + type + ", " + width + "x" + height + "m)";
        }
    }

    // ----------------------------------------------------------------
    // Room -- part of a House (cannot exist alone)
    // ----------------------------------------------------------------

    /**
     * A Room is created inside a House.
     * It composes Windows, which it creates and owns.
     */
    public static class Room {
        private final String name;          // e.g., "Living Room"
        private final double areaSqMeters;
        private final List<Window> windows = new ArrayList<>();

        // Package-private: only House should create Rooms
        Room(String name, double areaSqMeters) {
            this.name = name;
            this.areaSqMeters = areaSqMeters;
        }

        /**
         * Adds a window to this room.
         * The Room owns the window (composition).
         */
        public void addWindow(String type, double width, double height) {
            windows.add(new Window(type, width, height));
        }

        public String getName() {
            return name;
        }

        public double getAreaSqMeters() {
            return areaSqMeters;
        }

        public List<Window> getWindows() {
            return Collections.unmodifiableList(windows);
        }

        @Override
        public String toString() {
            return "Room(" + name + ", " + areaSqMeters + " m^2, "
                    + windows.size() + " windows)";
        }
    }

    // ----------------------------------------------------------------
    // House -- the composite root
    // ----------------------------------------------------------------

    /**
     * A House <b>composes</b> Rooms.
     * Rooms are created inside the House and destroyed with it.
     */
    public static class House {
        private final String address;
        private final List<Room> rooms = new ArrayList<>();

        public House(String address) {
            this.address = address;
        }

        /**
         * Creates a room inside this house (composition).
         * Notice: the caller does not create the Room -- the House does.
         *
         * @return the newly created Room so callers can add windows to it
         */
        public Room createRoom(String name, double areaSqMeters) {
            Room room = new Room(name, areaSqMeters);
            rooms.add(room);
            return room;
        }

        public List<Room> getRooms() {
            return Collections.unmodifiableList(rooms);
        }

        public String getAddress() {
            return address;
        }

        /**
         * When the house is "demolished", all rooms (and their windows)
         * are destroyed.  In Java we simulate this by clearing the list;
         * in languages with manual memory management the objects would
         * be explicitly freed.
         */
        public void demolish() {
            System.out.println("  Demolishing " + this + " ...");
            rooms.clear();  // all Room (and Window) references gone
            System.out.println("  All rooms and windows are now destroyed.");
        }

        @Override
        public String toString() {
            return "House(" + address + ")";
        }
    }

    // ----------------------------------------------------------------
    // Demo
    // ----------------------------------------------------------------

    /** Runs the composition demonstration. */
    public static void demo() {
        // The House creates its Rooms (composition)
        House house = new House("123 Elm Street");

        Room livingRoom = house.createRoom("Living Room", 30.0);
        livingRoom.addWindow("double-pane", 1.5, 1.2);
        livingRoom.addWindow("double-pane", 1.5, 1.2);

        Room kitchen = house.createRoom("Kitchen", 18.0);
        kitchen.addWindow("skylight", 0.8, 0.8);

        Room bedroom = house.createRoom("Bedroom", 22.0);
        bedroom.addWindow("double-pane", 1.2, 1.0);

        System.out.println(house + " contains:");
        for (Room room : house.getRooms()) {
            System.out.println("  " + room);
            for (Window w : room.getWindows()) {
                System.out.println("      " + w);
            }
        }

        // Demolish the house -- all parts disappear
        System.out.println();
        house.demolish();
        System.out.println("  Rooms remaining: " + house.getRooms().size());
    }
}
