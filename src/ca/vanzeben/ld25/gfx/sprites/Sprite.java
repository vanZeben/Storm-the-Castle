package ca.vanzeben.ld25.gfx.sprites;

public class Sprite {

  private SpriteSheet[] spriteSheets = { new BlockSpriteSheet("/sprite_sheets/blocks.png"),
      new MobSpriteSheet("/sprite_sheets/mobs.png"), new MobSpriteSheet("/sprite_sheets/gui.png") };

  public static Sprite BG_0 = new Sprite("BG", 0, 0, 16, 16, "BLOCK");
  public static Sprite BG_1 = new Sprite("BG", 1, 0, 16, 16, "BLOCK");
  public static Sprite BG_2 = new Sprite("BG", 2, 0, 16, 16, "BLOCK");
  public static Sprite BG_3 = new Sprite("BG", 3, 0, 16, 16, "BLOCK");
  public static Sprite BG_4 = new Sprite("BG", 4, 0, 16, 16, "BLOCK");

  public static Sprite PATH_0 = new Sprite("PATH", 0, 1, 16, 16, "BLOCK");
  public static Sprite PATH_1 = new Sprite("PATH", 1, 1, 16, 16, "BLOCK");
  public static Sprite PATH_2 = new Sprite("PATH", 2, 1, 16, 16, "BLOCK");
  public static Sprite PATH_3 = new Sprite("PATH", 3, 1, 16, 16, "BLOCK");
  public static Sprite PATH_4 = new Sprite("PATH", 4, 1, 16, 16, "BLOCK");
  public static Sprite PATH_5 = new Sprite("PATH", 5, 1, 16, 16, "BLOCK");
  public static Sprite PATH_6 = new Sprite("PATH", 6, 1, 16, 16, "BLOCK");
  public static Sprite PATH_7 = new Sprite("PATH", 7, 1, 16, 16, "BLOCK");
  public static Sprite PATH_8 = new Sprite("PATH", 8, 1, 16, 16, "BLOCK");
  public static Sprite PATH_9 = new Sprite("PATH", 9, 1, 16, 16, "BLOCK");
  public static Sprite PATH_10 = new Sprite("PATH", 10, 1, 16, 16, "BLOCK");
  public static Sprite PATH_11 = new Sprite("PATH", 11, 1, 16, 16, "BLOCK");
  public static Sprite PATH_12 = new Sprite("PATH", 12, 1, 16, 16, "BLOCK");
  public static Sprite PATH_13 = new Sprite("PATH", 13, 1, 16, 16, "BLOCK");
  public static Sprite PATH_14 = new Sprite("PATH", 14, 1, 16, 16, "BLOCK");
  public static Sprite PATH_15 = new Sprite("PATH", 15, 1, 16, 16, "BLOCK");

  public static Sprite START_0 = new Sprite("START", 0, 2, 16, 16, "BLOCK");
  public static Sprite START_1 = new Sprite("START", 1, 2, 16, 16, "BLOCK");

  public static Sprite END_0 = new Sprite("END", 0, 3, 16, 16, "BLOCK");
  public static Sprite END_1 = new Sprite("END", 1, 3, 16, 16, "BLOCK");
  public static Sprite END_2 = new Sprite("END", 2, 3, 16, 16, "BLOCK");

  public static Sprite BASE_TOWER_1 = new Sprite("BASE_TOWER", 0, 5, 16, 16, "BLOCK");
  public static Sprite BASE_TOWER_2 = new Sprite("BASE_TOWER", 1, 5, 16, 16, "BLOCK");
  public static Sprite TIER_2_TOWER_1 = new Sprite("TIER_2_TOWER", 0, 6, 16, 16, "BLOCK");
  public static Sprite TIER_2_TOWER_2 = new Sprite("TIER_2_TOWER", 1, 6, 16, 16, "BLOCK");

  public static Sprite PHOTON_PROJECTILE = new Sprite("PHOTON_PROJECTILE", 0, 9, 16, 16, "BLOCK");
  public static Sprite NINJA_PROJECTILE = new Sprite("NINJA_PROJECTILE", 1, 9, 16, 16, "BLOCK");

  public static Sprite VOID = new Sprite(0x000000);

  public static Sprite TIER_1_UNIT_UP_1 = new Sprite("TIER_1_UNIT", 0, 0, 16, 16, "MOB");
  public static Sprite TIER_1_UNIT_UP_2 = new Sprite("TIER_1_UNIT", 1, 0, 16, 16, "MOB");
  public static Sprite TIER_1_UNIT_RIGHT_1 = new Sprite("TIER_1_UNIT", 2, 0, 16, 16, "MOB");
  public static Sprite TIER_1_UNIT_RIGHT_2 = new Sprite("TIER_1_UNIT", 3, 0, 16, 16, "MOB");
  public static Sprite TIER_1_UNIT_DOWN_1 = new Sprite("TIER_1_UNIT", 4, 0, 16, 16, "MOB");
  public static Sprite TIER_1_UNIT_DOWN_2 = new Sprite("TIER_1_UNIT", 5, 0, 16, 16, "MOB");
  public static Sprite TIER_1_UNIT_LEFT_1 = new Sprite("TIER_1_UNIT", 6, 0, 16, 16, "MOB");
  public static Sprite TIER_1_UNIT_LEFT_2 = new Sprite("TIER_1_UNIT", 7, 0, 16, 16, "MOB");

