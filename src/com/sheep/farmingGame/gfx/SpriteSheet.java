package com.sheep.farmingGame.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    private String path;
    public final int width, height;
    public int[] pixels;

    public static SpriteSheet tileset = new SpriteSheet("/spritesheets/Dungeon.png", 256, 256);
    public static SpriteSheet torch = new SpriteSheet("/spritesheets/Torch.png", 48, 21);
    public static SpriteSheet fireball = new SpriteSheet("/spritesheets/fireball.png", 32, 16);

    public SpriteSheet(String path, int width, int height){
        this.path = path;
        this.width = width;
        this.height = height;

        pixels = new int[width*height];
        load();
    }

    private void load(){
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();

            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
