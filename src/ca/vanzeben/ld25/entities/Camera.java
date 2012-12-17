package ca.vanzeben.ld25.entities;

import ca.vanzeben.ld25.MainGame;
import ca.vanzeben.ld25.levels.Level;
import ca.vanzeben.ld25.listener.InputHandler;

public class Camera {

  private Level level;
  public int x, y;
  private InputHandler input;
  private int speed;

  public Camera(Level level, int x, int y, InputHandler input, int speed) {
    this.level = level;
    this.x = x;
    this.y = y;
    this.input = input;
    this.speed = speed;
  }

  public void update() {
    int xa = 0;
    int ya = 0;
    if (this.input.left) {
      xa--;
    }
    if (this.input.right) {
      xa++;
    }
    if (this.input.up) {
      ya--;
    }
    if (this.input.down) {
      ya++;
    }

    this.x += xa * speed;
    this.y += ya * speed;

    if (this.x < 0) {
      this.x = 0;
    }
    if (this.x > (level.width << 4) - (MainGame.WIDTH * MainGame.SCALE / 2)) {
      this.x = (level.width << 4) - (MainGame.WIDTH * MainGame.SCALE / 2);
    }
    if (this.y < 0) {
      this.y = 0;
    }
    if (this.y > (level.height << 4) - (MainGame.HEIGHT * MainGame.SCALE / 2)) {
      this.y = (level.height << 4) - (MainGame.HEIGHT * MainGame.SCALE / 2);
    }
  }
}
