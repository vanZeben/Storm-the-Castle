package ca.vanzeben.ld25.gfx;

import java.awt.Color;

public class DrawnString {

  public String msg;
  public int x, y;
  public int size;
  public int style;
  public Color color;

  public DrawnString(String msg, int x, int y, int size, int style, Color color) {
    this.msg = msg;
    this.x = x;
    this.y = y;
    this.size = size;
    this.style = style;
    this.color = color;
  }
}
