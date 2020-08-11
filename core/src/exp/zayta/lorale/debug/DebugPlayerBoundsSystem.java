package exp.zayta.lorale.debug;

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
import exp.zayta.lorale.engine.movement.movement_components.Position;
import exp.zayta.lorale.engine.movement.position_tracker.DebugPositionTrackerSystem;
import exp.zayta.lorale.engine.movement.position_tracker.PositionTracker;
import exp.zayta.lorale.engine.movement.position_tracker.PositionTrackerComponent;

public class DebugPlayerBoundsSystem extends EntitySystem {
    private static final Logger log = new Logger(DebugPositionTrackerSystem.class.getName(), Logger.DEBUG);
    private final Viewport viewport;
    private final BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private final GlyphLayout layout = new GlyphLayout();

    private Array<Entity> renderQueue = new Array<Entity>();
    private PositionTracker positionTracker;
    private static final Family FAMILY = Family.all(
//            PlayerTag.class,
//            PositionTrackerComponent.class,
            Position.class
    ).get();


    public DebugPlayerBoundsSystem(int priority, Viewport viewport) {
//        super(FAMILY);
        super(priority);
        this.viewport = viewport;
        this.shapeRenderer = new ShapeRenderer();
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
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for(Entity entity:entities)
            drawEntity(entity);
        shapeRenderer.end();

        renderQueue.clear();

    }
    private void drawEntity(Entity entity){
        Position position = Mappers.POSITION.get(entity);

        shapeRenderer.rect(position.getX(),position.getY(),position.getWidth(),position.getHeight());
    }
}
