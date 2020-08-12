package exp.zayta.lorale.engine.movement;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.entities.tags.PlayerTag;
import exp.zayta.lorale.engine.game_systems.character.follower.FollowerTag;
import exp.zayta.lorale.engine.movement.movement_components.MovementComponent;

import static exp.zayta.lorale.engine.movement.PositionsComparator.positionsComparator;


public class PositionsComparatorSystem extends IteratingSystem {

    private final static Family PLAYABLE_CHARACTERS = Family.all(
            MovementComponent.class

    ).one(PlayerTag.class, FollowerTag.class).get();
    public PositionsComparatorSystem(int priority) {
        super(PLAYABLE_CHARACTERS, priority);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(Mappers.MOVEMENT.get(entity).getDirection()!=Direction.none){

            positionsComparator.setDirection(Mappers.MOVEMENT.get(entity).getDirection());
        }

    }
}
