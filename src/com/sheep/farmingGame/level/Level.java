package com.sheep.farmingGame.level;

import com.sheep.farmingGame.Entity.*;
import com.sheep.farmingGame.Game;
import com.sheep.farmingGame.gfx.Screen;
import com.sheep.farmingGame.gfx.Sprite;
import com.sheep.farmingGame.util.AudioManager;
import com.sheep.farmingGame.util.MathUtil;

import java.util.ArrayList;
import java.util.Random;

public class Level {
    int width, height;
    int spawnCoolDown;

    ArrayList<Entity> entities = new ArrayList<>();

    Random random = new Random();

    public Level(int width, int height){
        this.width = width;
        this.height = height;

        Add(new Torch(width / 2 * 16 - 8 - 32, height / 2 * 16 + 32, this));
        Add(new Torch(width / 2 * 16 - 8 + 32, height / 2 * 16 + 32, this));
        Add(new Torch(width / 2 * 16 - 8 - 32, height / 2 * 16 - 32, this));
        Add(new Torch(width / 2 * 16 - 8 + 32, height / 2 * 16 - 32, this));
    }

    public void tick(){
        for(int i = 0; i < entities.size(); i++){
            entities.get(i).tick();
        }

        if(spawnCoolDown > 0){
            spawnCoolDown--;
        }
        else {
            int x = random.nextInt(Game.WIDTH);
            int y = random.nextInt(Game.HEIGHT - 16) + 16;

            Add(new SimpleEnemy(x, y, this));

            spawnCoolDown = 60*2;
        }
    }

    public void render(Screen screen){
        for(int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                if(y == 0)
                    screen.renderSprite(x*16,0,  Sprite.wall, false);
                else if(y == 1)
                    screen.renderSprite(x*16,y*16, Sprite.floorTop, false);
                else
                    screen.renderSprite(x*16,y*16, Sprite.floor, false);
            }
        }

        for(int i = 0; i < entities.size(); i++){
            entities.get(i).render(screen);
        }
    }

    public void Add(Entity entity){
        entities.add(entity);
    }

    public void Remove(Entity entity){
        entities.remove(entity);
    }

    public ArrayList<Entity> getEntities(){
        return entities;
    }

    public Entity GetEntityOfType(EntityType type){
        for(int i = 0; i < entities.size(); i++){
            if(entities.get(i).getType() == type)
                return entities.get(i);
        }

        return null;
    }



    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
