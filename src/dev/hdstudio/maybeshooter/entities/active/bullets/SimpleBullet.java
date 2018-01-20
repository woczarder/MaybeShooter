package dev.hdstudio.maybeshooter.entities.active.bullets;

import dev.hdstudio.maybeshooter.resources.Stats;

import java.awt.*;

public class SimpleBullet extends Bullet {

    public SimpleBullet(double x, double y, double rotation, boolean hurtPlayer, boolean hurtEnemy) {
        super(x, y, rotation, hurtPlayer, hurtEnemy);

        speed = Stats.simpleBulletData.speed;
        damage = Stats.simpleBulletData.damage;
        lifeTime = Stats.simpleBulletData.lifeTime;
    }

    @Override
    public void tick() {
        move();
        teleportOutBounds();
        checkAlive();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.CYAN);
        g.drawRect((int) x-5, (int) y-5, 10, 10);
    }
}
