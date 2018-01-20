package dev.hdstudio.maybeshooter.entities.active.creatures;

import dev.hdstudio.maybeshooter.entities.EntityManager;

import java.awt.*;

public class Enemy extends Creature {

    public Enemy(double x, double y) {
        super(x, y);
        hurtPlayer = true;

        health = 20;
        points = 20;
    }

    @Override
    public void tick() {

        double diffX = EntityManager.player.getX() - x;
        double diffY = EntityManager.player.getY() - y;

        rotation = Math.atan2(diffY, diffX);

        teleportOutBounds();
        move();
        checkAlive();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.drawRect((int) x - 5, (int) y - 5, 10, 10);

        g.drawLine((int) x, (int) y, (int) (x + Math.cos(rotation) * 60), (int) (y + Math.sin(rotation) * 60));
    }
}
