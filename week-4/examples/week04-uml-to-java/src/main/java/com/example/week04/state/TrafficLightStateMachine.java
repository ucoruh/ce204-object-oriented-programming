package com.example.week04.state;

/**
 * CEN206 - Week 4: State Diagram -- Traffic Light
 *
 * Implements the State design pattern to model a UML state diagram.
 *
 * <pre>
 *        ┌─────────────────────────────────────────┐
 *        │                                         │
 *   ●───>│  RED  ──timer──>  GREEN  ──timer──>  YELLOW ─┘
 *        │                                         │
 *        └─────────────────────────────────────────┘
 *
 *  Events : timer (automatic transition)
 *           emergencyStop (from any state -> RED)
 *
 *  Guards : none in this simplified version
 * </pre>
 *
 * Each state is represented by an enum value, and transitions are
 * handled by a {@code next()} method -- mapping directly from the
 * arrows on the state diagram.
 */
public class TrafficLightStateMachine {

    // ----------------------------------------------------------------
    // State enum
    // ----------------------------------------------------------------

    /** The possible states of a traffic light. */
    public enum State {
        RED {
            @Override
            public State onTimer() {
                System.out.println("    RED -> GREEN (timer fired)");
                return GREEN;
            }
        },
        GREEN {
            @Override
            public State onTimer() {
                System.out.println("    GREEN -> YELLOW (timer fired)");
                return YELLOW;
            }
        },
        YELLOW {
            @Override
            public State onTimer() {
                System.out.println("    YELLOW -> RED (timer fired)");
                return RED;
            }
        };

        /** Transition triggered by the timer event. */
        public abstract State onTimer();

        /** Emergency stop always transitions to RED. */
        public State onEmergencyStop() {
            System.out.println("    " + name() + " -> RED (emergency stop!)");
            return RED;
        }
    }

    // ----------------------------------------------------------------
    // Context class (the state machine itself)
    // ----------------------------------------------------------------

    /** The traffic light context that holds the current state. */
    public static class TrafficLight {
        private State currentState;

        public TrafficLight() {
            this.currentState = State.RED;  // initial state
            System.out.println("  TrafficLight initialised in state: " + currentState);
        }

        /** Fires the timer event, causing a state transition. */
        public void timer() {
            currentState = currentState.onTimer();
        }

        /** Fires the emergency stop event. */
        public void emergencyStop() {
            currentState = currentState.onEmergencyStop();
        }

        public State getCurrentState() {
            return currentState;
        }
    }

    // ----------------------------------------------------------------
    // Demo
    // ----------------------------------------------------------------

    /** Runs the traffic light state machine demonstration. */
    public static void demo() {
        TrafficLight light = new TrafficLight();

        // Normal cycle: RED -> GREEN -> YELLOW -> RED
        System.out.println("\n  Normal cycle:");
        light.timer();  // RED -> GREEN
        light.timer();  // GREEN -> YELLOW
        light.timer();  // YELLOW -> RED

        // Another tick and then emergency stop
        System.out.println("\n  Emergency stop from GREEN:");
        light.timer();          // RED -> GREEN
        light.emergencyStop();  // GREEN -> RED

        System.out.println("\n  Final state: " + light.getCurrentState());
    }
}
