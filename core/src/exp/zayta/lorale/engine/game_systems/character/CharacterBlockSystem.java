package exp.zayta.lorale.engine.game_systems.character;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import exp.zayta.lorale.GameConfig;
import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.entities.EntityType;
import exp.zayta.lorale.engine.entities.tags.CharacterTag;
import exp.zayta.lorale.engine.movement.Direction;
import exp.zayta.lorale.engine.movement.movement_components.MovementComponent;
import exp.zayta.lorale.engine.movement.movement_components.BoundsComponent;
import exp.zayta.lorale.engine.movement.position_tracker.PositionTracker;

import static exp.zayta.lorale.engine.movement.PositionsComparator.positionsComparator;


public class CharacterBlockSystem extends SortedIteratingSystem {
    //    private PooledEngine engine;
    private static final Logger log = new Logger(CharacterBlockSystem.class.getName(), Logger.DEBUG);

    private PositionTracker positionTracker;
    private static final Family family = Family.all(
            MovementComponent.class,
            BoundsComponent.class,
            CharacterTag.class).get();
    private float increment = Math.max(GameConfig.ENTITY_HEIGHT, GameConfig.ENTITY_WIDTH);
    public CharacterBlockSystem(int priority,PositionTracker positionTracker) {
        super(family,positionsComparator,priority);
//        this.engine = engine;
        this.positionTracker = positionTracker;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        forceSort();
        MovementComponent movementComponent = Mappers.MOVEMENT.get(entity);
        Vector2 targetPosition = movementComponent.getTargetPosition();
        Direction direction = movementComponent.getDirection();
        BoundsComponent bounds = Mappers.BOUNDS.get(entity);
        increment=Math.min(bounds.getWidth()/2,bounds.getHeight()/2);

//        Entity collidedCharacter = detectCollidedCharacter(targetPosition,movementComponent.getDirection(),bounds);
//        if(collidedCharacter!=null&&collidedCharacter!=entity){//if there's a collided character that is not the entity itself
//                movementComponent.setTargetPosition(Mappers.POSITION.get(entity).getPosition());
//        }

        int[] keys = new int[6];
//        log.debug("BoundsComponent tracker n is "+positionTracker.n);
        int key = positionTracker.generateKey(targetPosition.x,targetPosition.y);
        int keyAbove = key + PositionTracker.n;
        int keyBelow = key - PositionTracker.n;
//            int [] keys = new int []{
//              key,key-1,key+1,keyAbove-1,keyAbove,keyAbove+1,keyBelow-1,keyBelow,keyBelow+1
//            };
        switch (direction) {
            case none:
                break;
            case up:
                keys[0] = keyAbove;
                keys[1] = keyAbove + 1;
                keys[2] = keyAbove - 1;
                keys[3] = key - 1;
                keys[4] = key + 1;
                break;
            case down:
                keys[0] = keyBelow;
                keys[1] = keyBelow + 1;
                keys[2] = keyBelow - 1;
                keys[3] = key - 1;
                keys[4] = key + 1;
                break;
            case left:
                keys[0] = keyAbove - 1;
                keys[1] = key - 1;
                keys[2] = keyBelow - 1;
                keys[3] = keyAbove;
                keys[4] = keyBelow;
                break;
            case right:
                keys[0] = keyAbove + 1;
                keys[1] = key + 1;
                keys[2] = keyBelow + 1;
                keys[3] = keyAbove;
                keys[4] = keyBelow;
                break;
        }
        keys[5] = key;
        checkCollision(entity, keys);



    }

    private void checkCollision(Entity entity, int [] keys){
        for (int key: keys) {
            List<Entity> collidedEntities = positionTracker.getEntity(key);
            if(collidedEntities!=null) {
                for (Entity collidedEntity : collidedEntities) {
                    if (collidedEntity != null && collidedEntity != entity) {
                        log.debug("Collided w chara " + collidedEntity);
                                        if (checkCollisionBetween(entity,collidedEntity)){
                        blockEntity(entity);
                                            log.debug("BLock occured");
                                        }
                    }
                }
            }

        }
    }
    private boolean checkCollisionBetween(Entity e1, Entity e2){
       boolean ret =  overlaps(Mappers.BOUNDS.get(e1).getBounds(),Mappers.BOUNDS.get(e2).getBounds());
       log.debug("chckcollision returns "+ret);
       return ret;
    }

    public static boolean overlaps (Rectangle r1, Rectangle r2) {
        float threshold = 0.9f;
        return r1.x < r2.x + threshold*r2.width && r1.x + threshold*r1.width > r2.x && r1.y < r2.y + threshold*r2.height && r1.y + threshold*r1.height > r2.y;
    }

    private void blockEntity(Entity entity){
        MovementComponent movementComponent = Mappers.MOVEMENT.get(entity);
        log.debug("Collision occurred");
        movementComponent.setTargetPosition(Mappers.POSITION.get(entity).getPosition());

    }
//
//    private Entity detectCollidedCharacter(Vector2 targetPosition, Direction direction, BoundsComponent bounds){
//        float x = targetPosition.x,y=targetPosition.y, width = bounds.getWidth(),height=bounds.getHeight();
//        Entity collidedEntity = null;
//        switch (direction){
//            case up:
//                collidedEntity= collidesTop(x,y,width,height);
//                break;
//            case down:
//
//                collidedEntity= collidesBottom(x,y,width,height);
//                break;
//            case left:
//                collidedEntity= collidesLeft(x,y,width,height);
//                break;
//            case right:
//                collidedEntity= collidesRight(x,y,width,height);
//                break;
//        }
//        return collidedEntity;
//    }
//
//    private Entity collidesRight(float x, float y, float width, float height) {
//        for(float step = 0; step <= height; step += increment){
//            Entity tiledMapTile = getCollidedEntity(x + width, y + step);
//            if(tiledMapTile!=null)
//                return tiledMapTile;
//        }
//        return null;
//    }
//
//    private Entity collidesLeft(float x, float y, float width, float height) {
//
//        for(float step = 0; step <= height; step += increment){
//            Entity tiledMapTile = getCollidedEntity(x, y + step);
//            if(tiledMapTile!=null)
//                return tiledMapTile;
//        }
//        return null;
//    }
//
//    private Entity collidesTop(float x, float y, float width, float height) {
//        for(float step = 0; step <= width; step += increment){
//
//            Entity tiledMapTile = getCollidedEntity(x+step, y + height);
//            if(tiledMapTile!=null)
//                return tiledMapTile;
//        }
//        return null;
//
//    }
//
//    private Entity collidesBottom(float x, float y, float width, float height) {
//        for(float step = 0; step <= width; step += increment){
//
//            Entity tiledMapTile = getCollidedEntity(x+step, y);
//            if(tiledMapTile!=null)
//                return tiledMapTile;
//        }
//        return null;
//    }

//    private Entity getCollidedEntity(float x, float y){
//
//        Entity collidedCharacter = positionTracker.getEntityAtPos(EntityType.CHARACTER,x,y);
//        if(collidedCharacter!=null&&Mappers.POSITION.get(collidedCharacter).getBounds().contains(x,y)){
//            return collidedCharacter;
//        }
//        return null;
//    }



}

