package dev.hdstudio.maybeshooter.entities;

import dev.hdstudio.maybeshooter.Handler;

import java.awt.*;

public abstract class Entity {

    protected abstract void tick();

    protected abstract void render(Graphics2D g);


    // methods for every entity
    protected static Handler handler;

    protected double x, y;
    protected boolean hurtPlayer, hurtEnemy;

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
