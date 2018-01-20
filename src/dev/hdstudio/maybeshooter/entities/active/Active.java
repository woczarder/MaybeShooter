package dev.hdstudio.maybeshooter.entities.active;

import dev.hdstudio.maybeshooter.entities.Entity;
import dev.hdstudio.maybeshooter.entities.EntityManager;

import java.awt.*;

public abstract class Active extends Entity {

    protected double speed, rotation;
    protected int health;
    boolean alive;
    protected double xChange, yChange;

    public Active(double x, double y) {
        super(x, y);
        setAlive(true);
    }

    protected abstract void tick();

    protected abstract void render(Graphics2D g);

    // for child classes

    protected void move() {
        yChange = Math.sin(rotation) * speed;
        xChange = Math.cos(rotation) * speed;

        x += xChange;
        y += yChange;
    }

    protected void teleportOutBounds() {
        if (y < -1)
            y = EntityManager.handler.getHeight();
        if (x < -1)
            x = EntityManager.handler.getWidth();
        if (x > EntityManager.handler.getWidth()+1)
            x = 0;
        if (y > EntityManager.handler.getHeight()+1)
            y = 0;
    }

    protected void checkAlive() {
        if (health <= 0)
            setAlive(false);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public double getRotation() {
        return rotation;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x-5, (int) y-5, 10, 10);
    }

}
