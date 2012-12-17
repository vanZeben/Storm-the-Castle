package ca.vanzeben.ld25.levels.tiles;

import java.util.Random;

import ca.vanzeben.ld25.gfx.sprites.Sprite;
import ca.vanzeben.ld25.levels.Level;

public class TileTypes {

  public static final TileTypes[] tileTypes = { new TileTypes("BG"), new TileTypes("PATH"), new TileTypes("START"),
      new TileTypes("END"), new TileTypes("BASE_TOWER"), new TileTypes("TIER_2_TOWER"), new TileTypes("VOID") };

  public static TileTypes get(String type) {
    for (TileTypes tt : tileTypes) {
      if (tt.type.equals(type)) { return tt; }
    }
    return tileTypes[tileTypes.length - 1];
  }

  protected String type;

  public TileTypes(String type) {
    this.type = type;
  }

  private Random random = new Random();

  public Tile get(Level level, int x, int y) {
    if (type.equals("BG")) {
      Sprite sprite = Sprite.BG_0;
      switch (random.nextInt(8)) {
        case 1:
          sprite = Sprite.BG_1;
          break;
        case 2:
          sprite = Sprite.BG_2;
          break;
        case 3:
          if (random.nextInt(5) == 0) {
            sprite = Sprite.BG_3;
          }
          break;
        case 4:
          if (random.nextInt(5) == 0) {
            sprite = Sprite.BG_4;
          }
          break;
        default:
        case 0:
          sprite = Sprite.BG_0;
          break;

      }
      return new BasicBlockTile(level, sprite, "BG", 0xff333333, true, false, x, y);
    } else if (type.equals("PATH")) {
      return new PathBlockTile(level, new Sprite[] { Sprite.PATH_0, Sprite.PATH_1, Sprite.PATH_2, Sprite.PATH_3, Sprite.PATH_4,
          Sprite.PATH_5, Sprite.PATH_6, Sprite.PATH_7, Sprite.PATH_8, Sprite.PATH_9, Sprite.PATH_10, Sprite.PATH_11,
          Sprite.PATH_12, Sprite.PATH_13, Sprite.PATH_14, Sprite.PATH_15 }, "PATH", 0xff555555, false, false, x, y);
    } else if (type.equals("START")) {
      return new AnimatedBlockTile(level, new Sprite[] { Sprite.START_0, Sprite.START_1 }, "START", 0xff00ff00, false, false, x,
          y, 0.2);
    } else if (type.equals("END")) {
      return new AnimatedBlockTile(level, new Sprite[] { Sprite.END_0, Sprite.END_1, Sprite.END_2 }, "END", 0xffff0000, false,
          false, x, y, 0.1);
    } else if (type.equals("BASE_TOWER")) {
      return new AnimatedShootingTower(level, new Sprite[] { Sprite.BASE_TOWER_1, Sprite.BASE_TOWER_2 }, "BASE_TOWER",
          0xffffff00, true, false, x, y, "PHOTON_PROJECTILE", 3);
    } else if (type.equals("TIER_2_TOWER")) { return new AnimatedShootingTower(level, new Sprite[] { Sprite.TIER_2_TOWER_1,
        Sprite.TIER_2_TOWER_2 }, "TIER_2_TOWER", 0xffaaaa00, true, false, x, y, "NINJA_PROJECTILE", 5); }
    return new VoidTile(level, Sprite.VOID, x, y);
  }

  public Tile get() {
    return get(null, 0, 0);
  }
}
