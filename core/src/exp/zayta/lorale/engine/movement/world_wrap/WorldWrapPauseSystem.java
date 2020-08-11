package exp.zayta.lorale.engine.movement.world_wrap;


import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;

import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.movement.Direction;
import exp.zayta.lorale.engine.movement.movement_components.MovementComponent;

public class WorldWrapPauseSystem extends IteratingSystem {

    private static final Logger log = new Logger(WorldWrapPauseSystem.class.getName(), Logger.DEBUG);
    private static final Family FAMILY = Family.all(
            WorldWrapComponent.class,
//            Position.class,
            MovementComponent.class
    ).get();

    public WorldWrapPauseSystem(int priority)
    {
        super(FAMILY,priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        WorldWrapComponent worldWrapComponent = Mappers.WORLD_WRAP.get(entity);
        float top = worldWrapComponent.getTop(),left = worldWrapComponent.getLeft(),
                right = worldWrapComponent.getRight(),bottom = worldWrapComponent.getBottom();

        MovementComponent movement = Mappers.MOVEMENT.get(entity);
        Vector2 position = movement.getTargetPosition();

        Direction direction = movement.getDirection();
        float x = position.x; float y = position.y;

        if(direction==Direction.up&&y>=top){

            movement.setTargetPosition(Mappers.POSITION.get(entity).getPosition()); //stop entity from moving

        }
        else if(direction==Direction.down&&y<bottom){

            movement.setTargetPosition(Mappers.POSITION.get(entity).getPosition()); //stop entity from moving
        }
        else if(direction== Direction.left&&x<left){
            movement.setTargetPosition(Mappers.POSITION.get(entity).getPosition()); //stop entity from moving
        }
        else if(direction==Direction.right&&x>=right){

            movement.setTargetPosition(Mappers.POSITION.get(entity).getPosition()); //stop entity from moving
        }

    }

}
