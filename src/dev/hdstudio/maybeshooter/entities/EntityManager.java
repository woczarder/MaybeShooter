package dev.hdstudio.maybeshooter.entities;

import dev.hdstudio.maybeshooter.Handler;
import dev.hdstudio.maybeshooter.entities.active.bullets.*;
import dev.hdstudio.maybeshooter.entities.active.creatures.Enemy;
import dev.hdstudio.maybeshooter.entities.active.creatures.Player;
import dev.hdstudio.maybeshooter.resources.Stats;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class EntityManager {

    public static Handler handler;
    public static Player player;

    private LinkedList<Enemy> enemies;
    private ArrayList<Bullet> bullets;

    public EntityManager(Handler handler) {
        this.handler = handler;

        enemies = new LinkedList<>();
        bullets = new ArrayList<>();

    }


    public synchronized void tick() {
        // PLAYER
        if (player.isAlive())
            player.tick();

        // ENEMIES
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while(enemyIterator.hasNext()){
            Enemy e = enemyIterator.next();
            e.tick();
            if(!e.isAlive()) {
                enemyIterator.remove();
                Stats.score++;
                Stats.money += e.getPoints();
            }

        }

        // BULLETS
        Iterator<Bullet> it2 = bullets.iterator();
        while(it2.hasNext()){
            Bullet e = it2.next();
            e.tick();
            if(!e.isAlive())
                it2.remove();
        }

        // ENEMIES COLLISION WITH PLAYER AND PLAYER BULLETS
        for (Enemy enemy: enemies) {
            if (enemy.getBounds().intersects(player.getBounds())) {
                player.setHealth(player.getHealth() - 5); // lower player health by 5
                enemy.setAlive(false); // kill colliding enemy
            }

            for (Bullet bullet: bullets) {
                if (enemy.getBounds().intersects(bullet.getBounds())) {
                    enemy.setHealth(enemy.getHealth() - bullet.getDamage()); // lower enemy health by bullet damage
                    bullet.setAlive(false); // kill colliding bullet
                }
            }
        }

        switch (player.getShotsFired()) {
            case 1: // simple bullet
                player.setShotsFired(0);
                addBullet(new SimpleBullet(player.x, player.y, player.getRotation(), false, true));
                break;
            case 2: // hard bullet
                player.setShotsFired(0);
                addBullet(new HardBullet(player.x, player.y, player.getRotation(), false, true));
                break;
            case 3: // triple bullet
                player.setShotsFired(0);
                addBullet(new TripleBullet(player.x, player.y, player.getRotation()+0.21, false, true));
                addBullet(new TripleBullet(player.x, player.y, player.getRotation(), false, true));
                addBullet(new TripleBullet(player.x, player.y, player.getRotation()-0.21, false, true));
                break;
            case 4: // penta bullet
                player.setShotsFired(0);
                addBullet(new PentaBullet(player.x, player.y, player.getRotation()-0.21, false, true));
                addBullet(new PentaBullet(player.x, player.y, player.getRotation()-0.10, false, true));
                addBullet(new PentaBullet(player.x, player.y, player.getRotation(), false, true));
                addBullet(new PentaBullet(player.x, player.y, player.getRotation()+0.10, false, true));
                addBullet(new PentaBullet(player.x, player.y, player.getRotation()+0.21, false, true));
                break;
            case 5:
                player.setShotsFired(0);
                for (int i = 0; i < 8; i++) {
                    addBullet(new OctaBullet(player.x, player.y, player.getRotation()+0.79*i, false, true));
                }
                break;
        }

    }

    public synchronized void render(Graphics2D g) {
        // PLAYER
        if (player.isAlive())
            player.render(g);

        /*for (Enemy e: enemies) {
            e.render(g);
        }*/

        // ENEMIES
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while(enemyIterator.hasNext()){
            Enemy e = enemyIterator.next();
            e.render(g);
        }

        // BULLETS
        Iterator<Bullet> it2 = bullets.iterator();
        while(it2.hasNext()){
            Bullet e = it2.next();
            e.render(g);
        }

    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        enemy.setHandler(handler);
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
        bullet.setHandler(handler);
    }


// getters and setters and shit

    public void setPlayer(Player player) {
        this.player = player;
    }

}
