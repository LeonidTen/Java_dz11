
/*Урок 5. От простого к практике
 * Реализовать визуализацию */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Character {
    private int initiative;
    private int health;
    private String state = "stand";

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public abstract void attack(List<Character> characters);

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
}

public class Archer extends Character {
    public void attack(List<Character> characters) {
        for (Character character : characters) {
            if (character instanceof Peasant && character.getState().equals("stand")) {
                character.takeDamage(10);
                System.out.println("Archer shot at Peasant!");
                character.setState("busy");
                break;
            }
        }
    }
}

public class Peasant extends Character {
    public void attack(List<Character> characters) {
        // do nothing
    }
}

public class Main {
    public static void main(String[] args) {
        List<Character> team1 = new ArrayList<>();
        List<Character> team2 = new ArrayList<>();

        Archer archer1 = new Archer();
        archer1.setInitiative(5);
        archer1.setHealth(100);

        Peasant peasant1 = new Peasant();
        peasant1.setInitiative(3);
        peasant1.setHealth(50);

        Peasant peasant2 = new Peasant();
        peasant2.setInitiative(4);
        peasant2.setHealth(50);

        Archer archer2 = new Archer();
        archer2.setInitiative(6);
        archer2.setHealth(100);

        team1.add(archer1);
        team1.add(peasant1);
        team1.add(peasant2);
        team2.add(archer2);

        List<Character> allCharacters = new ArrayList<>();
        allCharacters.addAll(team1);
        allCharacters.addAll(team2);

        Collections.sort(allCharacters, (c1, c2) -> c2.getInitiative() - c1.getInitiative());

        for (Character character : allCharacters) {
            System.out.println("Health of " + character.getClass().getSimpleName() + " is " + character.getHealth());
        }

        for (Character character : allCharacters) {
            character.attack(allCharacters);
        }

        System.out.println("\n--- Characters attacked! ---\n");

        for (Character character : allCharacters) {
            System.out.println("Health of " + character.getClass().getSimpleName() + " is " + character.getHealth());
        }
    }
}