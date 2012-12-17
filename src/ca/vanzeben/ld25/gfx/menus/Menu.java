package ca.vanzeben.ld25.gfx.menus;

import ca.vanzeben.ld25.MainGame;
import ca.vanzeben.ld25.gfx.Screen;
import ca.vanzeben.ld25.listener.InputHandler;

public abstract class Menu {
  protected MainGame game;
  protected InputHandler input;

  public Menu(MainGame game, InputHandler input) {
    this.game = game;
    this.input = input;
  }

  public abstract void update();

  public abstract void render(Screen screen);
}
