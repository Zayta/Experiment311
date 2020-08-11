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
//    private float left, right, top, bottom;
    private static final Family FAMILY = Family.all(
            WorldWrapComponent.class,
//            Position.class,
            MovementComponent.class
    ).get();

    //    private float maxX, maxY;
    public WorldWrapPauseSystem(int priority)
    {
        super(FAMILY,priority);
//        this.left = leftBound;
//        this.right = leftBound+mapWidth;
//        this.bottom = bottomBound;
//        this.top = bottomBound+mapHeight;
//        this.viewport = viewport;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        WorldWrapComponent worldWrapComponent = Mappers.WORLD_WRAP.get(entity);
        float top = worldWrapComponent.getTop(),left = worldWrapComponent.getLeft(),
                right = worldWrapComponent.getRight(),bottom = worldWrapComponent.getBottom();
//        Position position = Mappers.POSITION.get(entity);
//        DimensionComponent dimension=Mappers.DIMENSION.get(entity);

        MovementComponent movement = Mappers.MOVEMENT.get(entity);
        Vector2 position = movement.getTargetPosition();
//        ////log.debug("num world wrap entities: "+getEngine().getEntitiesFor(FAMILY).size());

        Direction direction = movement.getDirection();
        float x = position.x; float y = position.y;

//        if(y>=top||y<=bottom||x<=left||x>=right){
//
//            movement.setTargetPosition(Mappers.POSITION.get(entity).getPosition()); //stop entity from moving
//        }
//
        if(direction==Direction.up&&y>=top){
//            movement.setDirection(Direction.none);
//            movement.setTargetPosition(x,top);
//            position.setPosition(x,top);

            movement.setTargetPosition(Mappers.POSITION.get(entity).getPosition()); //stop entity from moving

        }
        else if(direction==Direction.down&&y<bottom){
//            movement.setDirection(Direction.none);
//            position.setPosition(x,bottom);
//            movement.setTargetPosition(x,bottom);

            movement.setTargetPosition(Mappers.POSITION.get(entity).getPosition()); //stop entity from moving
        }
        else if(direction== Direction.left&&x<left){
//            movement.setDirection(Direction.none);
//            position.setPosition(left,y);
//            movement.setTargetPosition(left,y);

            movement.setTargetPosition(Mappers.POSITION.get(entity).getPosition()); //stop entity from moving
        }
        else if(direction==Direction.right&&x>=right){
//            movement.setDirection(Direction.none);
//            movement.setTargetPosition(right,y);
//            position.setPosition(right,y);

            movement.setTargetPosition(Mappers.POSITION.get(entity).getPosition()); //stop entity from moving
        }

    }

}
