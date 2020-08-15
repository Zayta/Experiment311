package exp.zayta.lorale.engine.render.animation;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

import exp.zayta.lorale.GameConfig;
import exp.zayta.lorale.engine.movement.Direction;

public class AnimationComponent implements Component {
    //animation
    private TextureRegion textureRegion;
    // Constant rows and columns of the sprite sheet
    private final int FRAME_COLS = 3, FRAME_ROWS = 4;
//    private Direction animationDirection;
    private final float frameDuration = GameConfig.MOVING_SPEED*1.5f;

    //    private Animation<TextureRegion> currentAnimation; private float currentTime=0;
    private ArrayList<Animation<TextureRegion>> animations;
    private Animation<TextureRegion> leftAnimation,rightAnimation,upAnimation,downAnimation,currentAnimation;

    public void init(Array<TextureAtlas.AtlasRegion> textureRegions){
        makeAnimation(textureRegions);
//        animationDirection = Direction.none;
    }



    public void makeAnimation(Array<TextureAtlas.AtlasRegion> textureRegions){
        animations = new ArrayList<Animation<TextureRegion>>(FRAME_ROWS);
        for(int i = 0; i<FRAME_ROWS; i++)
        {
            int index = i*FRAME_COLS;
            Array<TextureRegion> frames = new Array<TextureRegion>(new TextureRegion[]{
                    textureRegions.get(index),textureRegions.get(index+1),textureRegions.get(index+2)});
            animations.add(i,new Animation<TextureRegion>(frameDuration,frames, Animation.PlayMode.LOOP));
        }
        //this is based on my spritesheet.
        upAnimation = animations.get(3);
        downAnimation = animations.get(0);
        leftAnimation = animations.get(1);
        rightAnimation = animations.get(2);
        currentAnimation = upAnimation;
//        animationDirection = Direction.up;
        textureRegion=animations.get(0).getKeyFrame(0);
    }

    public TextureRegion getFrame(float time, Direction direction){
//        animationDirection = direction;
        if(direction== Direction.none){
            return currentAnimation.getKeyFrame(0);
        }
        setCurrentAnimation(direction);
        return currentAnimation.getKeyFrame(time);
    }
    public void setCurrentAnimation(Direction direction){
        switch (direction) {
            case up:
                currentAnimation= upAnimation;
                break;
            case down:
                currentAnimation= downAnimation;
                break;
            case left:
                currentAnimation= leftAnimation;
                break;
            case right:
                currentAnimation=rightAnimation;
                break;
            case none:
//                ////System.out.println("Animation Component is returning currentAnimation"+currentAnimation.toString());
            default:

        }

    }
    public TextureRegion getTextureRegion(){
        return textureRegion;
    }

}
