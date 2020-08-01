package exp.zayta.lorale.engine.movement.movement_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

import exp.zayta.lorale.engine.movement.Direction;


public class MovementComponent implements Component {
    private Direction direction = Direction.none;
    private Vector2 targetPosition = new Vector2();
    public void init(Vector2 targetPosition){
        this.targetPosition = targetPosition;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public Direction getDirection() {
        return direction;
    }
    public void move(Direction direction){
        setDirection(direction);
        targetPosition.add(direction.vector);
        targetPosition.set(Math.round(targetPosition.x),Math.round(targetPosition.y));
    }
    public void setTargetPosition(Vector2 targetPosition) {
        this.targetPosition.set(Math.round(targetPosition.x),Math.round(targetPosition.y));
    }

    public void setTargetPosition(float x, float y) {
        this.targetPosition.set(Math.round(x),Math.round(y));
    }

    public Vector2 getTargetPosition() {
        return targetPosition;
    }

}
