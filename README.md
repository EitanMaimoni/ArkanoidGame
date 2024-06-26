
## How to Run the Project

1. **Navigate to the Project Directory:**
    ```bash
    cd path/to/ArkanoidGameP2
    ```

2. **Compile the Java Files:**
    ```bash
    javac -d bin -cp "lib/biuoop-1.4.jar" src/*.java
    ```

3. **Create the JAR File:**
    ```bash
    jar cfm bin/ArkanoidGameP2.jar src/MANIFEST.MF -C bin .
    ```

4. **Run the JAR File:**
    ```bash
    java -jar bin/ArkanoidGameP2.jar
    ```

## Description of Classes

- **Ass3Game.java**: Entry point of the application, responsible for running the game.
- **Ball.java**: Represents a 2D ball object with position, radius, color, and velocity.
- **Block.java**: Represents a block in 2D space.
- **Collidable.java**: Interface representing an object that can be collided with.
- **CollisionInfo.java**: Represents information about a collision.
- **Game.java**: Represents the game, consisting of sprites and a game environment.
- **GameEnvironment.java**: Manages a collection of Collidable objects and provides methods for adding new objects and checking for collisions.
- **Line.java**: Represents a line in a 2D coordinate system.
- **Paddle.java**: Represents the player-controlled paddle in the game.
- **Point.java**: Represents a point in a 2D coordinate system.
- **Rectangle.java**: Represents a rectangle in 2D space.
- **Sprite.java**: Interface representing an object in the game.
- **SpriteCollection.java**: A collection of sprites that can be drawn on and updated.
- **Velocity.java**: Specifies the change in position on the `x` and `y` axes.

## Dependencies

- **biuoop-1.4.jar**: A required library for the GUI components.

## Author

Eitan Maimoni
