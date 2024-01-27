package main;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import inputs.*;

import static Utils.Constants.PlayerConstants.*;
import static Utils.Constants.Directions.*;

public class Panel extends JPanel {

    private mouseInputs mouseInputs;
    private Game game;
    public Panel(Game game) {

        mouseInputs = new mouseInputs(this);
        this.game = game;

        setPanelSize();
        addKeyListener(new keyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }




    private void setPanelSize() {
        Dimension size = new Dimension(1200, 800);
        setPreferredSize(size);
    }

    public void updateGame() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);

    }

    public Game getGame() {
        return game;
    }




}
