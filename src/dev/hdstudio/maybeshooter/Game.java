package dev.hdstudio.maybeshooter;

import dev.hdstudio.maybeshooter.display.Display;
import dev.hdstudio.maybeshooter.input.KeyManager;
import dev.hdstudio.maybeshooter.input.MouseManager;
import dev.hdstudio.maybeshooter.resources.Stats;
import dev.hdstudio.maybeshooter.states.MenuState;
import dev.hdstudio.maybeshooter.states.State;
import dev.hdstudio.maybeshooter.states.UpgradeState;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game {
//3840 × 2160
    private Display display;
    private final int width = 1280,
                    height = 720;

    private BufferStrategy bs;
    private Graphics2D g2;

    private Handler handler;

    // States
    public State gameState;
    public State menuState;
    public State upgradeState;

    // Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    public Game() {

        keyManager = new KeyManager();
        mouseManager = new MouseManager();

        init();

        //GAME LOOP
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(true){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                Toolkit.getDefaultToolkit().sync(); // For Linux
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
                handler.signal++;
            }
        }
    }

    private void init() {
        display = new Display(width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        handler = new Handler(this);

        menuState = new MenuState(handler);
        upgradeState = new UpgradeState(handler);
        State.setState(menuState);

        Stats.initBulletData();
    }

    private synchronized void tick() {
        keyManager.tick();

        if(State.getState() != null)
            State.getState().tick();
    }

    private synchronized void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g2 = (Graphics2D) bs.getDrawGraphics();
        //Clear Screen
        //g.clearRect(0, 0, width, height);
        g2.clearRect(0, 0, width, height);
        //Draw Here!

        g2.setColor(Color.GRAY);
        g2.fillRect(0,0, width, height);

        if (State.getState() != null)
            State.getState().render(g2);


        //End Drawing!
        bs.show();
        g2.dispose();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager(){
        return mouseManager;
    }
}
