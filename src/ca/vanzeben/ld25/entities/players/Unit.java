package ca.vanzeben.ld25.entities.players;

import java.util.ArrayList;

import ca.vanzeben.ld25.entities.Mob;
import ca.vanzeben.ld25.gfx.sprites.Sprite;
import ca.vanzeben.ld25.levels.Level;

public class Unit extends Mob {

  public int cost = 0;

  public Unit(Level level, String name, Sprite[] sprites, int x, int y, int speed, int health) {
    super(level, new ArrayList<Sprite>(), name, x, y, speed, health);
    for (Sprite s : sprites) {
      this.sprites.add(s);
    }
    this.cost = speed * health * 50;
  }
}
