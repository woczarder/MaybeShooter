package dev.hdstudio.maybeshooter.states;

import dev.hdstudio.maybeshooter.Handler;
import dev.hdstudio.maybeshooter.ui.UIManager;

import java.awt.*;

public abstract class State {

    private static State currentState = null;

    public static void setState(State state){
        currentState = state;
        handler.getMouseManager().setUIManager(currentState.getUiManager());
    }

    public static State getState(){
        return currentState;
    }

    //CLASS

    protected static Handler handler;

    protected UIManager uiManager;

    public State(Handler handler){
        this.handler = handler;
        uiManager = new UIManager(handler);
    }

    public UIManager getUiManager() {
        return uiManager;
    }

    public abstract void tick();

    public abstract void render(Graphics2D g);

}