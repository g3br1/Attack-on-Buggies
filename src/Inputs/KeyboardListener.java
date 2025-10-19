package Inputs;

import Game.GameStates;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Game.GameStates.*;

public class KeyboardListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            GameStates.gameState = PLAYING;
        else if(e.getKeyCode() == KeyEvent.VK_M)
            GameStates.gameState = MENU;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
