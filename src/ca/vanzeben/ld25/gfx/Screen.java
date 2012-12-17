package ca.vanzeben.ld25.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import ca.vanzeben.ld25.MainGame;
import ca.vanzeben.ld25.entities.Entity;
import ca.vanzeben.ld25.entities.EntityTypes;
import ca.vanzeben.ld25.entities.Mob;
import ca.vanzeben.ld25.entities.players.Unit;
import ca.vanzeben.ld25.gfx.sprites.ImageLoader;
import ca.vanzeben.ld25.gfx.sprites.Sprite;
import ca.vanzeben.ld25.levels.Level;
import ca.vanzeben.ld25.levels.tiles.Tile;

public class Screen {
  public int width, height;
  public int[] pixels;
  private Graphics graphics;
  public static final int TILE_SIZE = 16;
  private int xOffset, yOffset;

  public Screen(int width, int height) {
    this.width = width;
    this.height = height;
    this.pixels = new int[width * height];
  }

  public void setGraphics(Graphics g) {
    this.graphics = g;
  }

  public void setOffset(int xOffset, int yOffset) {
    this.xOffset = xOffset;
    this.yOffset = yOffset;
  }

  public void clear() {
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = 0;
    }
  }

  public void renderTile(Level level, int xOrig, int yOrig, Tile tile) {
    int xp = (xOrig * tile.getSprite().width) - xOffset;
    int yp = (yOrig * tile.getSprite().height) - yOffset;

    for (int y = 0; y < tile.getSprite().height; y++) {
      int yt = y + yp;
      for (int x = 0; x < tile.getSprite().width; x++) {
        int xt = x + xp;
        if (0 - tile.getSprite().width > xt || xt >= width || 0 - tile.getSprite().height > yt || yt >= height) {
          break;
        }
        if (xt < 0) {
          xt = 0;
        }

        if (yt < 0) {
          yt = 0;
        }
        int colour = tile.getSprite().pixels[x + y * tile.getSprite().width];
        if ((colour == 0xFFFF00FF || colour == 0xFF800080) && level != null) {
          colour = level.getBackgroundTile(xOrig, yOrig).getSprite().pixels[x + y
              * level.getBackgroundTile(xOrig, yOrig).getSprite().width];
        }
        pixels[xt + yt * width] = colour;
      }
    }
  }

  public void renderEntity(int xOrig, int yOrig, Entity entity, Level level) {
    int xp = xOrig - xOffset;
    int yp = yOrig - yOffset;
    for (int y = 0; y < entity.getCurrentSprite().height; y++) {
      int yt = y + yp;
      for (int x = 0; x < entity.getCurrentSprite().width; x++) {
        int xt = x + xp;
        if (0 - entity.getCurrentSprite().width > xt || xt >= width || 0 - entity.getCurrentSprite().height > yt || yt >= height) {
          break;
        }
        if (xt < 0) {
          xt = 0;
        }

        if (yt < 0) {
          yt = 0;
        }
        int colour = entity.getCurrentSprite().pixels[x + y * entity.getCurrentSprite().width];
        if (colour != 0xFFFF00FF && colour != 0xFF800080) {
          pixels[xt + yt * width] = colour;
        }
      }
    }
    if (entity instanceof Mob) {
      yp -= 8;
      for (int y = 0; y < ((Mob) entity).getCurrentHealthSprite().height; y++) {
        int yt = y + yp;
        for (int x = 0; x < ((Mob) entity).getCurrentHealthSprite().width; x++) {
          int xt = x + xp;
          if (0 - ((Mob) entity).getCurrentHealthSprite().width > xt || xt >= width
              || 0 - ((Mob) entity).getCurrentHealthSprite().height > yt || yt >= height) {
            break;
          }
          if (xt < 0) {
            xt = 0;
          }

          if (yt < 0) {
            yt = 0;
          }
          int colour = ((Mob) entity).getCurrentHealthSprite().pixels[x + y * ((Mob) entity).getCurrentHealthSprite().width];
          if (colour != 0xFFFF00FF && colour != 0xFF800080) {
            pixels[xt + yt * width] = colour;
          }
        }
      }
    }
  }

  private List<DrawnString> textToAdd = new ArrayList<DrawnString>();

  public void drawString(String msg, int x, int y, int size, int style, Color color) {
    synchronized (textToAdd) {
      textToAdd.add(new DrawnString(msg, x, y, size, style, color));
    }
  }

  private void setFont(int size, int style) {
    graphics.setFont(new Font("Helvetica", style, size));
  }

  public void drawCenteredString(String msg, int x, int y, int size, int style, Color color) {
    setFont(size, style);
    drawString(msg, x - getStringWidth(msg) / 2, y, size, style, color);
  }

  public void drawRightJustifiedString(String msg, int xOffset, int y, int size, int style, Color color) {
    setFont(size, style);
    drawString(msg, MainGame.WIDTH * MainGame.SCALE - getStringWidth(msg) - xOffset, y, size, style, color);
  }

  @SuppressWarnings("serial")
  public int getStringWidth(String msg) {
    if (graphics == null) { return 0; }
    FontMetrics metrics = graphics.getFontMetrics();
    if (metrics == null) {
      metrics = new FontMetrics(graphics.getFont()) {
      };
    }
    return metrics.stringWidth(msg);
  }

  public void renderText() {
    synchronized (textToAdd) {
      for (DrawnString s : textToAdd) {
        if (graphics == null) { return; }
        graphics.setColor(s.color);
        setFont(s.size, s.style);
        graphics.drawString(s.msg, s.x, s.y);
      }
    }
    textToAdd.clear();
  }

  public void renderHud() {
    for (int i = 0; i < EntityTypes.entityTypes.length; i++) {
      EntityTypes e = EntityTypes.entityTypes[i];
      Sprite sprite = e.get().getCurrentSprite();
      int xp = (width - (sprite.width / 2)) - (i << 4) - 8;
      int yp = height - sprite.height;
      for (int y = 0; y < sprite.height; y++) {
        int yt = y + yp;
        for (int x = 0; x < sprite.width; x++) {
          int xt = x + xp;
          if (0 > xt || xt >= width || 0 > yt || yt >= height) {
            break;
          }
          if (xt < 0) {
            xt = 0;
          }
          if (yt < 0) {
            yt = 0;
          }
          int colour = sprite.pixels[x + y * sprite.width];
          if (colour == 0xFFFF00FF || colour == 0xFF800080) {
            if (i == EntityTypes.entityTypes.length - 1) {
              colour = Sprite.SELECTOR_BG_LEFT.pixels[x + y * Sprite.SELECTOR_BG_LEFT.width];
            } else {
              colour = Sprite.SELECTOR_BG_MID.pixels[x + y * Sprite.SELECTOR_BG_MID.width];
            }
          }
          if (colour != 0xFFFF00FF && colour != 0xFF800080) {
            pixels[xt + yt * width] = colour;
          }
        }
      }
      int width = (this.width * MainGame.SCALE - i * 33) - 13;
      int height = this.height * MainGame.SCALE - 32;
      drawCenteredString(((Unit) e.get()).cost + "", width - 1, height - 1, 12, 1, Color.BLACK);
      drawCenteredString(((Unit) e.get()).cost + "", width, height, 12, 1, Color.WHITE);
    }

    // Money
    Sprite sprite = Sprite.COIN;
    int xp = 0;
    int yp = height - sprite.height;
    for (int y = 0; y < sprite.height; y++) {
      int yt = y + yp;
      for (int x = 0; x < sprite.width; x++) {
        int xt = x + xp;
        if (0 > xt || xt >= width || 0 > yt || yt >= height) {
          break;
        }
        if (xt < 0) {
          xt = 0;
        }
        if (yt < 0) {
          yt = 0;
        }
        int colour = sprite.pixels[x + y * sprite.width];
        if (colour != 0xFFFF00FF && colour != 0xFF800080) {
          pixels[xt + yt * width] = colour;
        }
      }
    }
    drawString(MainGame.game.money + "", 33 + 2, height * MainGame.SCALE - 6, 22, 1, Color.BLACK);
    drawString(MainGame.game.money + "", 33, height * MainGame.SCALE - 8, 22, 1, Color.WHITE);

    // Castle Health
    drawCenteredString((MainGame.game.level.maxHealth - MainGame.game.level.castleHealth) + "/" + MainGame.game.level.maxHealth,
        width + 2, height * MainGame.SCALE - 6, 22, 1, Color.BLACK);
    drawCenteredString((MainGame.game.level.maxHealth - MainGame.game.level.castleHealth) + "/" + MainGame.game.level.maxHealth,
        width, height * MainGame.SCALE - 8, 22, 1, Color.WHITE);
  }

  public void onScreenClick(Level level, int x, int y) {
    for (int i = 0; i < EntityTypes.entityTypes.length; i++) {
      EntityTypes e = EntityTypes.entityTypes[i];
      Sprite sprite = e.get().getCurrentSprite();
      int xMin = (width - (sprite.width / 2)) - (i << 4) - 8;
      int yMin = height - sprite.height;
      int xMax = xMin + sprite.width;
      int yMax = yMin + sprite.height;

      if (xMin <= x && x <= xMax && yMin <= y && y <= yMax) {
        level.sendForce(e.getType(), false, true);
        return;
      }
    }
  }

  public void fill(int color) {
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = color;
    }
  }

  public void renderCenteredImage(ImageLoader image) {
    int xp = width / 2 - image.width / 2;
    int yp = height / 2 - image.height / 2;
    for (int y = 0; y < image.height; y++) {
      int yt = y + yp;
      for (int x = 0; x < image.width; x++) {
        int xt = x + xp;
        if (0 - image.width > xt || xt >= width || 0 - image.height > yt || yt >= height) {
          break;
        }
        if (xt < 0) {
          xt = 0;
        }

        if (yt < 0) {
          yt = 0;
        }
        int colour = image.pixels[x + y * image.width];
        pixels[xt + yt * width] = colour;
      }
    }
  }

}