  public static Sprite TIER_2_UNIT_UP_1 = new Sprite("TIER_2_UNIT", 0, 1, 16, 16, "MOB");
  public static Sprite TIER_2_UNIT_UP_2 = new Sprite("TIER_2_UNIT", 1, 1, 16, 16, "MOB");
  public static Sprite TIER_2_UNIT_RIGHT_1 = new Sprite("TIER_2_UNIT", 2, 1, 16, 16, "MOB");
  public static Sprite TIER_2_UNIT_RIGHT_2 = new Sprite("TIER_2_UNIT", 3, 1, 16, 16, "MOB");
  public static Sprite TIER_2_UNIT_DOWN_1 = new Sprite("TIER_2_UNIT", 4, 1, 16, 16, "MOB");
  public static Sprite TIER_2_UNIT_DOWN_2 = new Sprite("TIER_2_UNIT", 5, 1, 16, 16, "MOB");
  public static Sprite TIER_2_UNIT_LEFT_1 = new Sprite("TIER_2_UNIT", 6, 1, 16, 16, "MOB");
  public static Sprite TIER_2_UNIT_LEFT_2 = new Sprite("TIER_2_UNIT", 7, 1, 16, 16, "MOB");

  public static Sprite TIER_3_UNIT_UP_1 = new Sprite("TIER_3_UNIT", 0, 2, 16, 16, "MOB");
  public static Sprite TIER_3_UNIT_UP_2 = new Sprite("TIER_3_UNIT", 1, 2, 16, 16, "MOB");
  public static Sprite TIER_3_UNIT_RIGHT_1 = new Sprite("TIER_3_UNIT", 2, 2, 16, 16, "MOB");
  public static Sprite TIER_3_UNIT_RIGHT_2 = new Sprite("TIER_3_UNIT", 3, 2, 16, 16, "MOB");
  public static Sprite TIER_3_UNIT_DOWN_1 = new Sprite("TIER_3_UNIT", 4, 2, 16, 16, "MOB");
  public static Sprite TIER_3_UNIT_DOWN_2 = new Sprite("TIER_3_UNIT", 5, 2, 16, 16, "MOB");
  public static Sprite TIER_3_UNIT_LEFT_1 = new Sprite("TIER_3_UNIT", 6, 2, 16, 16, "MOB");
  public static Sprite TIER_3_UNIT_LEFT_2 = new Sprite("TIER_3_UNIT", 7, 2, 16, 16, "MOB");

  public static Sprite TIER_4_UNIT_UP_1 = new Sprite("TIER_4_UNIT", 0, 3, 16, 16, "MOB");
  public static Sprite TIER_4_UNIT_UP_2 = new Sprite("TIER_4_UNIT", 1, 3, 16, 16, "MOB");
  public static Sprite TIER_4_UNIT_RIGHT_1 = new Sprite("TIER_4_UNIT", 2, 3, 16, 16, "MOB");
  public static Sprite TIER_4_UNIT_RIGHT_2 = new Sprite("TIER_4_UNIT", 3, 3, 16, 16, "MOB");
  public static Sprite TIER_4_UNIT_DOWN_1 = new Sprite("TIER_4_UNIT", 4, 3, 16, 16, "MOB");
  public static Sprite TIER_4_UNIT_DOWN_2 = new Sprite("TIER_4_UNIT", 5, 3, 16, 16, "MOB");
  public static Sprite TIER_4_UNIT_LEFT_1 = new Sprite("TIER_4_UNIT", 6, 3, 16, 16, "MOB");
  public static Sprite TIER_4_UNIT_LEFT_2 = new Sprite("TIER_4_UNIT", 7, 3, 16, 16, "MOB");

  public static Sprite TIER_5_UNIT_UP_1 = new Sprite("TIER_5_UNIT", 0, 4, 16, 16, "MOB");
  public static Sprite TIER_5_UNIT_UP_2 = new Sprite("TIER_5_UNIT", 1, 4, 16, 16, "MOB");
  public static Sprite TIER_5_UNIT_RIGHT_1 = new Sprite("TIER_5_UNIT", 2, 4, 16, 16, "MOB");
  public static Sprite TIER_5_UNIT_RIGHT_2 = new Sprite("TIER_5_UNIT", 3, 4, 16, 16, "MOB");
  public static Sprite TIER_5_UNIT_DOWN_1 = new Sprite("TIER_5_UNIT", 4, 4, 16, 16, "MOB");
  public static Sprite TIER_5_UNIT_DOWN_2 = new Sprite("TIER_5_UNIT", 5, 4, 16, 16, "MOB");
  public static Sprite TIER_5_UNIT_LEFT_1 = new Sprite("TIER_5_UNIT", 6, 4, 16, 16, "MOB");
  public static Sprite TIER_5_UNIT_LEFT_2 = new Sprite("TIER_5_UNIT", 7, 4, 16, 16, "MOB");

