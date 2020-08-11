package exp.zayta.lorale.engine.movement.position_tracker;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

import exp.zayta.lorale.engine.entities.EntityType;


public class PositionTrackerComponent implements Component, Pool.Poolable {
    private EntityType entityType;
    public void init(EntityType entityType){
        this.entityType = entityType;
    }
    public void setEntityType(EntityType entityType){
        this.entityType = entityType;
    }
    public EntityType getEntityType(){
        return entityType;
    }

    @Override
    public void reset() {
        entityType = null;
    }
}
