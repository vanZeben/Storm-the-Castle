package ca.vanzeben.ld25.gfx.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

  public int[] pixels;
  public int width;
  public int height;

  public ImageLoader(String path) {
    load(path);
  }

  private void load(String path) {
    try {
      BufferedImage image = ImageIO.read(ImageLoader.class.getResource(path));
      width = image.getWidth();
      height = image.getHeight();
      pixels = new int[width * height];
      image.getRGB(0, 0, width, height, pixels, 0, width);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
