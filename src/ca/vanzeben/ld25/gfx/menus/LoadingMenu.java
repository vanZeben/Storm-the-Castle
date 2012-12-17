package ca.vanzeben.ld25.gfx.menus;

import ca.vanzeben.ld25.MainGame;
import ca.vanzeben.ld25.gfx.Screen;
import ca.vanzeben.ld25.gfx.sprites.ImageLoader;
import ca.vanzeben.ld25.listener.InputHandler;
import ca.vanzeben.ld25.sound.Sound;

public class LoadingMenu extends Menu {

  private long startTime;
  private int loadDelay = 2;
  private ImageLoader image = new ImageLoader("/gui/loading.png");
  private Loader thread;

  public LoadingMenu(MainGame game, InputHandler input) {
    super(game, input);
    startTime = System.currentTimeMillis();
    load();
  }

  public void load() {
    thread = new Loader();
    thread.start();
  }

  @Override
  public void update() {
    if (thread.isDone && System.currentTimeMillis() - startTime >= loadDelay * 1000) {
      game.setMenu(new TitleMenu(game, input));
    }
  }

  @Override
  public void render(Screen screen) {
    screen.fill(0xFF008080);
    screen.renderCenteredImage(image);
  }

  class Loader extends Thread {
    public boolean isDone = false;

    public void run() {
      Sound.WARNING.play();
      game.loadLevels();
      game.reset();
      isDone = true;
    }
  }
}
