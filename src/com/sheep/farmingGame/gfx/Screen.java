package com.sheep.farmingGame.gfx;

import java.util.Arrays;

public class Screen {
    private final int width, height;
    public int[] pixels;

    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void renderSprite(int xp, int yp, Sprite sprite, boolean flipped){
        for(int y = 0; y < sprite.getHeight(); y++){
            int ya = y + yp;

            for(int x = 0; x < sprite.getWidth(); x++){
                int xa = x + xp;
                int xs;
                if(flipped)
                    xs = (sprite.getWidth() -1) - x;
                else
                    xs = x;

                if(xa < -sprite.getWidth() || xa > width || ya < 0 || ya > width) break;
                if(xa < 0) continue;
                if(xa > width - 1) continue;
                if(ya > height - 1) continue;

                int col = sprite.pixels[y * sprite.getWidth() + xs];
                if(col != 0xffff00ff)
                    pixels[ya * width + xa] = col;
            }
        }
    }

    public void clear(){
        Arrays.fill(pixels, 0x000000);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
