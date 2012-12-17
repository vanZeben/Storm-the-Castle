package ca.vanzeben.ld25.gfx.menus;

import java.awt.Color;

import ca.vanzeben.ld25.MainGame;
import ca.vanzeben.ld25.gfx.Screen;
import ca.vanzeben.ld25.gfx.sprites.ImageLoader;
import ca.vanzeben.ld25.listener.InputHandler;

public class WonMenu extends Menu {

  private ImageLoader image = new ImageLoader("/gui/win_bg.png");

  public WonMenu(MainGame game, InputHandler input) {
    super(game, input);
  }

  @Override
  public void update() {
    if (input.enter) {
      input.clear();
      game.nextLevel();
    }
  }

  @Override
  public void render(Screen screen) {
    screen.fill(0xFF008080);
    screen.renderCenteredImage(image);
    screen.drawRightJustifiedString("Next Level " + game.currentLevel, 20, 335 + 2, 22, 1, new Color(0xFF005050));
    screen.drawRightJustifiedString("Next Level " + game.currentLevel, 20, 335, 22, 1, Color.WHITE);

    screen.drawRightJustifiedString("Score " + game.totalScore, 20, 375 + 2, 22, 1, new Color(0xFF005050));
    screen.drawRightJustifiedString("Score " + game.totalScore, 20, 375, 22, 1, Color.WHITE);

    screen.drawRightJustifiedString("Time Played " + game.getTimePlayed(), 20, 375 + 34, 22, 1, new Color(0xFF005050));
    screen.drawRightJustifiedString("Time Played " + game.getTimePlayed(), 20, 375 + 32, 22, 1, Color.WHITE);

    screen.drawRightJustifiedString("(Press enter to continue)", 20, 500, 12, 1, Color.WHITE);

  }

}
