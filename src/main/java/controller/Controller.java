package controller;

import model.charactersModel.BallModel;
import view.charactersView.BallView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Objects;

import static controller.Utils.relativeLocation;

public abstract class Controller {
    public static void createBallView(String id){
        new BallView(id);
    }
    public static Point2D calculateViewLocation(Component component, String id){
        BallModel ballModel=findModel(id);
        Point corner=new Point(component.getX(),component.getY());
        assert ballModel != null;
        return relativeLocation(ballModel.getAnchor(),corner);
    }
    public static double getViewRadius(String id){
        return Objects.requireNonNull(findModel(id)).getRadius();
    }
    public static BallModel findModel(String id){
        for (BallModel ballModel: BallModel.ballModels){
            if (ballModel.getId().equals(id)) return ballModel;
        }
        return null;
    }
}
