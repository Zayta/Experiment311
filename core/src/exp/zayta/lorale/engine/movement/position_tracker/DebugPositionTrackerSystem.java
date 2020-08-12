package exp.zayta.lorale.engine.movement.position_tracker;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;

import exp.zayta.lorale.GameConfig;
import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.movement.movement_components.BoundsComponent;

public class DebugPositionTrackerSystem extends EntitySystem {
    private static final Logger log = new Logger(DebugPositionTrackerSystem.class.getName(), Logger.DEBUG);
    private final Viewport viewport;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private final GlyphLayout layout = new GlyphLayout();

    private Array<Entity> renderQueue = new Array<Entity>();
    private PositionTracker positionTracker;
    private static final Family FAMILY = Family.all(
//            PlayerTag.class,
            PositionTrackerComponent.class,
            BoundsComponent.class
    ).get();


    public DebugPositionTrackerSystem(int priority, PositionTracker positionTracker, Viewport viewport, SpriteBatch batch) {
//        super(FAMILY);
        super(priority);
        this.viewport = viewport;
        this.batch = batch;
        this.positionTracker = positionTracker;

        /**Customize Font**/
        this.font = new BitmapFont();
        float scaleX = GameConfig.VIRTUAL_WIDTH / GameConfig.WIDTH;
        float scaleY = GameConfig.VIRTUAL_HEIGHT / GameConfig.HEIGHT;
        float fontScale = 2;
        font.setUseIntegerPositions(false);
        font.setColor(Color.CYAN);
        font.getData().setScale(fontScale * scaleX, fontScale * scaleY);


    }

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(FAMILY);
        renderQueue.addAll(entities.toArray());

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
            for(Entity entity:entities)
                drawEntity(entity);
        batch.end();

        renderQueue.clear();

    }

    protected void drawEntity(Entity entity) {
        BoundsComponent boundsComponent = Mappers.BOUNDS.get(entity);

        layout.setText(font,/*"BoundsComponent: ("+boundsComponent.getX()+","+boundsComponent.getY()+")\n"+
                "BoundsComponent Raw Key: "+PositionTracker.generateKey(boundsComponent.getX(),boundsComponent.getY())+"\n"+
                "Bounds Raw Key: "+PositionTracker.generateKey(bounds.getX(),bounds.getY())+"\n"+*/
                "p:" + positionTracker.getKeyForEntity(entity));
        font.draw(batch, layout, boundsComponent.getX(), boundsComponent.getY() + boundsComponent.getHeight() + layout.height+0.1f);//0.1f is offset from bottom

    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);

    }
}