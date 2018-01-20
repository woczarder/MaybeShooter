package dev.hdstudio.maybeshooter.entities.active.bullets;

import dev.hdstudio.maybeshooter.entities.EntityManager;
import dev.hdstudio.maybeshooter.resources.BulletData;
import dev.hdstudio.maybeshooter.resources.Stats;

import java.awt.*;

public class HardBullet extends Bullet {

    public HardBullet(double x, double y, double rotation, boolean hurtPlayer, boolean hurtEnemy) {
        super(x, y, rotation, hurtPlayer, hurtEnemy);

        speed = Stats.hardBulletData.speed;
        damage = Stats.hardBulletData.damage;
        lifeTime = Stats.hardBulletData.lifeTime;
    }

    @Override
    public void tick() {
        move();
        teleportOutBounds();
        checkAlive();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.drawOval((int) x-5, (int) y-5, 10, 10);
    }
}
