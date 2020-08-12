package exp.zayta.lorale.engine.movement.world_wrap;


import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;

import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.movement.Direction;
import exp.zayta.lorale.engine.movement.movement_components.MovementComponent;
import exp.zayta.lorale.engine.movement.movement_components.Position;

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
        Vector2 targetPos = movement.getTargetPosition();

        Direction direction = movement.getDirection();
        float x = targetPos.x; float y = targetPos.y;
        Position position = Mappers.POSITION.get(entity);

        if(direction==Direction.up&&y>=top){
            y = Math.min(top,position.getY());
            movement.setTargetPosition(x,y); //stop entity from moving
        }
        if(direction==Direction.down&&y<=bottom){

            y = Math.max(bottom,position.getY());

            movement.setTargetPosition(x,y); //stop entity from moving
        }
        if(direction== Direction.left&&x<=left){
            x=Math.max(left,position.getX());
            y = position.getY();
            movement.setTargetPosition(x,y); //stop entity from moving
        }
        if(direction==Direction.right&&x>=right){
            x=Math.max(right,position.getX());

            movement.setTargetPosition(x,y); //stop entity from moving
        }

    }

}
