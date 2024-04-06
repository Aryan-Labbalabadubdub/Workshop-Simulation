package view;

import model.collision.Collidable;
import view.charactersView.BallView;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static controller.Constants.PANEL_SIZE;

public final class MotionPanel extends JPanel implements Collidable {
    private static MotionPanel INSTANCE;
    private final Random rng=new Random();
    private MotionPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black,5));
        setBackground(new Color(0,0,0,0));
        setSize(PANEL_SIZE);
        setLocationToCenter(GlassFrame.getINSTANCE());
        GlassFrame.getINSTANCE().add(this);
        Collidable.collidables.add(this);
    }
    public void setLocationToCenter(GlassFrame glassFrame){
        setLocation(glassFrame.getWidth()/2-getWidth()/2,glassFrame.getHeight()/2-getHeight()/2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (BallView ballView: BallView.ballViews){
            g.setColor(new Color(rng.nextInt(100,200),rng.nextInt(100,200),rng.nextInt(100,200)));
            Point2D location=ballView.getCurrentLocation();
            double radius=ballView.getCurrentRadius();
            g.fillOval((int) (location.getX()-radius), (int) (location.getY()-radius), (int) (2 *radius), (int) (2*radius));
        }
    }

    public static MotionPanel getINSTANCE() {
        if (INSTANCE==null) INSTANCE=new MotionPanel();
        return INSTANCE;
    }

    @Override
    public boolean isCircular() {
        return false;
    }

    @Override
    public double getRadius() {
        return 0;
    }

    @Override
    public Point2D getAnchor() {
        return null;
    }

    @Override
    public ArrayList<Point2D> getVertices() {
        return new ArrayList<>(List.of(new Point2D.Double(getX(),getY()),new Point2D.Double(getX()+getWidth(),getY()),
                new Point2D.Double(getX()+getWidth(),getY()+getHeight()),new Point2D.Double(getX(),getY()+getHeight())));
    }
}
