package ca.vanzeben.ld25;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import ca.vanzeben.ld25.entities.players.Unit;
import ca.vanzeben.ld25.gfx.Screen;
import ca.vanzeben.ld25.gfx.menus.LoadingMenu;
import ca.vanzeben.ld25.gfx.menus.LostMenu;
import ca.vanzeben.ld25.gfx.menus.Menu;
import ca.vanzeben.ld25.gfx.menus.WonMenu;
import ca.vanzeben.ld25.levels.Level;
import ca.vanzeben.ld25.listener.InputHandler;
import ca.vanzeben.ld25.listener.MouseHandler;
import ca.vanzeben.ld25.sound.Sound;

@SuppressWarnings("serial")
public class MainGame extends Canvas implements Runnable {

  public static final String GAME_TITLE = "Storm the Castle!";
  public static final int WIDTH = 480;
  public static final int HEIGHT = 270;
  public static final int SCALE = 2;

  public static MainGame game;
  private JFrame frame;
  private Screen screen;
  public Level level;
  private Thread thread;
  private boolean isRunning = false;
  private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
  private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

  public InputHandler input = new InputHandler();
  private MouseHandler mouseHandler = new MouseHandler();
  public boolean isApplet = false;

  public int fps = 0;
  public int ups = 0;

  public int totalScore = 0;
  public int money = 250;
  public long startTime;
  public long endTime;
  public Menu menu;

  public MainGame(boolean isApplet) {
    game = this;
    this.isApplet = isApplet;
    Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);

    this.frame = new JFrame();
    this.frame.setResizable(false);
    this.frame.add(game);
    this.frame.pack();

    this.frame.setLocationRelativeTo(null);
    this.frame.setVisible(true);
    if (!isApplet) {
      this.frame.setTitle(GAME_TITLE);
      this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    addKeyListener(input);
    addMouseListener(mouseHandler);
  }

  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  public void end(boolean won) {
    if (won) {
      game.setMenu(new WonMenu(MainGame.game, MainGame.game.input));
    } else {
      game.setMenu(new LostMenu(MainGame.game, MainGame.game.input));
      totalScore = 0;
      money = 250;
    }
    endTime = System.currentTimeMillis();

  }

  public void nextLevel() {
    game.setMenu(null);
    endTime = System.currentTimeMillis();
    currentLevel++;
    reset();
  }

  public int currentLevel = 1;
  private Level[] levels = new Level[4];

  public void loadLevels() {
    for (int i = 1; i <= 4; i++) {
      levels[i - 1] = new Level("/levels/" + i + ".png", input, true, true, i * 2);
    }
  }

  public void reset() {
    level = levels[currentLevel - 1];
    startTime = System.currentTimeMillis();
  }

  public synchronized void start() {
    if (this.isRunning) { return; }
    this.isRunning = true;
    this.thread = new Thread(this, "MainGameThread");
    this.thread.start();
  }

  public synchronized void stop() {
    if (!this.isRunning) { return; }
    this.isRunning = false;
    for (Sound s : Sound.values()) {
      s.reset();
    }
  }

  @Override
  public void run() {
    long lastTime = System.nanoTime();
    long lastTimer = System.currentTimeMillis();
    double ns = 1000000000D / 60D;
    double delta = 0D;

    int fps = 0;
    int ups = 0;
    this.init();

    while (this.isRunning) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while (delta >= 1) {
        update();
        delta--;
        ups++;
      }
      render();
      fps++;
      if (System.currentTimeMillis() - lastTimer >= 1000) {
        lastTimer += 1000;
        this.fps = fps;
        this.ups = ups;
        fps = 0;
        ups = 0;
      }
    }

  }

  private void init() {
    screen = new Screen(WIDTH, HEIGHT);
    setMenu(new LoadingMenu(this, input));
  }

  public void update() {
    input.update();
    if (menu != null) {
      menu.update();
    } else {
      if (level != null) {
        level.update();
      }
    }
  }

  public void render() {
    BufferStrategy strategy = getBufferStrategy();
    if (strategy == null) {
      createBufferStrategy(3);
      requestFocus();
      return;
    }
    screen.clear();
    Graphics g = strategy.getDrawGraphics();
    screen.setGraphics(g);

    if (menu != null) {
      menu.render(screen);
      // Rendering
    } else {
      int xScroll = level.camera.x;
      int yScroll = level.camera.y;
      level.render(xScroll, yScroll, screen);
      this.renderHud();
    }

    for (int i = 0; i < WIDTH * HEIGHT; i++) {
      pixels[i] = screen.pixels[i];
    }
    g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);

    screen.renderText();

    g.dispose();
    strategy.show();
  }

  private void renderHud() {
    screen.renderHud();

    // screen.drawString("FPS: " + this.fps, 10, 22 + 2, 22, 1, Color.BLACK);
    // screen.drawString("FPS: " + this.fps, 10, 22, 22, 1, Color.WHITE);
    //
    // screen.drawRightJustifiedString("UPS: " + this.ups, 8, 22 + 2, 22, 1,
    // Color.BLACK);
    // screen.drawRightJustifiedString("UPS: " + this.ups, 8, 22, 22, 1,
    // Color.WHITE);

    List<String> removedErrors = new ArrayList<String>();
    for (String s : errors.keySet()) {
      if (System.currentTimeMillis() - errors.get(s) >= 2 * 1000) {
        removedErrors.add(s);
      } else {
        screen.drawCenteredString(s, (WIDTH * SCALE) / 2, (HEIGHT * SCALE) / 2 + 2, 22, 1, Color.BLACK);
        screen.drawCenteredString(s, (WIDTH * SCALE) / 2, (HEIGHT * SCALE) / 2, 22, 1, Color.WHITE);
      }
    }

    for (String s : removedErrors) {
      errors.remove(s);
    }
  }

  public static void main(String[] args) {
    MainGame game = new MainGame(false);
    game.start();
  }

  public void click(int x, int y) {
    screen.onScreenClick(level, x / SCALE, y / SCALE);
  }

  public void addScore(Unit e) {
    money += e.cost * 1.6;
    totalScore += e.cost;
  }

  private HashMap<String, Long> errors = new HashMap<String, Long>();

  public void error(String msg) {
    errors.put(msg, System.currentTimeMillis());
  }

  public String getTimePlayed() {
    int seconds = (int) ((endTime - startTime) / 1000);
    int minutes = seconds / 60;
    int hours = minutes / 60;
    minutes %= 60;
    seconds %= 60;

    return String.format("%d:%02d:%02d", hours, minutes, seconds);
  }
}
