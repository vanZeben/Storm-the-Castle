package ca.vanzeben.ld25.gfx.menus;

import java.awt.Color;

import ca.vanzeben.ld25.MainGame;
import ca.vanzeben.ld25.gfx.Screen;

public class CreditsMenu extends Menu {

  private TitleMenu parent;

  private String[] msgs = new String[0];

  public CreditsMenu(TitleMenu parent) {
    super(parent.game, parent.input);
    this.parent = parent;
    msgs = new String[] { "Credits", "Thanks to Mattsolo97 for creating ", "the goat sound for this game", "All other content",
        "is by Ryan van Zeben", "(Press enter to return to main menu)" };
  }

  public void update() {
    if (input.enter) {
      game.setMenu(parent);
      input.clear();
      return;
    }
  }

  public void render(Screen screen) {
    screen.fill(0xFF008080);
    screen.renderCenteredImage(parent.image);
    for (int i = 0; i < msgs.length; i++) {
      String msg = msgs[i];
      int size = 20;
      if (i == 0) {
        size = 28;
      }
      if (i == msgs.length - 1) {
        size = 12;
      }
      screen.drawCenteredString(msg, MainGame.WIDTH * MainGame.SCALE / 2 + 300, 375 + (28 * i) + 2, size, 1,
          new Color(0xFF005050));
      screen.drawCenteredString(msg, MainGame.WIDTH * MainGame.SCALE / 2 + 300, 375 + (28 * i), size, 1, Color.WHITE);
    }
  }
}
