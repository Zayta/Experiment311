package exp.zayta.lorale.engine;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.entities.EntityType;
import exp.zayta.lorale.engine.entities.tags.PlayerTag;
import exp.zayta.lorale.engine.game_systems.character.follower.FollowerTag;
import exp.zayta.lorale.engine.movement.Direction;
import exp.zayta.lorale.engine.movement.movement_components.MovementComponent;
import exp.zayta.lorale.engine.movement.position_tracker.PositionTracker;

import static exp.zayta.lorale.engine.movement.PositionsComparator.positionsComparator;


//import z_oop.sokoban.entity.entities.Player;

public class PlayerController {

    private PooledEngine engine; private ImmutableArray<Entity> entities;
    private PlayScreen playScreen;
    private PositionTracker positionTracker;



    private final static Family PLAYABLE_CHARACTERS = Family.all(
            MovementComponent.class

    ).one(PlayerTag.class, FollowerTag.class).get();

    public PlayerController(PooledEngine engine, PlayScreen playScreen, PositionTracker positionTracker){
        this.engine = engine;
        this.positionTracker = positionTracker;
        this.playScreen = playScreen;
    }

    public void movePlayer(Direction direction){

        entities = engine.getEntitiesFor(PLAYABLE_CHARACTERS);

        Array<Entity> entityArray = new Array<Entity>(entities.toArray());
        for(Entity entity:entityArray){
//            if(Mappers.MOVEMENT.get(entity).getDirection()!=Direction.none){

                positionsComparator.setDirection(Mappers.MOVEMENT.get(entity).getDirection());
//            }
        }
        entityArray.sort(positionsComparator);
        for (int i = 0; i < entityArray.size; i++) {
            Entity entity = entityArray.get(i);
            MovementComponent movement = Mappers.MOVEMENT.get(entity);


            movement.setDirection(direction);
            if(Mappers.ANIMATION.get(entity)!=null)
                Mappers.ANIMATION.get(entity).setCurrentAnimation(direction);

//            ////System.out.println("Entities direction is "+movement.getDirection());
            if(movement.getDirection()==Direction.none)
                continue;
            movement.move(direction);
//            //todo clean up code
//            if(Mappers.PLAYER.get(entity)!=null){ //move player
//                Mappers.PLAYER.get(entity).setHasUnhandledInput(true);
//                Mappers.PLAYER.get(entity).setDirection(direction);
//            }
//            else{ //move follower
//
//                Mappers.FOLLOWER.get(entity).setHasUnhandledInput(true);
//                Mappers.FOLLOWER.get(entity).setDirection(direction);
//            }
        }
    }
//
//    public void addFollowers(){
//        entities = engine.getEntitiesFor(PLAYABLE_CHARACTERS);
//        for(Entity player:entities){
//            Vector2 position = Mappers.MOVEMENT.get(player).getTargetPosition();
//
//            for(int i = -1; i<=1;i++){
//                for(int j = -1; j<=1; j++){
//                    Entity entity1 = positionTracker.getEntityAtPos(EntityType.CHARACTER,position.x+i,position.y+j);
//                    if(entity1!=null&&!entities.contains(entity1,true)){
//                        entity1.add(engine.createComponent(FollowerTag.class));
//                        //System.out.println("Added follower");
//                    }
//                }
//            }
//
//        }
//
//    }
    public void removeFollowers(){
        entities = engine.getEntitiesFor(Family.all(FollowerTag.class).get());
        //remove PlayerTag from those entities

        for(int i = entities.size()-1;i>=0;i--){ //need to remove from back first
            Entity entity = entities.get(i);
            entity.remove(FollowerTag.class);
        }
    }

    public void restart(){
        playScreen.restart();
    }





    public void debugPlayerPosition(){
        ImmutableArray<Entity> entities = engine.getEntitiesFor(PLAYABLE_CHARACTERS);

        for(Entity entity:entities){
            if(Mappers.POSITION.get(entity)!=null)
            System.out.println("Entity "+entity+" is at position "+Mappers.POSITION.get(entity));

        }
    }

}
