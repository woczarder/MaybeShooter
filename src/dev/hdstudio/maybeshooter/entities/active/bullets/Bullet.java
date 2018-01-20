package dev.hdstudio.maybeshooter.entities.active.bullets;

import dev.hdstudio.maybeshooter.entities.active.Active;
import dev.hdstudio.maybeshooter.resources.BulletData;

import java.awt.*;

public abstract class Bullet extends Active {

    protected int damage;
    protected long startLife, endLife;
    protected double lifeTime;


    public Bullet(double x, double y, double rotation, boolean hurtPlayer, boolean hurtEnemy) {
        super(x, y);
        this.rotation = rotation;
        this.hurtPlayer = hurtPlayer;
        this.hurtEnemy = hurtEnemy;
        startLife = System.currentTimeMillis();

    }

    public abstract void tick();

    public abstract void render(Graphics2D g);

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    protected void checkAlive() {
        endLife = System.currentTimeMillis();
        if (endLife - startLife > lifeTime*1000)
            this.setAlive(false);
    }

}
