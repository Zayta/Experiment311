package exp.zayta.lorale.engine.game_systems.hand_hold;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

import exp.zayta.lorale.engine.entities.tags.PlayerTag;
import exp.zayta.lorale.engine.movement.Direction;


public class FollowerTag extends PlayerTag implements Component, Pool.Poolable {
    private Direction direction = Direction.none;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void reset() {
        direction = Direction.none;
    }


//    private ArrayList<Entity> followers;
//
//    public FollowerTag(){
//        this.followers = new ArrayList<Entity>();
//    }
//    public void addFollower(Entity follower){
//        this.followers.add(follower);
//    }
//
//
//
//    public ArrayList<Entity> getFollowers() {
//        return followers;
//    }
//
//
//    @Override
//    public void reset() {
//        followers.clear();
//    }
//
}
