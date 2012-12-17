package ca.vanzeben.ld25.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ca.vanzeben.ld25.MainGame;

public class MouseHandler implements MouseListener {

  @Override
  public void mouseClicked(MouseEvent e) {
    MainGame.game.click(e.getX(), e.getY());
  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }
}
