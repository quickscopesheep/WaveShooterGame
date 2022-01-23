package com.sheep.farmingGame;

import com.sheep.farmingGame.Entity.Player;
import com.sheep.farmingGame.Entity.SimpleEnemy;
import com.sheep.farmingGame.gfx.Screen;
import com.sheep.farmingGame.level.Level;
import com.sheep.farmingGame.util.AudioManager;
import com.sheep.farmingGame.util.Keyboard;
import com.sheep.farmingGame.util.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 320, HEIGHT = 240, SCALE = 2;

    private Thread thread;
    private JFrame frame;
    private Screen screen;

    private static AudioManager audioManager;

    private Level level;

    private boolean running;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public Game(){
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame = new JFrame();
        screen = new Screen(WIDTH, HEIGHT);
        audioManager = new AudioManager();

        level = new Level(WIDTH/16, HEIGHT/16);
        level.Add(new Player(level.getWidth() / 2 * 16, level.getHeight() / 2 * 16, level));

        Keyboard keyboard = new Keyboard();
        Mouse mouse = new Mouse();

        addKeyListener(keyboard);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);

        playMusic();
    }

    public synchronized void start(){
        thread = new Thread(this, "Display");
        running = true;
        thread.start();
    }

    public synchronized void stop(){
        try{
            running = false;
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void tick(){
        level.tick();
    }

    void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        screen.clear();

        level.render(screen);

        for(int i = 0; i < pixels.length; i++){
            pixels[i] = screen.pixels[i];
        }

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double ns = 1000000000 / 60.0;
        double delta = 0;

        int ticks = 0;
        int frames = 0;

        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1){
                tick();
                ticks++;
                delta--;
            }

            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("ticks: " + ticks + ", frames: " + frames);
                ticks = 0;
                frames = 0;
            }
        }
        stop();
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.frame.setResizable(false);
        game.frame.setTitle("RougeLike");

        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.add(game);

        game.frame.pack();
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }

    void playMusic(){
        Game.getAudioManager().loadSound(AudioManager.MUSIC_DRUMS);
        Game.getAudioManager().play();
        Game.getAudioManager().loop();
    }

    public static AudioManager getAudioManager() {
        return audioManager;
    }
}
