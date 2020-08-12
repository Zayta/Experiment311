package exp.zayta.lorale.engine.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.movement.movement_components.BoundsComponent;


public class RenderSystem extends EntitySystem {
    public static final Family FAMILY = Family.all(
            TextureComponent.class,
            BoundsComponent.class
    ).get();


    private final Viewport viewport;
    private final SpriteBatch batch;

    private Array<Entity> renderQueue = new Array<Entity>();
    private TextureRegion background;

    public RenderSystem(int priority, Viewport viewport, SpriteBatch batch){
        super(priority);
        this.viewport=viewport;
        this.batch = batch;
        this.background = background;
    }

    @Override
    public void update(float deltaTime) {

        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(FAMILY);
        renderQueue.addAll(entities.toArray());
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        drawEntities();

        batch.end();


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
