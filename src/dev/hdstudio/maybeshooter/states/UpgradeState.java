package dev.hdstudio.maybeshooter.states;

import dev.hdstudio.maybeshooter.Handler;
import dev.hdstudio.maybeshooter.resources.BulletData;
import dev.hdstudio.maybeshooter.resources.Stats;
import dev.hdstudio.maybeshooter.ui.ClickListener;
import dev.hdstudio.maybeshooter.ui.UIButton;
import dev.hdstudio.maybeshooter.ui.UIUpgradeButton;
import dev.hdstudio.maybeshooter.utils.Utils;

import java.awt.*;

public class UpgradeState extends State {



    public UpgradeState(Handler handler) {
        super(handler);

        uiManager.addObject(new UIButton(10, 10, 10, 10, new ClickListener() {
            @Override
            public void onClick() {
                State.setState(handler.getGame().menuState);
            }
        }));

        uiManager.addObject(new UIUpgradeButton(850, 270, new ClickListener() {
            @Override
            public void onClick() {
                Stats.maxSpeed += 0.1;
                Stats.money -= 100;
            }
        }));

        uiManager.addObject(new UIUpgradeButton(100, 270, () -> buyDamage(Stats.simpleBulletData)));

        uiManager.addObject(new UIUpgradeButton(200, 270, () -> buyDamage(Stats.hardBulletData)));

        uiManager.addObject(new UIUpgradeButton(300, 270, () -> buyDamage(Stats.tripleBulletData)));

        uiManager.addObject(new UIUpgradeButton(400, 270, () -> buyDamage(Stats.pentaBulletData)));

        uiManager.addObject(new UIUpgradeButton(500, 270, () -> buyDamage(Stats.octaBulletData)));

        uiManager.addObject(new UIUpgradeButton(100, 370, () -> buySpeed(Stats.simpleBulletData)));

        uiManager.addObject(new UIUpgradeButton(200, 370, () -> buySpeed(Stats.hardBulletData)));

        uiManager.addObject(new UIUpgradeButton(300, 370, () -> buySpeed(Stats.tripleBulletData)));

        uiManager.addObject(new UIUpgradeButton(400, 370, () -> buySpeed(Stats.pentaBulletData)));

        uiManager.addObject(new UIUpgradeButton(500, 370, () -> buySpeed(Stats.octaBulletData)));

        uiManager.addObject(new UIUpgradeButton(100, 470, () -> buyLifeTime(Stats.simpleBulletData)));

        uiManager.addObject(new UIUpgradeButton(200, 470, () -> buyLifeTime(Stats.hardBulletData)));

        uiManager.addObject(new UIUpgradeButton(300, 470, () -> buyLifeTime(Stats.tripleBulletData)));

        uiManager.addObject(new UIUpgradeButton(400, 470, () -> buyLifeTime(Stats.pentaBulletData)));

        uiManager.addObject(new UIUpgradeButton(500, 470, () -> buyLifeTime(Stats.octaBulletData)));


    }

    private void buyDamage(BulletData bulletData) {
        if (Stats.money >= bulletData.price.damage) {
            Stats.money -= bulletData.price.damage;
            bulletData.damage++;
            bulletData.price.damage += 10;
        }
    }

    private void buySpeed(BulletData bulletData) {
        if (Stats.money >= bulletData.price.speed) {
            Stats.money -= bulletData.price.speed;
            bulletData.speed += 0.1;
            bulletData.speed = Utils.roundOff(bulletData.speed);
            bulletData.price.speed += 10;
        }
    }

    private void buyLifeTime(BulletData bulletData) {
        if (Stats.money >= bulletData.price.lifeTime) {
            Stats.money -= bulletData.price.lifeTime;
            bulletData.lifeTime += 0.1;
            bulletData.lifeTime = Utils.roundOff(bulletData.lifeTime);
            bulletData.price.lifeTime += 10;
        }
    }

    @Override
    public void tick() {
        if (handler.getKeyManager().keyJustPressed(32)) { // spacebar - start a game
            State.setState(handler.getGame().menuState);
        }
        uiManager.tick();


    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());

        uiManager.render(g);

