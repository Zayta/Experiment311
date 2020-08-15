package exp.zayta.lorale.engine.map.tiled_map;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

import exp.zayta.lorale.GameConfig;
import exp.zayta.lorale.engine.map.tiled_map.tile_renderer.TiledMapEntityRenderer;

public class TiledMapRenderSystem extends EntitySystem{

    public static float unitScale = 1/ GameConfig.TILE_SIZE;

    private TiledMapEntityRenderer mapRenderer;
    private final Viewport viewport;

    public TiledMapRenderSystem(int priority, TiledMap map, Viewport viewport, PooledEngine engine){
        super(priority);
        this.viewport=viewport;
        mapRenderer = new TiledMapEntityRenderer(map, unitScale, engine);//unit scale is 1/(pixels per tile_width (or height) in tile sheet)
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        Camera camera = viewport.getCamera();
        camera.update();
        mapRenderer.setView((OrthographicCamera) camera);
        mapRenderer.render();
    }


}
