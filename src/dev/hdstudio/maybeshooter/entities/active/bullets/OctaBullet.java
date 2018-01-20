package dev.hdstudio.maybeshooter.entities.active.bullets;

import dev.hdstudio.maybeshooter.resources.Stats;

import java.awt.*;

public class OctaBullet extends Bullet {

    public OctaBullet(double x, double y, double rotation, boolean hurtPlayer, boolean hurtEnemy) {
        super(x, y, rotation, hurtPlayer, hurtEnemy);

        speed = Stats.octaBulletData.speed;
        damage = Stats.octaBulletData.damage;
        lifeTime = Stats.octaBulletData.lifeTime;
    }

    @Override
    public void tick() {
        move();
        teleportOutBounds();
        checkAlive();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.orange);
        g.drawRect((int) x-3, (int) y-3, 6, 6);
    }
}
