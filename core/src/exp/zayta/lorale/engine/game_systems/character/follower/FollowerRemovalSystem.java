package exp.zayta.lorale.engine.game_systems.character.follower;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;

import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.entities.tags.PlayerTag;
import exp.zayta.lorale.engine.movement.Direction;
import exp.zayta.lorale.engine.movement.movement_components.MovementComponent;

public class FollowerRemovalSystem extends EntitySystem {

    private static final Family PLAYABLE_CHARACTERS = Family.all(PlayerTag.class, MovementComponent.class).get();
    private static final Family FOLLOWERS = Family.all(FollowerTag.class, MovementComponent.class).get();
    public FollowerRemovalSystem(int priority){
        super(priority);
    }
    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> followers = getEngine().getEntitiesFor(FOLLOWERS);
        ImmutableArray<Entity> players = getEngine().getEntitiesFor(PLAYABLE_CHARACTERS);

        for(Entity follower: followers){
            boolean shouldRemove = true;
            MovementComponent followerMovement = Mappers.MOVEMENT.get(follower);
            Vector2 fpos = followerMovement.getTargetPosition();
            for(Entity player:players){
                Vector2 pos = Mappers.MOVEMENT.get(player).getTargetPosition();

                if(isAround(pos,fpos)){
                    shouldRemove=false;
                    break;
                }
            }

            if(shouldRemove){
                followerMovement.setDirection(Direction.none);
                follower.remove(FollowerTag.class);
            }
        }

    }

    //returns whether v2 is around v1
    private boolean isAround(Vector2 v1, Vector2 v2){
        Vector2[] aroundV1 = new Vector2[]{
                new Vector2(v1.x-1,v1.y),
                new Vector2(v1.x+1,v1.y),
                new Vector2(v1.x,v1.y+1),
                new Vector2(v1.x,v1.y-1),

                new Vector2(v1.x-1,v1.y+1),
                new Vector2(v1.x+1,v1.y+1),
                new Vector2(v1.x-1,v1.y-1),
                new Vector2(v1.x+1,v1.y-1),
        };
        float e = .1f;
//        boolean ret = false;
        for(Vector2 v: aroundV1){
            if(v2.epsilonEquals(v,e))
                return true;
        }
        return false;
//        return v2.epsilonEquals(v1Left,e)||v2.epsilonEquals(v1Right,e)||v2.epsilonEquals(v1Up,e)||v2.epsilonEquals(v1Down,e);
    }
}
