package com.sheep.farmingGame.Entity;

import com.sheep.farmingGame.Game;
import com.sheep.farmingGame.gfx.Screen;
import com.sheep.farmingGame.gfx.Sprite;
import com.sheep.farmingGame.level.Level;
import com.sheep.farmingGame.util.AudioManager;
import com.sheep.farmingGame.util.MathUtil;

public class SimpleEnemy extends Mob{
    boolean flipped;
    float damageCoolDown, moveCoolDown;

    float attackDirX, attackDirY;

    public SimpleEnemy(int x, int y, Level level) {
        super(x, y,16, 16, EntityType.SIMPLE_ENEMY, level);
        health = 50;
    }

    @Override
    public void tick() {
        super.tick();

        Player player = (Player) level.GetEntityOfType(EntityType.PLAYER);

        if(player == null) return;

        float difX = player.x - this.x;
        float difY = player.y - this.y;

        float dirX = difX;
        float dirY = difY;

        if(dirX > 1) dirX = 1;
        if(dirX < 0) dirX = -1;
        if(dirY > 1) dirY = 1;
        if(dirY < 0) dirY = -1;

        if(dirX != 0) attackDirX = dirX;
        if(dirY != 0) attackDirY = dirY;

        flipped = dirX > 0;

        if(MathUtil.Distance(this.x, this.y, player.x, player.y) < 5f && damageCoolDown <= 0){
            player.Damage(25f, attackDirX * 20, attackDirY * 20, 10);
            damageCoolDown = 60;
            moveCoolDown = 15;
        }

        if(damageCoolDown > 0) damageCoolDown--;

        if(moveCoolDown > 0) moveCoolDown--;
        else move(dirX * .5f, dirY * .5f);
    }

    @Override
    boolean collision(float ax, float ay) {
        return false;
    }

    @Override
    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime) {
        super.Damage(damage, knockBackX, knockBackY, knockBackTime);
        Game.getAudioManager().loadSound(AudioManager.SFX_HIT_ENEMY);
        Game.getAudioManager().play();
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite((int)x, (int)y, Sprite.SimpleEnemy, flipped);
    }
}
