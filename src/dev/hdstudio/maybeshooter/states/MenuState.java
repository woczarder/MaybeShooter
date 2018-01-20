package dev.hdstudio.maybeshooter.states;

import dev.hdstudio.maybeshooter.Handler;
import dev.hdstudio.maybeshooter.resources.Stats;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends State {

    public MenuState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) { // spacebar - start a game
            handler.getGame().gameState = new GameState(handler);
            State.setState(handler.getGame().gameState);
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_U)) { // U - upgrade menu
            State.setState(handler.getGame().upgradeState);
        }

        //System.out.println(handler.getMouseManager().getMouseX() + handler.getMouseManager().getMouseY());
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());

        g.setColor(Color.WHITE);
        g.drawString("Press space bar", 300, 300);
        g.drawString("Fire with Z, X, C, V, B", 300, 330);
        g.drawString("Steer with arrows, you can accelerate like a bugatti", 300, 350);
        g.drawString("Press U for upgrades, mai bae", 300, 430);
    }
}