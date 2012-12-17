package ca.vanzeben.ld25.entities;

import java.util.List;

import ca.vanzeben.ld25.gfx.Screen;
import ca.vanzeben.ld25.gfx.sprites.Sprite;
import ca.vanzeben.ld25.levels.Level;
import ca.vanzeben.ld25.levels.tiles.Tile;

public abstract class Mob extends Entity {

  protected int maxHealth;
  protected int health;
  public boolean isHit = false;

  protected boolean isMoving = false;
  protected int moveDir = 0;
  protected int previousMoveDir = 1;
  protected int numSteps = 0;

  protected int speed = 1;

  protected int[] currentAnimationIndex = { 0, 0 };
  protected int currentAnimationFrame = 0;
  protected long lastTime;
  protected double animationLength = 0.2;

  public Mob(Level level, List<Sprite> sprites, String name, int x, int y, int speed, int health) {
    super(level, name, sprites);
    this.speed = speed;
    this.x = x;
    this.y = y;
    this.health = health;
    this.maxHealth = health;
    lastTime = System.currentTimeMillis();
  }

  public void update() {
    if (moveDir == 0) {
      move(0, -1);
      currentAnimationIndex = new int[] { 0, 1 };
    } else if (moveDir == 1) {
      move(1, 0);
      currentAnimationIndex = new int[] { 2, 3 };
    } else if (moveDir == 2) {
      move(0, 1);
      currentAnimationIndex = new int[] { 4, 5 };
    } else if (moveDir == 3) {
      move(-1, 0);
      currentAnimationIndex = new int[] { 6, 7 };
    }
    if (isHit()) {
      health--;
      isHit = false;
    }
    if (health == 0) {
      removed = true;
    }
    if (System.currentTimeMillis() - lastTime >= (animationLength * 1000D)) {
      switchRenderSprite();
      lastTime = System.currentTimeMillis();
    }
  }

  public void switchRenderSprite() {
    currentAnimationFrame = (currentAnimationFrame + 1) % 2;
  }

  @Override
  public Sprite getCurrentSprite() {
    return sprites.get(currentAnimationIndex[currentAnimationFrame]);
  }

  public void render(Screen screen) {
    screen.renderEntity(x, y, this, level);
  }

  public void move(int xa, int ya) {
    if (!hasCollided(xa * speed, ya * speed) && !isOppositeDir()) {
      if (ya < 0) {
        // up
        moveDir = 0;
      } else if (ya > 0) {
        // down
        moveDir = 2;
      } else if (xa < 0) {
        // left
        moveDir = 3;
      } else if (xa > 0) {
        // right
        moveDir = 1;
      }
      numSteps++;
      previousMoveDir = moveDir;

      x += xa * speed;
      y += ya * speed;
    } else {
      moveDir = (moveDir + 1) % 4;
    }
  }

  public boolean hasCollided(int xTo, int yTo) {
    int[][] collisionBox = { { 0, 0 }, { 15, 0 }, { 0, 15 }, { 15, 15 } };

    for (int i = 0; i < collisionBox.length; i++) {
      int xx = collisionBox[i][0];
      int yy = collisionBox[i][1];
      if (isSolidTile(xx, yy, xTo, yTo)) { return true; }
    }
    return false;
  }

  public boolean isSolidTile(int x, int y, int xAlt, int yAlt) {
    Tile lastTile = level.getTile((this.x + x) >> 4, (this.y + y) >> 4);
    Tile newTile = level.getTile((this.x + x + xAlt) >> 4, (this.y + y + yAlt) >> 4);
    if (!lastTile.equals(newTile) && newTile.isSolid()) { return true; }
    return false;
  }

  public boolean isHit() {
    return isHit;
  }

  public List<Sprite> getSprites() {
    return sprites;
  }

  public String getName() {
    return name;
  }

  public boolean isOppositeDir() {
    if (previousMoveDir == 0) {
      return moveDir == 2;
    } else if (previousMoveDir == 2) {
      return moveDir == 0;
    } else if (previousMoveDir == 1) {
      return moveDir == 3;
    } else if (previousMoveDir == 3) { return moveDir == 1; }
    return false;
  }

  public boolean hasCompleted() {
    if (this.x == level.getEndLocation()[0] && this.y == level.getEndLocation()[1]) { return true; }
    return false;
  }

  public Sprite getCurrentHealthSprite() {
    int healthPercent = (int) (((double) health / (double) maxHealth) * (double) 100);
    if (healthPercent >= 100) { return Sprite.HEALTH_MAX; }
    if (healthPercent >= 90) { return Sprite.HEALTH_90; }
    if (healthPercent >= 80) { return Sprite.HEALTH_80; }
    if (healthPercent >= 70) { return Sprite.HEALTH_70; }
    if (healthPercent >= 60) { return Sprite.HEALTH_60; }
    if (healthPercent >= 50) { return Sprite.HEALTH_50; }
    if (healthPercent >= 40) { return Sprite.HEALTH_40; }
    if (healthPercent >= 30) { return Sprite.HEALTH_30; }
    if (healthPercent >= 20) { return Sprite.HEALTH_20; }
    if (healthPercent >= 10) { return Sprite.HEALTH_10; }
    return Sprite.HEALTH_DEAD;
  }
}
