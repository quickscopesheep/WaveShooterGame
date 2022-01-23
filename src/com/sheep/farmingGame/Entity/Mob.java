package com.sheep.farmingGame.Entity;

import com.sheep.farmingGame.Game;
import com.sheep.farmingGame.gfx.Screen;
import com.sheep.farmingGame.level.Level;

public class Mob extends Entity{
    float health;
    float knockBackX, knockBackY, knockBackTime;

    public Mob(int x, int y, int w, int h, EntityType type, Level level) {
        super(x, y, w, h, type, level);
    }

    @Override
    public void tick() {
        if(knockBackX != 0 || knockBackY != 0){
            float frameKnockBackX = knockBackX / knockBackTime;
            float frameKnockBackY = knockBackY / knockBackTime;

            move(frameKnockBackX, frameKnockBackY);

            knockBackX -= frameKnockBackX;
            knockBackY -= frameKnockBackY;
        }
    }

    @Override
    public void render(Screen screen) {

    }

    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime){
        health -= damage;
        this.knockBackX = knockBackX;
        this.knockBackY = knockBackY;
        this.knockBackTime = knockBackTime;

        System.out.println(type.toString() + " damaged: new health: " + health);

        if(health <= 0)
            Remove();
    }

    void move(float ax, float ay){
        if(!collision(x+ax, y)){
            x += ax;
        }
        if(!collision(x, y+ay)){
            y += ay;
        }
    }
}
