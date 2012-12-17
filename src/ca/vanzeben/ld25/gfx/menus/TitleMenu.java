package ca.vanzeben.ld25.gfx.menus;

import java.awt.Color;

import ca.vanzeben.ld25.MainGame;
import ca.vanzeben.ld25.gfx.Screen;
import ca.vanzeben.ld25.gfx.sprites.ImageLoader;
import ca.vanzeben.ld25.listener.InputHandler;
import ca.vanzeben.ld25.sound.Sound;

public class TitleMenu extends Menu {

  private int selected = 0;
  private String[] options = new String[0];
  public ImageLoader image = new ImageLoader("/gui/title_bg.png");
  public Sound ambientSound = Sound.AMBIENT;

  public TitleMenu(MainGame game, InputHandler input) {
    super(game, input);
    options = new String[] { "Start Game", "How to play", "About", "Credits" };
    ambientSound.loop();
  }

  @Override
  public void update() {
    if (this.input.up) {
      selected--;
      this.input.clear();
    }
    if (input.down) {
      selected++;
      this.input.clear();
    }
    if (selected < 0) {
      selected += options.length;
    }
    if (selected >= options.length) {
      selected -= options.length;
    }
    if (input.enter) {
      this.input.clear();
      switch (selected) {
        case 0:
          game.reset();
          game.setMenu(null);
          ambientSound.stop();
          break;
        case 1:
          game.setMenu(new InstructionMenu(this));
          break;
        case 2:
          game.setMenu(new AboutMenu(this));
          return;
        case 3:
          game.setMenu(new CreditsMenu(this));
          return;
      }
    }
  }

  @Override
  public void render(Screen screen) {
    screen.fill(0xFF008080);
    screen.renderCenteredImage(image);

    for (int i = 0; i < options.length; i++) {
      String msg = options[i];
      if (i == selected) {
        msg = "> " + msg + " <";
      }
      screen.drawCenteredString(msg, MainGame.WIDTH * MainGame.SCALE / 2 + 300, 400 + (28 * i) + 2, 28, 1, new Color(0xFF005050));
      screen.drawCenteredString(msg, MainGame.WIDTH * MainGame.SCALE / 2 + 300, 400 + (28 * i), 28, 1, Color.WHITE);
    }
  }
}
