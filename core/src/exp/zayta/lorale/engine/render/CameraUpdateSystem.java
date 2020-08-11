package exp.zayta.lorale.engine.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.entities.tags.PlayerTag;
import exp.zayta.lorale.engine.movement.movement_components.Position;

public class CameraUpdateSystem extends IteratingSystem {
    private static final Family family = Family.all(PlayerTag.class, Position.class).get();
    private Viewport viewport; private float mapWidth,mapHeight;
    //todo fix
    public CameraUpdateSystem(int priority, Viewport viewport) {
        super(family,priority);
        this.viewport = viewport;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Position position = Mappers.POSITION.get(entity);
        updateCamera(position.getX(),position.getY());
    }

    private void updateCamera(float x, float y) {
        //uncomment below for camera to always follow player
//        boolean cameraShouldMove = mapWidth>viewport.getWorldWidth()||mapHeight>viewport.getWorldHeight();
//      uncomment below for camera to only move when player is out of screeh
        Vector3 camPos= new Vector3(viewport.getCamera().position).sub(viewport.getWorldWidth()/2,viewport.getWorldHeight()/2,0);
//        boolean cameraShouldMove = x>=camPos.x+viewport.getWorldWidth()
//                ||x<=camPos.x
//                ||y>=camPos.y+viewport.getWorldHeight()
//                ||y<=camPos.y;
//        if(cameraShouldMove){
            Vector3 newCameraPosition = new Vector3(x,y,0);
            viewport.getCamera().position.set(newCameraPosition);
//        }
    }
}
