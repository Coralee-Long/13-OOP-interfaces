# Interfaces

## ⭐️ What is an Interface?
### An Interface is a `contract` or `blueprint` for a class.

**➡️ Interfaces define `what` a class does, but now `how`:**
```java
    // Playable.java

    public interface Playable {
        // Just write the plain method, with no body 
        void play();
    }
```
**➡️ Classes `implement` the interfaces to provide the `how`:**
```java
    // Video.java

    public class Video implements Playable {
        @Override
        public void play() {
        // play the video
        }
    }
```
```java
// Audio.java

    public class Audio implements Playable {
        @Override
        public void play() {
            // play the audio
        }
    }
```
---
## ⭐️ But what's the point?
**➡️ Interfaces allow us to write code that is `agnostic` to the implementation:**

```java
    // Player.java
    
    public class Player {
        public void play(Playable playable) {
            playable.play();
        }
    }
```
```java
// Main.java

    public class Main {
        public static void main(String[] args) {
            Player player = new Player();
            
            player.play(new Video());
            player.play(new Audio());
        }
    }
```
---
## ⭐️ Real-World Example 1: 
```java
    // Interfaces:

    public interface Prey {
    // declare method, but not body (so no implementation of the method)
    void flee();
    }

    public interface Predator {
    // declare method, but not body (so no implementation of the method)
    void hunt();
    }
```
```java
    // Classes:
    
    public class Rabbit implements Prey {
    // Define what this method is going to do (always use @Override)
        @Override
        public void flee() { // note how we have a body to set up implementation
            System.out.println("*The Rabbit is fleeing*");
        }
    }

    public class Wolf implements Predator {
        // Define what this method is going to do (always use @Override)
        @Override
        public void hunt() { // note how we have a body to set up implementation
            System.out.println("*The Wolf is hunting*");
        }
    }
    
    public class Fish implements Prey, Predator {
        @Override
        public void flee() { // note how we have a body to set up implementation
            System.out.println("*The Fish is fleeing*");
        }

        @Override
        public void hunt() { // note how we have a body to set up implementation
            System.out.println("*The Fish is hunting*");
        }
    }
    
```
```java
    // Main
    public class Main {
        public static void main(String[] args) {
            // Create instances of animals
            Rabbit rabbit = new Rabbit();
            Wolf wolf = new Wolf();
            Fish fish = new Fish();
            
            // Call methods
            rabbit.flee(); // *The Rabbit is fleeing*
            wolf.hunt(); // *The Wolf is hunting*
            
            // Classes can apply more than one interface
            fish.flee(); // *The Fish is fleeing*
            fish.hunt(); // *The Fish is fleeing*
        }
    }
```
## ⭐️ Polymorphism in Example 1:
```java
    // AnimalBehaviourController.java

    public class AnimalBehaviourController {
        public void makeHunt(Predator predator) {
            predator.hunt(); // Calls hunt method, regardless of Predator type
        }
        
        public void makeFlee(Prey prey) {
            prey.flee(); // Calls flee method, regardless of Prey type
        }
    }
```
```java
    // Main.java

    public class Main {
        public static void main(String[] args) {
            // Create instances of animals
            Rabbit rabbit = new Rabbit();
            Wolf wolf = new Wolf();
            Fish fish = new Fish();
    
            // Create the controller
            AnimalBehaviorController controller = new AnimalBehaviorController();
    
            // Use polymorphism to make animals perform their behaviors
            controller.makeFlee(rabbit); // *The Rabbit is fleeing
            controller.makeHunt(wolf);  // *The Wolf is hunting*
            controller.makeHunt(fish); // *The Fish is hunting*
            controller.makeFlee(fish); // *The Fish is fleeing*
        }
    }
```
---
## ⭐️ Real-World Example 2: Logger
```java
    // Logger.java

    public interface Logger {
        void log(String message);
    }
```
```java
    // ConsoleLogger.java

    public class ConsoleLogger implements Logger {
        @Override
        public void log(String message) {
        System.out.println(message);
        }
    }
```
```java
    // FileLogger.java
    
    public class FileLogger implements Logger {
        @Override
        public void log(String message) {
            // write message to file
        }
    }
```
**Usage:**
```java
    // User.java

    public class User {
        private final Logger logger;
        
        public User(Logger logger) {
            this.logger = logger;
        }
        
        public void checkUserName() {
            logger.log("Username is: " + userName);
        }
    }
```
**Common pattern to combine multiple implementations of an interface:**

```java
    // CompositeLogger.java

    public class CompositeLogger implements Logger {
        private final List<Logger> loggers;
    
        public static CompositeLogger of(Logger... loggers) {
            return new CompositeLogger(List.of(loggers));
        }
    
        @Override
        public void log(String message) {
            for (Logger logger: loggers) {
                logger.log(message);
                // Prints "Username is: John Doe"
                // Writes "Username is: Joan Doe" to file
            }
        }
    }
   
```
---