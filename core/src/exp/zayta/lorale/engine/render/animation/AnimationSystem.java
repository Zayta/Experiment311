package exp.zayta.lorale.engine.render.animation;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.movement.Direction;
import exp.zayta.lorale.engine.movement.movement_components.MovementComponent;
import exp.zayta.lorale.engine.render.TextureComponent;


public class AnimationSystem extends IteratingSystem {
    private float animationStateTime = 0;
    private static Family FAMILY= Family.all(
            AnimationComponent.class,
            MovementComponent.class,
            TextureComponent.class
    ).get();
    public AnimationSystem(int priority) {
        super(FAMILY,priority);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
            animationStateTime+=deltaTime;
            AnimationComponent animationComponent = Mappers.ANIMATION.get(entity);
            TextureComponent textureComponent = Mappers.TEXTURE.get(entity);
            Direction direction = Mappers.MOVEMENT.get(entity).getDirection();
            TextureRegion textureRegion = animationComponent.getFrame(animationStateTime,direction);
            textureComponent.setRegion(textureRegion);

    }
}
