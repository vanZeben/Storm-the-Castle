package ca.vanzeben.ld25.entities.projectiles;

import java.util.ArrayList;

import ca.vanzeben.ld25.entities.Entity;
import ca.vanzeben.ld25.gfx.Screen;
import ca.vanzeben.ld25.gfx.sprites.Sprite;
import ca.vanzeben.ld25.levels.Level;

public class Projectile extends Entity {

  public int moveDir = 0;
  public int speed;

  public Projectile(Level level, String name, int x, int y, int moveDir, int speed, Sprite[] sprites) {
    super(level, name, new ArrayList<Sprite>());
    this.x = x;
    this.y = y;
    this.moveDir = moveDir;
    this.speed = speed;
    for (Sprite sprite : sprites) {
      this.sprites.add(sprite);
    }
  }

  @Override
  public void update() {
    switch (moveDir) {
      case 0:
        move(-1, -1);
        break;
      case 1:
        move(0, -1);
        break;
      case 2:
        move(1, -1);
        break;
      case 3:
        move(1, 0);
        break;
      case 4:
        move(1, 1);
        break;
      case 5:
        move(0, 1);
        break;
      case 6:
        move(-1, 1);
        break;
      case 7:
        move(-1, 0);
        break;
    }
  }

  public void move(int xa, int ya) {
    if (xa != 0 && ya != 0) {
      move(xa, 0);
      move(0, ya);
      return;
    }
    x += xa * speed;
    y += ya * speed;
  }

  @Override
  public void render(Screen screen) {
    screen.renderEntity(this.x, this.y, this, level);
  }
}
