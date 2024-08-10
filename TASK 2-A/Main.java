// Singleton class to manage game state
class GameState {
    private static GameState instance;
    private int level;
    private String difficulty;

    // Private constructor to prevent instantiation
    private GameState() {
        level = 1;
        difficulty = "Easy";
    }

    // Public method to provide access to the single instance of GameState
    public static GameState getInstance() {
        if (instance == null) {
            synchronized (GameState.class) {
                if (instance == null) {
                    instance = new GameState();
                }
            }
        }
        return instance;
    }

    // Methods to manage game state
    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }
}

// Abstract Enemy class
abstract class Enemy {
    public abstract void attack();
}

// Concrete classes for different types of enemies
class EasyEnemy extends Enemy {
    public void attack() {
        System.out.println("Easy Enemy attacks with minimal damage.");
    }
}

class MediumEnemy extends Enemy {
    public void attack() {
        System.out.println("Medium Enemy attacks with moderate damage.");
    }
}

class HardEnemy extends Enemy {
    public void attack() {
        System.out.println("Hard Enemy attacks with maximum damage.");
    }
}

// EnemyFactory class to create different types of enemies
class EnemyFactory {
    public Enemy createEnemy(String difficulty) {
        switch (difficulty) {
            case "Easy":
                return new EasyEnemy();
            case "Medium":
                return new MediumEnemy();
            case "Hard":
                return new HardEnemy();
            default:
                return null;
        }
    }
}

// Abstract Weapon class
abstract class Weapon {
    public abstract void use();
}

// Concrete classes for different types of weapons
class Sword extends Weapon {
    public void use() {
        System.out.println("Using a Sword.");
    }
}

class Bow extends Weapon {
    public void use() {
        System.out.println("Using a Bow.");
    }
}

// Abstract PowerUp class
abstract class PowerUp {
    public abstract void apply();
}

// Concrete classes for different types of power-ups
class HealthBoost extends PowerUp {
    public void apply() {
        System.out.println("Health Boost applied.");
    }
}

class SpeedBoost extends PowerUp {
    public void apply() {
        System.out.println("Speed Boost applied.");
    }
}

// Abstract factory interface for creating weapons and power-ups
interface GameItemFactory {
    Weapon createWeapon();
    PowerUp createPowerUp();
}

// Concrete factory classes for creating specific weapons and power-ups
class EasyGameItemFactory implements GameItemFactory {
    public Weapon createWeapon() {
        return new Sword();
    }

    public PowerUp createPowerUp() {
        return new HealthBoost();
    }
}

class HardGameItemFactory implements GameItemFactory {
    public Weapon createWeapon() {
        return new Bow();
    }

    public PowerUp createPowerUp() {
        return new SpeedBoost();
    }
}

// Main class to combine all the patterns and simulate the game
public class Main {
    public static void main(String[] args) {
        // Singleton pattern: Managing game state
        GameState gameState = GameState.getInstance();
        gameState.setLevel(1);
        gameState.setDifficulty("Medium");

        // Factory Method pattern: Creating enemies based on difficulty
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy enemy = enemyFactory.createEnemy(gameState.getDifficulty());
        enemy.attack();

        // Abstract Factory pattern: Creating weapons and power-ups based on difficulty
        GameItemFactory itemFactory;
        if (gameState.getDifficulty().equals("Easy")) {
            itemFactory = new EasyGameItemFactory();
        } else {
            itemFactory = new HardGameItemFactory();
        }

        Weapon weapon = itemFactory.createWeapon();
        PowerUp powerUp = itemFactory.createPowerUp();

        weapon.use();
        powerUp.apply();
    }
}
