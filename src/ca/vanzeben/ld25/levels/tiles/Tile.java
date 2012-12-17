package ca.vanzeben.ld25.levels.tiles;

import ca.vanzeben.ld25.gfx.Screen;
import ca.vanzeben.ld25.gfx.sprites.Sprite;
import ca.vanzeben.ld25.levels.Level;

public abstract class Tile {

  protected Level level;
  protected String name;
  protected Sprite sprite;
  protected boolean isSolid;
  protected boolean isEmitter;
  private int colour;
  public int x, y;
  public Tile(Level level, Sprite sprite, String name, int colour, boolean isSolid, boolean isEmitter, int x, int y) {
    this.level = level;
    this.name = name;
    this.sprite = sprite;
    this.isSolid = isSolid;
    this.isEmitter = isEmitter;
    this.colour = colour;
    this.x = x;
    this.y = y;
  }

  public abstract void update(Level level);

  public abstract void render(Screen screen);

  public String getName() {
    return name;
  }

  public Sprite getSprite() {
    return sprite;
  }

  public int getColour() {
    return colour;
  }

  public boolean isSolid() {
    return isSolid;
  }

  public boolean isEmitter() {
    return isEmitter;
  }

}
