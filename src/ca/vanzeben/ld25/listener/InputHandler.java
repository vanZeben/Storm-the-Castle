package ca.vanzeben.ld25.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

  private boolean[] keys = new boolean[65536];
  public boolean up, down, left, right, enter;

  public void update() {
    up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
    down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
    left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
    right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
    enter = keys[KeyEvent.VK_ENTER];
  }

  public void keyPressed(KeyEvent e) {
    keys[e.getKeyCode()] = true;
  }

  public void keyReleased(KeyEvent e) {
    keys[e.getKeyCode()] = false;
  }

  public void keyTyped(KeyEvent e) {
  }

  public void clear() {
    for (int i = 0; i < keys.length; i++) {
      keys[i] = false;
    }
  }
}
