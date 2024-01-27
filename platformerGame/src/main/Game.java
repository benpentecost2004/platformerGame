package main;

import Entities.Player;

import java.awt.*;

public class Game implements Runnable{

    private Window gameWindow;
    private Panel gamePanel;
    private Thread gameThread;
    private final int FPS_MAX = 120;
    private final int TICKS_MAX = 200;

    private Player player;
    public Game() {
        initClasses();
        gamePanel = new Panel(this);
        gameWindow = new Window(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();

    }

    private void initClasses() {
        player = new Player(200, 200);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        player.update();
    }

    public void render(Graphics g) {
        player.render(g);
    }

    @Override
    // game loop
    public void run() {

        double TPF = 1000000000.0 / FPS_MAX;
        double TPT = 1000000000.0 / TICKS_MAX;

        long previousTime = System.nanoTime();

        int frames = 0;
        int ticks = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaT = 0;
        double deltaF = 0;

        while(true) {

            long currentTime = System.nanoTime();

            deltaT += (currentTime - previousTime) / TPT;
            deltaF += (currentTime - previousTime) / TPF;
            previousTime = currentTime;

            if(deltaT >= 1) {
                update();
                ticks++;
                deltaT--;
            }

            if(deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " TPS: " + ticks);
                frames = 0;
                ticks = 0;
            }


        }
    }

    public Player getPlayer() {
        return player;
    }


    public void windowFocusLost() {
        player.resetDirBooleans();
    }
}
