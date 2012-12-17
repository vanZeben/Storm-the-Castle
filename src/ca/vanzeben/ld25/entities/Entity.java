package ca.vanzeben.ld25.entities;

import java.util.List;

import ca.vanzeben.ld25.gfx.Screen;
import ca.vanzeben.ld25.gfx.sprites.Sprite;
import ca.vanzeben.ld25.levels.Level;

public abstract class Entity {

  protected String name;
  public int x, y;
  public boolean removed = false;
  protected Level level;
  protected List<Sprite> sprites;
  protected int spriteIndex = 0;

  public Entity(Level level, String name, List<Sprite> sprites) {
    init(level);
    this.sprites = sprites;
    this.name = name;
  }

  public abstract void update();

  public abstract void render(Screen screen);

  public void remove() {
    removed = true;
  }

  public final void init(Level level) {
    this.level = level;
  }

  public String getName() {
    return name;
  }

  public int getTileX() {
    return (int) (Math.floor((double) x + 4) / 16D);
  }

  public int getTileY() {
    return (int) (Math.floor((double) y + 2) / 16D);
  }

  public Sprite getCurrentSprite() {
    return this.sprites.get(spriteIndex);
  }
}
