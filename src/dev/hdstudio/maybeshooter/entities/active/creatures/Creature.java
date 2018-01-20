package dev.hdstudio.maybeshooter.entities.active.creatures;

import dev.hdstudio.maybeshooter.entities.active.Active;

import java.awt.*;

public abstract class Creature extends Active {

    protected int points;
    protected int maxHealth;

    public Creature(double x, double y) {
        super(x, y);
        speed = 1;
    }

    protected abstract void tick();

    protected abstract void render(Graphics2D g);

    // for child classes

    protected void checkMaxHealth() {
        if (health > maxHealth)
            health = maxHealth;
    }

    public int getPoints() {
        return points;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
