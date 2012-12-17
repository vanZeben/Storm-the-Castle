package ca.vanzeben.ld25;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class MainGameApplet extends Applet {

  private MainGame game;

  public void init() {
    game = new MainGame(true);
    setLayout(new BorderLayout());
    Dimension size = new Dimension(MainGame.WIDTH * MainGame.SCALE, MainGame.HEIGHT * MainGame.SCALE);
    setSize(size);
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    add(game);
  }

  public void start() {
    game.start();
  }

  public void stop() {
    game.stop();
  }
}
