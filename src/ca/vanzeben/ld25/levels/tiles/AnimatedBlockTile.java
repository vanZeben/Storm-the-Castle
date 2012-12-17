package ca.vanzeben.ld25.levels.tiles;

import ca.vanzeben.ld25.gfx.sprites.Sprite;
import ca.vanzeben.ld25.levels.Level;

public class AnimatedBlockTile extends BasicBlockTile {

  protected Sprite[] sprites;
  protected int currentAnimationIndex = 0;
  protected int currentAnimationIndexDir = +1;
  protected long lastTime;
  protected double animationLength = 0.1;

  public AnimatedBlockTile(Level level, Sprite[] sprites, String name, int colour, boolean isSolid, boolean isEmitter, int x,
      int y) {
    super(level, null, name, colour, isSolid, isEmitter, x, y);
    this.sprites = sprites;
    lastTime = System.currentTimeMillis();
  }

  public AnimatedBlockTile(Level level, Sprite[] sprites, String name, int colour, boolean isSolid, boolean isEmitter, int x,
      int y, double animationLength) {
    super(level, null, name, colour, isSolid, isEmitter, x, y);
    this.sprites = sprites;
    lastTime = System.currentTimeMillis();
    this.animationLength = animationLength;
  }

  public void update(Level level) {
    super.update(level);
    if (System.currentTimeMillis() - lastTime >= (animationLength * 1000D)) {
      switchRenderTile();
      lastTime = System.currentTimeMillis();
    }
  }

  public void switchRenderTile() {
    currentAnimationIndex = (currentAnimationIndex + currentAnimationIndexDir) % sprites.length;
    if (currentAnimationIndex == 0) {
      currentAnimationIndexDir = +1;
    } else if (currentAnimationIndex == sprites.length - 1) {
      currentAnimationIndexDir = -1;
    }
  }

  @Override
  public Sprite getSprite() {
    return sprites[currentAnimationIndex];
  }

}
