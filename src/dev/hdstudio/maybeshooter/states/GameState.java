package dev.hdstudio.maybeshooter.states;


import dev.hdstudio.maybeshooter.Handler;
import dev.hdstudio.maybeshooter.entities.EntityManager;
import dev.hdstudio.maybeshooter.entities.active.creatures.Enemy;
import dev.hdstudio.maybeshooter.entities.active.creatures.Player;
import dev.hdstudio.maybeshooter.resources.Stats;
import dev.hdstudio.maybeshooter.utils.Utils;

import javax.swing.plaf.synth.SynthStyle;
import java.awt.*;

public class GameState extends State {

    private EntityManager entityManager;
    private Player player;

    private long startGameTime, spawnTimer;

    private static Color healthBarColor = new Color(255, 0, 0, 76);

    public GameState(Handler handler) {
        super(handler);

        init();

        startGameTime = System.currentTimeMillis();
        spawnTimer = System.currentTimeMillis();
    }

    private void init() {
        entityManager = new EntityManager(handler);

        player = new Player(100, 100);
        player.setHandler(handler);
        entityManager.setPlayer(player);
        Stats.health = Stats.maxHealth;
    }

    @Override
    public void tick() {

        if (handler.getKeyManager().keyJustPressed(32)) { // spacebar
            handler.getGame().gameState = null;
            State.setState(handler.getGame().menuState);
        }

        if (player.isAlive()) {
            entityManager.tick();
        } else {
            State.setState(handler.getGame().menuState);
        }

        if(System.currentTimeMillis() - startGameTime >= 20000) {
            Stats.wave++;
            startGameTime = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - spawnTimer >= 1000) {
            spawnTimer = System.currentTimeMillis();
            spawnEnemies(Stats.wave);
        }
    }

    @Override
    public void render(Graphics2D g) {
        entityManager.render(g);

        g.setColor(Color.WHITE);
        g.drawString("Health: "+String.valueOf(player.getHealth()), 10, 20);
        g.drawString("Points: "+String.valueOf(player.getPoints()), 10, 40);
        g.drawString("Wave: "+String.valueOf(Stats.wave), 10, 60);
        g.drawString("Money: "+String.valueOf(Stats.money), 10, 80);
        g.drawString("Score: "+String.valueOf(Stats.score), 10, 100);
        g.drawString("MaxHealth: "+String.valueOf(Stats.maxHealth), 10, 120);

        // Health bar
        g.setColor(Color.BLACK);
        g.drawRect(handler.getWidth()/2 - 101, 29, 201, 31);
        g.setColor(healthBarColor);
        g.fillRect(handler.getWidth()/2 - 100, 30, (int)((float)player.getHealth()/player.getMaxHealth()*200), 30);
    }

    private void spawnEnemies(int number) {
        double xSpawn = 0, ySpawn = 0;
        for (int i = 0; i < number; i++) {
            int draw = Utils.drawNumbersBetween(1,4);
            if (draw == 1) {
                xSpawn = Math.random()*handler.getWidth();
                ySpawn = 0;
            } else if (draw == 2) {
                xSpawn = Math.random()*handler.getWidth();
                ySpawn = handler.getHeight();
            } else if (draw == 3) {
                xSpawn = 0;
                ySpawn = Math.random()*handler.getHeight();
            }else if (draw == 4) {
                xSpawn = handler.getWidth();
                ySpawn = Math.random()*handler.getHeight();
            }
            entityManager.addEnemy(new Enemy(xSpawn, ySpawn));
        }
    }

}