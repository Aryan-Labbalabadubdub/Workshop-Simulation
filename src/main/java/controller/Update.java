package controller;

import model.charactersModel.BallModel;
import model.collision.Collidable;
import model.collision.CollisionState;
import model.movement.Direction;
import model.movement.Movable;
import view.GlassFrame;
import view.MotionPanel;
import view.charactersView.BallView;

import javax.swing.*;

import java.util.ArrayList;

import static controller.Constants.*;
import static controller.Controller.calculateViewLocation;
import static controller.Controller.getViewRadius;
import static controller.Utils.relativeLocation;

public class Update {

    public Update() {
        new Timer((int) FRAME_UPDATE_TIME, e -> updateView()){{setCoalesce(true);}}.start();
        new Timer((int) MODEL_UPDATE_TIME, e -> updateModel()){{setCoalesce(true);}}.start();
    }

    public void updateView(){
        for (BallView ballView: BallView.ballViews){
            ballView.setCurrentLocation(calculateViewLocation(MotionPanel.getINSTANCE(),ballView.getId()));
            ballView.setCurrentRadius(getViewRadius(ballView.getId()));
        }
        GlassFrame.getINSTANCE().repaint();
    }
    public void updateModel(){
        for (BallModel ballModel: BallModel.ballModels){
            ballModel.move();
        }
        ArrayList<Collidable> collidables=new ArrayList<>(Collidable.collidables);
        for (int i=0;i<collidables.size();i++){
            for (int j=i+1;j<collidables.size();j++){
                CollisionState collisionState=collidables.get(i).collides(collidables.get(j));
                if (collisionState!=null){
                    if (collidables.get(i) instanceof Movable){
                        ((Movable) collidables.get(i)).setDirection(new Direction(relativeLocation(collidables.get(i).getAnchor(),collisionState.collisionPoint)));
                    }
                    if (collidables.get(j) instanceof Movable){
                        ((Movable) collidables.get(j)).setDirection(new Direction(relativeLocation(collidables.get(j).getAnchor(),collisionState.collisionPoint)));
                    }
                }
            }
        }
    }
}
