package com.sheep.farmingGame.Entity;

import com.sheep.farmingGame.Game;
import com.sheep.farmingGame.gfx.Screen;
import com.sheep.farmingGame.gfx.Sprite;
import com.sheep.farmingGame.level.Level;

public class Torch extends Entity{
    int frame;
    int tick;

    public Torch(int x, int y, Level level) {
        super(x, y, 8, 14, EntityType.TORCH, level);
        frame = 1;
    }

    @Override
    public void tick() {
        if(tick != 7){
            tick++;
        }else{
            tick = 0;
            if(frame != 2){
                frame++;
            }else{
                frame = 1;
            }
        }
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite((int)x, (int)y, Sprite.torchAnim[frame], false);
    }

    @Override
    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime) {

    }
}
