package dev.hdstudio.maybeshooter.ui;

import java.awt.*;

public class UIUpgradeButton extends UIObject {

    private ClickListener clicker;

    public UIUpgradeButton(float x, float y, ClickListener clicker) {
        super(x, y, 100, 70);
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

        g.setColor(Color.WHITE);
        g.drawString("CURRENT:", (int) x+2, (int) y+15);
        //g.drawString(label, (int) x+2, (int) y+30);
        g.drawString("BUY FOR:", (int) x+2, (int) y+50);
        //g.drawString(label2, (int) x+2, (int) y+65);

    }

    @Override
    public void onClick() {
        clicker.onClick();
    }

}