  public static Sprite TIER_6_UNIT_UP_1 = new Sprite("TIER_6_UNIT", 0, 5, 16, 16, "MOB");
  public static Sprite TIER_6_UNIT_UP_2 = new Sprite("TIER_6_UNIT", 1, 5, 16, 16, "MOB");
  public static Sprite TIER_6_UNIT_RIGHT_1 = new Sprite("TIER_6_UNIT", 2, 5, 16, 16, "MOB");
  public static Sprite TIER_6_UNIT_RIGHT_2 = new Sprite("TIER_6_UNIT", 3, 5, 16, 16, "MOB");
  public static Sprite TIER_6_UNIT_DOWN_1 = new Sprite("TIER_6_UNIT", 4, 5, 16, 16, "MOB");
  public static Sprite TIER_6_UNIT_DOWN_2 = new Sprite("TIER_6_UNIT", 5, 5, 16, 16, "MOB");
  public static Sprite TIER_6_UNIT_LEFT_1 = new Sprite("TIER_6_UNIT", 6, 5, 16, 16, "MOB");
  public static Sprite TIER_6_UNIT_LEFT_2 = new Sprite("TIER_6_UNIT", 7, 5, 16, 16, "MOB");

  public static Sprite TIER_2_UNIT = new Sprite("TIER_1_UNIT", 0, 1, 16, 16, "MOB");
  public static Sprite TIER_3_UNIT = new Sprite("TIER_3_UNIT", 0, 2, 16, 16, "MOB");
  public static Sprite TIER_4_UNIT = new Sprite("TIER_4_UNIT", 0, 3, 16, 16, "MOB");
  public static Sprite TIER_5_UNIT = new Sprite("TIER_5_UNIT", 0, 4, 16, 16, "MOB");
  public static Sprite TIER_6_UNIT = new Sprite("TIER_6_UNIT", 0, 5, 16, 16, "MOB");

  public static Sprite SELECTOR_BG_LEFT = new Sprite("SELECTOR_BG_LEFT", 0, 0, 16, 16, "GUI");
  public static Sprite SELECTOR_BG_MID = new Sprite("SELECTOR_BG_MID", 1, 0, 16, 16, "GUI");
  public static Sprite SELECTOR_BG_RIGHT = new Sprite("SELECTOR_BG_RIGHT", 2, 0, 16, 16, "GUI");

  public static Sprite COIN = new Sprite("COIN", 3, 0, 16, 16, "GUI");

  public static Sprite HEALTH_MAX = new Sprite("HEALTH_MAX", 0, 1, 16, 16, "GUI");
  public static Sprite HEALTH_90 = new Sprite("HEALTH_90", 1, 1, 16, 16, "GUI");
  public static Sprite HEALTH_80 = new Sprite("HEALTH_80", 2, 1, 16, 16, "GUI");
  public static Sprite HEALTH_70 = new Sprite("HEALTH_70", 3, 1, 16, 16, "GUI");
  public static Sprite HEALTH_60 = new Sprite("HEALTH_60", 4, 1, 16, 16, "GUI");
  public static Sprite HEALTH_50 = new Sprite("HEALTH_50", 5, 1, 16, 16, "GUI");
  public static Sprite HEALTH_40 = new Sprite("HEALTH_40", 6, 1, 16, 16, "GUI");
  public static Sprite HEALTH_30 = new Sprite("HEALTH_30", 7, 1, 16, 16, "GUI");
  public static Sprite HEALTH_20 = new Sprite("HEALTH_20", 8, 1, 16, 16, "GUI");
  public static Sprite HEALTH_10 = new Sprite("HEALTH_10", 9, 1, 16, 16, "GUI");
  public static Sprite HEALTH_DEAD = new Sprite("HEALTH_DEAD", 10, 1, 16, 16, "GUI");

  public String name;
  public int x, y;
  public int width, height;
  public int[] pixels;

  public Sprite(String name, int x, int y, int width, int height, String spriteType) {
    this.name = name;
    this.x = x << 4;
    this.y = y << 4;
    this.width = width;
    this.height = height;
    this.pixels = new int[width * height];
    this.create(spriteType);
  }

  public Sprite(int colour) {
    this.x = 0;
    this.y = 0;
    this.width = 16;
    this.height = 16;
    this.pixels = new int[width * height];
    create(colour);
  }

  private void create(String spriteType) {
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        SpriteSheet sheet = getSpriteSheet(spriteType);
        pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.width];
      }
    }
  }

  private void create(int colour) {
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        pixels[x + y * width] = colour;
      }
    }
  }

  private SpriteSheet getSpriteSheet(String type) {
    if (type.equals("MOB")) {
      return spriteSheets[1];
    } else if (type.equals("BLOCK")) {
      return spriteSheets[0];
    } else {
      return spriteSheets[2];
    }
  }

}
