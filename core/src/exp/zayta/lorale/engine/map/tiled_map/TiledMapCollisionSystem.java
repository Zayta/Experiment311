package exp.zayta.lorale.engine.map.tiled_map;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;

import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.entities.tags.CharacterTag;
import exp.zayta.lorale.engine.map.tiled_map.tile_collision.TiledMapCollisionDetector;
import exp.zayta.lorale.engine.movement.movement_components.MovementComponent;
import exp.zayta.lorale.engine.movement.movement_components.BoundsComponent;
import exp.zayta.lorale.engine.movement.movement_components.Position;

public class TiledMapCollisionSystem extends IteratingSystem {
    private static final Logger log = new Logger(TiledMapCollisionSystem.class.getName(), Logger.DEBUG);

    private static final Family family = Family.all(
            MovementComponent.class,
            Position.class,
            BoundsComponent.class,
            CharacterTag.class).get();
    private TiledMapCollisionDetector collisionDetector;
    public TiledMapCollisionSystem(int priority, TiledMapTileLayer tiledMapCollisionLayer) {
        super(family,priority);
        collisionDetector = new TiledMapCollisionDetector(tiledMapCollisionLayer);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MovementComponent movementComponent = Mappers.MOVEMENT.get(entity);
        Vector2 targetPosition = movementComponent.getTargetPosition();
        BoundsComponent bounds = Mappers.BOUNDS.get(entity);

            TiledMapTile collidedTile = collisionDetector.getCollidedTile(targetPosition.x,targetPosition.y,
                    bounds.getWidth(),bounds.getHeight(),movementComponent.getDirection());
            if(collidedTile!=null){
                //handle collision event
                MapProperties tileProperties = collidedTile.getProperties();
                if(tileProperties.containsKey("hi")){ //if tile has certain property, do certain thing

                }
//                log.debug("Collision with collision layer happened: "+tileProperties.getKeys());
                //block entity from moving
                targetPosition.set(Mappers.POSITION.get(entity).getPosition()); //stop entity from moving

            }


    }

}