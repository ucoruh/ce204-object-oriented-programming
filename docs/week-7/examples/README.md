# CEN206 - Week 7: UMPLE Part-2 Examples (Advanced Features)

This folder contains UMPLE (`.ump`) files demonstrating advanced UMPLE features: state machines, guards, concurrent regions, and code generation with mixins.

## Files

| File | Topic | Description |
|------|-------|-------------|
| `state-machine-basic.ump` | Basic State Machine | Traffic light with states, events, entry/exit actions |
| `state-machine-nested.ump` | Nested States | Phone with hierarchical sub-states (On.Locked, On.Home, On.InApp.AppRunning) |
| `state-machine-guards.ump` | Guards & Actions | Elevator with guard conditions, transition actions, entry/exit actions |
| `state-machine-concurrent.ump` | Concurrent Regions | Washing machine with three independent state machines (motor, water, door) |
| `code-generation-example.ump` | Full Example with Mixins | Online shop with classes, associations, state machines, traits, and mixins |

## How to Compile

### Quick Test: try.umple.org

1. Visit **[https://try.umple.org](https://try.umple.org)**.
2. Paste the contents of any `.ump` file.
3. View the generated class diagram and Java code.

### Command Line

```bash
# Generate Java code
java -jar umple.jar state-machine-basic.ump

# Compile the generated Java
javac *.java

# Run (for files with main methods)
java TrafficLight
java Phone
java Elevator
java WashingMachine
java OnlineShopDemo
```

## UMPLE State Machine Syntax Reference

### Basic State Machine

```umple
class MyClass {
    // "status" is the state machine name
    status {
        StateA {
            eventX -> StateB;     // transition on event
        }
        StateB {
            eventY -> StateA;
        }
    }
}
```

### Entry and Exit Actions

Entry actions run when entering a state. Exit actions run when leaving.

```umple
class MyClass {
    status {
        Active {
            entry / {
                System.out.println("Entered Active state");
                // any Java code here
            }
            exit / {
                System.out.println("Leaving Active state");
            }
            deactivate -> Inactive;
        }
        Inactive {
            activate -> Active;
        }
    }
}
```

### Transition Actions

Actions that run during a specific transition (not on every entry/exit).

```umple
class MyClass {
    status {
        StateA {
            // Action runs only for this specific transition
            myEvent / {
                System.out.println("Transitioning!");
            } -> StateB;
        }
        StateB { }
    }
}
```

### Guard Conditions

Guards are boolean expressions in square brackets. The transition only fires if the guard is true.

```umple
class Door {
    Boolean hasKey = false;

    doorState {
        Locked {
            // Guard: only unlock if hasKey is true
            tryOpen [hasKey] -> Open;

            // Alternative path when guard fails
            tryOpen [!hasKey] / {
                System.out.println("Door is locked!");
            } -> Locked;
        }
        Open {
            close -> Locked;
        }
    }
}
```

### Nested (Hierarchical) States

States can contain sub-states. Events handled at the parent level apply to all children.

```umple
class Device {
    deviceState {
        Off {
            turnOn -> On;
        }
        On {
            // This event applies to ALL sub-states of On
            turnOff -> Off;

            // Sub-states
            Idle {
                start -> Running;
            }
            Running {
                pause -> Paused;
            }
            Paused {
                resume -> Running;
            }
        }
    }
}
```

### Concurrent State Regions

Define multiple state machines in one class. Each runs independently.

```umple
class Robot {
    // Region 1: movement
    movementState {
        Stationary { move -> Moving; }
        Moving { stop -> Stationary; }
    }

    // Region 2: arm
    armState {
        ArmIdle { grab -> Grabbing; }
        Grabbing { release -> ArmIdle; }
    }

    // Region 3: sensor
    sensorState {
        Scanning { detect -> Detected; }
        Detected { reset -> Scanning; }
    }
}
```

### Self-Transitions

```umple
class Counter {
    Integer count = 0;
    status {
        Active {
            // Self-transition: stays in same state but runs action
            increment / { count++; } -> Active;
        }
    }
}
```

### Auto-Transitions

Transitions that fire automatically after entry actions complete.

```umple
class Process {
    processState {
        Starting {
            entry / { System.out.println("Starting..."); }
            -> Running;    // auto-transition (no event needed)
        }
        Running {
            stop -> Stopped;
        }
        Stopped { }
    }
}
```

## Generated Code Structure

When you compile an UMPLE file with a state machine, the generated Java code includes:

1. **Enum** for the state machine states.
2. **Getter** method (`getStatusFullName()`) to query the current state.
3. **Event methods** (e.g., `timer()`, `submit()`) that trigger transitions.
4. **Guard evaluation** inside event methods.
5. **Entry/exit/transition actions** called at the appropriate points.

Example of generated code structure:

```java
// Generated by UMPLE
public class TrafficLight {
    public enum Status { Red, Green, Yellow }

    private Status status;

    public TrafficLight() {
        setStatus(Status.Red);  // initial state
    }

    public boolean timer() {
        switch (status) {
            case Red:
                setStatus(Status.Green);
                return true;
            case Green:
                setStatus(Status.Yellow);
                return true;
            case Yellow:
                setStatus(Status.Red);
                return true;
        }
        return false;
    }

    private void setStatus(Status newStatus) {
        // exit action for old state
        // set state
        status = newStatus;
        // entry action for new state
    }
}
```

## Further Reading

- [UMPLE State Machines](https://cruise.umple.org/umple/StateMachines.html)
- [UMPLE User Manual](https://cruise.umple.org/umple/)
- [UMPLE Traits](https://cruise.umple.org/umple/Traits.html)
- [UMPLE Mixins](https://cruise.umple.org/umple/Mixins.html)
- [Try UMPLE Online](https://try.umple.org)
