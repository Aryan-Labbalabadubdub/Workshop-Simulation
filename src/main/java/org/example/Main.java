package org.example;

import controller.Constants;
import controller.Update;
import model.charactersModel.BallModel;
import view.GlassFrame;
import view.MotionPanel;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class Main {
    static Random rng=new Random();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GlassFrame.getINSTANCE();
            MotionPanel.getINSTANCE();
            new Update();
            for(int i = 0; i< Constants.NUMBER_OF_BALLS; i++){
                double randomX=rng.nextDouble(MotionPanel.getINSTANCE().getX(),MotionPanel.getINSTANCE().getX()+MotionPanel.getINSTANCE().getWidth());
                double randomY=rng.nextDouble(MotionPanel.getINSTANCE().getY(),MotionPanel.getINSTANCE().getY()+MotionPanel.getINSTANCE().getHeight());
                double randomRadius=rng.nextDouble(0, Constants.MAX_RADIUS);
                new BallModel(new Point2D.Double(randomX,randomY),randomRadius);
            }
        });
    }
}