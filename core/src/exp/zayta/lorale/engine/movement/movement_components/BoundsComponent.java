package exp.zayta.lorale.engine.movement.movement_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;

import exp.zayta.lorale.GameConfig;

public class BoundsComponent implements Component,Pool.Poolable{

    private float width = GameConfig.ENTITY_WIDTH;
    private float height = GameConfig.ENTITY_HEIGHT;


    private Rectangle bounds = new Rectangle();
    public void init(float left, float bottom){
        bounds = new Rectangle(left,bottom,width,height);
    }
    public void setBounds(float x, float y, float width, float height){
        bounds.set(x,y,width,height);
    }
    public void setBounds(float left, float bottom){
        bounds.setPosition(left,bottom);
    }
    public Rectangle getBounds() {
        return bounds;
    }

    //x is left and y is bottom
    public float getX(){return bounds.x;}
    public float getY(){return bounds.y;}

    public float getWidth(){
        return bounds.width;
    }
    public float getHeight(){
        return bounds.height;
    }

    public float getCenterX(){
        return bounds.x+bounds.width/2;
    }
    public float getCenterY(){
        return bounds.y+bounds.height/2;
    }
    public float getLeft(){
        return getX()-bounds.width;
    }
    public float getBottom(){
        return getY()-bounds.height;
    }
    public float getTop(){
        return bounds.y+bounds.height;
    }
    public float getRight(){
        return bounds.x+bounds.width;
    }

    @Override
    public void reset() {
        bounds = new Rectangle(0,0,width,height);
    }
}
