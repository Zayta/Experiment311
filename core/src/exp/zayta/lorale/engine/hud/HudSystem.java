package exp.zayta.lorale.engine.hud;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;


public class HudSystem extends EntitySystem {
    private static final Logger log = new Logger(HudSystem.class.getName(), Logger.DEBUG);

    private final GlyphLayout layout = new GlyphLayout();

    private Hud hud;
    public HudSystem(int priority,   Hud hud)
    {
        super(priority);
        this.hud = hud;
    }

    @Override
    public void update(float deltaTime) {


        hud.getViewport().apply();
//        batch.setProjectionMatrix(hud.getCamera().combined);
        hud.draw();
        hud.act(); //draw the Hud
    }


}
