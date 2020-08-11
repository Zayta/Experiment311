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
    private float width = GameConfig.ENTITY_WIDTH;
    private float height = GameConfig.ENTITY_HEIGHT;

    private Rectangle bounds;

    public void init(float x, float y){
        position = new Vector2(x,y);
        bounds = new Rectangle(x, y, width, height);
    }
    public void setPosition(float x, float y) {
        position.set(x,y);
        updateBounds();
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
        updateBounds();
    }

    public void setY(float y) {
        position.set(position.x,y);
        updateBounds();
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void updateBounds() {
        bounds.setPosition(position);
    }

    @Override
    public String toString() {
        return position.toString();
    }

    @Override
    public void reset() {
        width = 1;height=1;

        position = new Vector2(0,0);
        bounds = new Rectangle(0, 0, width, height);
    }
}
