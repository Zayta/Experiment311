package exp.zayta.lorale.engine.movement;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.math.Vector2;

import exp.zayta.lorale.GameConfig;
import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.movement.movement_components.MovementComponent;
import exp.zayta.lorale.engine.movement.movement_components.Position;

import static exp.zayta.lorale.engine.movement.PositionsComparator.positionsComparator;

/**
 * Moves player to his/her target position in his/her movement component
 */
public class MovementSystem extends SortedIteratingSystem {
    private final static Family FAMILY = Family.all(
            MovementComponent.class,
            Position.class
    ).get();
    public MovementSystem(int priority) {
        super(FAMILY,positionsComparator,priority);
    }

    @Override
    protected void processEntity(Entity entity, float delta) {
        //todo check for change then force sort
        forceSort();
        MovementComponent movementComponent = Mappers.MOVEMENT.get(entity);

        Vector2 position = Mappers.POSITION.get(entity).getPosition();
//        position.set(movementComponent.getTargetPosition());

        if (!position.equals(movementComponent.getTargetPosition())) {

            Vector2 targetPosition = movementComponent.getTargetPosition();
            ////System.out.println("Moving "+entity+" from "+position+" to "+movementComponent.getTargetPosition());
//            float progress = delta * GameConfig.MOVING_SPEED;
//            position.lerp(targetPosition, progress);

            position.set(targetPosition);
//            //if character pos and target pos are almost the same
//            if (position.dst(targetPosition) < 0.01f) {
//                movementComponent.setDirection(Direction.none);
//                position.set(targetPosition);
//            }
        }
        else{
            movementComponent.setDirection(Direction.none);
        }

    }
}
