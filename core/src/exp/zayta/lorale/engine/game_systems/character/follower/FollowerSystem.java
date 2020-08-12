package exp.zayta.lorale.engine.game_systems.character.follower;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.movement.movement_components.MovementComponent;

public class FollowerSystem extends IteratingSystem {
    private static final Family family = Family.all(FollowerTag.class, MovementComponent.class).get();
    public FollowerSystem(int priority) {
        super(family,priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //set follower movement direction
        FollowerTag followerTag = Mappers.FOLLOWER.get(entity);
        MovementComponent movementComponent = Mappers.MOVEMENT.get(entity);
        movementComponent.move(followerTag.getDirection());

        //remove the follower tag
        entity.remove(FollowerTag.class);


    }
}
