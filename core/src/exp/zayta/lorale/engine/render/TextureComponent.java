package exp.zayta.lorale.engine.render;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import exp.zayta.lorale.GameConfig;


public class TextureComponent implements Component{

    private TextureRegion region=null;
    private float renderOffset=0,renderWidth= GameConfig.ENTITY_SIZE,renderHeight= GameConfig.ENTITY_SIZE;
    private TextureRegion firstRegion = null;
    public void init(TextureRegion region, float renderOffset, float renderWidth, float renderHeight){
        this.region = region;
        this.renderOffset = renderOffset;
        this.renderWidth = renderWidth;
        this.renderHeight = renderHeight;
        this.firstRegion = region;
    }
    public void init(TextureRegion region){
        this.region = region;
        this.renderOffset = 0;
        this.renderWidth = GameConfig.ENTITY_SIZE;
        this.renderHeight = GameConfig.ENTITY_SIZE;
        this.firstRegion = region;
    }
    public TextureRegion getRegion() { return region; }
    public void setRegion(TextureRegion region) { this.region = region; }

    public float getRenderOffset(){
        return renderOffset;
    }

    public float getRenderWidth(){
        return renderWidth;
    }

    public float getRenderHeight(){
        return renderHeight;
    }

    public TextureRegion getFirstRegion() {
        return firstRegion;
    }
}
