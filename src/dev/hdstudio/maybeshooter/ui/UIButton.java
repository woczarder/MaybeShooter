package dev.hdstudio.maybeshooter.ui;

import java.awt.*;

public class UIButton extends UIObject {

    private ClickListener clicker;

    public UIButton(float x, float y, int width, int height, ClickListener clicker) {
        super(x, y, width, height);
        this.clicker = clicker;
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(100,100,100));
        if(hovering)
            g.setColor(new Color(150,150,150));
        g.fillRect((int) x, (int) y, width, height);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }

}