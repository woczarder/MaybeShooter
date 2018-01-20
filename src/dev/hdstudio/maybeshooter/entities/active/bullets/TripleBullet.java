package dev.hdstudio.maybeshooter.entities.active.bullets;

import dev.hdstudio.maybeshooter.resources.BulletData;
import dev.hdstudio.maybeshooter.resources.Stats;

import java.awt.*;

public class TripleBullet extends Bullet {

    public TripleBullet(double x, double y, double rotation, boolean hurtPlayer, boolean hurtEnemy) {
        super(x, y, rotation, hurtPlayer, hurtEnemy);

        speed = Stats.tripleBulletData.speed;
        damage = Stats.tripleBulletData.damage;
        lifeTime = Stats.tripleBulletData.lifeTime;
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
        g.drawRect((int) x-3, (int) y-3, 6, 6);
    }
}
