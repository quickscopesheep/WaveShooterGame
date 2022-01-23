package com.sheep.farmingGame.Entity;

import com.sheep.farmingGame.Game;
import com.sheep.farmingGame.gfx.Screen;
import com.sheep.farmingGame.gfx.Sprite;
import com.sheep.farmingGame.level.Level;
import com.sheep.farmingGame.util.AudioManager;
import com.sheep.farmingGame.util.Keyboard;
import com.sheep.farmingGame.util.Mouse;

public class Player extends Mob{
    boolean flipped;

    int attackCoolDown;

    public Player(int x, int y, Level level) {
        super(x, y, 16, 16, EntityType.PLAYER, level);
        health = 100;
    }

    @Override
    public void tick() {
        super.tick();

        int ix = 0, iy = 0;

        if(Keyboard.UP){
            iy = -1;
        }
        if(Keyboard.DOWN){
            iy = 1;
        }
        if(Keyboard.LEFT){
            ix = -1;
        }
        if(Keyboard.RIGHT){
            ix = 1;
        }

         flipped = Mouse.getMouseX() < this.x;

        if(attackCoolDown > 0) attackCoolDown--;

        if(Mouse.getButton() == 1 && attackCoolDown <= 0){
            float dx = Mouse.getMouseX() - 8 - this.x;
            float dy = Mouse.getMouseY() - 8 - this.y;
            float dir = (float) Math.atan2(dy, dx);
            Shoot(dir);

            attackCoolDown = 25;
        }

        move(ix, iy);
    }

    void Shoot(float ang){
        level.Add(new FireBall(this.x, this.y, ang, this, level));
        Game.getAudioManager().loadSound(AudioManager.SFX_FIREBALL);
        Game.getAudioManager().play();
    }

    @Override
    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime) {
        super.Damage(damage, knockBackX, knockBackY, knockBackTime);
        Game.getAudioManager().loadSound(AudioManager.SFX_HIT_PLAYER);
        Game.getAudioManager().play();
    }

    @Override
    public void render(Screen screen){
        screen.renderSprite((int)x, (int)y, Sprite.wizard, flipped);
    }
}
