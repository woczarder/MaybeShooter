package dev.hdstudio.maybeshooter.entities.active.creatures;

import dev.hdstudio.maybeshooter.resources.Stats;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Creature {

    private int shotsFired;

    public Player(double x, double y) {
        super(x, y);

        this.health = Stats.health;
        this.maxHealth = Stats.maxHealth;
    }

    @Override
    public void tick() {
        changeRotation();
        changeSpeed();

        fire();

        teleportOutBounds();
        move();
        checkAlive();
        checkMaxHealth();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.drawRect((int) x-5, (int) y-5, 10, 10);

        //g.drawString(String.valueOf(rotation), 10, 10);
        //g.drawString(String.valueOf(health), 10, 20);
        g.drawLine((int)x, (int)y, (int)(x+Math.cos(rotation)*60), (int)(y+Math.sin(rotation)*60));
    }

    private void fire() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_Z)) // simple bullet
            shotsFired = 1;
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_X)) // triple bullet
            shotsFired = 2;
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_C)) // triple bullet
            shotsFired = 3;
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_V)) // penta bullet
            shotsFired = 4;
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_B)) // penta bullet
            shotsFired = 5;

    }

    private void changeRotation() {
        if (handler.getKeyManager().aLeft)
            rotation -= 0.05;
        else if (handler.getKeyManager().aRight)
            rotation += 0.05;
    }

    private void changeSpeed() {
        if (handler.getKeyManager().aUp && speed < Stats.maxSpeed)
            speed += 0.1;
        if (handler.getKeyManager().aDown && speed > 0 && speed > Stats.minSpeed)
            speed -= 0.1;
        if (speed < 0)
            speed = 0;
    }


    // getters and setters and shit

    public int getShotsFired() {
        return shotsFired;
    }

    public void setShotsFired(int shotsFired) {
        this.shotsFired = shotsFired;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points) {
        points += points;
    }
}
