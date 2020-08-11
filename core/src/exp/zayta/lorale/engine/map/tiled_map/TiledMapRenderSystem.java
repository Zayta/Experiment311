package exp.zayta.lorale.engine.map.tiled_map;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

import exp.zayta.lorale.GameConfig;

public class TiledMapRenderSystem extends EntitySystem{

    public static float unitScale = 1/ GameConfig.TILE_SIZE;

    private OrthogonalTiledMapRenderer mapRenderer;
    private final Viewport viewport;

    public TiledMapRenderSystem(int priority,TiledMap map, Viewport viewport){
        super(priority);
        this.viewport=viewport;
        mapRenderer = new OrthogonalTiledMapRenderer(map, unitScale);//unit scale is 1/(pixels per tile_width (or height) in tile sheet)
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
