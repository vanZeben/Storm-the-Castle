package ca.vanzeben.ld25.levels.tiles;

import ca.vanzeben.ld25.gfx.sprites.Sprite;
import ca.vanzeben.ld25.levels.Level;

public class PathBlockTile extends BasicBlockTile {

  protected Sprite[] sprites;

  public PathBlockTile(Level level, Sprite[] sprites, String name, int colour, boolean isSolid, boolean isEmitter, int x, int y) {
    super(level, null, name, colour, isSolid, isEmitter, x, y);
    this.sprites = sprites;
  }

  @Override
  public void update(Level level) {
    super.update(level);
  }

  @Override
  public Sprite getSprite() {
    Sprite sprite = sprites[0];
    boolean[] hasValueOnSide = { false, false, false, false };
    for (int i = 0; i < hasValueOnSide.length; i++) {
      Tile tile = null;
      switch (i) {
        case 0:
          // north
          tile = this.level.getTile(this.x, this.y - 1);
          break;
        case 1:
          // south
          tile = this.level.getTile(this.x, this.y + 1);
          break;
        case 2:
          // east
          tile = this.level.getTile(this.x - 1, this.y);
          break;
        case 3:
          // west
          tile = this.level.getTile(this.x + 1, this.y);
          break;
      }
      hasValueOnSide[i] = tile.getName().equals("PATH") || tile.getName().equals("START") || tile.getName().equals("END")
          || tile instanceof BasicShootingTower;
    }
    if (!hasValueOnSide[0] && !hasValueOnSide[1] && !hasValueOnSide[2] && !hasValueOnSide[3]) {
      sprite = sprites[0];
    } else if (hasValueOnSide[0] && !hasValueOnSide[1] && !hasValueOnSide[2] && !hasValueOnSide[3]) {
      sprite = sprites[1];
    } else if (!hasValueOnSide[0] && hasValueOnSide[1] && !hasValueOnSide[2] && !hasValueOnSide[3]) {
      sprite = sprites[2];
    } else if (hasValueOnSide[0] && hasValueOnSide[1] && !hasValueOnSide[2] && !hasValueOnSide[3]) {
      sprite = sprites[3];
    } else if (!hasValueOnSide[0] && !hasValueOnSide[1] && !hasValueOnSide[2] && hasValueOnSide[3]) {
      sprite = sprites[4];
    } else if (!hasValueOnSide[0] && !hasValueOnSide[1] && hasValueOnSide[2] && !hasValueOnSide[3]) {
      sprite = sprites[5];
    } else if (!hasValueOnSide[0] && !hasValueOnSide[1] && hasValueOnSide[2] && hasValueOnSide[3]) {
      sprite = sprites[6];
    } else if (hasValueOnSide[0] && hasValueOnSide[1] && hasValueOnSide[2] && hasValueOnSide[3]) {
      sprite = sprites[7];
    } else if (hasValueOnSide[0] && !hasValueOnSide[1] && !hasValueOnSide[2] && hasValueOnSide[3]) {
      sprite = sprites[8];
    } else if (!hasValueOnSide[0] && hasValueOnSide[1] && !hasValueOnSide[2] && hasValueOnSide[3]) {
      sprite = sprites[9];
    } else if (!hasValueOnSide[0] && hasValueOnSide[1] && hasValueOnSide[2] && !hasValueOnSide[3]) {
      sprite = sprites[10];
    } else if (hasValueOnSide[0] && !hasValueOnSide[1] && hasValueOnSide[2] && !hasValueOnSide[3]) {
      sprite = sprites[11];
    } else if (hasValueOnSide[0] && !hasValueOnSide[1] && hasValueOnSide[2] && hasValueOnSide[3]) {
      sprite = sprites[12];
    } else if (!hasValueOnSide[0] && hasValueOnSide[1] && hasValueOnSide[2] && hasValueOnSide[3]) {
      sprite = sprites[13];
    } else if (hasValueOnSide[0] && hasValueOnSide[1] && hasValueOnSide[2] && !hasValueOnSide[3]) {
      sprite = sprites[14];
    } else if (hasValueOnSide[0] && hasValueOnSide[1] && !hasValueOnSide[2] && hasValueOnSide[3]) {
      sprite = sprites[15];
    }
    return sprite;
  }
}
