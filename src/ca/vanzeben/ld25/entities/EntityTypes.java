package ca.vanzeben.ld25.entities;

import ca.vanzeben.ld25.entities.players.Unit;
import ca.vanzeben.ld25.gfx.sprites.Sprite;
import ca.vanzeben.ld25.levels.Level;

public class EntityTypes {

  public static final EntityTypes[] entityTypes = { new EntityTypes("TIER_1_UNIT"), new EntityTypes("TIER_2_UNIT"),
      new EntityTypes("TIER_3_UNIT"), new EntityTypes("TIER_4_UNIT"), new EntityTypes("TIER_5_UNIT"),
      new EntityTypes("TIER_6_UNIT") };

  public static EntityTypes get(String type) {
    for (EntityTypes et : entityTypes) {
      if (et.type.equals(type)) { return et; }
    }
    return null;
  }

  private String type;

  private EntityTypes(String type) {
    this.type = type;
  }

  public Entity get(Level level, int x, int y) {
    if (type.equals("TIER_1_UNIT")) { return new Unit(level, type, new Sprite[] { Sprite.TIER_1_UNIT_UP_1,
        Sprite.TIER_1_UNIT_UP_2, Sprite.TIER_1_UNIT_RIGHT_1, Sprite.TIER_1_UNIT_RIGHT_2, Sprite.TIER_1_UNIT_DOWN_1,
        Sprite.TIER_1_UNIT_DOWN_2, Sprite.TIER_1_UNIT_LEFT_1, Sprite.TIER_1_UNIT_LEFT_2 }, x, y, 1, 2); }
    if (type.equals("TIER_2_UNIT")) { return new Unit(level, type, new Sprite[] { Sprite.TIER_2_UNIT_UP_1,
        Sprite.TIER_2_UNIT_UP_2, Sprite.TIER_2_UNIT_RIGHT_1, Sprite.TIER_2_UNIT_RIGHT_2, Sprite.TIER_2_UNIT_DOWN_1,
        Sprite.TIER_2_UNIT_DOWN_2, Sprite.TIER_2_UNIT_LEFT_1, Sprite.TIER_2_UNIT_LEFT_2 }, x, y, 1, 4); }
    if (type.equals("TIER_3_UNIT")) { return new Unit(level, type, new Sprite[] { Sprite.TIER_3_UNIT_UP_1,
        Sprite.TIER_3_UNIT_UP_2, Sprite.TIER_3_UNIT_RIGHT_1, Sprite.TIER_3_UNIT_RIGHT_2, Sprite.TIER_3_UNIT_DOWN_1,
        Sprite.TIER_3_UNIT_DOWN_2, Sprite.TIER_3_UNIT_LEFT_1, Sprite.TIER_3_UNIT_LEFT_2 }, x, y, 1, 6); }
    if (type.equals("TIER_4_UNIT")) { return new Unit(level, type, new Sprite[] { Sprite.TIER_4_UNIT_UP_1,
        Sprite.TIER_4_UNIT_UP_2, Sprite.TIER_4_UNIT_RIGHT_1, Sprite.TIER_4_UNIT_RIGHT_2, Sprite.TIER_4_UNIT_DOWN_1,
        Sprite.TIER_4_UNIT_DOWN_2, Sprite.TIER_4_UNIT_LEFT_1, Sprite.TIER_4_UNIT_LEFT_2 }, x, y, 2, 2); }
    if (type.equals("TIER_5_UNIT")) { return new Unit(level, type, new Sprite[] { Sprite.TIER_5_UNIT_UP_1,
        Sprite.TIER_5_UNIT_UP_2, Sprite.TIER_5_UNIT_RIGHT_1, Sprite.TIER_5_UNIT_RIGHT_2, Sprite.TIER_5_UNIT_DOWN_1,
        Sprite.TIER_5_UNIT_DOWN_2, Sprite.TIER_5_UNIT_LEFT_1, Sprite.TIER_5_UNIT_LEFT_2 }, x, y, 2, 4); }
    if (type.equals("TIER_6_UNIT")) { return new Unit(level, type, new Sprite[] { Sprite.TIER_6_UNIT_UP_1,
        Sprite.TIER_6_UNIT_UP_2, Sprite.TIER_6_UNIT_RIGHT_1, Sprite.TIER_6_UNIT_RIGHT_2, Sprite.TIER_6_UNIT_DOWN_1,
        Sprite.TIER_6_UNIT_DOWN_2, Sprite.TIER_6_UNIT_LEFT_1, Sprite.TIER_6_UNIT_LEFT_2 }, x, y, 2, 6); }
    return null;
  }

  public Entity get() {
    return get(null, 0, 0);
  }

  public String getType() {
    return type;
  }
}
