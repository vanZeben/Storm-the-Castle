package ca.vanzeben.ld25.levels.tiles;

import ca.vanzeben.ld25.gfx.Screen;
import ca.vanzeben.ld25.gfx.sprites.Sprite;
import ca.vanzeben.ld25.levels.Level;

public class BasicBlockTile extends Tile {

  public BasicBlockTile(Level level, Sprite sprite, String name, int colour, boolean isSolid, boolean isEmitter, int x, int y) {
    super(level, sprite, name, colour, isSolid, isEmitter, x, y);
  }

  public void update(Level level) {
    if (this.level == null || !this.level.equals(level)) {
      this.level = level;
    }
  }

  public void render(Screen screen) {
    screen.renderTile(this.level, this.x, this.y, this);
  }

}
