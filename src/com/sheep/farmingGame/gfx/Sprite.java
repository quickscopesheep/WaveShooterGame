package com.sheep.farmingGame.gfx;

public class Sprite {
    private final int width, height;
    private int x, y;

    public int[] pixels;
    private SpriteSheet sheet;

    public static Sprite wall = new Sprite(1, 1, 16, 16, SpriteSheet.tileset);
    public static Sprite floor = new Sprite(2, 3, 16, 16, SpriteSheet.tileset);
    public static Sprite floorTop = new Sprite(2, 2, 16, 16, SpriteSheet.tileset);

    public static Sprite[] torchAnim = {
            new Sprite(0, 0, 16, 21, SpriteSheet.torch),
            new Sprite(1, 0, 16, 21, SpriteSheet.torch),
            new Sprite(2, 0, 16, 21, SpriteSheet.torch)
    };

    public static Sprite[] fireball = {
            new Sprite(0, 0, 16, 16, SpriteSheet.fireball),
            new Sprite(1, 0, 16, 16, SpriteSheet.fireball)
    };

    public static Sprite wizard = new Sprite(9, 14, 16, 16, SpriteSheet.tileset);
    public static Sprite SimpleEnemy = new Sprite(2, 10, 16, 16, SpriteSheet.tileset);
    public static Sprite RangedEnemy = new Sprite(5, 10, 16, 16, SpriteSheet.tileset);

    public Sprite(int x, int y, int w, int h, SpriteSheet sheet){
        this.width = w;
        this.height = h;
        this.x = x * w;
        this.y = y * h;
        this.sheet = sheet;

        pixels = new int[w * h];
        load();
    }

    public Sprite(int w, int h, int Colour){
        width = w;
        height = h;
        pixels = new int[w * h];

        fillColour(Colour);
    }

    private void fillColour(int colour){
        for(int i = 0; i < width*height; i++){
                pixels[i] = colour;
        }
    }

    private void load(){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                pixels[y*width+x] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.width];
            }
        }
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
