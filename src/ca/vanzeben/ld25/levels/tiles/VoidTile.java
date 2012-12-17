package ca.vanzeben.ld25.levels.tiles;

import ca.vanzeben.ld25.gfx.Screen;
import ca.vanzeben.ld25.gfx.sprites.Sprite;
import ca.vanzeben.ld25.levels.Level;

public class VoidTile extends Tile {

  public VoidTile(Level level, Sprite sprite, int x, int y) {
    super(level, sprite, "Void", 0x000000, true, false, x, y);
  }

  public void update(Level level) {
    if (this.level == null || !this.level.equals(level)) {
      this.level = level;
    }
  }

  @Override
  public void render(Screen screen) {
    screen.renderTile(level, this.x * sprite.width, this.y * sprite.height, this);
  }
}
