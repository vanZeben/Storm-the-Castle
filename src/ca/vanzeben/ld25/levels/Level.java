package ca.vanzeben.ld25.levels;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import ca.vanzeben.ld25.MainGame;
import ca.vanzeben.ld25.entities.Camera;
import ca.vanzeben.ld25.entities.Entity;
import ca.vanzeben.ld25.entities.EntityTypes;
import ca.vanzeben.ld25.entities.Mob;
import ca.vanzeben.ld25.entities.players.Unit;
import ca.vanzeben.ld25.entities.projectiles.Projectile;
import ca.vanzeben.ld25.gfx.Screen;
import ca.vanzeben.ld25.gfx.sprites.Sprite;
import ca.vanzeben.ld25.levels.tiles.Tile;
import ca.vanzeben.ld25.levels.tiles.TileTypes;
import ca.vanzeben.ld25.listener.InputHandler;
import ca.vanzeben.ld25.sound.Sound;

public class Level {

  public int width, height;

  private String pathOfImage;

  public Tile[] tiles;
  private String backgroundTileType;
  public int numEntitiesPassed = 0;

  private volatile List<Entity> entities = new ArrayList<Entity>();
  public Camera camera;

  public int castleHealth = 1;
  public int maxHealth = castleHealth;

  private boolean shouldMusicPlay = false;

  public Level(int width, int height, InputHandler input) {
    this.width = width;
    this.height = height;
    tiles = new Tile[width * height];
    this.backgroundTileType = "PATH";
    this.generateLevel(width + height / 2);
    this.camera = new Camera(this, 0, 0, input, 5);
  }

  public Level(String path, InputHandler input, boolean music, boolean camera, int castleHealth) {
    this.pathOfImage = path;
    this.backgroundTileType = "PATH";
    this.loadLevelFromFile();
    shouldMusicPlay = music;
    if (camera) {
      this.camera = new Camera(this, 0, 0, input, 5);
    }
    this.castleHealth = castleHealth;
    this.maxHealth = castleHealth;
  }

  private Random random = new Random();

