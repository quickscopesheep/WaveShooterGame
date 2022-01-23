package com.sheep.farmingGame.Entity;

import com.sheep.farmingGame.Game;
import com.sheep.farmingGame.gfx.Screen;
import com.sheep.farmingGame.gfx.Sprite;
import com.sheep.farmingGame.level.Level;
import com.sheep.farmingGame.util.MathUtil;

public class FireBall extends Entity{
    float dx, dy;
    int frame, tick;

    Entity spawner;

    public FireBall(float x, float y, float ang, Entity spawner, Level level) {
        super((int)x, (int) y, 16, 16, EntityType.FIRE_BALL, level);
        dx = (float)Math.cos(ang);
        dy = (float)Math.sin(ang);
        this.spawner = spawner;
    }

    @Override
    public void tick() {
        x += dx * 2;
        y += dy * 2;

        if(collision(x, y)){
            Remove();
        }

        if(tick != 7){
            tick++;
        }else{
            tick = 0;
            if(frame != 1){
                frame++;
            }else{
                frame = 0;
            }
        }

        for (Entity entity : level.getEntities()) {
            if(entity == spawner || entity == this || entity.type == EntityType.TORCH) continue;
            boolean hit = false;

            if(MathUtil.Distance(x ,y, entity.getX(), entity.getY()) < 10){
                entity.Damage(25, dx * 20, dy * 20, 5);
                Remove();
                hit = true;
            }

            if(hit) break;
        }
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite((int)x, (int)y, Sprite.fireball[frame], false);
    }

    @Override
    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime) {

    }
}
