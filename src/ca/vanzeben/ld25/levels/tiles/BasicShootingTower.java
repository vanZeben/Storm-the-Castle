package ca.vanzeben.ld25.levels.tiles;

import java.util.Iterator;

import ca.vanzeben.ld25.entities.Entity;
import ca.vanzeben.ld25.entities.projectiles.Projectile;
import ca.vanzeben.ld25.gfx.sprites.Sprite;
import ca.vanzeben.ld25.levels.Level;
import ca.vanzeben.ld25.sound.Sound;

public class BasicShootingTower extends BasicBlockTile {

  private String projectileType;
  private int range;
  private long lastFireTime;
  private double delay;

  public BasicShootingTower(Level level, Sprite sprite, String name, int colour, boolean isSolid, boolean isEmitter, int x,
      int y, String projectileType, int range) {
    super(level, sprite, name, colour, isSolid, isEmitter, x, y);
    this.projectileType = projectileType;
    this.range = range;
    this.delay = 1;
    this.lastFireTime = (long) (System.currentTimeMillis() - (delay * 1000D));
  }

  @Override
  public void update(Level level) {
    super.update(level);
    fireAtNearTargets();
  }

  public void fireAtNearTargets() {
    synchronized (level.getEntities()) {
      for (Iterator<Entity> it = level.getEntities().iterator(); it.hasNext();) {
        Entity e = (Entity) it.next();
        if (!(e instanceof Projectile) && getDistToTarget(e.getTileX(), e.getTileY()) <= range
            && (System.currentTimeMillis() - lastFireTime) >= delay * 1000D) {
          shoot(e.getTileX(), e.getTileY());
          return;
        }
      }
    }
  }

  public void shoot(int x, int y) {
    lastFireTime = System.currentTimeMillis();
    level.sendProjectile(this, getDirToTarget(x - this.x, y - this.y), projectileType);
    if (projectileType.equals("PHOTON_PROJECTILE")) {
      Sound.PHOTON_FIRE.play();
    } else if (projectileType.equals("NINJA_PROJECTILE")) {
      Sound.NINJA_STAR_FIRE.play();
    }
  }

  private int getDistToTarget(int x, int y) {
    return (int) Math.abs(Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2)));
  }

  private int getDirToTarget(int x, int y) {
    if (x < 0 && y < 0) {
      return 0;
    } else if (x == 0 && y < 0) {
      return 1;
    } else if (x > 0 && y < 0) {
      return 2;
    } else if (x > 0 && y == 0) {
      return 3;
    } else if (x > 0 && y > 0) {
      return 4;
    } else if (x == 0 && y > 0) {
      return 5;
    } else if (x < 0 && y > 0) {
      return 6;
    } else if (x < 0 && y == 0) {
      return 7;
    } else {
      return -1;
    }
  }
}
