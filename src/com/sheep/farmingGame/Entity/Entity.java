package com.sheep.farmingGame.Entity;

import com.sheep.farmingGame.Game;
import com.sheep.farmingGame.gfx.Screen;
import com.sheep.farmingGame.level.Level;

public abstract class Entity {
    float x, y;
    float w, h;
    EntityType type;
    Level level;
    boolean removed;

    public Entity(int x, int y, float w, float h, EntityType type, Level level){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.type = type;
        this.level = level;
    }

    public abstract void tick();
    public abstract void render(Screen screen);

    public abstract void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime);

    public void Remove(){
        removed = true;
        level.Remove(this);
    }

    boolean collision(float ax, float ay){
        if(ay <= 0 || ay >= level.getHeight() * 16 - h || ax <= 0 || ax >= level.getWidth() * 16 - w) return true;

        return false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Level getLevel() {
        return level;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public EntityType getType() {
        return type;
    }
}
