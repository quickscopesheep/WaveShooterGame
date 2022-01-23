package com.sheep.farmingGame.util;

import com.sheep.farmingGame.Game;

import javax.swing.event.MenuKeyEvent;
import java.awt.event.*;

public class Keyboard implements KeyListener {
    public static boolean UP, DOWN, LEFT, RIGHT, USE1, USE2;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> UP = true;
            case KeyEvent.VK_S -> DOWN = true;
            case KeyEvent.VK_A -> LEFT = true;
            case KeyEvent.VK_D -> RIGHT = true;
            case KeyEvent.VK_Z -> USE1 = true;
            case KeyEvent.VK_X -> USE2 = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> UP = false;
            case KeyEvent.VK_S -> DOWN = false;
            case KeyEvent.VK_A -> LEFT = false;
            case KeyEvent.VK_D -> RIGHT = false;
            case KeyEvent.VK_Z -> USE1 = false;
            case KeyEvent.VK_X -> USE2 = false;
        }
    }
}
