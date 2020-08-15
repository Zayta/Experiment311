package exp.zayta.lorale.engine.map.tiled_map.tile_renderer;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.map.tiled_map.LayerNames;
import exp.zayta.lorale.engine.movement.movement_components.BoundsComponent;
import exp.zayta.lorale.engine.render.TextureComponent;

import static exp.zayta.lorale.engine.render.TopDownComparator.topDownComparator;

public class TiledMapEntityRenderer extends OrthogonalTiledMapRenderer {
    private PooledEngine engine;

    private Array<Entity> renderQueue = new Array<Entity>();
    private static final Family FAMILY = Family.all(
            TextureComponent.class,
            BoundsComponent.class
    ).get();


    public TiledMapEntityRenderer(TiledMap map, float unitScale, PooledEngine engine) {
        super(map,unitScale);
        this.engine = engine;
    }

    public void render() {
        ImmutableArray<Entity> entities = engine.getEntitiesFor(FAMILY);
        renderQueue.addAll(entities.toArray());
        renderQueue.sort(topDownComparator);

        beginRender();
        int currentLayer = 0;
        for (MapLayer layer : map.getLayers()) {
            if (layer.isVisible()) {
                if (layer instanceof TiledMapTileLayer) {
                    renderTileLayer((TiledMapTileLayer)layer);
                    currentLayer++;
                    if(map.getLayers().get(currentLayer).getName().equals(LayerNames.initPositionsLayer)){
                        drawEntities();
                    }
                } else {
                    for (MapObject object : layer.getObjects()) {
                        renderObject(object);
                    }
                }
            }
        }
        endRender();
        renderQueue.clear();
    }
    private void drawEntities(){
        for(Entity entity:renderQueue) {
            BoundsComponent position = Mappers.BOUNDS.get(entity);
            TextureComponent texture = Mappers.TEXTURE.get(entity);
            batch.draw(texture.getRegion(), position.getX(), position.getY(), position.getWidth(), position.getHeight());


//            batch.draw(entityTemplate.getTextureRegion(), entityTemplate.getDrawX()+renderOffset, entityTemplate.getDrawY(),width,height);

        }

    }
}
