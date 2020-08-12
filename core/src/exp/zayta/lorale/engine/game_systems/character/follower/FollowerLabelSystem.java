package exp.zayta.lorale.engine.game_systems.character.follower;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;

import exp.zayta.lorale.GameConfig;
import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.movement.movement_components.MovementComponent;
import exp.zayta.lorale.engine.movement.movement_components.Position;


public class FollowerLabelSystem extends IteratingSystem {
    private static final Logger log = new Logger(FollowerLabelSystem.class.getName(), Logger.DEBUG);
    private final Viewport viewport;
    private final SpriteBatch batch;
    private final BitmapFont font;

    private final GlyphLayout layout = new GlyphLayout();

    private Array<Entity> renderQueue = new Array<Entity>();
    private static final Family FOLLOWERS = Family.all(FollowerTag.class, MovementComponent.class).get();



    public FollowerLabelSystem(int priority, Viewport viewport, SpriteBatch batch) {
        super(FOLLOWERS,priority);
        this.viewport = viewport;
        this.batch = batch;
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

        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(FOLLOWERS);
        renderQueue.addAll(entities.toArray());

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        super.update(deltaTime);

        batch.end();

        renderQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Position position = Mappers.POSITION.get(entity);


        layout.setText(font,/*"Position: ("+position.getX()+","+position.getY()+")\n"+
                "Position Raw Key: "+PositionTracker.generateKey(position.getX(),position.getY())+"\n"+
                "Bounds Raw Key: "+PositionTracker.generateKey(bounds.getX(),bounds.getY())+"\n"+*/
                /*"\\../"*/"o/\\o");
        font.draw(batch, layout, position.getX()+GameConfig.ENTITY_WIDTH/2+0.25f, position.getY() + GameConfig.ENTITY_HEIGHT + layout.height+0.1f);//0.1f is offset from bottom
    }


}