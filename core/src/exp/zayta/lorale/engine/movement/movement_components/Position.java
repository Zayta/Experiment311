package exp.zayta.lorale.engine.movement.movement_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

import exp.zayta.lorale.GameConfig;

public class Position implements Component, Pool.Poolable {
    // == attributes ==
    private Vector2 position;
    public void init(float x, float y){
        position = new Vector2(x,y);
    }
    public void setPosition(float x, float y) {
        position.set(x,y);
    }
    public float getX() {
        return (position.x);
    }

    public float getY() {
        return (position.y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setX(float x) {
        position.set(x,position.y);

    }

    public void setY(float y) {
        position.set(position.x,y);

    }



    @Override
    public String toString() {
        return position.toString();
    }

    @Override
    public void reset() {
        position = new Vector2(0,0);
    }
}