  private void generateLevel(int difficulty) {
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        tiles[x + y * width] = TileTypes.get("BG").get(this, x, y);
      }
    }
    int startX = (random.nextInt(100)) >> 4, startY = (random.nextInt(100)) >> 4;
    int numTiles = difficulty * 10;
    int numTurrets = (numTiles / 25) * 2;

    tiles[startX + startY * width] = TileTypes.get("START").get(this, startX, startY);
    int previousX = startX;
    int previousY = startY;

    tiles[previousX + previousY * width] = TileTypes.get("END").get(this, previousX, previousY);
  }

  private void loadLevelFromFile() {
    BufferedImage image = null;
    int[] tiles;
    try {
      image = ImageIO.read(Level.class.getResource(this.pathOfImage));
      int w = width = image.getWidth();
      int h = height = image.getHeight();
      tiles = new int[w * h];
      this.tiles = new Tile[w * h];
      image.getRGB(0, 0, w, h, tiles, 0, w);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        this.tiles[x + y * width] = parseTileFromColour(tiles[x + y * width], x, y);
      }
    }
  }

  public void update() {
    if (shouldMusicPlay) {
      Sound.MAIN_LEVEL_BG.loop();
    }

    synchronized (this.entities) {
      for (Iterator<Entity> it = this.entities.iterator(); it.hasNext();) {
        Entity e = (Entity) it.next();
        for (Iterator<Entity> pit = this.entities.iterator(); pit.hasNext();) {
          Entity proj = (Entity) pit.next();
          if (proj instanceof Projectile && e instanceof Mob) {
            if (proj.getTileX() == e.getTileX() && proj.getTileY() == e.getTileY()) {
              ((Mob) e).isHit = true;
              proj.removed = true;
            }
          }
        }
      }
    }

    synchronized (this.entities) {
      for (Iterator<Entity> it = this.entities.iterator(); it.hasNext();) {
        Entity e = (Entity) it.next();
        e.update();
        if (e instanceof Mob && ((Mob) e).hasCompleted()) {
          e.removed = true;
          Sound.COIN_UP.play();
          MainGame.game.addScore((Unit) e);
          castleHealth--;
        }
        if (e.removed) {
          it.remove();
        }
      }
    }

    if (castleHealth <= 0) {
      Sound.EXPLOSION.play();
      MainGame.game.end(true);
      return;
    }

    if (!canGetMoreUnits()) {
      MainGame.game.end(false);
      return;
    }

    for (int i = 0; i < tiles.length; i++) {
      Tile tile = getTile(i);
      tile.update(this);
    }

    if (camera != null) {
      camera.update();
    }
  }

  private boolean canGetMoreUnits() {
    boolean moreUnitsAvailable = true;
    int lowestCost = 1000;
    for (EntityTypes et : EntityTypes.entityTypes) {
      if (lowestCost > ((Unit) et.get()).cost) {
        lowestCost = ((Unit) et.get()).cost;
      }
    }
    if (MainGame.game.money < lowestCost && !hasUnitsInLevel()) {
      moreUnitsAvailable = false;
    }
    return moreUnitsAvailable;
  }

  public boolean hasUnitsInLevel() {
    boolean units = false;
    synchronized (this.entities) {
      for (Iterator<Entity> it = this.entities.iterator(); it.hasNext();) {
        Entity e = (Entity) it.next();
        if (e instanceof Mob) {
          units = true;
          break;
        }
      }
    }
    return units;
  }

  public void render(int xScroll, int yScroll, Screen screen) {
    if (xScroll < 0) {
      xScroll = 0;
    }
    if (xScroll > ((width << 4) - screen.width)) {
      xScroll = ((width << 4) - screen.width);
    }
    if (yScroll < 0) {
      yScroll = 0;
    }
    if (yScroll > ((height << 4) - screen.height)) {
      yScroll = ((height << 4) - screen.height);
    }

    screen.setOffset(xScroll, yScroll);
    int xMin = xScroll >> 4;
    int xMax = (xScroll + screen.width) >> 4;
    int yMin = yScroll >> 4;
    int yMax = (yScroll + screen.height) >> 4;

    for (int y = yMin; y < yMax + 15; y++) {
      for (int x = xMin; x < xMax + 15; x++) {
        Tile tile = getTile(x, y);
        tile.render(screen);
      }
    }
    synchronized (this.entities) {
      for (Iterator<Entity> it = this.entities.iterator(); it.hasNext();) {
        Entity e = (Entity) it.next();
        e.render(screen);
      }
    }
  }

  public Tile getTile(int index) {
    return tiles[index];
  }

  public Tile getTile(int x, int y) {
    if (0 > x || x >= width || 0 > y || y >= height) { return TileTypes.get("VOID").get(this, x, y); }
    return tiles[x + y * width];
  }

  private Tile parseTileFromColour(int colour, int x, int y) {
    for (TileTypes tt : TileTypes.tileTypes) {
      if (tt.get().getColour() == colour) { return tt.get(this, x, y); }
    }
    return TileTypes.get("VOID").get(this, x, y);
  }

  public Tile getBackgroundTile(int x, int y) {
    return TileTypes.get(backgroundTileType).get(this, x, y);
  }

  public void sendForce(String entity, boolean override, boolean sounds) {
    EntityTypes et = EntityTypes.get(entity);
    if (et != null) {
      if (!override && ((Unit) et.get()).cost > MainGame.game.money) {
        MainGame.game.error("Unit too expensive");
        return;
      }

      if (!override) {
        MainGame.game.money -= ((Unit) et.get()).cost;
      }
      synchronized (this.entities) {
        this.entities.add(et.get(this, this.getStartLocation()[0], this.getStartLocation()[1]));
      }

      if (sounds) {
        if (et.get().getName().equals("TIER_1_UNIT")) {
          Sound.TIER_1_UNIT.play();
        } else if (et.get().getName().equals("TIER_2_UNIT")) {
          Sound.TIER_1_UNIT.play();
        } else if (et.get().getName().equals("TIER_3_UNIT")) {
          Sound.TIER_1_UNIT.play();
        } else if (et.get().getName().equals("TIER_4_UNIT")) {
          Sound.TIER_4_UNIT.play();
        } else if (et.get().getName().equals("TIER_5_UNIT")) {
          Sound.TIER_1_UNIT.play();
        } else if (et.get().getName().equals("TIER_6_UNIT")) {
          Sound.TIER_1_UNIT.play();
        }
      }
    }
  }

  public void sendProjectile(Tile tile, int moveDir, String projectileType) {
    Projectile proj = null;
    if (projectileType.equals("PHOTON_PROJECTILE")) {
      proj = new Projectile(this, projectileType, tile.x << 4, tile.y << 4, moveDir, 1, new Sprite[] { Sprite.PHOTON_PROJECTILE });
    } else if (projectileType.equals("NINJA_PROJECTILE")) {
      proj = new Projectile(this, projectileType, tile.x << 4, tile.y << 4, moveDir, 1, new Sprite[] { Sprite.NINJA_PROJECTILE });
    }
    if (proj != null) {
      synchronized (this.entities) {
        this.entities.add(proj);
      }
    }
  }

  public int[] getStartLocation() {
    int[] coords = { 0, 0 };
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Tile tile = getTile(x, y);
        if (tile.getName().equals("START")) {
          coords[0] = (x << 4);
          coords[1] = (y << 4);
          return coords;
        }
      }
    }
    return coords;
  }

  public int[] getEndLocation() {
    int[] coords = { 0, 0 };
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Tile tile = getTile(x, y);
        if (tile.getName().equals("END")) {
          coords[0] = (x << 4);
          coords[1] = (y << 4);
          return coords;
        }
      }
    }
    return coords;
  }

  public List<Entity> getEntities() {
    return entities;
  }
}