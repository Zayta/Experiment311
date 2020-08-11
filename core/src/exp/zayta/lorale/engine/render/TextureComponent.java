package exp.zayta.lorale.engine.render;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import exp.zayta.lorale.GameConfig;


public class TextureComponent implements Component{

    private TextureRegion region=null;
    private TextureRegion firstRegion = null;
    public void init(TextureRegion region, float renderOffset, float renderWidth, float renderHeight){
        this.region = region;
        this.firstRegion = region;
    }
    public void init(TextureRegion region){
        this.region = region;
        this.firstRegion = region;
    }
    public TextureRegion getRegion() { return region; }
    public void setRegion(TextureRegion region) { this.region = region; }


    public TextureRegion getFirstRegion() {
        return firstRegion;
    }
}
