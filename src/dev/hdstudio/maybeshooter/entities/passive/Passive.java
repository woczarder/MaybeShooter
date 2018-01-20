package dev.hdstudio.maybeshooter.entities.passive;

import dev.hdstudio.maybeshooter.entities.Entity;

import java.awt.*;

public abstract class Passive extends Entity {

    public Passive(double x, double y) {
        super(x, y);
    }

    protected abstract void tick();

    protected abstract void render(Graphics2D g);
}