        g.setColor(Color.WHITE);
        g.drawString("UPGRADE YOUR BABYYY", handler.getWidth()/2-100, 30);
        g.setColor(Color.WHITE);

        g.drawString("Points: "+String.valueOf(Stats.score), 10, 40);
        g.drawString("Wave: "+String.valueOf(Stats.wave), 10, 60);
        g.drawString("Money: "+String.valueOf(Stats.money), 10, 80);
        g.drawString("Score: "+String.valueOf(Stats.score), 10, 100);

        g.drawString("Player",  800, 200);

        g.drawString("Max speed",750, 300);
        g.drawString("Min speed",750, 400);
        g.drawString("Health",   750, 500);

        g.drawString(String.valueOf(Utils.roundOff(Stats.maxSpeed)),850, 300); //+65
        g.drawString(String.valueOf(Stats.minSpeed),                850, 400);
        g.drawString(String.valueOf(Stats.health),                  850, 500);

        g.drawString("Simple",  100, 200);
        g.drawString("Hard",    200, 200);
        g.drawString("Triple",  300, 200);
        g.drawString("Penta",   400, 200);
        g.drawString("Octa",    500, 200);

        g.drawString("Damage",  30, 300);
        g.drawString("Speed",   30, 400);
        g.drawString("LifeTime",30, 500);

        g.drawString(String.valueOf(Stats.simpleBulletData.damage),         100, 300);
        g.drawString(String.valueOf(Stats.simpleBulletData.speed),          100, 400);
        g.drawString(String.valueOf(Stats.simpleBulletData.lifeTime),       100, 500);

        g.drawString(String.valueOf(Stats.simpleBulletData.price.damage),   100, 335); // +35
        g.drawString(String.valueOf(Stats.simpleBulletData.price.speed),    100, 435);
        g.drawString(String.valueOf(Stats.simpleBulletData.price.lifeTime), 100, 535);

        g.drawString(String.valueOf(Stats.hardBulletData.damage),           200, 300);
        g.drawString(String.valueOf(Stats.hardBulletData.speed),            200, 400);
        g.drawString(String.valueOf(Stats.hardBulletData.lifeTime),         200, 500);

        g.drawString(String.valueOf(Stats.hardBulletData.price.damage),         200, 335);
        g.drawString(String.valueOf(Stats.hardBulletData.price.speed),          200, 435);
        g.drawString(String.valueOf(Stats.hardBulletData.price.lifeTime),       200, 535);

        g.drawString(String.valueOf(Stats.tripleBulletData.damage),         300, 300);
        g.drawString(String.valueOf(Stats.tripleBulletData.speed),          300, 400);
        g.drawString(String.valueOf(Stats.tripleBulletData.lifeTime),       300, 500);

        g.drawString(String.valueOf(Stats.tripleBulletData.price.damage),         300, 335);
        g.drawString(String.valueOf(Stats.tripleBulletData.price.speed),          300, 435);
        g.drawString(String.valueOf(Stats.tripleBulletData.price.lifeTime),       300, 535);

        g.drawString(String.valueOf(Stats.pentaBulletData.damage),          400, 300);
        g.drawString(String.valueOf(Stats.pentaBulletData.speed),           400, 400);
        g.drawString(String.valueOf(Stats.pentaBulletData.lifeTime),        400, 500);

        g.drawString(String.valueOf(Stats.pentaBulletData.price.damage),          400, 335);
        g.drawString(String.valueOf(Stats.pentaBulletData.price.speed),           400, 435);
        g.drawString(String.valueOf(Stats.pentaBulletData.price.lifeTime),        400, 535);

        g.drawString(String.valueOf(Stats.octaBulletData.damage),           500, 300);
        g.drawString(String.valueOf(Stats.octaBulletData.speed),            500, 400);
        g.drawString(String.valueOf(Stats.octaBulletData.lifeTime),         500, 500);

        g.drawString(String.valueOf(Stats.octaBulletData.price.damage),           500, 335);
        g.drawString(String.valueOf(Stats.octaBulletData.price.speed),            500, 435);
        g.drawString(String.valueOf(Stats.octaBulletData.price.lifeTime),         500, 535);
    }
}
