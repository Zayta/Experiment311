package exp.zayta.lorale.engine.movement.position_tracker;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.math.Vector2;

import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.movement.movement_components.MovementComponent;
import exp.zayta.lorale.engine.movement.movement_components.Position;

import static exp.zayta.lorale.engine.movement.PositionsComparator.positionsComparator;


public class PositionTrackerSystem extends SortedIteratingSystem {
    private PositionTracker positionTracker;
    private static final Family TRACKED_ENTITIES=Family.all(
            PositionTrackerComponent.class
    ).one(Position.class,
            MovementComponent.class).get();
    public PositionTrackerSystem(int priority,PositionTracker positionTracker)
    {
        super(TRACKED_ENTITIES,positionsComparator,priority);
        this.positionTracker = positionTracker;
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        forceSort();
        Position position = Mappers.POSITION.get(entity);
        Vector2 pos = new Vector2(position.getX(),position.getY());
        MovementComponent movementComponent = Mappers.MOVEMENT.get(entity);
        if(movementComponent!=null){
            pos.set(movementComponent.getTargetPosition());
        }

        positionTracker.updateGlobalTracker(entity, pos.x, pos.y);

        PositionTrackerComponent positionTrackerComponent = Mappers.POSITION_TRACKER.get(entity);
        positionTracker.updateEntityTracker(entity, pos.x, pos.y);

    }
}